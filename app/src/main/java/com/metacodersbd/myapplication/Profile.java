package com.metacodersbd.myapplication;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.metacodersbd.myapplication.loginAcconuntSetup.accountSetupUploadModel;
import com.metacodersbd.myapplication.loginAcconuntSetup.getProfile;
import com.roger.catloadinglibrary.CatLoadingView;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile extends AppCompatActivity {

    TextView name , cgpa , depart_name  ,batch_name , mail_address  , Blood_Group , Phone ;
    CircleImageView IMAGE_PLACE ;
    String url ;
    CatLoadingView mView;

            FirebaseUser user ;
 FirebaseAuth mAuth;
   // private static final String TAG = "ViewDatabase";

    //changing some code in by commenting . now i will upload ?? Aso huuuuuuuu phn doa
    //fb te ph dei.....dei oka

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);
        Intent i = getIntent();
        final String User_id = i.getStringExtra("UID");
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        final String mail = user.getEmail();
        mView = new CatLoadingView() ;
        //  Toast.makeText(getApplicationContext(), ""+User_id , Toast.LENGTH_SHORT).show();

        //declring the view
        IMAGE_PLACE = (CircleImageView) findViewById(R.id.image_profile) ;
        name = (TextView)findViewById(R.id.profile_name) ;
        cgpa = (TextView)findViewById(R.id.cgpa_card) ;
        batch_name = (TextView)findViewById(R.id.batch_card) ;
        depart_name = (TextView)findViewById(R.id.dpart_profile);
        mail_address  = (TextView) findViewById(R.id.mail_profile);
        Blood_Group = (TextView) findViewById(R.id.blood_profile);
        Phone = (TextView) findViewById(R.id.ph_profile);

            mView.show(getSupportFragmentManager()," ");
            mView.setCanceledOnTouchOutside(false);



        //calling firebase

        FirebaseDatabase database = FirebaseDatabase.getInstance() ;
        final DatabaseReference mRef = database.getReference("Users").child(User_id);


        //getting data firebase Database

                       mRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {



                        getProfile model = dataSnapshot.getValue(getProfile.class);
                    name.setText(model.getUser_name());
                    Phone.setText(model.getUser_phn());
                    cgpa.setText(model.getCgpa());
                    Blood_Group.setText(model.getUser_bloodgroup());
                    depart_name.setText(model.getUser_dpt());
                    mail_address.setText(mail);
                    batch_name.setText(model.getUser_batch());
                    url = model.getUser_image() ;
                Picasso.get().load(url).placeholder(R.drawable.plaementpro).error(R.drawable.plaementpro)
                        .noFade()
                        .into(IMAGE_PLACE);







            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                mView.dismiss();
            }
        }, 1800);



    }
}
