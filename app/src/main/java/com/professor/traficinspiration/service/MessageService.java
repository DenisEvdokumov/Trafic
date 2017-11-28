package com.professor.traficinspiration.service;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.professor.traficinspiration.ApplicationContext;
import com.professor.traficinspiration.MyAlertDialogFragment;
import com.professor.traficinspiration.R;
import com.professor.traficinspiration.activity.SignInActivity;
import com.professor.traficinspiration.model.messages.CompleteOrderRequestMessage;
import com.professor.traficinspiration.model.messages.CompleteOrderResponseMessage;
import com.professor.traficinspiration.model.messages.OrdersResponseMessage;
import com.professor.traficinspiration.model.Order;
import com.professor.traficinspiration.model.messages.ResponseMessage;
import com.professor.traficinspiration.model.messages.SupportRequestMessage;
import com.professor.traficinspiration.model.messages.SupportResponseMessage;
import com.professor.traficinspiration.model.User;
import com.professor.traficinspiration.model.messages.UserRequestMessage;
import com.professor.traficinspiration.model.messages.UserResponseMessage;
import com.professor.traficinspiration.model.messages.WithdrawRequestMessage;
import com.professor.traficinspiration.model.messages.WithdrawResponseMessage;
import com.professor.traficinspiration.model.tasks.CheckInstallTask;
import com.professor.traficinspiration.model.tasks.FindTask;
import com.professor.traficinspiration.model.tasks.OpenTask;
import com.professor.traficinspiration.model.tasks.ReopenTask;
import com.professor.traficinspiration.model.tasks.Task;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

                if (!isResponseSuccessful(response)) {
                    Intent toSignInActivity = new Intent(ApplicationContext.getContext(), SignInActivity.class);
                    ApplicationContext.getContext().startActivity(toSignInActivity);
                    return;
                }

                Toast.makeText(ApplicationContext.getContext(), "Success", Toast.LENGTH_LONG).show();

                UserResponseMessage userResponseMessage = response.body();

                User user = ApplicationContext.getUser();
                user.setId(userResponseMessage.getId());
                user.setPassword(password);
                user.setBalance(userResponseMessage.getBalance());
                user.setOrdersCompleted(userResponseMessage.getOrdersCompleted());
                user.setReferralsCount(userResponseMessage.getReferralsCount());

                // отобразить информацию о пользователе
                ImageView userPhotoView = (ImageView) ApplicationContext.getContext().findViewById(R.id.avatar);

                Uri uri = user.getPhotoUrl();
                Picasso.with(ApplicationContext.getContext())
                        .load(uri)
                        .placeholder(R.drawable.default_account_icon)
                        .error(R.drawable.default_account_icon)
                        .into(userPhotoView);


