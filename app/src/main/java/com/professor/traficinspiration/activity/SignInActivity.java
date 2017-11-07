package com.professor.traficinspiration.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.professor.traficinspiration.ApplicationContext;
import com.professor.traficinspiration.MyAlertDialogFragment;
import com.professor.traficinspiration.R;
import com.professor.traficinspiration.model.User;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    EditText passwordEditText;
    CheckBox remainSignedInCheckBox;


    String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        findViewById(R.id.back_button).setVisibility(View.INVISIBLE);
        ((TextView)findViewById(R.id.header_title)).setText("Авторизация");

//        ApplicationContext.setContext(this);

        userEmail = ApplicationContext.getUser().getEmail();

        TextView emailEditText = (TextView) findViewById(R.id.et_email);
        emailEditText.setText(userEmail);

        passwordEditText = (EditText) findViewById(R.id.et_password);
//        remainSignedInCheckBox = (CheckBox) findViewById(R.id.cb_remain_signed_in);

        findViewById(R.id.btn_login).setOnClickListener(this);
        findViewById(R.id.btn_register).setOnClickListener(this);
        findViewById(R.id.forgot_password).setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        String password;
        switch (v.getId()) {
            case R.id.btn_login:
                password = passwordEditText.getText().toString();

                // password check
                if (password.equals("")){
                    Toast.makeText(SignInActivity.this, "Passwords must not be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                ApplicationContext.getMessageService().getOrCreateUser(userEmail, password, "authorization", 0L);

                this.finish();
                // continued in MessageService onResponse
                break;

            case R.id.btn_register:

                Intent toRegistrationActivity = new Intent(this, RegistrationActivity.class);
                this.startActivity(toRegistrationActivity);

//                this.finish();

                break;
            case R.id.forgot_password:

                Toast.makeText(SignInActivity.this, "Сочувствую :-). Функция востановления пароля пока не поддерживается.", Toast.LENGTH_SHORT).show();

                break;

            case R.id.back_button:
                finish();
                break;
        }
    }
}