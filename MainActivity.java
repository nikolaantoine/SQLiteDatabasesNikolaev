package com.example.sqlitedatabases;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("Users", Context.MODE_PRIVATE, null);

            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS users (name VARCHAR, age INT(3))");
            sqLiteDatabase.execSQL("INSERT INTO users (name, age) VALUES ('Rob', 34)");
            sqLiteDatabase.execSQL("INSERT INTO users (name, age) VALUES ('Tommy', 18)");

            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM users", null);

            int nameIndex = cursor.getColumnIndex("name");
            int ageIndex = cursor.getColumnIndex("age");

            cursor.moveToFirst();

            while (true) {
                Log.i("name", cursor.getString(nameIndex));
                Log.i("age", Integer.toString(cursor.getInt(ageIndex)));

                cursor.moveToNext();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}