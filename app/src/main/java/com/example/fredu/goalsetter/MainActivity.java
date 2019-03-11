package com.example.fredu.goalsetter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.util.SortedList;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageButton;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";

    public static final String BASE_URL = "https://goalsetapp.herokuapp.com/api/register/";
    private AutoCompleteTextView mEmailEditText;
    private EditText mPasswordEditText;
    private String email;
    private String password;
    AppCompatButton mLoginButton;
    AppCompatButton mCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
mEmailEditText = findViewById(R.id.email_log);
mPasswordEditText = findViewById(R.id.password_log);
mCreateAccount = findViewById(R.id.create_account);
mCreateAccount.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, RegistrationPage.class);
        startActivity(intent);

    }
});
Intent intent = getIntent();
email = intent.getStringExtra("EMAIL");
password = intent.getStringExtra("PASSWORD");
if(mEmailEditText.getText().toString().equals(email) && mPasswordEditText.getText().toString().equals(password)){
    Toast.makeText(this, "Matches", Toast.LENGTH_SHORT).show();

}else{
    Toast.makeText(this, "Can't log in presently", Toast.LENGTH_SHORT).show();
}

    }


}


