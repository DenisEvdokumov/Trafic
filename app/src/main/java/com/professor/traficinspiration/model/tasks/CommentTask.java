package com.professor.traficinspiration.model.tasks;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.professor.traficinspiration.model.Order;

public class CommentTask extends Task {

    public CommentTask(Order order) {
        super(order, "Comment");

        buttonString = "Оставить комментарий";
        titleString = "Оставить комментарий";
        description = "Оставьте комментарий к приложению с оценкой 5.";

        if (order.getKeywords() != null && !order.getKeywords().equals("")) {
            description = description + " Комментарий должен содержать слова: " + order.getKeywords();
        }
    }

    @Override
    public boolean executeTask(Activity activity) {
        String baseUrl = "https://play.google.com/store/apps/details?id=";

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(baseUrl + order.getPackageName()));
        activity.startActivity(browserIntent);

        complete();

        return true;
    }
}
