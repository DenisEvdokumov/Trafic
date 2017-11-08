package com.professor.traficinspiration.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.professor.traficinspiration.ApplicationContext;
import com.professor.traficinspiration.MyAlertDialogFragment;
import com.professor.traficinspiration.model.Order;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    DBHelper dbHelper = new DBHelper(ApplicationContext.getContext());

    public void writeOrdersToDB() {

//        Toast.makeText(ApplicationContext.getContext(), "write to database...", Toast.LENGTH_LONG).show();

        // создаем объект для данных
        ContentValues cv = new ContentValues();

        // подключаемся к БД
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // нужна оптимизация - вынести в отдельный метод...

//        db.beginTransaction();
        for (Order order : ApplicationContext.getActiveOrderList()) {
            // подготовим данные для вставки в виде пар: наименование столбца - значение
            cv.put("_id", order.getId());
            cv.put("name", order.getName());
            cv.put("package_name", order.getPackageName());
            cv.put("payment", order.getPayment());
            cv.put("finished", String.valueOf(order.isFinished()));
            cv.put("payed", String.valueOf(order.isPayed()));
            cv.put("open_date", order.getOpenDate().getTime());
            cv.put("open_count", order.getOpenCount());
            cv.put("open_interval", order.getOpenInterval());
            cv.put("tasks_status", order.getTasksStatus());
            cv.put("img_url", order.getImageUrl());

//            Toast.makeText(ApplicationContext.getContext(), order.getName() + " - status: " + order.getTasksStatus(), Toast.LENGTH_LONG).show();

            // вставляем или обновляем запись
            int updateCount = db.update("orders", cv, "_id=?", new String[]{String.valueOf(order.getId())});
            if (updateCount == 0) {
                db.insert("orders", null, cv);
            }

//            Toast.makeText(ApplicationContext.getContext(), "wrote " + order.getName(), Toast.LENGTH_LONG).show();
        }

        for (Order order : ApplicationContext.getHistoryOrderList()) {
            // подготовим данные для вставки в виде пар: наименование столбца - значение
            cv.put("_id", order.getId());
            cv.put("name", order.getName());
            cv.put("package_name", order.getPackageName());
            cv.put("payment", order.getPayment());
            cv.put("finished", String.valueOf(order.isFinished()));
            cv.put("payed", String.valueOf(order.isPayed()));
            cv.put("open_date", order.getOpenDate().getTime());
            cv.put("open_count", order.getOpenCount());
            cv.put("open_interval", order.getOpenInterval());
            cv.put("tasks_status", order.getTasksStatus());
            cv.put("img_url", order.getImageUrl());

            // вставляем или обновляем запись
            int updateCount = db.update("orders", cv, "_id=?", new String[]{String.valueOf(order.getId())});
            if (updateCount == 0) {
                db.insert("orders", null, cv);
            }
        }
//        db.setTransactionSuccessful();
//        db.endTransaction();

        // для ускорения также можно использовать SQLiteStatement


        db.close();
        // закрываем подключение к БД
        dbHelper.close();
    }


    public void readOrdersFromDB() {

        // подключаемся к БД
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // делаем запрос всех данных из таблицы mytable, получаем Cursor
        Cursor resultCursor = db.query("orders", null, null, null, null, null, null);

        // ставим позицию курсора на первую строку выборки
        // если в выборке нет строк, вернется false

        if (!resultCursor.moveToFirst()) {
            Toast.makeText(ApplicationContext.getContext(), "no rows", Toast.LENGTH_LONG).show();

            resultCursor.close();

            db.close();
            // закрываем подключение к БД
            dbHelper.close();

            return;
        }

        // определяем номера столбцов по имени в выборке
        int idColIndex = resultCursor.getColumnIndex("_id");
        int nameColIndex = resultCursor.getColumnIndex("name");
        int packageNameColIndex = resultCursor.getColumnIndex("package_name");
        int paymentColIndex = resultCursor.getColumnIndex("payment");
        int finishedColIndex = resultCursor.getColumnIndex("finished");
        int payedColIndex = resultCursor.getColumnIndex("payed");
        int openDateColIndex = resultCursor.getColumnIndex("open_date");
        int openCountColIndex = resultCursor.getColumnIndex("open_count");
        int openIntervalColIndex = resultCursor.getColumnIndex("open_interval");
        int tasksStatusColIndex = resultCursor.getColumnIndex("tasks_status");
        int imageUrlColIndex = resultCursor.getColumnIndex("img_url");

        List<Order> activeOrderList = new ArrayList<>();
        List<Order> historyOrderList = new ArrayList<>();
//        List<Order> newOrderList = ApplicationContext.getNewOrderList();


        do {
//            Toast.makeText(ApplicationContext.getContext(), "read... " + resultCursor.getString(nameColIndex) + " - finished: " + resultCursor.getString(finishedColIndex) + ", payed: " + resultCursor.getString(payedColIndex), Toast.LENGTH_LONG).show();
//            Toast.makeText(ApplicationContext.getContext(), "id: " + resultCursor.getLong(idColIndex), Toast.LENGTH_LONG).show();
//            Toast.makeText(ApplicationContext.getContext(), "open date value: " + resultCursor.getLong(openDateColIndex), Toast.LENGTH_LONG).show();
//            Toast.makeText(ApplicationContext.getContext(), resultCursor.getString(nameColIndex) + " - status: " + resultCursor.getString(tasksStatusColIndex), Toast.LENGTH_LONG).show();

            Order order = new Order(
                    resultCursor.getLong(idColIndex),
                    resultCursor.getString(nameColIndex),
                    resultCursor.getString(packageNameColIndex),
                    resultCursor.getDouble(paymentColIndex),
                    resultCursor.getString(finishedColIndex),
                    resultCursor.getString(payedColIndex),
                    resultCursor.getLong(openDateColIndex),
                    resultCursor.getInt(openCountColIndex),
                    resultCursor.getInt(openIntervalColIndex),
                    resultCursor.getString(tasksStatusColIndex),
                    resultCursor.getString(imageUrlColIndex)
            );

            // разделить на активные и завершенные (оплаченные)
            if (order.isPayed()) {
                historyOrderList.add(order);
            } else {
                activeOrderList.add(order);
            }


            // убрать задачи из списка новых задач, полученных с сервера
//            newOrderList.remove(order);
//            Toast.makeText(ApplicationContext.getContext(), "removed from new order list - " + newOrderList.remove(order), Toast.LENGTH_LONG).show();

//            newOrderList.remove(order);

            // переход на следующую строку
            // а если следующей нет (текущая - последняя), то false - выходим из цикла
        } while (resultCursor.moveToNext());

        Toast.makeText(ApplicationContext.getContext(), "local data is read", Toast.LENGTH_LONG).show();


        ApplicationContext.setActiveOrderList(activeOrderList);
        ApplicationContext.setHistoryOrderList(historyOrderList);
//        ApplicationContext.setNewOrderList(newOrderList);

        resultCursor.close();

        // закрываем подключение к БД
        dbHelper.close();

    }
}
