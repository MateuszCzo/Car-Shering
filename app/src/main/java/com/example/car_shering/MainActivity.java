package com.example.car_shering;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 123;

    Button filters;
    RecyclerView recyclerView;
    CarRecViewAdapter adapter;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        filters = findViewById(R.id.filter);
        recyclerView = findViewById(R.id.recyclerViev);

        db = new DBHelper(this);

        filters.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.i("MyApp","open filters");
                Intent intent = new Intent(MainActivity.this, FiltersActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        startActivity(new Intent(MainActivity.this, LoginActivity.class));

        adapter = new CarRecViewAdapter(this);
        setCars();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    //ustawienie menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    //ustawienie listener menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settingsMenu:
                Log.i("MyApp", "settings");
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                return true;
            case R.id.refreshMenu:
                Log.i("MyApp","refresh");
                setCars();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //przechwycenie filtrow
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, requestCode, data);
        switch (requestCode) {
            case REQUEST_CODE:
                if (resultCode == Activity.RESULT_OK) {
                    String typ_nadwizia=data.getStringExtra("typNadwozia");
                    String marka_pojazdu=data.getStringExtra("markaPojazdu");
                    String cena_od=data.getStringExtra("cenaOd");
                    String cena_do=data.getStringExtra("cenaDo");
                    String pojemnosc_od=data.getStringExtra("pojemnoscOd");
                    String pojemnosc_do=data.getStringExtra("pojemnoscDo");
                    String rodzaj_paliwa=data.getStringExtra("rodzajPaliwa");
                    String przebieg_od=data.getStringExtra("przebiegOd");
                    String przebieg_do=data.getStringExtra("przebiegDo");
                    ArrayList<Car> cars = new ArrayList<>();
                    Cursor result = db.getData(typ_nadwizia, marka_pojazdu, cena_od, cena_do, pojemnosc_od, pojemnosc_do, rodzaj_paliwa, przebieg_od, przebieg_do);
                    Log.i("Myapp", "pobieranie danych zakonczone");
                    if (result.getCount()==0) {
                        Log.i("Myapp", "pusto");
                    }
                    else {
                        while(result.moveToNext()) {
                            cars.add(new Car(
                                    result.getInt(6),
                                    result.getString(0),
                                    result.getString(1),
                                    result.getInt(2),
                                    Float.valueOf(result.getString(3)),
                                    result.getString(4),
                                    result.getInt(5)
                            ));
                        }
                    }
                    adapter.setCars(cars);
                }
        }
    }

    public void setCars() {
        //Przykladowe uzycie RecyclerView
        ArrayList<Car> cars = new ArrayList<>();
        String typ_nadwizia=null;
        String marka_pojazdu=null;
        String cena_od=null;
        String cena_do=null;
        String pojemnosc_od=null;
        String pojemnosc_do=null;
        String rodzaj_paliwa=null;
        String przebieg_od=null;
        String przebieg_do=null;
        Cursor result = db.getData(typ_nadwizia, marka_pojazdu, cena_od, cena_do, pojemnosc_od, pojemnosc_do, rodzaj_paliwa, przebieg_od, przebieg_do);
        Log.i("Myapp", "pobieranie danych zakonczone");
        if (result.getCount()==0) {
            Log.i("Myapp", "pusto");
        }
        else {
            while(result.moveToNext()) {
                cars.add(new Car(
                        result.getInt(6),
                        result.getString(0),
                        result.getString(1),
                        result.getInt(2),
                        Float.valueOf(result.getString(3)),
                        result.getString(4),
                        result.getInt(5)
                ));
            }
        }
        adapter.setCars(cars);
    }
}