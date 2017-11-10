package com.professor.traficinspiration.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.professor.traficinspiration.ApplicationContext;
import com.professor.traficinspiration.R;
import com.professor.traficinspiration.model.Order;
import com.professor.traficinspiration.model.tasks.Task;
import com.professor.traficinspiration.service.adapter.TaskAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OrderDetails extends AppCompatActivity implements View.OnClickListener /*implements AdapterView.OnItemClickListener*/ {

    Order order;
    ListView tasksListView;
    List<Task> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        findViewById(R.id.back_button).setOnClickListener(this);
        ((TextView)findViewById(R.id.header_title)).setText("Выполнение заказа");

        Long orderId = getIntent().getLongExtra("id_order", -1L);

        order = ApplicationContext.getIdToActiveOrderMap().get(orderId);

        if (order == null) {
            order = ApplicationContext.getIdToHistoryOrderMap().get(orderId);
        }

        String name = order.getName();
        double payment = order.getPayment();

        ((TextView) findViewById(R.id.txtTitle)).setText(name);
        ((TextView) findViewById(R.id.txtPrice)).setText(String.valueOf(payment));
//        ((TextView) findViewById(R.id.txtId)).setText(String.valueOf(order.getId()));


        // set order icon
        ImageView orderIcon = (ImageView) this.findViewById(R.id.order_icon);
        Drawable savedImage = order.getIconImage();

        if (savedImage == null) {
            Picasso.with(this)
                    .load(order.getImageUrl())
                    .into(orderIcon);

            order.setIconImage(orderIcon.getDrawable());
        } else {
            orderIcon.setImageDrawable(savedImage);
        }

        taskList = order.getTaskList();
        tasksListView = (ListView) findViewById(R.id.listView);

    }

    @Override
    protected void onResume() {
        super.onResume();

        tasksListView.setAdapter(new TaskAdapter(OrderDetails.this, taskList));

        setListViewHeightBasedOnChildren(tasksListView);

        if (order.isFinished()) {
            ((TextView)findViewById(R.id.txtAlert)).setText("Заказ выполнен!");
        }

        findViewById(R.id.header_title).setFocusable(true);
        findViewById(R.id.header_title).setFocusableInTouchMode(true);
        findViewById(R.id.header_title).requestFocusFromTouch();
        findViewById(R.id.header_title).requestFocus();
        findViewById(R.id.header_title).requestLayout();

    }


    public static void setListViewHeightBasedOnChildren(ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += listItem.getMeasuredHeight() + 15;
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount()));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.back_button) {
            finish();
        }
    }
}
