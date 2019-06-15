package com.metacodersbd.myapplication.BloodActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.metacodersbd.myapplication.R;

public class BloodPage extends AppCompatActivity {

    Button findDonorBtn ;


    Intent i  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_page);

        getSupportActionBar().hide();

        //init the view

        findDonorBtn = findViewById(R.id.findDonorBtn);

        //setON Click listeners


        findDonorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(getApplicationContext() ,chooseBloodGroup.class );
                startActivity(i);


            }
        });










    }
}
