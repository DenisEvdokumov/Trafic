package com.professor.traficinspiration.service.adapter;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.professor.traficinspiration.ApplicationContext;
import com.professor.traficinspiration.R;
import com.professor.traficinspiration.model.Order;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OrderAdapter extends ArrayAdapter<Order> {

    private Context context;
    private List<Order> values;

    public OrderAdapter(@NonNull Context context, List<Order> values) {
        super(context, R.layout.order_list_item_pagination, values);

        this.context = context;
        this.values = values;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.order_list_item_pagination, parent, false);
        }

//        android.R.layout.simple_list_item_1 - встроенный элемент для отображения элемента списка
        TextView orderText = (TextView) row.findViewById(R.id.list_item_pagination_text);
        TextView orderPayment = (TextView) row.findViewById(R.id.list_item_pagination_payment);

        Order item = values.get(position);

        long id = item.getId();
        String message = item.getName();
        String payment = String.valueOf(item.getPayment()) + " руб.";
        orderText.setText(message);
        orderPayment.setText(payment);


        ImageView orderIcon = (ImageView) row.findViewById(R.id.list_item_pagination_icon);
        Drawable savedImage = item.getIconImage();
        try {


            if (savedImage == null) {
                Picasso.with(context)
                        .load(item.getImageUrl())
                        .into(orderIcon);

                item.setIconImage(orderIcon.getDrawable());
            } else {
                orderIcon.setImageDrawable(savedImage);
            }
        }
        catch (Exception e){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                savedImage = ApplicationContext.getContext().getDrawable(R.drawable.main_new);
                orderIcon.setImageDrawable(savedImage);
            }
        }


//        Toast.makeText(context, "id = " + id, Toast.LENGTH_SHORT).show();

        row.setTag(id);

        return row;
    }
}
