package com.professor.traficinspiration.service;


import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.widget.Toast;

import com.professor.traficinspiration.ApplicationContext;
import com.professor.traficinspiration.R;

import static android.content.Context.ALARM_SERVICE;
import static android.content.Context.NOTIFICATION_SERVICE;

public class AlarmManagerNotificator {

    NotificationManager nm;
    AlarmManager am;

    Intent intent2;
    PendingIntent pIntent2;

    public void init() {
        am = (AlarmManager) ApplicationContext.getContext().getSystemService(ALARM_SERVICE);

        intent2 = createIntent();
        pIntent2 = PendingIntent.getBroadcast(ApplicationContext.getContext(), 0, intent2, 0);

        am.set(AlarmManager.RTC, System.currentTimeMillis() + 6 * 60 * 60 * 1000, pIntent2);
//        am.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + 3000, /*60 * */60 * 1000, pIntent2);

    }

    public void cancelNotification() {
        am.cancel(pIntent2);
    }


    Intent createIntent() {
        Intent intent = new Intent(ApplicationContext.getContext(), Receiver.class);
        intent.putExtra("userId", ApplicationContext.getUser().getId());
        intent.putExtra("sessionToken", ApplicationContext.getSessionToken());
        return intent;
    }



}
