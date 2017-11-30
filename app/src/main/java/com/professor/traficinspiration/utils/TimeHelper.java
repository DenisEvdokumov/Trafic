package com.professor.traficinspiration.utils;

import android.os.AsyncTask;

import com.professor.traficinspiration.model.tasks.Task;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class TimeHelper extends AsyncTask<Task, Void, String> {

    @Override
    protected String doInBackground(Task... args) {
        String time = null;
        Document doc;
        try {
            doc = Jsoup.connect("https://time.is/Unix_time_now").get();
            Elements title = doc.select("#twd");
            time = title.text() + "000";
        } catch (IOException e) {
            // do nothing
            // or use locale time? ...
        }
        return time;
    }

}
