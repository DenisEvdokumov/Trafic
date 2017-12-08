package com.professor.traficinspiration.model.tasks;


import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.professor.traficinspiration.ApplicationContext;
import com.professor.traficinspiration.model.Order;
import com.professor.traficinspiration.utils.TimeHelper;

import java.util.Date;
import java.util.concurrent.ExecutionException;

public class OpenTask extends Task {

    public OpenTask(Order order) {
        super(order, "Open");

        buttonString = "Открыть приложение";
        description = "Откройте приложение обязательно через этот заказ чтобы задание было засчитано.";
        titleString = "Открыть приложение";
    }

//    public boolean checkRunning(Map<String, String> parameters) {
//
//        String packageName = parameters.get("packageName");
//
//        ActivityManager manager = (ActivityManager) ApplicationContext.getContext().getSystemService(ACTIVITY_SERVICE);
//        List<ActivityManager.RunningAppProcessInfo> runningProcesses = manager.getRunningAppProcesses();
//
//        for (ActivityManager.RunningAppProcessInfo process: runningProcesses) {
//            if (process.processName.equals(packageName)) {
//                return true;
//            }
//        }
//
//        return false;
//    }

    @Override
    public boolean executeTask(Activity activity) {
        Intent launchIntent = activity.getPackageManager().getLaunchIntentForPackage(order.getPackageName());
        if (launchIntent != null) {

            String timeString = null;

            try {
                timeString = new TimeHelper().execute(this).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            Date currentDate;
            // проверка получена ли дата...
            if (timeString == null) {
                currentDate = new Date(System.currentTimeMillis());
            } else {
                currentDate = new Date(Long.parseLong(timeString));
            }

            order.setOpenDate(currentDate);

            activity.startActivity(launchIntent);
            Toast.makeText(activity, "Выполнено", Toast.LENGTH_SHORT).show();
            // отметить задачу как выполненную
            complete();

            return true;
        } else {
            Toast.makeText(activity, "Приложение не установлено", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
