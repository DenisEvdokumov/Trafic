package com.professor.traficinspiration.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.professor.traficinspiration.ApplicationContext;


public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ordersDB";
    public static final String TABLE_ORDERS = "orders";

    public DBHelper(Context context) {
        // конструктор суперкласса
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        Toast.makeText(ApplicationContext.getContext(), "creating database", Toast.LENGTH_LONG).show();

        // создаем таблицу с полями
        db.execSQL("create table orders(" +
                "_id integer primary key," +
//                "order_id integer," +
                "name text," +
                "package_name text," +
                "payment real," +
                "finished text," +
                "payed text," +
                "open_date integer," +
                "open_count integer," +
                "open_interval integer," +
                "tasks_status text," +
                "img_url text" +
                ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

