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

    Button findDonorBtn , addReqBtn  , showReq, myreq  ;


    Intent i  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_page);

        getSupportActionBar().hide();

        //init the view

        findDonorBtn = findViewById(R.id.findDonorBtn);
        addReqBtn = findViewById(R.id.addReq_btn);
        showReq = findViewById(R.id.seeReqBtn);
        myreq = findViewById(R.id.myReqBtn);


        //setON Click listeners


        addReqBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                i = new Intent(getApplicationContext() ,AddBloodReq.class );
                startActivity(i);
            }
        });

        findDonorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(getApplicationContext() ,chooseBloodGroup.class );
                startActivity(i);


            }
        });

        showReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = new Intent(getApplicationContext() ,requestedBloodList.class );
                startActivity(i);



            }
        });


        myreq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = new Intent(getApplicationContext() ,myReq.class );
                startActivity(i);



            }
        });







    }
}
