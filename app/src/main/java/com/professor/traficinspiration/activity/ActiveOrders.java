package com.professor.traficinspiration.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.professor.traficinspiration.ApplicationContext;
import com.professor.traficinspiration.R;
import com.professor.traficinspiration.model.Order;
import com.professor.traficinspiration.service.adapter.OrderAdapter;

import java.util.List;

public class ActiveOrders extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    ListView ordersListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_orders);

        findViewById(R.id.back_button).setOnClickListener(this);
        ((TextView)findViewById(R.id.header_title)).setText("Активные заказы");

        List<Order> orderList = ApplicationContext.getActiveOrderList();

        ordersListView = (ListView) findViewById(R.id.listView);
        ordersListView.setAdapter(new OrderAdapter(ActiveOrders.this, orderList));

        ordersListView.setOnItemClickListener(this);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


//        this.a.e = this.a.d.optJSONObject(i);
//        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.a.getSystemService("connectivity")).getActiveNetworkInfo();
//        Object obj = (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) ? null : 1;
//        if (obj == null) {
//            this.a.a(2131034171);
//        } else if (this.a.e.has("partner")) {
//            this.a.startActivityForResult(new Intent(this.a, PartnerNew.class).putExtra("partner", Integer.parseInt(this.a.e.optString("partner"))).putExtra("title", this.a.e.optString("title")).putExtra("thumbnail", this.a.e.optString("thumbnail")).putExtra("price", this.a.e.optString("price")), 100);
//        } else {
//            this.a.startActivityForResult(new Intent(this.a, OrderNew.class).putExtra("id_order", this.a.e.optString("id")), 100);
//        }


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
