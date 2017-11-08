package com.professor.traficinspiration.model.tasks;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.professor.traficinspiration.model.Order;

public class CommentTask extends Task {

    public CommentTask() {
        super("Comment");
    }

    public CommentTask(Order order) {
        super(order, "Comment");
    }

    @Override
    public boolean executeTask(Activity activity) {
        String baseUrl = "https://play.google.com/store/apps/details?id=";

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(baseUrl + order.getPackageName()));
        activity.startActivity(browserIntent);

        return false;
    }
}
