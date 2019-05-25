package com.metacodersbd.myapplication.ChatSystemUniversal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.metacodersbd.myapplication.R;

public class chatRoom extends AppCompatActivity {

    TabLayout tabLayout  ;
    AppBarLayout appBarLayout ;
    ViewPager viewPager ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        getSupportActionBar().hide();

        tabLayout= (TabLayout)findViewById(R.id.tabLayout) ;
      //  appBarLayout = findViewById(R.id.appbarId) ;
        viewPager =findViewById(R.id.viewPager) ;




    }
}
