package com.professor.traficinspiration.service;


import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.professor.traficinspiration.ApplicationContext;
import com.professor.traficinspiration.MyAlertDialogFragment;
import com.professor.traficinspiration.model.Order;
import com.professor.traficinspiration.model.User;
import com.professor.traficinspiration.model.WithdrawHistoryEntry;
import com.professor.traficinspiration.model.messages.CompleteOrderRequestMessage;
import com.professor.traficinspiration.model.messages.CompleteOrderResponseMessage;
import com.professor.traficinspiration.model.messages.EncryptionRequestMessage;
import com.professor.traficinspiration.model.messages.EncryptionRequestMessage2;
import com.professor.traficinspiration.model.messages.EncryptionResponseMessage;
import com.professor.traficinspiration.model.messages.EncryptionResponseMessage2;
import com.professor.traficinspiration.model.messages.OrdersResponseMessage;
import com.professor.traficinspiration.model.messages.ResponseMessage;
import com.professor.traficinspiration.model.messages.SupportRequestMessage;
import com.professor.traficinspiration.model.messages.SupportResponseMessage;
import com.professor.traficinspiration.model.messages.UserRequestMessage;
import com.professor.traficinspiration.model.messages.UserResponseMessage;
import com.professor.traficinspiration.model.messages.WithdrawHistoryResponseMessage;
import com.professor.traficinspiration.model.messages.WithdrawRequestMessage;
import com.professor.traficinspiration.model.messages.WithdrawResponseMessage;
import com.professor.traficinspiration.service.handler.OrdersHandler;
import com.professor.traficinspiration.service.handler.UserHandler;
import com.professor.traficinspiration.utils.FirstStep;
import com.professor.traficinspiration.utils.FirstStep2;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.professor.traficinspiration.ApplicationContext.getContext;

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

        if(connectToServer()) {


            if (UserHandler.handle(getOrCreateUser(email, password, action, idReferrer))) {
                OrdersHandler.handle(getOrders(false));

                ApplicationContext.notificator.init();
            }
        }

    }

    private Boolean connectToServer() {



        EncryptionResponseMessage encryptionResponseMessage = SendFistEncryptKey();


        EncryptionResponseMessage2 encryptionResponseMessage2 = RequestEncryptKey(encryptionResponseMessage);



        if(chekcMAC_MAC(encryptionResponseMessage2.getKeyMAC_MAC(),encryptionResponseMessage2.getKeyMAC())){

            String KeyMAC_real = FirstStep2.decrypt(encryptionResponseMessage2.getKeyMAC(), ApplicationContext.getKeyAES());
            ApplicationContext.setKeyMAC(KeyMAC_real);
            Log.i("1", "KeyMAC_real    "  + KeyMAC_real);
            return true;
        }


        return false;
    }

    private EncryptionResponseMessage2 RequestEncryptKey(EncryptionResponseMessage encryptionResponseMessage) {
        final EncryptionRequestMessage2 encryptionRequestMessage2 = FirstStep2.
                genetateEncryptionRequestMessage(encryptionResponseMessage);
        FistConnectToServerAPI fistConnectToServerAPI = retrofit.create(FistConnectToServerAPI.class);
        final Call<EncryptionResponseMessage2> call2 = fistConnectToServerAPI.getFirstKey2(encryptionRequestMessage2);

        Response<EncryptionResponseMessage2> response2 = null;

        RequestExecutor requestExecutor2 = new RequestExecutor();

        Log.i("1", encryptionRequestMessage2.getIdSessionMAC().toString());
        try {
            response2 = requestExecutor2.execute(call2).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Log.i("1", e.toString());
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            Log.i("1", e.toString());
            return null;
        }


        if (!isResponseSuccessful(response2)) {
            try {
                Log.i("1", response2.errorBody().string().toString());
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }

        return response2.body();

    }

    private EncryptionResponseMessage SendFistEncryptKey() {
        final EncryptionRequestMessage encryptionRequestMessage = FirstStep.genetateEncryptionRequestMessage();
        FistConnectToServerAPI fistConnectToServerAPI = retrofit.create(FistConnectToServerAPI.class);
        final Call<EncryptionResponseMessage> call = fistConnectToServerAPI.getFirstKey(encryptionRequestMessage);

        Response<EncryptionResponseMessage> response = null;

        RequestExecutor requestExecutor = new RequestExecutor();

        try {
            response = requestExecutor.execute(call).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Log.i("1", e.toString());
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            Log.i("1", e.toString());
            return null;
        }


        if (!isResponseSuccessful(response)) {
            Log.i("1", "no isResponseSuccessful");
            return null;
        }
        return response.body();
    }

    private Boolean chekcMAC_MAC(String keyMAC_MAC,String keyMAC) {



        String keyMACold = ApplicationContext.getKeyMAC();

        String KeyMAC_from_MAC = FirstStep2.decrypt(keyMAC_MAC, keyMACold);
        //Проверка равен ли KeyMAC к KeyMAC_MACdecoded
        if(keyMAC.equals(KeyMAC_from_MAC)) {
            Log.i("1","KeyMAC == KeyMAC_MACdecoded ---------------------------");
        }else {
            return false;
        }
        return true;
    }

    public User getOrCreateUser(final String email, final String password, final String action, final Long idReferrer) {

        // !!! encrypt password...

        final UserRequestMessage userRequestMessage = new UserRequestMessage();

        Log.i("1", "KeyMAC_real         ----------------   "  + ApplicationContext.getKeyMAC());
        Log.i("1", "KeyAES         -----------------      "  + ApplicationContext.getKeyAES());
        Log.i("1", "SessionID       -----------------      "  + ApplicationContext.getIdSession());

        String emailAES = encryptAES(email);
        userRequestMessage.setEmail(emailAES);
        userRequestMessage.setEmailMAC(encrypt(emailAES));

        String passwordAES = encryptAES(password);
        userRequestMessage.setPassword(passwordAES);
        userRequestMessage.setPasswordMAC(encrypt(passwordAES));

        userRequestMessage.setAction(action);


        userRequestMessage.setIdSession(ApplicationContext.getIdSession());
        userRequestMessage.setIdSessionMAC(encrypt(ApplicationContext.getIdSession()));

        String sequenceAES = encryptAES(String.valueOf(Integer.parseInt(ApplicationContext.getSequence())));

        userRequestMessage.setSequence(sequenceAES);
        userRequestMessage.setSequenceMAC(encrypt(sequenceAES));





        UserService userService = retrofit.create(UserService.class);
        final Call<UserResponseMessage> call = userService.getOrCreateUser(userRequestMessage);

        Response<UserResponseMessage> response = null;

        RequestExecutor requestExecutor = new RequestExecutor();

        try {
            response = requestExecutor.execute(call).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }


        if (!isResponseSuccessful(response)) {
            return null;
        }

        UserResponseMessage userResponseMessage = response.body();

        User user = getUserForResponse(userResponseMessage);

        if(chekcMAC_MAC(userResponseMessage.getKeyMACMAC(),userResponseMessage.getKeyMAC())){

            String KeyMAC_real = FirstStep2.decrypt(userResponseMessage.getKeyMAC(), ApplicationContext.getKeyAES());
            ApplicationContext.setKeyMAC(KeyMAC_real);
            Log.i("1", "KeyMAC_real    "  + KeyMAC_real);

        }




        return null;
    }

    private User getUserForResponse(UserResponseMessage userResponseMessage) {
        User user = new User();
        user.setId(Long.parseLong(decrypt(userResponseMessage.getId())));
    }

    private String decrypt(String string) {
        String encryptString = FirstStep2.decrypt(string,ApplicationContext.getKeyMAC());
        return encryptString;
    }

    private String encryptAES(String string) {
        String encryptString = FirstStep2.encrypt(string,ApplicationContext.getKeyAES());
        return encryptString;

    }

    private String encrypt(String string) {
        String encryptString = FirstStep2.encrypt(string,ApplicationContext.getKeyMAC());
        return encryptString;
    }

    public List<Order> getOrders(boolean history) {
        return getOrders(ApplicationContext.getUser().getId(), ApplicationContext.getIdSession(), history);
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
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }

        if (!isResponseSuccessful(response)) {
            return null;
        }

        OrdersResponseMessage ordersResponseMessage = response.body();

        return ordersResponseMessage.getOrderList();
    }

    public void completeOrder(final Order order) {
        // метод использует ApplicationContext и он должен быть доступен при выполнении

        User user = ApplicationContext.getUser();

        CompleteOrderRequestMessage completeOrderRequestMessage = new CompleteOrderRequestMessage(user.getId(), ApplicationContext.getIdSession(), order.getId());
        completeOrderRequestMessage.setReview(order.isComment());

        OrderService orderService = retrofit.create(OrderService.class);
        Call<CompleteOrderResponseMessage> call = orderService.completeOrder(completeOrderRequestMessage);


        Response<OrdersResponseMessage> response = null;

        RequestExecutor requestExecutor = new RequestExecutor();

        try {
            response = requestExecutor.execute(call).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return;
        }

        if (!isResponseSuccessful(response)) {
            return;
        }

        if (order.isComment()) {
            Toast.makeText(getContext(), "Оплата за выполнение будет перечислена после проверки модератором", Toast.LENGTH_LONG).show();
        } else {
            double payment = order.getPayment();
            user.setBalance(user.getBalance() + payment);
            order.setPayed(true);
        }

        // переместить выполненную задачу в архив
        ApplicationContext.getIdToActiveOrderMap().remove(order.getId());
        ApplicationContext.getIdToHistoryOrderMap().put(order.getId(), order);


        ApplicationContext.getDatabaseManager().writeOrderToDB(order);

    }

    public void withdraw(int amount, String withdrawType, String accountNumber, String notice) {
        WithdrawRequestMessage withdrawRequestMessage = new WithdrawRequestMessage(ApplicationContext.getUser().getId(), ApplicationContext.getIdSession(), amount, withdrawType, accountNumber, notice);

        MoneyService moneyService = retrofit.create(MoneyService.class);
        Call<WithdrawResponseMessage> call = moneyService.withdraw(withdrawRequestMessage);

        call.enqueue(new Callback<WithdrawResponseMessage>() {
            @Override
            public void onResponse(Call<WithdrawResponseMessage> call, Response<WithdrawResponseMessage> response) {

                if (!isResponseSuccessful(response)) {
                    return;
                }

                Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<WithdrawResponseMessage> call, Throwable t) {
                MyAlertDialogFragment.createAndShowErrorDialog("Возникла ошибка в процессе попытки вывода средств");
            }
        });
    }

    public List<WithdrawHistoryEntry> getWithdrawHistory() {

        MoneyService moneyService = retrofit.create(MoneyService.class);

        User user = ApplicationContext.getUser();

        Call<WithdrawHistoryResponseMessage> call = moneyService.getWithdrawHistory(user.getId(), ApplicationContext.getIdSession());

        Response<WithdrawHistoryResponseMessage> response = null;

        RequestExecutor requestExecutor = new RequestExecutor();

        try {
            response = requestExecutor.execute(call).get();

//            Toast.makeText(ApplicationContext.getContext(), "" + response.toString(), Toast.LENGTH_LONG).show();

        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }

        if (!isResponseSuccessful(response)) {
            return null;
        }

        WithdrawHistoryResponseMessage withdrawHistoryResponseMessage = response.body();

        return withdrawHistoryResponseMessage.getWithdrawList();

    }

    public void sendSupportRequest(String message) {
        SupportRequestMessage supportRequestMessage = new SupportRequestMessage(ApplicationContext.getUser().getId(), ApplicationContext.getIdSession(), message);

        SupportService supportService = retrofit.create(SupportService.class);
        Call<SupportResponseMessage> call = supportService.sendSupportRequest(supportRequestMessage);

        call.enqueue(new Callback<SupportResponseMessage>() {
            @Override
            public void onResponse(Call<SupportResponseMessage> call, Response<SupportResponseMessage> response) {

                if (!isResponseSuccessful(response)) {
                    return;
                }

                Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();


            }

            @Override
            public void onFailure(Call<SupportResponseMessage> call, Throwable t) {

            }
        });

    }

