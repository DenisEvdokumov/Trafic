package com.professor.traficinspiration.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.professor.traficinspiration.ApplicationContext;
import com.professor.traficinspiration.R;

public class MoneyActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money);

        findViewById(R.id.back_button).setOnClickListener(this);
        ((TextView)findViewById(R.id.header_title)).setText("Вывод денег");

        final double balance = ApplicationContext.getUser().getBalance();

        TextView balanceView = (TextView) findViewById(R.id.balance_view);
        balanceView.setText(String.valueOf(balance));

        String[] data = {"mobile", "webMoney"};

        // адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

//        Spinner transferTypeSpinner = (Spinner) findViewById(R.id.spinner_transfer_type);
//        transferTypeSpinner.setAdapter(adapter);
//        // заголовок
//        transferTypeSpinner.setPrompt("Transfer type");
//        // выделяем элемент
//        transferTypeSpinner.setSelection(1);
//        // устанавливаем обработчик нажатия
//        transferTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view,
//                                       int position, long id) {
//                // показываем позиция нажатого элемента
////                Toast.makeText(getBaseContext(), "Position = " + position, Toast.LENGTH_SHORT).show();
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> arg0) {
//            }
//        });

        final String withdrawType = getIntent().getStringExtra("withdrawType");
//        ((TextView)findViewById(R.id.lbl_transfer_type)).setText(withdrawType);



        Button transferButton = (Button) findViewById(R.id.transferButton);

        transferButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText transferNumberEditText = (EditText) findViewById(R.id.et_transfer_number);
                EditText transferAmountEditText = (EditText) findViewById(R.id.et_transfer_amount);
//                Spinner transferTypeSpinner = (Spinner) findViewById(R.id.spinner_transfer_type);

                String amountString = transferAmountEditText.getText().toString();
                String numberString = transferNumberEditText.getText().toString();

                if (amountString.equals("") || numberString.equals("")) {
                    Toast.makeText(MoneyActivity.this, "Должны быть заполнены все поля", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (Integer.parseInt(amountString) < 100) {
                    Toast.makeText(MoneyActivity.this, "Минимальная сумма для вывода 100 руб.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (Integer.parseInt(amountString) > balance) {
                    Toast.makeText(MoneyActivity.this, "Недостаточно средств на счету", Toast.LENGTH_SHORT).show();
                    return;
                }

                int amount = Integer.parseInt(amountString);
//                long number = Long.parseLong(numberString);

                // вариант с числовым идентификатором типа оплаты
//                int type = transferTypeSpinner.getSelectedItemPosition();
//                String type = (String) transferTypeSpinner.getSelectedItem();

                ApplicationContext.getMessageService().withdraw(amount, withdrawType, numberString, "");
            }
        });


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.back_button) {
            finish();
        }
    }
}
