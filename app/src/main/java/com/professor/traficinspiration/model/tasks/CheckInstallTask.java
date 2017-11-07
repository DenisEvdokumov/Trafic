package com.professor.traficinspiration.model.tasks;


import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.widget.Toast;

import com.professor.traficinspiration.ApplicationContext;
import com.professor.traficinspiration.model.Order;

import java.util.List;

public class CheckInstallTask extends Task {

    public CheckInstallTask(Order order) {
        super(order, "Check install");

        buttonString = "Проверить установку";
        description = "";
        titleString = "Проверить установку";
    }

    @Override
    public void executeTask(Activity activity) {
        PackageManager pm = ApplicationContext.getContext().getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        boolean installed = false;

        for (ApplicationInfo current: packages) {
            if (current.packageName.equals(order.getPackageName())) {
                installed = true;
            }
        }

        if (installed) {
            Toast.makeText(activity, "Приложение установлено", Toast.LENGTH_SHORT).show();
            complete();
        } else {
            // TODO: выдача сообщения, что приложение не установлено
            Toast.makeText(activity, "Приложение не установлено", Toast.LENGTH_SHORT).show();
        }

    }
}
