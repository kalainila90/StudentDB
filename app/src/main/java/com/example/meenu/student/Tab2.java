package com.example.meenu.student;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by meenu on 21-Aug-16.
 */
public class Tab2 extends AppCompatActivity {
    SQLiteDatabase db;
    ArrayList<String> list = new ArrayList<String>();

 public void onCreate(Bundle savedInstanceState) {

     super.onCreate(savedInstanceState);
     setContentView(R.layout.tab2);
     getParent().getActionBar();

     Cursor c;

     try
     {
         db=openOrCreateDatabase("Studentdb", SQLiteDatabase.CREATE_IF_NECESSARY,null);
          c= db.rawQuery("SELECT * FROM student",null);


         final ListView lv=(ListView) findViewById(R.id.listView);
         ArrayList<String> list = new ArrayList<String>();


          //c.moveToFirst();
         //String temp="";
         while(c.moveToNext())
         {
             String s2=c.getString(0);
             String s3=c.getString(1);
             //String s3=c.getString(1);
             //String s4=c.getString(2);
               list.add(s2+"\t\t"+s3);

             //temp=temp+"\n Name:"+s2+"\t Email:"+s3+"\t Mobile:"+s4;
             //c.moveToNext();
         }
         //v.setText(temp);
         final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1
                 ,list);
         /*{

             @Override
             public View getView(int position, View convertView, ViewGroup parent){

                 View view = super.getView(position, convertView, parent);

                 TextView t = (TextView) view.findViewById(android.R.id.text1);

                 t.setTextColor(Color.parseColor("#ffffff"));
                 t.setTextSize(22);
                 return view;
             }

         };*/

         lv.setAdapter(adapter);
         registerForContextMenu(lv);

         lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 view.setSelected(true);
                 DbConnect dc = new DbConnect(Tab2.this);
                 dc.open();

                 Intent i = new Intent(Tab2.this,Details.class);
                 i.putExtra("Details",dc.getAllData(position));

                           startActivity(i);
                 dc.close();

             }
         });
     }
     catch(Exception e)
     {

     }


 }

    public void onCreateContextMenu(ContextMenu menu, View v,ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu1, menu);
        menu.setHeaderTitle("Action");
    }

    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item3:

                break;

            case R.id.item4:
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                Cursor c= db.rawQuery("SELECT * FROM student",null);
                c.moveToPosition(info.position);
                String id = c.getString(c.getColumnIndex("ID"));
                DbConnect dc = new DbConnect(this);
                dc.open();
                dc.delete(Integer.parseInt(id));

              // list.delete(info.position);
               // adapter.notifyDataSetChanged();

                Toast.makeText(this,"Deleted...",Toast.LENGTH_SHORT).show();
                break;

        }
           return true;
        }


    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu1, menu);

        return true;
    }


}
