package com.professor.traficinspiration.service.handler;


import android.util.Log;
import android.widget.Toast;

import com.professor.traficinspiration.ApplicationContext;
import com.professor.traficinspiration.model.Order;
import com.professor.traficinspiration.model.messages.OrderResponse;
import com.professor.traficinspiration.model.tasks.CheckInstallTask;
import com.professor.traficinspiration.model.tasks.CommentTask;
import com.professor.traficinspiration.model.tasks.FindTask;
import com.professor.traficinspiration.model.tasks.OpenTask;
import com.professor.traficinspiration.model.tasks.ReopenTask;
import com.professor.traficinspiration.model.tasks.Task;
import com.professor.traficinspiration.service.MessageService;
import com.professor.traficinspiration.utils.FirstStep2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrdersHandler {

    public static void handle(List<OrderResponse> orderResponseList) {
        if (orderResponseList == null) {
            Toast.makeText(ApplicationContext.getContext(), "Не удалось получить список заказов", Toast.LENGTH_SHORT).show();
            return;
        }

        List<Order> newOrderList = new ArrayList<>();
        List<Order> activeOrderList = ApplicationContext.getActiveOrderList();
        List<Order> orderList = new ArrayList<>();
        for (OrderResponse orderResponse: orderResponseList){
            Order order = new Order();
            order.setId(Long.parseLong(decryptAES(orderResponse.getId())));
            order.setName(decryptAES(orderResponse.getName()));
            order.setPackageName(decryptAES(orderResponse.getPackageName()));
            order.setImageUrl(decryptAES(orderResponse.getImgUrl()));
            order.setKeywords(decryptAES(orderResponse.getKeyWords()));
            order.setPayment(Double.parseDouble(decryptAES(orderResponse.getPayment())));
            order.setOpenCount(Integer.parseInt(decryptAES(orderResponse.getOpenCount())));
            order.setOpenInterval(Integer.parseInt(decryptAES(orderResponse.getOpenInterval())));
            order.setNeededReviews(Integer.parseInt(decryptAES(orderResponse.getNeededReviews())));
            order.setDoneReviews(Integer.parseInt(decryptAES(orderResponse.getDoneReviews())));
            order.setReview(Integer.parseInt(decryptAES(orderResponse.getNeededReviews())));
            orderList.add(order);
            Log.i("1","IMAGE " + order.getImageUrl());
        }

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

    private static String decryptAES(String string) {
        String encryptString = FirstStep2.decrypt(string,ApplicationContext.getKeyAES());
        return encryptString;
    }


}
