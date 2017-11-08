package com.professor.traficinspiration.model.tasks;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.professor.traficinspiration.model.Order;

public class FindTask extends Task {

    public FindTask(Order order) {
        super(order, "Find");

        buttonString = "Найти приложение";
        description = "Найдите приложение в Google play и установите его.";
        titleString = "Найти и установить приложение";
    }

    @Override
    public boolean executeTask(Activity activity) {
        String baseUrl = "https://play.google.com/store/apps/details?id=";

        complete();

//        Intent browserIntent = new Intent(ApplicationContext.getContext(), WebViewActivity.class);
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(baseUrl + order.getPackageName()));
        activity.startActivity(browserIntent);

        return true;
    }
}
