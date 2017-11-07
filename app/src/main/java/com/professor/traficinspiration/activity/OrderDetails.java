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

//        Toast.makeText(ApplicationContext.getContext(), order.getIconImage().toString(), Toast.LENGTH_LONG).show();
//        ((ImageView) this.findViewById(R.id.order_icon)).setImageDrawable(order.getIconImage());
//        this.findViewById(R.id.order_icon).refreshDrawableState();

        taskList = order.getTaskList();
        tasksListView = (ListView) findViewById(R.id.listView);

//        tasksListView.setAdapter(new TaskAdapter(OrderDetails.this, taskList));
//        tasksListView.setOnItemClickListener(this);


//        setContentView(R.layout.orders_test);
//
//        Long orderId = getIntent().getLongExtra("id_order", -1L);
//
//        order = ApplicationContext.getIdToActiveOrderMap().get(orderId);
//
//
//        ((TextView) findViewById(R.id.txtTitle)).setText(order.getName());
//        ((TextView) findViewById(R.id.txtPrice)).setText(String.valueOf(order.getPayment()));
//        ((TextView) findViewById(R.id.txtId)).setText((int) order.getId());
//
//
//        List<Task> taskList = order.getTaskList();
//        tasksListView = (ListView) findViewById(R.id.listView);
//        tasksListView.setAdapter(new TaskAdapter(OrderDetails.this, taskList));
//        tasksListView.setOnItemClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        tasksListView.setAdapter(new TaskAdapter(OrderDetails.this, taskList));

        setListViewHeightBasedOnChildren(tasksListView);

    }


    //    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        String taskName = (String) view.getTag();
//
//        List<Task> taskList = order.getTaskList();
//
//        for (Task task: taskList) {
//            if (task.taskName.equals(taskName)) {
//                task.executeTask(this);
//            }
//        }
//
//    }


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
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount())) + 20;
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
