package com.metacodersbd.myapplication.ChatSystemUniversal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.metacodersbd.myapplication.R;

public class chatRoom extends AppCompatActivity {

    TabLayout tabLayout  ;
    AppBarLayout appBarLayout ;
    ViewPager viewPager ;
    String name , pplink , batch , dpart  ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

       // getSupportActionBar().hide();
        Intent o = getIntent();
        name = o.getStringExtra("NAME");
        pplink = o.getStringExtra("IMAGE");
        batch = o.getStringExtra("BATCH");
        dpart = o.getStringExtra("DPARTMENT");


        tabLayout= (TabLayout)findViewById(R.id.tabLayout) ;
      //  appBarLayout = findViewById(R.id.appbarId) ;
        viewPager =findViewById(R.id.viewPager) ;

         viewPagerAdapter adapter = new viewPagerAdapter(getSupportFragmentManager());

         adapter.AddFragment(new allVarcityChat() , "All Chat");
         adapter.AddFragment(new allDeptChat() , "Dept Chat");
         adapter.AddFragment(new groupChat() , "Group Chat");
         adapter.AddFragment(new teacherChat() , "Teacher");

         viewPager.setAdapter(adapter) ;
         tabLayout.setupWithViewPager(viewPager) ;

    }

    public String getMyName() {
        return name;
    }


    public  String getMyimage(){



        return  pplink;
    }

    public String getMyBatch(){

        return  batch ;

    }
    public  String getMyDpart(){

        return  dpart ;
    }


}
