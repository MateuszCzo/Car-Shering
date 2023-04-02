package com.example.car_shering;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.DataOutputStream;

public class RegisterActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    EditText repPassword;
    Button register;
    Button login;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.registerEmail);
        password = findViewById(R.id.registerPass);
        repPassword = findViewById(R.id.registerRepPass);
        register = findViewById(R.id.registerRegister);
        login = findViewById(R.id.registerLogin);

        db = new DBHelper(this);

        //creating on click listeners
        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //laczenie sie z baza danych i tworzenie nowego konta
                if (password.getText().toString().equals(repPassword.getText().toString())) {
                    int id = db.insertUser(email.getText().toString(),password.getText().toString());
                    if (id != -1) {
                        Log.i("MyApp","register accepted");

                        //zapisanie id uazytkownika do shared preferences
                        SharedPreferences sp = getSharedPreferences("UserData", MODE_PRIVATE);
                        SharedPreferences.Editor editor =sp.edit();
                        editor.putInt("user_id", id);
                        editor.apply();

                        //powrot do mainAvtivity
                        Intent intent = new Intent();
                        intent.putExtra("RegistrationSuccessful", true);
                        setResult(Activity.RESULT_OK, intent);
                        finish();
                    }
                    else {
                        Log.i("MyApp","register not accepted");
                        Toast.makeText(RegisterActivity.this, "register not accepted", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Log.i("MyApp","register not accepted");
                    Toast.makeText(RegisterActivity.this, "register not accepted", Toast.LENGTH_SHORT).show();
                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //powrot do logninActivity
                Intent intent = new Intent();
                intent.putExtra("RegistrationSuccessful", false);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}