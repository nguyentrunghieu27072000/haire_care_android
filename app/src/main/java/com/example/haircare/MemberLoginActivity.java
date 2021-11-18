package com.example.haircare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.haircare.Share.DataLocalManager;

public class MemberLoginActivity extends AppCompatActivity {

    private EditText editTextPhoneNumber, editTextPassword;
    private Button buttonLogin;

    private String phoneNumber = "";
    private String password = "";
    private static final String TAG = MemberLoginActivity.class.getName();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_login);

        initUI();

        SetLogin();

        EventActivity();


    }

    private void EventActivity() {

    }

    private void SetLogin() {
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (password.equals(editTextPassword.getText().toString())) {
                    DataLocalManager.setPrefUserName("" + phoneNumber);
                    DataLocalManager.setPrefIsLogged(true);
                    if (phoneNumber.equals("+84973271208")) {
                        DataLocalManager.setPrefIsAdmin(true);
                    }

                    finish();
                    startActivity(new Intent(MemberLoginActivity.this, HomeActivity.class));
                    finishAffinity();
                } else {
                    Toast.makeText(MemberLoginActivity.this, "Mã PIN không chính xác", Toast.LENGTH_SHORT).show();
                    editTextPassword.setText("");
                }
            }
        });
        editTextPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (editTextPassword.getText().toString().length() < 6) {
                    buttonLogin.setEnabled(false);
                    editTextPassword.setError("Mã PIN 6 số");
                } else {
                    buttonLogin.setEnabled(true);
                    editTextPassword.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void initUI() {
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNummber);
        editTextPassword = findViewById(R.id.editTextPassWord);
        buttonLogin = findViewById(R.id.btnSiginMember);
        phoneNumber = getIntent().getStringExtra("PhoneNumber");
        password = getIntent().getStringExtra("Password");
        editTextPhoneNumber.setText(phoneNumber);
        editTextPassword.requestFocus();
    }
}