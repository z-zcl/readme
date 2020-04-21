package com.example.day03_zuoye;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    private ViewPager main_vp;
    private Button home3_but;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        main_vp = (ViewPager) findViewById(R.id.main_vp);
        ArrayList<View> views = new ArrayList<>();
        View inflate = LayoutInflater.from(this).inflate(R.layout.home, null);
        View inflate2 = LayoutInflater.from(this).inflate(R.layout.home2, null);
        View inflate3 = LayoutInflater.from(this).inflate(R.layout.home3, null);
        views.add(inflate);
        views.add(inflate2);
        views.add(inflate3);
        VpAdapter adapter = new VpAdapter(views);
        main_vp.setAdapter(adapter);

        home3_but = inflate3.findViewById(R.id.home3_but);
        home3_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ProjectActivity.class);
                startActivity(intent);
            }
        });
    }


}
