package com.professor.traficinspiration.activity;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.professor.traficinspiration.ApplicationContext;
import com.professor.traficinspiration.R;
import com.professor.traficinspiration.model.User;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserInfoActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        findViewById(R.id.back_button).setOnClickListener(this);
        ((TextView)findViewById(R.id.header_title)).setText("Ваш профиль");


        User user = ApplicationContext.getUser();

//        Drawable userPhoto = user.getPhoto();
//        ImageView userPhotoView = (ImageView) ApplicationContext.getContext().findViewById(R.id.avatar);
//
//
//        if (userPhoto == null) {
//            Uri uri = user.getPhotoUrl();
//            Picasso.with(ApplicationContext.getContext())
//                    .load(uri)
//                    .placeholder(R.drawable.default_account_icon)
//                    .error(R.drawable.default_account_icon)
//                    .into(userPhotoView);
//
//            user.setPhoto(userPhotoView.getDrawable());
//
//        } else {
//            userPhotoView.setImageDrawable(userPhoto);
//        }

        ((CircleImageView) findViewById(R.id.avatar)).setImageDrawable(user.getPhoto());

        TextView idTextView = (TextView) findViewById(R.id.text_user_id);
        idTextView.setText(String.valueOf(user.getId()));

        TextView nameTextView = (TextView) findViewById(R.id.text_user_name);
        nameTextView.setText(user.getName());

        TextView balanceTextView = (TextView) findViewById(R.id.text_user_balance);
        balanceTextView.setText(String.valueOf("БАЛАНС\n" + user.getBalance() + " руб."));

        TextView emailTextView = (TextView) findViewById(R.id.text_user_email);
        emailTextView.setText(user.getEmail());

        TextView ordersCompletedTextView = (TextView) findViewById(R.id.text_user_orders_completed);
        ordersCompletedTextView.setText(String.valueOf(user.getOrdersCompleted() + "\nВИПОЛНЕННЫХ\nЗАКАЗОВ"));

        TextView referralsCountTextView = (TextView) findViewById(R.id.text_user_referrals_count);
        referralsCountTextView.setText(String.valueOf(user.getReferralsCount() + "\nРЕФЕРАЛОВ"));
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.back_button) {
            finish();
        }
    }

}
