package com.professor.traficinspiration.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.professor.traficinspiration.ApplicationContext;
import com.professor.traficinspiration.R;

public class ReferralsActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_referrals);

        findViewById(R.id.back_button).setOnClickListener(this);
        ((TextView)findViewById(R.id.header_title)).setText("Рефералы");

        ((TextView) findViewById(R.id.text_referrals_count)).setText(String.valueOf(ApplicationContext.getUser().getReferralsCount()));
        ((TextView) findViewById(R.id.text_referral_code)).setText(String.valueOf(ApplicationContext.getUser().getId()));
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.back_button) {
            finish();
        }
    }
}
