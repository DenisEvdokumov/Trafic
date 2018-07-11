package com.professor.traficinspiration.service.handler;


import android.widget.Toast;

import com.professor.traficinspiration.ApplicationContext;
import com.professor.traficinspiration.model.Order;
import com.professor.traficinspiration.model.tasks.CheckInstallTask;
import com.professor.traficinspiration.model.tasks.CommentTask;
import com.professor.traficinspiration.model.tasks.FindTask;
import com.professor.traficinspiration.model.tasks.OpenTask;
import com.professor.traficinspiration.model.tasks.ReopenTask;
import com.professor.traficinspiration.model.tasks.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderHistoryHandler {

    public static void handle(List<Order> orderList) {
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
}
