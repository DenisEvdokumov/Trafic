package com.professor.traficinspiration.service;


import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.professor.traficinspiration.ApplicationContext;
import com.professor.traficinspiration.R;
import com.professor.traficinspiration.activity.MainActivity;
import com.professor.traficinspiration.model.Order;

import java.util.List;

import static android.content.Context.ALARM_SERVICE;
import static android.content.Context.NOTIFICATION_SERVICE;

public class NotificationReceiver extends BroadcastReceiver {

    long userId;
    String sessionToken;
    Context context;

    @Override
    public void onReceive(Context ctx, Intent intent) {
        context = ctx;

        userId = intent.getLongExtra("userId", 0L);
        sessionToken = intent.getStringExtra("sessionToken");

        // refresh notificator
        Intent intent2;
        PendingIntent pIntent2;

        intent2 = createIntent();
        pIntent2 = PendingIntent.getBroadcast(context, 0, intent2, 0);

        AlarmManager am = (AlarmManager) ctx.getSystemService(ALARM_SERVICE);
        am.set(AlarmManager.RTC, System.currentTimeMillis() + 6 * 60 * 60 * 1000, pIntent2);


        // проверить наличие незавершенных задач
        // есть - создать уведомление
        // нет - ничего не делать
        List<Order> orderList = ApplicationContext.getMessageService().getOrders(userId, sessionToken, false);

        String notificatorText = "У Вас есть незавершенные задачи";

        if (orderList == null || orderList.size() == 0) {
//            notificatorText = "У Вас нет незавершенных задач :-)";
            return;
        }


        Intent resultIntent = new Intent(ctx, MainActivity.class);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(ctx, 0, resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);


        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(ctx)
                        .setSmallIcon(R.drawable.icon)
                        .setContentTitle("Мотиватор")
                        .setContentText(notificatorText)
                        .setContentIntent(resultPendingIntent);

        Notification notification = builder.build();

        NotificationManager notificationManager =
                (NotificationManager) ctx.getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);

    }


    Intent createIntent() {
        Intent intent = new Intent(context, NotificationReceiver.class);
        intent.putExtra("userId", userId);
        intent.putExtra("sessionToken", sessionToken);
        return intent;
    }
}
