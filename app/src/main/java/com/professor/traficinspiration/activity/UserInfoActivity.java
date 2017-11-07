package com.professor.traficinspiration.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.professor.traficinspiration.ApplicationContext;
import com.professor.traficinspiration.R;
import com.professor.traficinspiration.model.User;
import com.squareup.picasso.Picasso;

public class UserInfoActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        findViewById(R.id.back_button).setOnClickListener(this);
        ((TextView)findViewById(R.id.header_title)).setText("Ваш профиль");

        User user = ApplicationContext.getUser();

        Picasso.with(this)
                .load(user.getPhotoUrl())
                .into((ImageView) findViewById(R.id.avatar));

//        Picasso.with(this).load("").get();


        TextView idTextView = (TextView) findViewById(R.id.text_user_id);
        idTextView.setText(String.valueOf(user.getId()));

        TextView nameTextView = (TextView) findViewById(R.id.text_user_name);
        nameTextView.setText(user.getName());

        TextView balanceTextView = (TextView) findViewById(R.id.text_user_balance);
        balanceTextView.setText(String.valueOf("БАЛАНС\n" + user.getBalance() + " руб."));

        TextView emailTextView = (TextView) findViewById(R.id.text_user_email);
        emailTextView.setText(user.getEmail());

        TextView ordersCompletedTextView = (TextView) findViewById(R.id.text_user_orders_completed);
        ordersCompletedTextView.setText(String.valueOf(user.getOrdersCompleted() + "\nВИПОЛНЕННЫХ\n ЗАКАЗОВ"));

        TextView referralsCountTextView = (TextView) findViewById(R.id.text_user_referrals_count);
        referralsCountTextView.setText(String.valueOf(user.getReferralsCount() + "\n РЕФЕРАЛОВ"));
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.back_button) {
            finish();
        }
    }
}
