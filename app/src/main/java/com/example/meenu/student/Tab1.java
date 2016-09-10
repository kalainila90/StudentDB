package com.example.meenu.student;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by meenu on 21-Aug-16.
 */
public class Tab1 extends AppCompatActivity {
    EditText n, e, m;
    Button save;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab1);


        n = (EditText) findViewById(R.id.editText);
        e = (EditText) findViewById(R.id.editText2);
        m = (EditText) findViewById(R.id.editText3);
        save = (Button) findViewById(R.id.save);

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);


        save.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {

                String name = n.getText().toString();
                String email = e.getText().toString();
                String mobile = m.getText().toString();
                String course=spinner.getSelectedItem().toString();


                if((!name.equals(""))&&(!email.equals(""))&&(!mobile.equals(""))&&(course != null))
{
                    boolean works = true;
                    try {
                        DbConnect dc = new DbConnect(Tab1.this);
                        dc.open();


                        dc.create(name, email, mobile,course);
                        Toast.makeText(Tab1.this, "Inserted...", Toast.LENGTH_SHORT).show();
                        dc.close();

                    } catch (Exception e) {
                        works = false;

                        Toast.makeText(Tab1.this, "Error...", Toast.LENGTH_SHORT).show();
                    }
                }else{
    Toast.makeText(Tab1.this,"Enter the required values...",Toast.LENGTH_SHORT).show();
}

            }
        });



        try {
            SQLiteDatabase db = openOrCreateDatabase("Studentdb", SQLiteDatabase.CREATE_IF_NECESSARY, null);
            Cursor c = db.rawQuery("SELECT * FROM course", null);
            ArrayList<String> list = new ArrayList<String>();

            list.add("--select--");
            while (c.moveToNext()) {
                String course = c.getString(0);
                list.add(course);

            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinner.setAdapter(adapter);
        }catch (Exception e){

        }




    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.rightmenu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.item1:
                startActivity(new Intent(Tab1.this, Viewall.class));
                break;
            case R.id.item2:
                startActivity(new Intent(Tab1.this, Course.class));
                break;


        }
        return true;

    }
}
