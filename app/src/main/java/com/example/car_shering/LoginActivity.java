package com.example.car_shering;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.lang.Math;

public class LoginActivity extends AppCompatActivity {

    Button login;
    Button forgotPass;
    Button register;
    EditText email;
    EditText password;
    DBHelper db;

    private static final int REQUEST_CODE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Log.i("MyApp","loginActivity");

        //finding buttons
        login = findViewById(R.id.login);
        forgotPass = findViewById(R.id.forgotPass);
        register = findViewById(R.id.register);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        db = new DBHelper(this);

        //creating on click listeners
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int id = db.checkUser(email.getText().toString(),password.getText().toString());
                Log.i("MyApp","user id:"+id);
                if (id != -1) {
                    Log.i("MyApp","login accepted");

                    //zapisanie id uzytkownika do shared preferences
                    SharedPreferences sp = getSharedPreferences("UserData", MODE_PRIVATE);
                    SharedPreferences.Editor editor =sp.edit();
                    Log.i("MyApp", "user id login:"+id);
                    editor.putInt("user_id", id);
                    editor.apply();

                    //powrot do mainAvitivty
                    finish();
                }
                else {
                    Log.i("MyApp","login not accepted");
                    Toast.makeText(LoginActivity.this, "zle dane", Toast.LENGTH_SHORT).show();
                }
            }
        });
        forgotPass.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int userId = db.checkUser(email.getText().toString());
                if (userId != -1) {
                    String newPassword = Double.toString(Math.round(Math.random()*10000));

                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);

                    SendEmail mailing = new SendEmail();
                    mailing.sendEmail(email.getText().toString(), "New Password!", newPassword);

                    db.updatePassword(userId, newPassword);
                    Log.i("MyApp","send email");
                }
                else {
                    Toast.makeText(LoginActivity.this, "Wrong email!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //otwieranie registerActivity
                Log.i("MyApp","open registerActivity");
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, requestCode, data);
        switch (requestCode) {
            case REQUEST_CODE:
                if (resultCode == Activity.RESULT_OK) {
                    if (data == null) return;
                    boolean regSuc = data.getBooleanExtra("RegistrationSuccessful", false);
                    if (regSuc) {
                        Log.i("MyApp","Registration Successful");
                        finish();
                    }
                    else {
                        Log.i("MyApp","Return To Login");
                    }
                }
        }
    }
}