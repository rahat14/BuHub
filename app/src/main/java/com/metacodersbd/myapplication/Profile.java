package com.metacodersbd.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.metacodersbd.myapplication.loginAcconuntSetup.accountSetupUploadModel;
import com.metacodersbd.myapplication.loginAcconuntSetup.getProfile;

public class Profile extends AppCompatActivity {

    TextView name , cgpa , depart_name  ,batch_name , mail_address  , Blood_Group , Phone ;
    ImageView IMAGE_PLACE ;

            FirebaseUser user ;
 FirebaseAuth mAuth;
    private static final String TAG = "ViewDatabase";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);
        Intent i = getIntent();
        final String User_id = i.getStringExtra("UID");
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        final String USERID = user.getUid();
        //  Toast.makeText(getApplicationContext(), ""+User_id , Toast.LENGTH_SHORT).show();

        //declring the view
        IMAGE_PLACE = (ImageView)findViewById(R.id.image_profile) ;
        name = (TextView)findViewById(R.id.profile_name) ;
        cgpa = (TextView)findViewById(R.id.cgpa_card) ;
        batch_name = (TextView)findViewById(R.id.batch_card) ;
        depart_name = (TextView)findViewById(R.id.dpart_profile);
        mail_address  = (TextView) findViewById(R.id.mail_profile);
        Blood_Group = (TextView) findViewById(R.id.blood_profile);
        Phone = (TextView) findViewById(R.id.ph_profile);


        //calling firebase

        FirebaseDatabase database = FirebaseDatabase.getInstance() ;
        final DatabaseReference mRef = database.getReference();


        //getting data firebase Database
         mRef.child("Users").addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                 for(DataSnapshot ds : dataSnapshot.getChildren() ){

                     getProfile model = new getProfile();
                  model.setUser_name(ds.child(USERID).getValue(getProfile.class).getUser_name());

                     Log.d(TAG, "showData: name: " + model.getUser_phn());
                     name.setText(model.getUser_name());


                 }


             }

             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {

             }
         });







    }
}