//                    user.setPhoto(userPhotoView.getDrawable());


                String balanceString = "Баланс: " + user.getBalance();
                ((TextView) ApplicationContext.getContext().findViewById(R.id.txtName)).setText(user.getName());
                ((TextView) ApplicationContext.getContext().findViewById(R.id.txtMoney)).setText(balanceString);
                ApplicationContext.getContext().findViewById(R.id.accountInfoButton).setVisibility(View.VISIBLE);

                ApplicationContext.setSessionToken(userResponseMessage.getToken());

                SharedPreferences sharedPreferences = ApplicationContext.getContext().getSharedPreferences(user.getEmail(), Context.MODE_PRIVATE);
                sharedPreferences.edit().putString("password", password).apply();

                getOrders();

                // запрашивание истории если локальная история пуста
                // (избыточный запрос если история в принципе пуста)
                // (более рационально было бы запрашивать историю непосредственно при переходе на экран истории...)
                List<Order> historyOrderList = ApplicationContext.getHistoryOrderList();
                if (historyOrderList.size() == 0) {
                    ApplicationContext.getMessageService().getOrderHistory();
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

        Call<OrdersResponseMessage> call = orderService.getOrders(ApplicationContext.getUser().getId(), ApplicationContext.getSessionToken());

        call.enqueue(new Callback<OrdersResponseMessage>() {
            @Override
            public void onResponse(Call<OrdersResponseMessage> call, Response<OrdersResponseMessage> response) {

                if (!isResponseSuccessful(response)) {
                    return;
                }

                OrdersResponseMessage ordersResponseMessage = response.body();


                List<Order> newOrderList = new ArrayList<>();
                List<Order> activeOrderList = ApplicationContext.getActiveOrderList();


                for (Order order : ordersResponseMessage.getOrderList()) {
//                        if (activeOrderList.contains(order)) {
//                            continue;
//                        }

                    // если заказ не поступил с сервера, то он был удален или его выполнение уже невозможно => нужно убрать его из активных заказов...

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

                activeOrderList.retainAll(newOrderList);
                newOrderList.removeAll(activeOrderList);

                ApplicationContext.setNewOrderList(newOrderList);
                ApplicationContext.setActiveOrderList(activeOrderList);

//                    Toast.makeText(ApplicationContext.getContext(), "newOrderList is set", Toast.LENGTH_LONG).show();


            }

            @Override
            public void onFailure(Call<OrdersResponseMessage> call, Throwable t) {
                MyAlertDialogFragment.createAndShowErrorDialog("something went wrong in getOrders: " + Arrays.toString(t.getCause().getStackTrace()));
            }
        });
    }

    public void getOrderHistory() {
        OrderService orderService = retrofit.create(OrderService.class);

        Call<OrdersResponseMessage> call = orderService.getOrdersHistory(ApplicationContext.getUser().getId(), ApplicationContext.getSessionToken());

        call.enqueue(new Callback<OrdersResponseMessage>() {
            @Override
            public void onResponse(Call<OrdersResponseMessage> call, Response<OrdersResponseMessage> response) {

                if (!isResponseSuccessful(response)) {
                    return;
                }

                OrdersResponseMessage ordersResponseMessage = response.body();


                List<Order> historyOrderList = new ArrayList<>();

                List<Order> currentHistoryOrderList = ApplicationContext.getHistoryOrderList();

                for (Order order : ordersResponseMessage.getOrderList()) {

//                        Toast.makeText(ApplicationContext.getContext(), "order - " + order.getId(), Toast.LENGTH_LONG).show();

                    if (currentHistoryOrderList.contains(order)) {
//                            Toast.makeText(ApplicationContext.getContext(), "already in history", Toast.LENGTH_LONG).show();

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

                    order.setFinished(true);
                    order.setPayed(true);

                    historyOrderList.add(order);
                }

                ApplicationContext.setHistoryOrderList(historyOrderList);

                ApplicationContext.getDatabaseManager().writeListToDB(historyOrderList);

            }

            @Override
            public void onFailure(Call<OrdersResponseMessage> call, Throwable t) {
                MyAlertDialogFragment.createAndShowErrorDialog("something went wrong in getOrdersHistory: " + Arrays.toString(t.getCause().getStackTrace()));
            }
        });
    }

    public void completeOrder(final Order order) {
        final User user = ApplicationContext.getUser();

        CompleteOrderRequestMessage completeOrderRequestMessage = new CompleteOrderRequestMessage(user.getId(), ApplicationContext.getSessionToken(), order.getId());

        OrderService orderService = retrofit.create(OrderService.class);
        Call<CompleteOrderResponseMessage> call = orderService.completeOrder(completeOrderRequestMessage);

        call.enqueue(new Callback<CompleteOrderResponseMessage>() {
            @Override
            public void onResponse(Call<CompleteOrderResponseMessage> call, Response<CompleteOrderResponseMessage> response) {

                if (!isResponseSuccessful(response)) {
                    return;
                }


                double payment = order.getPayment();
                user.setBalance(user.getBalance() + payment);
                order.setPayed(true);
//                Toast.makeText(ApplicationContext.getContext(), "Order completed", Toast.LENGTH_SHORT).show();

                // переместить выполненную задачу в архив
                ApplicationContext.getIdToActiveOrderMap().remove(order.getId());
                ApplicationContext.getIdToHistoryOrderMap().put(order.getId(), order);


                ApplicationContext.getDatabaseManager().writeOrderToDB(order);

//                Toast.makeText(ApplicationContext.getContext(), "Can't confirm order completion on server", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<CompleteOrderResponseMessage> call, Throwable t) {
//                Toast.makeText(ApplicationContext.getContext(), "Something went wrong in completeOrder", Toast.LENGTH_SHORT).show();
                MyAlertDialogFragment.createAndShowErrorDialog("Something went wrong in completeOrder");
            }
        });
    }

    public void withdraw(int amount, boolean notice) {
        final User user = ApplicationContext.getUser();

        WithdrawRequestMessage withdrawRequestMessage = new WithdrawRequestMessage(user.getId(), ApplicationContext.getSessionToken(), amount, notice);

        MoneyService moneyService = retrofit.create(MoneyService.class);
        Call<WithdrawResponseMessage> call = moneyService.withdraw(withdrawRequestMessage);

        call.enqueue(new Callback<WithdrawResponseMessage>() {
            @Override
            public void onResponse(Call<WithdrawResponseMessage> call, Response<WithdrawResponseMessage> response) {

                if (!isResponseSuccessful(response)) {
                    return;
                }

                Toast.makeText(ApplicationContext.getContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<WithdrawResponseMessage> call, Throwable t) {
                MyAlertDialogFragment.createAndShowErrorDialog("something went wrong in withdraw");
            }
        });
    }


    public void sendSupportRequest(String message) {

        SupportRequestMessage supportRequestMessage = new SupportRequestMessage(ApplicationContext.getUser().getId(), ApplicationContext.getSessionToken(), message);

        SupportService supportService = retrofit.create(SupportService.class);
        Call<SupportResponseMessage> call = supportService.sendSupportRequest(supportRequestMessage);

        call.enqueue(new Callback<SupportResponseMessage>() {
            @Override
            public void onResponse(Call<SupportResponseMessage> call, Response<SupportResponseMessage> response) {

                if (!isResponseSuccessful(response)) {
                    return;
                }


            }

            @Override
            public void onFailure(Call<SupportResponseMessage> call, Throwable t) {

            }
        });


    }

    private boolean isResponseSuccessful(Response<? extends ResponseMessage> response) {

        if (!response.isSuccessful()) {
            MyAlertDialogFragment.createAndShowErrorDialog(response.toString());
            return false;
        }

        if (response.body().getErrors() != null) {
            Toast.makeText(ApplicationContext.getContext(), Arrays.deepToString(response.body().getErrors().values().toArray()), Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }


}
