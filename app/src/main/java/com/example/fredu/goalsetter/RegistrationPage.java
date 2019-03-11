package com.example.fredu.goalsetter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Patterns;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegistrationPage extends AppCompatActivity {

    private EditText mUsernameEditText;
    private AutoCompleteTextView mEmailEditText;
    private EditText mPhoneNumEditText;
    private EditText mPasswordEditText;
    private View focusView = null;
    private String email;
    private String password;
    private String username;
    private String phoneNumber;
    AppCompatButton mCreateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);

        mUsernameEditText = findViewById(R.id.username);
        mEmailEditText = findViewById(R.id.email_reg);
        mPhoneNumEditText = findViewById(R.id.phone_num);
        mPasswordEditText = findViewById(R.id.password_reg);
        mCreateButton = findViewById(R.id.create_button);
mCreateButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        registerUser();
    }
});
    }

    public void registerUser(){
        username = mUsernameEditText.getText().toString().trim();
        email = mEmailEditText.getText().toString().trim();
        phoneNumber = mPhoneNumEditText.getText().toString().trim();
        password = mPasswordEditText.getText().toString().trim();

        if(email.isEmpty()){
mEmailEditText.setText("Email is required");
mEmailEditText.requestFocus();
return ;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mEmailEditText.setText("Enter valid email");
            mEmailEditText.requestFocus();
            return;
        }
        if(password.isEmpty()){
            mPasswordEditText.setText("Password is required");
            mPasswordEditText.requestFocus();
            return;
        }
        if(password.length() < 6){
            mPasswordEditText.setText("Password should be at least 6 characters long");
            mPasswordEditText.requestFocus();
            return;
        }
        if(username.isEmpty()){
            mUsernameEditText.setText("A username is required");
            mUsernameEditText.requestFocus();
            return;
        }
        if(phoneNumber.isEmpty()){
            mPhoneNumEditText.setText("A phone number is required");
            mPhoneNumEditText.requestFocus();
            return;
        }
        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getApi()
                .register(username, email, phoneNumber, password);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 201) {
                    try {
                        String s = response.body() != null ? response.body().string() : null;
                        Toast.makeText(RegistrationPage.this, "Registered", Toast.LENGTH_SHORT).show();
                        Toast.makeText(RegistrationPage.this, s, Toast.LENGTH_SHORT).show();
                        mCreateButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(RegistrationPage.this, "clicked", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegistrationPage.this, MainActivity.class);
                                intent.putExtra("EMAIL", email);
                                intent.putExtra("PASSWORD", password);
                                startActivity(intent);
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        String s = response.errorBody() != null ? response.errorBody().string() : null;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(RegistrationPage.this, "Error registering", Toast.LENGTH_SHORT).show();
                Toast.makeText(RegistrationPage.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
