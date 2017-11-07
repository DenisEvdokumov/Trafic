package com.professor.traficinspiration.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.professor.traficinspiration.ApplicationContext;
import com.professor.traficinspiration.R;
import com.professor.traficinspiration.model.Order;
import com.professor.traficinspiration.service.adapter.OrderAdapter;

import java.util.List;

public class NewOrders extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    ListView ordersListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_orders);

        findViewById(R.id.back_button).setOnClickListener(this);
        ((TextView)findViewById(R.id.header_title)).setText("Новые заказы");
    }

    @Override
    protected void onResume() {
        super.onResume();

        List<Order> newOrderList = ApplicationContext.getNewOrderList();

        ordersListView = (ListView) findViewById(R.id.listView);
        ordersListView.setAdapter(new OrderAdapter(NewOrders.this, newOrderList));

        ordersListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        long orderId = (Long) view.getTag();

        // изъять заказ из newOrders и добавить в activeOrders
        Order activatingOrder = ApplicationContext.getIdToNewOrderMap().remove(orderId);
        ApplicationContext.getIdToActiveOrderMap().put(orderId, activatingOrder);

        Intent toOrderDetailsActivity = new Intent(this, OrderDetails.class).putExtra("id_order", orderId);
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
