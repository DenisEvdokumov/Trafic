package com.professor.traficinspiration.model.tasks;


import android.app.Activity;

import com.professor.traficinspiration.ApplicationContext;
import com.professor.traficinspiration.model.Order;

public abstract class Task {

//    long id;
    Order order;
    public String taskName;

    public String buttonString;
    public String description;
    public String titleString;

    boolean completed;

    // срок годности задачи... firstOpen + value
    int expiredAfter;

    // internal/external tasks
    int taskType; // enum...

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public Task(Order order, String taskName) {
        this.order = order;
        this.taskName = taskName;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public abstract boolean executeTask(Activity activity);

    public void complete() {
        completed = true;
        // проверить все ли задачи выполнены
        order.checkCompletion();
    }
}
