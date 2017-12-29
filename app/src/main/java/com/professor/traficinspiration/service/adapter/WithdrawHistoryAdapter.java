package com.professor.traficinspiration.service.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.professor.traficinspiration.R;
import com.professor.traficinspiration.model.Order;
import com.professor.traficinspiration.model.Question;
import com.professor.traficinspiration.model.WithdrawHistoryEntry;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;

public class WithdrawHistoryAdapter extends ArrayAdapter<WithdrawHistoryEntry> {

    private Context context;
    private List<WithdrawHistoryEntry> values;

    public WithdrawHistoryAdapter(@NonNull Context context, List<WithdrawHistoryEntry> values) {
        super(context, R.layout.withdraw_history_item_pagination, values);

        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.withdraw_history_item_pagination, parent, false);
        }

        TextView typeText = (TextView) row.findViewById(R.id.txt_withdraw_type);
        TextView accountNumberText = (TextView) row.findViewById(R.id.txt_account_number);
        TextView amountText = (TextView) row.findViewById(R.id.txt_amount);
        TextView createDateText = (TextView) row.findViewById(R.id.txt_create_date);
        TextView updateDateText = (TextView) row.findViewById(R.id.txt_update_date);


        WithdrawHistoryEntry item = values.get(position);

        String type = item.getWithdrawType();
        String accountNumber = item.getAccountNumber();
        String amount = String.valueOf(item.getAmount());
        int status = item.getStatus();

        String createDate = DateFormat.getDateInstance().format(item.getRequestDate());
        String updateDate = DateFormat.getDateInstance().format(item.getUpdateDate());


        String withdrawType;
        switch (type) {
            case "mobile":
                withdrawType = "Мобильный счет";
                break;
            default:
                withdrawType = "Неизвестно";
        }

        typeText.setText(withdrawType);
        accountNumberText.setText(accountNumber);
        amountText.setText(amount + "руб.");
        createDateText.setText(createDate);

        if (status == 1) {
            updateDateText.setText(updateDate);
        }

        return row;
    }
}
