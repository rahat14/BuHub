package com.metacodersbd.myapplication.BloodActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.metacodersbd.myapplication.R;

public class chooseBloodGroup extends AppCompatActivity {

    Button Apos , Amin , Bpos , Bmin , Opos , Omin , ABpos , ABmin ;
    String bldGroup = "bloodGroup" ;
    Button submitBtn , backBtn ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_blood_group);
        getSupportActionBar().hide();


        Apos = findViewById(R.id.bldGroupIdApos);
        Amin = findViewById(R.id.bldGroupIdAmin);
        Bpos = findViewById(R.id.bldGroupIdBpos);
         Bmin = findViewById(R.id.bldGroupIdBmin);
        Omin = findViewById(R.id.bldGroupIdOmin);
        Opos = findViewById(R.id.bldGroupIdOpos);
        ABmin = findViewById(R.id.bldGroupIdABmin);
        ABpos = findViewById(R.id.bldGroupIdABpos);
        submitBtn = findViewById(R.id.submitBtn);
        backBtn =findViewById(R.id.backBtn);


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(getApplicationContext() ,allDonorList.class );
                i.putExtra("BG", bldGroup);
                startActivity(i);


            }
        });



        Apos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bldGroup = "A+";

                //set the clicked button red ;

                Apos.setBackground(getDrawable(R.drawable.trans_red));
                Apos.setTextColor(Color.parseColor("#ffffff"));

                //set all other button color  default

                Amin.setBackground(getDrawable(R.drawable.trns_back));
                Bpos.setBackground(getDrawable(R.drawable.trns_back));
                Bmin.setBackground(getDrawable(R.drawable.trns_back));
                Opos.setBackground(getDrawable(R.drawable.trns_back));
                Omin.setBackground(getDrawable(R.drawable.trns_back));
                ABmin.setBackground(getDrawable(R.drawable.trns_back));
                ABpos.setBackground(getDrawable(R.drawable.trns_back));



                Amin.setTextColor(Color.parseColor("#D0CECE"));
                Bpos.setTextColor(Color.parseColor("#D0CECE"));
                Bmin.setTextColor(Color.parseColor("#D0CECE"));
                Opos.setTextColor(Color.parseColor("#D0CECE"));
                Omin.setTextColor(Color.parseColor("#D0CECE"));
                ABmin.setTextColor(Color.parseColor("#D0CECE"));
                ABpos.setTextColor(Color.parseColor("#D0CECE"));


            }
        });

        Amin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bldGroup = "A-";

                //set the clicked button red ;

                Amin.setBackground(getDrawable(R.drawable.trans_red));
                Amin.setTextColor(Color.parseColor("#ffffff"));

                //set all other button color  default

                Apos.setBackground(getDrawable(R.drawable.trns_back));
                Bpos.setBackground(getDrawable(R.drawable.trns_back));
                Bmin.setBackground(getDrawable(R.drawable.trns_back));
                Opos.setBackground(getDrawable(R.drawable.trns_back));
                Omin.setBackground(getDrawable(R.drawable.trns_back));
                ABmin.setBackground(getDrawable(R.drawable.trns_back));
                ABpos.setBackground(getDrawable(R.drawable.trns_back));


                Apos.setTextColor(Color.parseColor("#D0CECE"));
                Bpos.setTextColor(Color.parseColor("#D0CECE"));
                Bmin.setTextColor(Color.parseColor("#D0CECE"));
                Opos.setTextColor(Color.parseColor("#D0CECE"));
                Omin.setTextColor(Color.parseColor("#D0CECE"));
                ABmin.setTextColor(Color.parseColor("#D0CECE"));
                ABpos.setTextColor(Color.parseColor("#D0CECE"));


            }
        });
        Bpos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bldGroup = "B+";

                //set the clicked button red ;

                Bpos.setBackground(getDrawable(R.drawable.trans_red));
                Bpos.setTextColor(Color.parseColor("#ffffff"));

                //set all other button color  default

                Amin.setBackground(getDrawable(R.drawable.trns_back));
                Apos.setBackground(getDrawable(R.drawable.trns_back));
                Bmin.setBackground(getDrawable(R.drawable.trns_back));
                Opos.setBackground(getDrawable(R.drawable.trns_back));
                Omin.setBackground(getDrawable(R.drawable.trns_back));
                ABmin.setBackground(getDrawable(R.drawable.trns_back));
                ABpos.setBackground(getDrawable(R.drawable.trns_back));



                Amin.setTextColor(Color.parseColor("#D0CECE"));
                Apos.setTextColor(Color.parseColor("#D0CECE"));
                Bmin.setTextColor(Color.parseColor("#D0CECE"));
                Opos.setTextColor(Color.parseColor("#D0CECE"));
                Omin.setTextColor(Color.parseColor("#D0CECE"));
                ABmin.setTextColor(Color.parseColor("#D0CECE"));
                ABpos.setTextColor(Color.parseColor("#D0CECE"));


            }
        });
        Bmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bldGroup = "B-";
                //set the clicked button red ;

                Bmin.setBackground(getDrawable(R.drawable.trans_red));
                Bmin.setTextColor(Color.parseColor("#ffffff"));

                //set all other button color  default

                Amin.setBackground(getDrawable(R.drawable.trns_back));
                Bpos.setBackground(getDrawable(R.drawable.trns_back));
                Apos.setBackground(getDrawable(R.drawable.trns_back));
                Opos.setBackground(getDrawable(R.drawable.trns_back));
                Omin.setBackground(getDrawable(R.drawable.trns_back));
                ABmin.setBackground(getDrawable(R.drawable.trns_back));
                ABpos.setBackground(getDrawable(R.drawable.trns_back));

                Amin.setTextColor(Color.parseColor("#D0CECE"));
                Bpos.setTextColor(Color.parseColor("#D0CECE"));
                Apos.setTextColor(Color.parseColor("#D0CECE"));
                Opos.setTextColor(Color.parseColor("#D0CECE"));
                Omin.setTextColor(Color.parseColor("#D0CECE"));
                ABmin.setTextColor(Color.parseColor("#D0CECE"));
                ABpos.setTextColor(Color.parseColor("#D0CECE"));

            }
        });
        Opos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bldGroup = "O+";

                //set the clicked button red ;

                Opos.setBackground(getDrawable(R.drawable.trans_red));
                Opos.setTextColor(Color.parseColor("#ffffff"));

                //set all other button color  default

                Amin.setBackground(getDrawable(R.drawable.trns_back));
                Bpos.setBackground(getDrawable(R.drawable.trns_back));
                Bmin.setBackground(getDrawable(R.drawable.trns_back));
                Apos.setBackground(getDrawable(R.drawable.trns_back));
                Omin.setBackground(getDrawable(R.drawable.trns_back));
                ABmin.setBackground(getDrawable(R.drawable.trns_back));
                ABpos.setBackground(getDrawable(R.drawable.trns_back));

                Amin.setTextColor(Color.parseColor("#D0CECE"));
                Bpos.setTextColor(Color.parseColor("#D0CECE"));
                Bmin.setTextColor(Color.parseColor("#D0CECE"));
                Apos.setTextColor(Color.parseColor("#D0CECE"));
                Omin.setTextColor(Color.parseColor("#D0CECE"));
                ABmin.setTextColor(Color.parseColor("#D0CECE"));
                ABpos.setTextColor(Color.parseColor("#D0CECE"));


            }
        });
        Omin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bldGroup = "O-";

                //set the clicked button red ;

                Omin.setBackground(getDrawable(R.drawable.trans_red));
                Omin.setTextColor(Color.parseColor("#ffffff"));

                //set all other button color  default

                Amin.setBackground(getDrawable(R.drawable.trns_back));
                Bpos.setBackground(getDrawable(R.drawable.trns_back));
                Bmin.setBackground(getDrawable(R.drawable.trns_back));
                Opos.setBackground(getDrawable(R.drawable.trns_back));
                ABpos.setBackground(getDrawable(R.drawable.trns_back));
                ABmin.setBackground(getDrawable(R.drawable.trns_back));
                Apos.setBackground(getDrawable(R.drawable.trns_back));

                //set All color to backcolor

                Amin.setTextColor(Color.parseColor("#D0CECE"));
                Bpos.setTextColor(Color.parseColor("#D0CECE"));
                Bmin.setTextColor(Color.parseColor("#D0CECE"));
                Opos.setTextColor(Color.parseColor("#D0CECE"));
                ABpos.setTextColor(Color.parseColor("#D0CECE"));
                ABmin.setTextColor(Color.parseColor("#D0CECE"));
                Apos.setTextColor(Color.parseColor("#D0CECE"));

            }
        });

        ABpos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bldGroup = "AB+";
                //set the clicked button red ;

                ABpos.setBackground(getDrawable(R.drawable.trans_red));
                ABpos.setTextColor(Color.parseColor("#ffffff"));

                //set all other button color  default

                Amin.setBackground(getDrawable(R.drawable.trns_back));
                Bpos.setBackground(getDrawable(R.drawable.trns_back));
                Bmin.setBackground(getDrawable(R.drawable.trns_back));
                Opos.setBackground(getDrawable(R.drawable.trns_back));
                Omin.setBackground(getDrawable(R.drawable.trns_back));
                ABmin.setBackground(getDrawable(R.drawable.trns_back));
                Apos.setBackground(getDrawable(R.drawable.trns_back));


                Amin.setTextColor(Color.parseColor("#D0CECE"));
                Bpos.setTextColor(Color.parseColor("#D0CECE"));
                Bmin.setTextColor(Color.parseColor("#D0CECE"));
                Opos.setTextColor(Color.parseColor("#D0CECE"));
                Omin.setTextColor(Color.parseColor("#D0CECE"));
                ABmin.setTextColor(Color.parseColor("#D0CECE"));
                Apos.setTextColor(Color.parseColor("#D0CECE"));

            }
        });
        ABmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bldGroup = "AB-";
                //set the clicked button red ;

                ABmin.setBackground(getDrawable(R.drawable.trans_red));
                ABmin.setTextColor(Color.parseColor("#ffffff"));

                //set all other button color  default

                Amin.setBackground(getDrawable(R.drawable.trns_back));
                Bpos.setBackground(getDrawable(R.drawable.trns_back));
                Bmin.setBackground(getDrawable(R.drawable.trns_back));
                Opos.setBackground(getDrawable(R.drawable.trns_back));
                Omin.setBackground(getDrawable(R.drawable.trns_back));
                Apos.setBackground(getDrawable(R.drawable.trns_back));
                ABpos.setBackground(getDrawable(R.drawable.trns_back));

                Amin.setTextColor(Color.parseColor("#D0CECE"));
                Bpos.setTextColor(Color.parseColor("#D0CECE"));
                Bmin.setTextColor(Color.parseColor("#D0CECE"));
                Opos.setTextColor(Color.parseColor("#D0CECE"));
                Omin.setTextColor(Color.parseColor("#D0CECE"));
                Apos.setTextColor(Color.parseColor("#D0CECE"));
                ABpos.setTextColor(Color.parseColor("#D0CECE"));


            }
        });






    }
}
