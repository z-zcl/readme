package com.example.day03_zuoye;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import java.util.ArrayList;

public class ProjectActivity extends AppCompatActivity {

    private Toolbar tool;
    private ViewPager vp;
    private TabLayout tabMode;
    private NavigationView navigation;
    private DrawerLayout dl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        initView();
    }

    private void initView() {
        tool = (Toolbar) findViewById(R.id.tool);
        vp = (ViewPager) findViewById(R.id.vp);
        tabMode = (TabLayout) findViewById(R.id.tabMode);
        navigation = (NavigationView) findViewById(R.id.navigation);
        dl = (DrawerLayout) findViewById(R.id.dl);
        View view = navigation.getHeaderView(0);
        ImageView img = view.findViewById(R.id.head_img);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProjectActivity.this, WebActivity.class);
                startActivity(intent);
            }
        });
        setSupportActionBar(tool);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, dl, tool, R.string.app_name, R.string.app_name);
        dl.addDrawerListener(toggle);
        toggle.syncState();
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new MeFragment());
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), fragments);
        vp.setAdapter(adapter);
        tabMode.setupWithViewPager(vp);
        tabMode.getTabAt(0).setText("首页");
        tabMode.getTabAt(1).setText("我的");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,0,0,"11");
        return super.onCreateOptionsMenu(menu);
    }
}
