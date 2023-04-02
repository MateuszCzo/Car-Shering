package com.example.car_shering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    EditText newPass;
    EditText newEmail;
    Button changeBtn;
    Button returnBtn;
    int user_id;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        newPass = findViewById(R.id.newPass);
        newEmail = findViewById(R.id.newEmail);
        changeBtn = findViewById(R.id.changeBtn);
        returnBtn = findViewById(R.id.returnBtn);

        db = new DBHelper(this);

        SharedPreferences sp = getApplicationContext().getSharedPreferences("UserData", Context.MODE_PRIVATE);
        user_id = sp.getInt("user_id", -1);

        Log.i("MyApp", "user_id:"+user_id);

        changeBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //zmiana hasla lub email w bazie danych (po id)
                if (!newPass.getText().toString().equals("")) {
                    if (db.updatePassword(user_id, newPass.getText().toString())) {
                        Toast.makeText(SettingsActivity.this, "success", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(SettingsActivity.this, "error", Toast.LENGTH_SHORT).show();
                    }
                }
                if (!newEmail.getText().toString().equals("")) {
                    //sprawdzenie czy email juz zajety
                    if (db.checkUser(newEmail.getText().toString()) == -1) {
                        if (db.updateEmail(user_id, newEmail.getText().toString())) {
                            Toast.makeText(SettingsActivity.this, "success", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(SettingsActivity.this, "error", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        returnBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.i("MyApp", "return to mainActivity");
                finish();
            }
        });
    }
}