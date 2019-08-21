package com.metacodersbd.myapplication.profilePackage;

import android.content.Intent;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.metacodersbd.myapplication.R;
import com.metacodersbd.myapplication.loginAcconuntSetup.getProfile;
import com.roger.catloadinglibrary.CatLoadingView;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile extends AppCompatActivity {

    TextView name , cgpa , depart_name  ,batch_name , mail_address  , Blood_Group , Phone ;
    CircleImageView IMAGE_PLACE ;
    String url ;
    CatLoadingView mView;
    ImageButton editProfileBtn ;
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
        editProfileBtn = findViewById(R.id.editProfile);



            mView.show(getSupportFragmentManager()," ");
            mView.setCanceledOnTouchOutside(false);



        //calling firebase

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference mRef = database.getReference("Users").child(User_id);
        mRef.keepSynced(true);

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

             /*   Picasso.get().load(url).placeholder(R.drawable.plaementpro).error(R.drawable.plaementpro)
                        .noFade()
                        .into(IMAGE_PLACE);

*/

                        Glide.with(Profile.this)
                                .load(url)
                                .skipMemoryCache(true)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
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

        editProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext() , editProfilePage.class);

                i.putExtra("NAME" ,name.getText().toString()) ;
                i.putExtra("PHONE", Phone.getText().toString());
                i.putExtra("CGPA", cgpa.getText().toString());
                i.putExtra("BGG", Blood_Group.getText().toString());
                i.putExtra("MAIL", mail_address.getText().toString());
                i.putExtra("BATCH", batch_name.getText().toString());
                i.putExtra("URL",url);



                startActivity(i);


            }
        });
    }



}
