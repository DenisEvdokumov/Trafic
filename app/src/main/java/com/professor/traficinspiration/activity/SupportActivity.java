package com.professor.traficinspiration.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.professor.traficinspiration.ApplicationContext;
import com.professor.traficinspiration.R;

public class SupportActivity extends AppCompatActivity implements View.OnClickListener {

    TextView supportMessageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);

        findViewById(R.id.back_button).setOnClickListener(this);
        ((TextView)findViewById(R.id.header_title)).setText("Поддержка");

        findViewById(R.id.btn_support_request).setOnClickListener(this);

        supportMessageTextView = (TextView)findViewById(R.id.support_message);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.back_button:
                finish();
                break;
            case R.id.btn_support_request:
                ApplicationContext.getMessageService().sendSupportRequest(String.valueOf(supportMessageTextView.getText()));

                break;
        }
    }
}
