package com.professor.traficinspiration.service;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.professor.traficinspiration.ApplicationContext;
import com.professor.traficinspiration.MyAlertDialogFragment;
import com.professor.traficinspiration.R;
import com.professor.traficinspiration.TestErrorMessage;
import com.professor.traficinspiration.activity.MainActivity;
import com.professor.traficinspiration.activity.RegistrationActivity;
import com.professor.traficinspiration.activity.SignInActivity;
import com.professor.traficinspiration.model.CompleteOrderRequest;
import com.professor.traficinspiration.model.CompleteOrderResponse;
import com.professor.traficinspiration.model.GetOrdersResponse;
import com.professor.traficinspiration.model.Order;
import com.professor.traficinspiration.model.User;
import com.professor.traficinspiration.model.UserRequestMessage;
import com.professor.traficinspiration.model.UserResponseMessage;
import com.professor.traficinspiration.model.tasks.CheckInstallTask;
import com.professor.traficinspiration.model.tasks.FindTask;
import com.professor.traficinspiration.model.tasks.OpenTask;
import com.professor.traficinspiration.model.tasks.ReopenTask;
import com.professor.traficinspiration.model.tasks.Task;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MessageService {

    static Retrofit retrofit;

    public MessageService() {
        Retrofit.Builder builder = new Retrofit
                .Builder()
                .baseUrl("http://tapmoney.testmy.tk/rest/")
                .addConverterFactory(GsonConverterFactory.create());

        retrofit = builder.build();
    }


    public void getOrCreateUser(final String email, final String password, final String action, final Long idReferrer) {

        final UserRequestMessage userRequestMessage = new UserRequestMessage(email, password, action, idReferrer);
        UserService userService = retrofit.create(UserService.class);
        Call<UserResponseMessage> userCall = userService.getOrCreateUser(userRequestMessage);


        userCall.enqueue(new Callback<UserResponseMessage>() {
            @Override
            public void onResponse(Call<UserResponseMessage> call, Response<UserResponseMessage> response) {

                if (response.isSuccessful()) {

                    // check response body
                    if (response.body().getErrors() != null) {
                        Toast.makeText(ApplicationContext.getContext(), Arrays.deepToString(response.body().getErrors().values().toArray()), Toast.LENGTH_LONG).show();

                        Intent toSignInActivity = new Intent(ApplicationContext.getContext(), SignInActivity.class);
                        ApplicationContext.getContext().startActivity(toSignInActivity);

                        return;
                    }


                    UserResponseMessage userResponseMessage = response.body();

                    User user = ApplicationContext.getUser();
                    user.setId(userResponseMessage.getId());
                    user.setPassword(password);
                    user.setBalance(userResponseMessage.getBalance());
                    user.setOrdersCompleted(userResponseMessage.getOrdersCompleted());
                    user.setReferralsCount(userResponseMessage.getReferralsCount());

                    // отобразить информацию о пользователе
                    ImageView userPhoto = (ImageView) ApplicationContext.getContext().findViewById(R.id.avatar);

                    Uri uri = user.getPhotoUrl();
                    Picasso.with(ApplicationContext.getContext())
                            .load(uri)
//                    .placeholder(android.R.drawable.sym_def_app_icon)
//                    .error(android.R.drawable.sym_def_app_icon)
                            .into(userPhoto);

                    user.setPhoto(userPhoto.getDrawable());


                    String balanceString = "Баланс: " + user.getBalance();
                    ((TextView) ApplicationContext.getContext().findViewById(R.id.txtName)).setText(user.getName());
                    ((TextView) ApplicationContext.getContext().findViewById(R.id.txtMoney)).setText(balanceString);
                    ApplicationContext.getContext().findViewById(R.id.accountInfoButton).setVisibility(View.VISIBLE);

                    ApplicationContext.setSessionToken(userResponseMessage.getToken());

                    SharedPreferences sharedPreferences = ApplicationContext.getContext().getSharedPreferences(user.getEmail(), Context.MODE_PRIVATE);
                    sharedPreferences.edit().putString("password", password).apply();

                    getOrders();

                } else {
                    MyAlertDialogFragment.createAndShowErrorDialog("error during creating or retrieving user");
                }
            }

            @Override
            public void onFailure(Call<UserResponseMessage> call, Throwable t) {

                MyAlertDialogFragment.createAndShowErrorDialog("something went wrong during creating or retrieving user");
            }


        });


    }

    public void getOrders() {

        OrderService orderService = retrofit.create(OrderService.class);

        Call<GetOrdersResponse> call = orderService.getOrders(ApplicationContext.getUser().getId(), ApplicationContext.getSessionToken());

        call.enqueue(new Callback<GetOrdersResponse>() {
            @Override
            public void onResponse(Call<GetOrdersResponse> call, Response<GetOrdersResponse> response) {
                if (response.isSuccessful()) {

                    // check response body
                    if (response.body().getErrors() != null) {
                        Toast.makeText(ApplicationContext.getContext(), Arrays.deepToString(response.body().getErrors().values().toArray()), Toast.LENGTH_LONG).show();
                        return;
                    }

                    GetOrdersResponse getOrdersResponse = response.body();


                    List<Order> newOrderList = new ArrayList<>();

                    for (Order order : getOrdersResponse.getOrderList()) {
                        if (ApplicationContext.getActiveOrderList().contains(order)) {
                            continue;
                        }

                        List<Task> taskList = new ArrayList<>(Arrays.asList(
                                new FindTask(order),
                                new CheckInstallTask(order),
                                new OpenTask(order)
                        ));

                        for (int i = 1; i < order.getOpenCount(); i++) {
                            taskList.add(new ReopenTask(order.getOpenInterval()));
                        }
                        order.setTaskList(taskList);

                        newOrderList.add(order);
                    }

                    ApplicationContext.setNewOrderList(newOrderList);

                    Toast.makeText(ApplicationContext.getContext(), "newOrderList is set", Toast.LENGTH_LONG).show();


                } else {
                    MyAlertDialogFragment.createAndShowErrorDialog("Error during retrieving orders. Try again later");
//                    Toast.makeText(ApplicationContext.getContext(), "Error during retrieving orders. Try again later", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<GetOrdersResponse> call, Throwable t) {
                MyAlertDialogFragment.createAndShowErrorDialog("something went wrong in getOrders: " + Arrays.toString(t.getCause().getStackTrace()));
            }
        });

    }

    public void completeOrder(final Order order) {
        final User user = ApplicationContext.getUser();

        CompleteOrderRequest completeOrderRequest = new CompleteOrderRequest(user.getId(), ApplicationContext.getSessionToken(), order.getId());

        OrderService orderService = retrofit.create(OrderService.class);
        Call<CompleteOrderResponse> call = orderService.completeOrder(completeOrderRequest);

        call.enqueue(new Callback<CompleteOrderResponse>() {
            @Override
            public void onResponse(Call<CompleteOrderResponse> call, Response<CompleteOrderResponse> response) {

                if (!response.isSuccessful()) {
                    MyAlertDialogFragment.createAndShowErrorDialog(response.toString());
                    return;
                }

                CompleteOrderResponse success = response.body();


                // check response body
                if (success.getErrors() != null) {
                    Toast.makeText(ApplicationContext.getContext(), Arrays.deepToString(response.body().getErrors().values().toArray()), Toast.LENGTH_LONG).show();
                    return;
                }


                double payment = order.getPayment();
                user.setBalance(user.getBalance() + payment);
                order.setPayed(true);
                Toast.makeText(ApplicationContext.getContext(), "Order completed", Toast.LENGTH_SHORT).show();

                // переместить выполненную задачу в архив
                ApplicationContext.getIdToActiveOrderMap().remove(order.getId());
                ApplicationContext.getHistoryOrderList().add(order);


//                Toast.makeText(ApplicationContext.getContext(), "Can't confirm order completion on server", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<CompleteOrderResponse> call, Throwable t) {
//                Toast.makeText(ApplicationContext.getContext(), "Something went wrong in completeOrder", Toast.LENGTH_SHORT).show();
                MyAlertDialogFragment.createAndShowErrorDialog("Something went wrong in completeOrder");
            }
        });
    }

    public void transferMoney(final int amount, int destinationType, long destinationNumber) {
        final User user = ApplicationContext.getUser();

        MoneyService moneyService = retrofit.create(MoneyService.class);
        Call<Boolean> call = moneyService.transferMoney(amount, user.getId(), destinationType, destinationNumber);

        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Boolean success = response.body();
                if (success) {
                    user.setBalance(user.getBalance() - amount);

                    Toast.makeText(ApplicationContext.getContext(), "Transfer successful", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                MyAlertDialogFragment.createAndShowErrorDialog("something went wrong in transferMoney");
            }
        });

    }


}
