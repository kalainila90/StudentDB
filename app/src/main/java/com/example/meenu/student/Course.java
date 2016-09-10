package com.example.meenu.student;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by meenu on 20-Aug-16.
 */
public class Course extends AppCompatActivity{
    EditText co,d;
    Button add;
    TextView t;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course);


        co= (EditText) findViewById(R.id.editText4);
        d= (EditText) findViewById(R.id.editText5);
        t=(TextView) findViewById(R.id.textView10);
        add= (Button) findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean works=true;
                try {
                    DbConnect dc = new DbConnect(Course.this);
                    dc.open();
                    String course = co.getText().toString();
                    String duration = d.getText().toString();
                    dc.createcourse(course,duration);

                    SQLiteDatabase db=openOrCreateDatabase("Studentdb", SQLiteDatabase.CREATE_IF_NECESSARY,null);
                    Cursor c= db.rawQuery("SELECT * FROM course",null);


                    c.moveToFirst();

                    String temp="";
                    while(! c.isAfterLast())
                    {
                        String s2=c.getString(0);
                        String s3=c.getString(1);

                        temp=temp+"\n Course:"+s2+"\t Duration:"+s3;
                        c.moveToNext();
                    }
                    t.setText(temp);



                    Toast.makeText(Course.this,"Added...",Toast.LENGTH_SHORT).show();
                }
                catch(Exception e){
                    works=false;
                    Toast.makeText(Course.this,"Error...",Toast.LENGTH_SHORT).show();
                }


            }
        });




    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.rightmenu, menu);
        return true;
    }
}
