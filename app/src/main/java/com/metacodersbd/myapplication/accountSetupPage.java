package com.metacodersbd.myapplication;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


public class accountSetupPage extends AppCompatActivity {
    CheckBox   checkBg;
    EditText Bg ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setup_page);
        // Reomoving Toolbar
        ActionBar actionBar = getSupportActionBar() ;
        actionBar.hide();




        //declareing by id
      checkBg = (CheckBox)findViewById(R.id.checkbox1) ;
      Bg =(EditText)findViewById(R.id.BG) ;


        // setting on clilck listener
        Bg.setVisibility(View.GONE);
        checkBg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBg.isChecked()){

                   Bg.setVisibility(View.VISIBLE);

                }
                else
                    Bg.setVisibility(View.GONE);



            }
        });


    }
}
