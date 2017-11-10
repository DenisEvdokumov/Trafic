package com.professor.traficinspiration;


import android.app.Activity;
import android.content.SharedPreferences;

import com.professor.traficinspiration.model.User;
import com.professor.traficinspiration.model.tasks.CheckInstallTask;
import com.professor.traficinspiration.model.tasks.FindTask;
import com.professor.traficinspiration.model.tasks.OpenTask;
import com.professor.traficinspiration.model.Order;
import com.professor.traficinspiration.model.tasks.ReopenTask;
import com.professor.traficinspiration.model.tasks.Task;
import com.professor.traficinspiration.service.MessageService;
import com.professor.traficinspiration.utils.DatabaseManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class ApplicationContext {

    // не совсем правильно записывать сюда конкретное Activity, так как оно может меняться...
    static Activity context;

    // DatabaseManager создается при установлении context =>
    //      вызов DatabaseManager до этого вызовет NullPointerException
    //      при переназначении context DatabaseManager также будет пересоздаваться
    static DatabaseManager databaseManager;

    // или не хранить эту информацию в приложении и при необходимости запрашивать из файловой системы
    static User user;
    static String sessionToken;
//    public static GoogleSignInAccount acct;

    static MessageService messageService = new MessageService();

    // фейковые данные

//    static List<Task> taskList = new ArrayList<>();
//    static List<Order> newOrderList = new ArrayList<>();
//    static List<Order> historyOrderList = new ArrayList<>();

//    static List<Order> activeOrderList = new ArrayList<>();

    static Map<Long, Order> idToNewOrderMap = new HashMap<>();
    static Map<Long, Order> idToActiveOrderMap = new HashMap<>();

    static Map<Long, Order> idToHistoryOrderMap = new HashMap<>();


//    static {
//        taskList.add(new FindTask());
//        taskList.add(new CheckInstallTask());
//        taskList.add(new OpenTask());
//        taskList.add(new ReopenTask(2));
//
//
//        newOrderList.add(new Order(1,"dsa", 5, "org.telegram.messenger", new ArrayList<>(taskList)));
//        newOrderList.add(new Order(2,"cxz", 6, "org.telegram.messenger", new ArrayList<>(taskList))); // пишется только с активных (со второго раза) и не запоминается
//        newOrderList.add(new Order(0, "ewq", 4, "org.telegram.messenger", new ArrayList<>(taskList))); // пишется и запоминается с первого раза
//
//
//        idToNewOrderMap.put(1L, newOrderList.get(0));
//        idToNewOrderMap.put(2L, newOrderList.get(1));
//        idToNewOrderMap.put(0L, newOrderList.get(2));
//    }





    public static Activity getContext() {
        return context;
    }

    public static void setContext(Activity context) {
        ApplicationContext.context = context;
        databaseManager = new DatabaseManager();
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        ApplicationContext.user = user;
        SharedPreferences sPref = context.getSharedPreferences(user.getName(), MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        editor.putLong("userId", user.getId());
        editor.apply();
    }


    public static List<Order> getNewOrderList() {
        // optimization needed

//        return idToNewOrderMap.values();

        List<Order> orderList = new ArrayList<>();
        for (Order order: idToNewOrderMap.values()){
            orderList.add(order);
        }
        return orderList;
    }

    public static void setNewOrderList(List<Order> newOrderList) {
        idToNewOrderMap.clear();
//        or
//        idToNewOrderMap = new HashMap<>();

        for (Order order: newOrderList) {
            idToNewOrderMap.put(order.getId(), order);
        }
    }

    public static List<Order> getActiveOrderList() {
        // optimization needed
        List<Order> orderList = new ArrayList<>();
        orderList.addAll(idToActiveOrderMap.values());
        return orderList;
    }

    public static void setActiveOrderList(List<Order> orderList) {
        idToActiveOrderMap.clear();
        for (Order order: orderList) {
            ApplicationContext.idToActiveOrderMap.put(order.getId(), order);
        }
    }

    public static List<Order> getHistoryOrderList() {
        List<Order> orderList = new ArrayList<>();
        orderList.addAll(idToHistoryOrderMap.values());
        return orderList;

//        return historyOrderList;
    }

    public static void setHistoryOrderList(List<Order> historyOrderList) {
        idToHistoryOrderMap.clear();
        for (Order order: historyOrderList) {
            ApplicationContext.idToHistoryOrderMap.put(order.getId(), order);
        }

//        ApplicationContext.historyOrderList = historyOrderList;
    }

    public static MessageService getMessageService() {
        return messageService;
    }

    public static Map<Long, Order> getIdToNewOrderMap() {
        return idToNewOrderMap;
    }

    public static void setIdToNewOrderMap(Map<Long, Order> idToNewOrderMap) {
        ApplicationContext.idToNewOrderMap = idToNewOrderMap;
    }

    public static Map<Long, Order> getIdToActiveOrderMap() {
        return idToActiveOrderMap;
    }

    public static void setIdToActiveOrderMap(Map<Long, Order> idToActiveOrderMap) {
        ApplicationContext.idToActiveOrderMap = idToActiveOrderMap;
    }

    public static Map<Long, Order> getIdToHistoryOrderMap() {
        return idToHistoryOrderMap;
    }

    public static void setIdToHistoryOrderMap(Map<Long, Order> idToHistoryOrderMap) {
        ApplicationContext.idToHistoryOrderMap = idToHistoryOrderMap;
    }

    public static String getSessionToken() {
        return sessionToken;
    }

    public static void setSessionToken(String sessionToken) {
        ApplicationContext.sessionToken = sessionToken;
    }

    public static DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

}
