package com.example.car_shering;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class FiltersActivity extends AppCompatActivity {

    Button filter;
    Spinner typNadwozia;
    Spinner markaPojazdu;
    EditText cenaOd;
    EditText cenaDo;
    EditText pojemnoscOd;
    EditText pojemnoscDo;
    Spinner rodzajPaliwa;
    EditText przebiegOd;
    EditText przebiegDo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filters);

        filter = findViewById(R.id.filter);
        typNadwozia = findViewById(R.id.typNadwozia);
        markaPojazdu = findViewById(R.id.markaPojazdu);
        cenaOd = findViewById(R.id.cenaOd);
        cenaDo = findViewById(R.id.cenaDo);
        pojemnoscOd = findViewById(R.id.pojemnoscOd);
        pojemnoscDo = findViewById(R.id.pojemnoscDo);
        rodzajPaliwa = findViewById(R.id.rodzajPaliwa);
        przebiegOd = findViewById(R.id.przebiegOd);
        przebiegDo = findViewById(R.id.przebiegDo);

        //po nacisnieciu przycisku wraca do mainActivity i przesyla opcje filtrowania
        filter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.i("MyApp", "filter");
                Intent intent = new Intent();
                intent.putExtra("typNadwozia", typNadwozia.getItemAtPosition(typNadwozia.getSelectedItemPosition()).toString());
                intent.putExtra("markaPojazdu", markaPojazdu.getItemAtPosition(markaPojazdu.getSelectedItemPosition()).toString());
                intent.putExtra("cenaOd", cenaOd.getText());
                intent.putExtra("cenaDo", cenaDo.getText());
                intent.putExtra("pojemnoscOd", pojemnoscOd.getText());
                intent.putExtra("pojemnoscDo", pojemnoscDo.getText());
                intent.putExtra("rodzajPaliwa", rodzajPaliwa.getItemAtPosition(rodzajPaliwa.getSelectedItemPosition()).toString());
                intent.putExtra("przebiegOd", przebiegOd.getText());
                intent.putExtra("przebiegDo", przebiegDo.getText());
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}