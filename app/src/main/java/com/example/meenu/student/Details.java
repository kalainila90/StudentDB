package com.example.meenu.student;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by meenu on 21-Aug-16.
 */
public class Details extends AppCompatActivity {

    SQLiteDatabase db;
    EditText n, e, m;
    Cursor c;
    String temp;
    int id;
    TextView v;
    int position;
    ImageButton edit,delete;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);
        v=(TextView)findViewById(R.id.textView12);
        //delete=(ImageButton) findViewById(R.id.imageButton);


        Intent i = getIntent();

           temp = i.getExtras().getString("Details");
           v.setText(temp);


    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu1, menu);
        return true;
    }
    }

