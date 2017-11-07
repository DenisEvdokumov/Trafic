package com.professor.traficinspiration.model.tasks;


import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.professor.traficinspiration.model.Order;

import java.util.Date;

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
    public void executeTask(Activity activity) {
        Intent launchIntent = activity.getPackageManager().getLaunchIntentForPackage(order.getPackageName());
        if (launchIntent != null) {

            Date openDate = new Date(System.currentTimeMillis());
            order.setOpenDate(openDate);

            activity.startActivity(launchIntent);
            Toast.makeText(activity, "Выполнено", Toast.LENGTH_SHORT).show();
            // отметить задачу как выполненную
            complete();

        } else {
            Toast.makeText(activity, "Приложение не установлено", Toast.LENGTH_SHORT).show();
        }
    }
}
