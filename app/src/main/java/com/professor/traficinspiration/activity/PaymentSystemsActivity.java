package com.professor.traficinspiration.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.professor.traficinspiration.R;

public class PaymentSystemsActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_systems);

        findViewById(R.id.back_button).setOnClickListener(this);
        ((TextView) findViewById(R.id.header_title)).setText("Вывод денег");


        findViewById(R.id.btn_mobile).setOnClickListener(this);
        findViewById(R.id.btn_history).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.back_button:
                finish();
                break;
            case R.id.btn_history:
//                Toast.makeText(this, "У Вас нет ни одной выплаты", Toast.LENGTH_LONG).show();
                Intent toWithdrawHistoryActivity = new Intent(this, WithdrawHistoryActivity.class);
                startActivity(toWithdrawHistoryActivity);
                break;
            case R.id.btn_mobile:
                Intent toMoneyActivity = new Intent(this, MoneyActivity.class).putExtra("withdrawType", (String) v.getTag());
                startActivity(toMoneyActivity);
                break;

        }
    }
}
