package com.example.meenu.student;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by meenu on 21-Aug-16.
 */
public class Viewall extends Activity {

    SQLiteDatabase db;
    Cursor c;
    String temp;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);
        TextView v =(TextView)findViewById(R.id.textView12);
        db=openOrCreateDatabase("Studentdb", SQLiteDatabase.CREATE_IF_NECESSARY,null);
        c= db.rawQuery("SELECT * FROM student",null);
        while(c.moveToNext())
        {
            String s2=c.getString(0);
            String s3=c.getString(1);
            String s4=c.getString(2);
            String s5=c.getString(3);
            String s6=c.getString(4);
            temp=temp+"\n ID:"+s2+"\n Name:"+s3+"\n EMail:"+s4+"\n Mobile:"+s5+"\n Course:"+s6+"\n";

        }
        v.setText(temp);
    }
}