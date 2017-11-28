package com.professor.traficinspiration.model.tasks;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.professor.traficinspiration.model.Order;

public class FindTask extends Task {

    public FindTask(Order order) {
        super(order, "Find");

        buttonString = "Найти приложение";
        description = "Найдите приложение в Google play и установите его. Вы должны найти приложение с точно такой же иконкой.";
        titleString = "Найти и установить приложение";
    }

    @Override
    public boolean executeTask(Activity activity) {
        String basePackageUrl = "https://play.google.com/store/apps/details?id=";
        String baseUrl = "https://play.google.com/store/search?q=";
        String urlEnd = "&c=apps";

        String keywords = order.getKeywords();

        String finalUrl;

        if (keywords == null || keywords.equals("")) {
            finalUrl = basePackageUrl + order.getPackageName();
        } else {
            finalUrl = baseUrl + order.getKeywords() + urlEnd;
        }

        complete();

//        Intent browserIntent = new Intent(ApplicationContext.getContext(), WebViewActivity.class);
//        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(baseUrl + order.getPackageName()));
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(finalUrl));

        activity.startActivity(browserIntent);

        return true;
    }
}
