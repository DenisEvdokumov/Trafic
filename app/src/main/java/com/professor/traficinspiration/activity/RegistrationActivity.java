package com.professor.traficinspiration.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.professor.traficinspiration.ApplicationContext;
import com.professor.traficinspiration.R;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    EditText passwordEditText;
    EditText confirmPasswordEditText;
    EditText referrerEditText,emailEditText;
    //emailEditText

    String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        findViewById(R.id.back_button).setOnClickListener(this);
        ((TextView)findViewById(R.id.header_title)).setText("Регистрация");

//        ApplicationContext.setContext(this);

        userEmail = ApplicationContext.getUser().getEmail();

        emailEditText = (EditText) findViewById(R.id.et_email);
        emailEditText.setText(userEmail);

        passwordEditText = (EditText) findViewById(R.id.et_password);
        confirmPasswordEditText = (EditText) findViewById(R.id.et_confirm_password);
        referrerEditText = (EditText) findViewById(R.id.et_referrer);

        findViewById(R.id.btn_register).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.back_button) {
            finish();
        } else if (id == R.id.btn_register) {
            String password,email;

            password = passwordEditText.getText().toString();
            email = emailEditText.getText().toString();
            String confirmPassword = confirmPasswordEditText.getText().toString();

            // password check
            // можно добавить проверку сложности пароля
            if (password.equals("")){
                Toast.makeText(RegistrationActivity.this, "Passwords must not be empty", Toast.LENGTH_SHORT).show();
                return;
            }
            if (email.equals("")){
                Toast.makeText(RegistrationActivity.this, "Passwords must not be empty", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!password.equals(confirmPassword)){
                Toast.makeText(RegistrationActivity.this, "Passwords not match", Toast.LENGTH_SHORT).show();
                return;
            }


            String idReferrerString = referrerEditText.getText().toString();
            Long idReferrer = null;

            if (!idReferrerString.equals("")) {
                idReferrer = Long.parseLong(idReferrerString);
            }

            ApplicationContext.getMessageService().executeEnterSequence(email, password, "registration", idReferrer);

            this.finish();
            // continued in MessageService onResponse
        }


    }
}
