package com.metacodersbd.myapplication.BloodActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.metacodersbd.myapplication.Profile;
import com.metacodersbd.myapplication.R;
import com.metacodersbd.myapplication.loginAcconuntSetup.getProfile;

import java.util.Calendar;

public class AddBloodReq extends AppCompatActivity {


    TextView  datePicker  , timePicker ;
    DatePickerDialog  datePickerDialog ;
    String amPmChecker  , date  ="null", time ="null", Loc  ;

    Button Apos , Amin , Bpos , Bmin , Opos , Omin , ABpos , ABmin ;
    String bldGroup = "bloodGroup" ;
    Button submitBtn ;
    EditText  needer   ,  loc  ;
    String  ph =  "nan" ;
    String pp = " ";

DatabaseReference mRef ;
ProgressBar mbar ;

    String uid ;





    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_blood_req);

        mRef = FirebaseDatabase.getInstance().getReference("BloodReq");

        Apos = findViewById(R.id.bldGroupIdApos);
        Amin = findViewById(R.id.bldGroupIdAmin);
        Bpos = findViewById(R.id.bldGroupIdBpos);
        Bmin = findViewById(R.id.bldGroupIdBmin);
        Omin = findViewById(R.id.bldGroupIdOmin);
        Opos = findViewById(R.id.bldGroupIdOpos);
        ABmin = findViewById(R.id.bldGroupIdABmin);
        ABpos = findViewById(R.id.bldGroupIdABpos);
        submitBtn = findViewById(R.id.submitBtn);
        timePicker = findViewById(R.id.timeEdit);
        datePicker = findViewById(R.id.dateEdit);
        needer = findViewById(R.id.name_add);
        loc = findViewById(R.id.loc);
        mbar = findViewById(R.id.progresssbar);
        mbar.setVisibility(View.GONE);



        //.selecting Blood

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





        ///

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mbar.setVisibility(View.VISIBLE);

                String Name = needer.getText().toString();
                String LOc = loc.getText().toString();


                if(!TextUtils.isEmpty(Name) && !TextUtils.isEmpty(LOc)&& !bldGroup.contains("bloodGroup")
                ){


                    uploadDataToFireBase(Name , LOc   , ph , time , date );

                      //   OpenDialogue();

                         Toast.makeText(getApplicationContext() , Name + "" + LOc , Toast.LENGTH_SHORT )
                                 .show();

                }
                else {
                    Toast.makeText(getApplicationContext() , "Fill the Data Properly" , Toast.LENGTH_SHORT)
                            .show();
                            mbar.setVisibility(View.GONE);
                }



            }
        });



        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // calling the date Picker

                Calendar c = Calendar.getInstance();

                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);

                datePickerDialog = new DatePickerDialog(AddBloodReq.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        datePicker.setText(dayOfMonth + "/"+(month+1)+"/"+year);
                        date = dayOfMonth + "/"+(month+1)+"/"+year ;



                    }
                } ,year , month , day);

                datePickerDialog.show();
            }
        });



        timePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //calling the time picker

                TimePickerDialog timePickerDialog  = new TimePickerDialog(AddBloodReq.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        if (hourOfDay >= 12){
                            amPmChecker = "AM";

                        }
                        else  {
                            amPmChecker= "PM";
                        }


                        timePicker.setText(hourOfDay+":"+minute+" "+amPmChecker);

                        time = hourOfDay+":"+minute+" "+amPmChecker ;

                    }
                }, 0,0, false);


                timePickerDialog.show();
            }
        });

    }

    private void uploadDataToFireBase(String name, String lOc, String ph, String time, String date) {

        String id  = mRef.push().getKey();

       /// String postID , uid , needer  , loc , timee , datee , bg ;

                modelForBloodReq model = new modelForBloodReq(id  , uid , name , lOc  , time , date   , bldGroup, ph  , pp  ) ;

                mRef.child(id).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        mbar.setVisibility(View.GONE);

                        OpenDialogue();



                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {


                                Toast.makeText(getApplicationContext() , "Error :"+ e.getMessage() , Toast.LENGTH_SHORT)
                                        .show();
                                mbar.setVisibility(View.GONE);
                            }
                        });
    }

    private void OpenDialogue() {

        final Dialog  dialog  = new Dialog(AddBloodReq.this);
        dialog.setContentView(R.layout.done_dialogue_in_blood) ;

        Button okBtn = dialog.findViewById(R.id.okBtn) ;


        dialog.setCancelable(false);

        dialog.show();


        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
                finish();
            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth mauth  = FirebaseAuth.getInstance();
         uid = mauth.getUid() ;


        DatabaseReference mref = FirebaseDatabase.getInstance().getReference("Users").child(uid);

       mref.addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

           getProfile model = dataSnapshot.getValue(getProfile.class);

               ph = model.getUser_phn();
               pp = model.getUser_image() ;




           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });


    }
}
