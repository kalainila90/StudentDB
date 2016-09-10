package com.example.meenu.student;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by meenu on 24-Aug-16.
 */
public class Search extends AppCompatActivity {
    EditText s;
    Button search1;
    String temp;
    TextView t;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        s=(EditText)findViewById(R.id.editText6);
        search1 = (Button)findViewById(R.id.search1);
        t=(TextView)findViewById(R.id.textView8);
        search1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = s.getText().toString();



                    if (!name.equals("")) {
                        SQLiteDatabase db = openOrCreateDatabase("Studentdb", SQLiteDatabase.CREATE_IF_NECESSARY, null);
                        Cursor c = db.rawQuery("SELECT * FROM student WHERE Name='" + name + "'", null);
                        if (c.moveToNext()) {
                            String s2 = c.getString(0);
                            String s3 = c.getString(1);
                            String s4 = c.getString(2);
                            String s5 = c.getString(3);
                            String s6 = c.getString(4);
                            temp = "\n ID:" + s2 + "\n Name:" + s3 + "\n EMail:" + s4 + "\n Mobile:" + s5 + "\n Course:" + s6 + "\n";
                            t.setText(temp);

                        }

                        else {
                            Toast.makeText(Search.this, "Name doesn't exists...", Toast.LENGTH_SHORT).show();
                        }



                    } else
                    {
                        Toast.makeText(Search.this, "Enter name to search...", Toast.LENGTH_SHORT).show();
                    }





            }
        });


        }
    }

