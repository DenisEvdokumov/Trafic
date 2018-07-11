package com.professor.traficinspiration.service.handler;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.professor.traficinspiration.ApplicationContext;
import com.professor.traficinspiration.R;
import com.professor.traficinspiration.activity.SignInActivity;
import com.professor.traficinspiration.model.User;
import com.squareup.picasso.Picasso;

public class UserHandler {

    public static boolean handle(User user) {
        if (user == null) {
            Intent toSignInActivity = new Intent(ApplicationContext.getContext(), SignInActivity.class);
            ApplicationContext.getContext().startActivity(toSignInActivity);
//            MyAlertDialogFragment.createAndShowErrorDialog("Сервер не отвечает. Проверьте соединение с интернетом");

//            Toast.makeText(ApplicationContext.getContext(), "intent - " + toSignInActivity.toString(), Toast.LENGTH_SHORT).show();


//            Toast.makeText(ApplicationContext.getContext(), "Сервер не отвечает. Проверьте соединение с интернетом", Toast.LENGTH_SHORT).show();

            return false;
        }

        // отобразить информацию о пользователе
        ImageView userPhotoView = (ImageView) ApplicationContext.getContext().findViewById(R.id.avatar);

        Uri uri = user.getPhotoUrl();
        Picasso.with(ApplicationContext.getContext())
                .load(uri)
                .placeholder(R.drawable.default_account_icon)
                .error(R.drawable.default_account_icon)
                .into(userPhotoView);

//                    user.setPhoto(userPhotoView.getDrawable());

        String balanceString = "Баланс: " + user.getBalance();
        ((TextView) ApplicationContext.getContext().findViewById(R.id.txtName)).setText(user.getName());
        ((TextView) ApplicationContext.getContext().findViewById(R.id.txtMoney)).setText(balanceString);
        ApplicationContext.getContext().findViewById(R.id.accountInfoButton).setVisibility(View.VISIBLE);


        SharedPreferences sharedPreferences = ApplicationContext.getContext().getSharedPreferences(user.getEmail(), Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("password", user.getPassword()).apply();

        return true;
    }
}
