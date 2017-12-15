package com.professor.traficinspiration.service;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.professor.traficinspiration.ApplicationContext;
import com.professor.traficinspiration.MyAlertDialogFragment;
import com.professor.traficinspiration.R;
import com.professor.traficinspiration.activity.SignInActivity;
import com.professor.traficinspiration.model.Order;
import com.professor.traficinspiration.model.User;
import com.professor.traficinspiration.model.messages.CompleteOrderRequestMessage;
import com.professor.traficinspiration.model.messages.CompleteOrderResponseMessage;
import com.professor.traficinspiration.model.messages.OrdersResponseMessage;
import com.professor.traficinspiration.model.messages.ResponseMessage;
import com.professor.traficinspiration.model.messages.SupportRequestMessage;
import com.professor.traficinspiration.model.messages.SupportResponseMessage;
import com.professor.traficinspiration.model.messages.UserRequestMessage;
import com.professor.traficinspiration.model.messages.UserResponseMessage;
import com.professor.traficinspiration.model.messages.WithdrawRequestMessage;
import com.professor.traficinspiration.model.messages.WithdrawResponseMessage;
import com.professor.traficinspiration.model.tasks.CheckInstallTask;
import com.professor.traficinspiration.model.tasks.CommentTask;
import com.professor.traficinspiration.model.tasks.FindTask;
import com.professor.traficinspiration.model.tasks.OpenTask;
import com.professor.traficinspiration.model.tasks.ReopenTask;
import com.professor.traficinspiration.model.tasks.Task;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

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


    public void executeEnterSequence(final String email, final String password, final String action, final Long idReferrer) {

        if (handleUser(getOrCreateUser(email, password, action, idReferrer))) {
            handleOrders(getOrders(false));
//            handleOrderHistory(getOrders(true));
        }


    }

    public User getOrCreateUser(final String email, final String password, final String action, final Long idReferrer) {

        final UserRequestMessage userRequestMessage = new UserRequestMessage(email, password, action, idReferrer);
        UserService userService = retrofit.create(UserService.class);
        final Call<UserResponseMessage> call = userService.getOrCreateUser(userRequestMessage);

        Response<UserResponseMessage> response = null;

        RequestExecutor requestExecutor = new RequestExecutor();

        try {
            response = requestExecutor.execute(call).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        if (!isResponseSuccessful(response)) {
            return null;
        }

//        Toast.makeText(ApplicationContext.getContext(), "Success", Toast.LENGTH_LONG).show();

        UserResponseMessage userResponseMessage = response.body();
        ApplicationContext.setSessionToken(userResponseMessage.getToken());


        User user = ApplicationContext.getUser();
        user.setId(userResponseMessage.getId());
        user.setPassword(password);
        user.setBalance(userResponseMessage.getBalance());
        user.setOrdersCompleted(userResponseMessage.getOrdersCompleted());
        user.setReferralsCount(userResponseMessage.getReferralsCount());


        return user;
    }

    public List<Order> getOrders(boolean history) {
//        OrderService orderService = retrofit.create(OrderService.class);
//
//        Call<OrdersResponseMessage> call;
//
////        MyAlertDialogFragment.createAndShowErrorDialog(ApplicationContext.class.getClassLoader().toString());
////        Toast.makeText(ApplicationContext.getContext(), ApplicationContext.class.getClassLoader().toString(), Toast.LENGTH_LONG).show();
//
//        if (history) {
//            call = orderService.getOrdersHistory(ApplicationContext.getUser().getId(), ApplicationContext.getSessionToken());
//        } else {
//            call = orderService.getOrders(ApplicationContext.getUser().getId(), ApplicationContext.getSessionToken());
//        }
//
//        Response<OrdersResponseMessage> response = null;
//
//        RequestExecutor requestExecutor = new RequestExecutor();
//
//        try {
//            response = requestExecutor.execute(call).get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//
//        if (!isResponseSuccessful(response)) {
//            return null;
//        }
//
//        OrdersResponseMessage ordersResponseMessage = response.body();
//
//        return ordersResponseMessage.getOrderList();

        return getOrders(ApplicationContext.getUser().getId(), ApplicationContext.getSessionToken(), history);

    }

    public List<Order> getOrders(long userId, String sessionToken, boolean history) {
        OrderService orderService = retrofit.create(OrderService.class);

        Call<OrdersResponseMessage> call;

        if (history) {
            call = orderService.getOrdersHistory(userId, sessionToken);
        } else {
            call = orderService.getOrders(userId, sessionToken);
        }

        Response<OrdersResponseMessage> response = null;

        RequestExecutor requestExecutor = new RequestExecutor();

        try {
            response = requestExecutor.execute(call).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (!isResponseSuccessful(response)) {
            return null;
        }

        OrdersResponseMessage ordersResponseMessage = response.body();

        return ordersResponseMessage.getOrderList();
    }

    public boolean handleUser(User user) {

        if (user == null) {
            Intent toSignInActivity = new Intent(ApplicationContext.getContext(), SignInActivity.class);
            ApplicationContext.getContext().startActivity(toSignInActivity);
//            Toast.makeText(ApplicationContext.getContext(), "Can't retrieve orders", Toast.LENGTH_SHORT).show();
            return false;
        }

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


        SharedPreferences sharedPreferences = ApplicationContext.getContext().getSharedPreferences(user.getEmail(), Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("password", user.getPassword()).apply();

        ApplicationContext.notificator.init();

        return true;
    }

    public void handleOrders(List<Order> orderList) {

        if (orderList == null) {
            Toast.makeText(ApplicationContext.getContext(), "Не удалось получить список заказов", Toast.LENGTH_SHORT).show();
            return;
        }

        List<Order> newOrderList = new ArrayList<>();
        List<Order> activeOrderList = ApplicationContext.getActiveOrderList();


        for (Order order : orderList) {
//                        if (activeOrderList.contains(order)) {
//                            continue;
//                        }

            // если заказ не поступил с сервера, то он был удален или его выполнение уже невозможно => нужно убрать его из активных заказов...

            List<Task> taskList = new ArrayList<>(Arrays.asList(
                    new FindTask(order),
                    new CheckInstallTask(order),
                    new OpenTask(order)
            ));

            if (order.getNeededReviews() > order.getDoneReviews()) {
                order.setComment(true);
                taskList.add(new CommentTask(order));
            }

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
    }

    public void handleOrderHistory(List<Order> orderList) {

        if (orderList == null) {
            Toast.makeText(ApplicationContext.getContext(), "Не удалось получить список заказов", Toast.LENGTH_SHORT).show();
            return;
        }

        List<Order> currentHistoryOrderList = ApplicationContext.getHistoryOrderList();

        for (Order order : orderList) {
            if (currentHistoryOrderList.contains(order)) {
                continue;
            }

            List<Task> taskList = new ArrayList<>(Arrays.asList(
                    new FindTask(order),
                    new CheckInstallTask(order),
                    new OpenTask(order)
            ));

            if (order.getNeededReviews() > order.getDoneReviews()) {
                order.setComment(true);
                taskList.add(new CommentTask(order));
            }

            for (int i = 1; i < order.getOpenCount(); i++) {
                taskList.add(new ReopenTask(order.getOpenInterval()));
            }

            for (Task task : taskList) {
                task.setCompleted(true);
            }

            order.setTaskList(taskList);

            order.setFinished(true);

            if (order.getReview() == 1) {
                order.setPayed(true);
            }

            currentHistoryOrderList.add(order);
        }

        ApplicationContext.setHistoryOrderList(currentHistoryOrderList);

        ApplicationContext.getDatabaseManager().writeListToDB(currentHistoryOrderList);
    }

    public void completeOrder(final Order order) {
        // метод использует ApplicationContext и он должен быть доступен при выполнении

        final User user = ApplicationContext.getUser();

        CompleteOrderRequestMessage completeOrderRequestMessage = new CompleteOrderRequestMessage(user.getId(), ApplicationContext.getSessionToken(), order.getId());
        completeOrderRequestMessage.setReview(order.isComment());

        OrderService orderService = retrofit.create(OrderService.class);
        Call<CompleteOrderResponseMessage> call = orderService.completeOrder(completeOrderRequestMessage);


        Response<OrdersResponseMessage> response = null;

        RequestExecutor requestExecutor = new RequestExecutor();

        try {
            response = requestExecutor.execute(call).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (!isResponseSuccessful(response)) {
            return;
        }

        if (order.isComment()) {
            Toast.makeText(ApplicationContext.getContext(), "Оплата за выполнение будет перечислена после проверки модератором", Toast.LENGTH_LONG).show();

        } else {
            double payment = order.getPayment();
            user.setBalance(user.getBalance() + payment);
            order.setPayed(true);
        }


//        Toast.makeText(ApplicationContext.getContext(), "Order completed", Toast.LENGTH_SHORT).show();

        // переместить выполненную задачу в архив
        ApplicationContext.getIdToActiveOrderMap().remove(order.getId());
        ApplicationContext.getIdToHistoryOrderMap().put(order.getId(), order);


        ApplicationContext.getDatabaseManager().writeOrderToDB(order);

    }

    public void withdraw(int amount, String withdrawType, String accountNumber, String notice) {
        WithdrawRequestMessage withdrawRequestMessage = new WithdrawRequestMessage(ApplicationContext.getUser().getId(), ApplicationContext.getSessionToken(), amount, withdrawType, accountNumber, notice);

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

                Toast.makeText(ApplicationContext.getContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();


            }

            @Override
            public void onFailure(Call<SupportResponseMessage> call, Throwable t) {

            }
        });


    }

    private boolean isResponseSuccessful(Response<? extends ResponseMessage> response) {

        if (response == null || !response.isSuccessful()) {
            Toast.makeText(ApplicationContext.getContext(), "Сервер не отвечает. Проверьте соединение с интернетом", Toast.LENGTH_SHORT).show();

            MyAlertDialogFragment.createAndShowErrorDialog("" + response);
            return false;
        }

        if (response.body().getErrors() != null) {
            Toast.makeText(ApplicationContext.getContext(), Arrays.deepToString(response.body().getErrors().values().toArray()), Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }


    static class RequestExecutor extends AsyncTask<Call, Void, Response> {

        @Override
        protected Response doInBackground(Call... calls) {
            try {
                return calls[0].execute();
            } catch (IOException e) {
                MyAlertDialogFragment.createAndShowErrorDialog("Ошибка соединения");
//                e.printStackTrace();
            }
            return null;
        }
    }

}
