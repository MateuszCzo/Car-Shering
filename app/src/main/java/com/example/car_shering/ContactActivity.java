package com.example.car_shering;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ContactActivity extends AppCompatActivity {

    int user_id;
    int car_id;
    DBHelper db;
    Button button;
    EditText editTextPhone;
    EditText edit_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        button = findViewById(R.id.button);
        editTextPhone = findViewById(R.id.editTextPhone);
        edit_text = findViewById(R.id.edit_text);

        db = new DBHelper(this);

        SharedPreferences sp = getSharedPreferences("UserData", MODE_PRIVATE);
        user_id = sp.getInt("user_id", -1);

        Intent i = getIntent();
        car_id = i.getIntExtra("car_id", -1);

        Log.i("MyApp", "car id: "+car_id);
        Log.i("MyApp", "user_id "+user_id);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (car_id == -1 || user_id == -1 || editTextPhone.getText().toString().equals("")) return;
                if (db.insertInfo(user_id, car_id, Integer.parseInt(editTextPhone.getText().toString()), edit_text.getText().toString())) {
                    Toast.makeText(ContactActivity.this, "ok", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(ContactActivity.this, "not ok", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}