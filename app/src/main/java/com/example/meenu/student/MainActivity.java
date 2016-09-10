package com.example.meenu.student;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends TabActivity {

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TabHost tabhost = (TabHost) findViewById(android.R.id.tabhost);
        TabHost.TabSpec tab1 = tabhost.newTabSpec("First Tab");
        TabHost.TabSpec tab2 = tabhost.newTabSpec("Second Tab");
        TabHost.TabSpec tab3 = tabhost.newTabSpec("Third Tab");


        tab1.setIndicator("",getResources().getDrawable(R.drawable.ic_add_circle_outline_black_24dp));
        tab1.setContent(new Intent(this,Tab1.class));

        tab2.setIndicator("",getResources().getDrawable(R.drawable.ic_visibility_black_24dp));
        tab2.setContent(new Intent(this,Tab2.class));

        tab3.setIndicator("",getResources().getDrawable(R.drawable.ic_search_black_24dp));
        tab3.setContent(new Intent(this,Search.class));

        tabhost.addTab(tab1);
        tabhost.addTab(tab2);
        tabhost.addTab(tab3);



    }



}











