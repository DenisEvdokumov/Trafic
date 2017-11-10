package com.professor.traficinspiration.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.professor.traficinspiration.ApplicationContext;
import com.professor.traficinspiration.R;
import com.professor.traficinspiration.model.Order;
import com.professor.traficinspiration.service.MessageService;
import com.professor.traficinspiration.service.adapter.OrderAdapter;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

public class OrdersHistory extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    ListView ordersListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_list);

        findViewById(R.id.back_button).setOnClickListener(this);
        ((TextView)findViewById(R.id.header_title)).setText("История заказов");




//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                ApplicationContext.getMessageService().getOrderHistory();
//            }
//        });
//        thread.start();
//
//        try {
//            thread.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }



        // получить историю с сервера
//        ApplicationContext.getMessageService().getOrderHistory();


//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        // отрисовка происходит раньше чем с сервера приходят данные

        List<Order> orderList = ApplicationContext.getHistoryOrderList();

        ordersListView = (ListView) findViewById(R.id.listView);
        ordersListView.setAdapter(new OrderAdapter(OrdersHistory.this, orderList));

        ordersListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent toOrderDetailsActivity = new Intent(this, OrderDetails.class).putExtra("id_order", (Long) view.getTag());
        startActivity(toOrderDetailsActivity);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.back_button) {
            finish();
        }
    }
}
