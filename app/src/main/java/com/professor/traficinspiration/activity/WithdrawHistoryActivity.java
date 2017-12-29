package com.professor.traficinspiration.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.professor.traficinspiration.ApplicationContext;
import com.professor.traficinspiration.R;
import com.professor.traficinspiration.model.Order;
import com.professor.traficinspiration.model.WithdrawHistoryEntry;
import com.professor.traficinspiration.service.adapter.OrderAdapter;
import com.professor.traficinspiration.service.adapter.WithdrawHistoryAdapter;

import java.util.List;

public class WithdrawHistoryActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw_history);

        findViewById(R.id.back_button).setOnClickListener(this);
        ((TextView) findViewById(R.id.header_title)).setText("История выплат");


        List<WithdrawHistoryEntry> withdrawHistory = ApplicationContext.getMessageService().getWithdrawHistory();

        if (withdrawHistory == null) {
            Toast.makeText(ApplicationContext.getContext(), "История пуста", Toast.LENGTH_LONG).show();
            return;

        }

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new WithdrawHistoryAdapter(WithdrawHistoryActivity.this, withdrawHistory));

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
