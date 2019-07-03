package com.metacodersbd.myapplication.BloodActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.metacodersbd.myapplication.R;

public class BloodPage extends AppCompatActivity {

    Button findDonorBtn , addReqBtn  , showReq, myreq  ;
    TextView donornumber , ownReq , sameDonorNumber , reqnumber  ;
    FirebaseAuth mauth ;
    String uid ;

int count = 0 ;

    Intent i  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_page);

        getSupportActionBar().hide();
        mauth = FirebaseAuth.getInstance();
         uid = mauth.getUid();


        //init the view
        donornumber = findViewById(R.id.donorNumber);
        ownReq = findViewById(R.id.ownReqNum);
        sameDonorNumber = findViewById(R.id.sameReqList);
        reqnumber = findViewById(R.id.reqNumber);


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





getTotalcountOFUsers();
getTotalReq();
getOwnReq();





    }
    public  void getTotalcountOFUsers(){

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

// i used the single or the value.. depending if you want to keep track
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //Log.e(dataSnapshot.getKey(),dataSnapshot.getChildrenCount() + "");

                if(dataSnapshot.getKey().equals("Users")){


                       donornumber.setText(  dataSnapshot.getChildrenCount()+ "");

                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }
    public  void getTotalReq(){

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

// i used the single or the value.. depending if you want to keep track
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //Log.e(dataSnapshot.getKey(),dataSnapshot.getChildrenCount() + "");

                if(dataSnapshot.getKey().equals("BloodReq")){


                    reqnumber.setText(  dataSnapshot.getChildrenCount()+ "");
                    sameDonorNumber.setText(dataSnapshot.getChildrenCount()+"");

                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }
    public  void getOwnReq(){

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("BloodReq");
        Query query = myRef.orderByChild("uid").equalTo(uid);


// i used the single or the value.. depending if you want to keep track

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                count = (int) dataSnapshot.getChildrenCount();
                ownReq.setText(count + "");


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }
}
