package com.professor.traficinspiration.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.professor.traficinspiration.R;

public class NewsActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        findViewById(R.id.back_button).setOnClickListener(this);
        ((TextView) findViewById(R.id.header_title)).setText("Новости проекта");
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.back_button:
                finish();
                break;
        }
    }
}