//    public int getServerKey(int clientKey) {
//        EncryptingService encryptingService = retrofit.create(EncryptingService.class);
//
//        Call<EncryptionResponseMessage> call = encryptingService.getServerKey(clientKey);
//
//        Response<EncryptionResponseMessage> response = null;
//
//        RequestExecutor requestExecutor = new RequestExecutor();
//
//        try {
//            response = requestExecutor.execute(call).get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//            return -1;
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//            return -1;
//        }
//
//        if (!isResponseSuccessful(response)) {
//            return -1;
//        }
//
//        EncryptionResponseMessage encryptionResponseMessage = response.body();
//
////        ApplicationContext.setIdSession(encryptionResponseMessage.getToken());
//
//        return encryptionResponseMessage.getServerKey();
//    }

    private boolean isResponseSuccessful(Response<? extends ResponseMessage> response) {

        if (response == null || !response.isSuccessful()) {
            if (getContext() != null) {
                Toast.makeText(getContext(), "Сервер не отвечает. Проверьте соединение с интернетом", Toast.LENGTH_SHORT).show();
            }
//            MyAlertDialogFragment.createAndShowErrorDialog("Сервер не отвечает. Проверьте соединение с интернетом");
            return false;
        }

        if (response.body().getErrors() != null) {
            if (getContext() != null) {
                Toast.makeText(getContext(), Arrays.deepToString(response.body().getErrors().values().toArray()), Toast.LENGTH_LONG).show();
            }
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
