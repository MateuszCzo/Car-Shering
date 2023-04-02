package com.example.car_shering;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table Userdetails(name TEXT primary key, contact TEXT, dob TEXT)");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists Userdetails");
    }

    public int checkUser(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Cursor cursor = db.rawQuery("Select Id_uzytkownika from Userdetails where Email=? and Hasło=?", new String[]{email, password});
        Log.i("MyApp", "count number check:" + cursor.getCount());
        if (cursor.getCount() > 0) {
            cursor.moveToNext();
            return cursor.getInt(0);
        }
        return -1;
    }

    public int insertUser(String email, String password) {
        //checking if email is already used
        if (checkUser(email) != -1) return -1;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Email", email);
        contentValues.put("Hasło", password);
        long result = db.insert("Userdetails", null, contentValues);
        Log.i("MyApp", "result insert:" + result);
        if (result != -1) {
            //retur user id
            return checkUser(email, password);
        }
        return -1;
    }

    public int checkUser(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Cursor cursor = db.rawQuery("Select Id_uzytkownika from Userdetails where Email=?", new String[]{email});
        Log.i("MyApp", "count number check:" + cursor.getCount());
        if (cursor.getCount() > 0) {
            cursor.moveToNext();
            return cursor.getInt(0);
        }
        return -1;
    }

    public Boolean updateEmail(int user_id, String newEmail) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Email", newEmail);
        Cursor cursor = db.rawQuery("Select * from Userdetails where Id_uzytkownika = ?", new String[]{String.valueOf(user_id)});//toInt() cast(example as integer)
        if (cursor.getCount() > 0) {
            long result = db.update("Userdetails", contentValues, "Id_uzytkownika=?", new String[]{String.valueOf(user_id)});
            if (result != -1) return true;
        }
        return false;
    }

    public Boolean updatePassword(int user_id, String newPassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Hasło", newPassword);
        Cursor cursor = db.rawQuery("Select * from Userdetails where Id_uzytkownika = ?", new String[]{String.valueOf(user_id)});
        if (cursor.getCount() > 0) {
            long result = db.update("Userdetails", contentValues, "Id_uzytkownika=?", new String[]{String.valueOf(user_id)});
            if (result != -1) return true;
        }
        return false;
    }

    public Cursor getData(String typ_nadwizia, String marka_pojazdy, String cena_od, String cena_do, String pojemnosc_od, String pojemnosc_do, String rodzaj_paliwa, String przebieg_od, String przebied_do) {
        SQLiteDatabase db = this.getWritableDatabase();
        String zapytanie = "Select * from Samochody";
        String[] atrybuty = new String[9];
        int index=0;
        if(typ_nadwizia!=null||marka_pojazdy!=null||cena_od!=null||cena_do!=null||pojemnosc_od!=null||pojemnosc_do!=null||rodzaj_paliwa!=null||przebieg_od!=null||przebied_do!=null) {
            zapytanie += " where";
        }
        if (typ_nadwizia!=null) {
            zapytanie += " Typ_nadwozia=? and";
            atrybuty[index++]=typ_nadwizia;
        }
        if (marka_pojazdy!=null) {
            zapytanie += " Marka_pojazdu=? and";
            atrybuty[index++]=marka_pojazdy;
        }
        if (cena_od!=null) {
            zapytanie += " Cena>=? and";
            atrybuty[index++]=cena_od;
        }
        if (cena_do!=null) {
            zapytanie += " Cena<=? and";
            atrybuty[index++]=cena_do;
        }
        if (pojemnosc_od!=null) {
            zapytanie += " Pojemnosc<=? and";
            atrybuty[index++]=pojemnosc_od;
        }
        if (pojemnosc_do!=null) {
            zapytanie += " Pojemnosc<=? and";
            atrybuty[index++]=pojemnosc_do;
        }
        if (rodzaj_paliwa!=null) {
            zapytanie += " Rodzaj_paliwa=? and";
            atrybuty[index++]=rodzaj_paliwa;
        }
        if (przebieg_od!=null) {
            zapytanie += " Przebieg>=? and";
            atrybuty[index++]=przebieg_od;
        }
        if (przebied_do!=null) {
            zapytanie += " Przebieg<=? and";
            atrybuty[index++]=przebied_do;
        }
        String[] new_atrybuty = new String[index];
        for (int i=0;i<index;i++) {
            new_atrybuty[i]=atrybuty[i];
            Log.i("MyApp","attrig "+i+" : "+new_atrybuty[i]);
        }
        if (index!=0) zapytanie=zapytanie.substring(0, zapytanie.length()-4);
        Log.i("MyApp",zapytanie);
        Cursor cursor = db.rawQuery(zapytanie, new_atrybuty);
        return cursor;
    }

    public boolean insertInfo(int user_id, int car_id, int phoneNumber, String text) {
        Log.i("MyApp", "succesful");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Id_samochodu", car_id);
        contentValues.put("Nr_telefonu", phoneNumber);
        contentValues.put("Tekst", text);
        contentValues.put("Id_uzytkownika", user_id);
        long result = db.insert("Kontakt", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
}
