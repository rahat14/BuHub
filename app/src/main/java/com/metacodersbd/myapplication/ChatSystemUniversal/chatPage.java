package com.metacodersbd.myapplication.ChatSystemUniversal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.metacodersbd.myapplication.NewsFeedSection.modelForNewsFeed;
import com.metacodersbd.myapplication.R;
import com.metacodersbd.myapplication.newFeedHistory.viewholderForHistory;

public class chatPage extends AppCompatActivity {
    LinearLayoutManager mlayoutManager ;
    RecyclerView mrecyclerView ;
    FirebaseDatabase mFirebaseDatabase ;
    DatabaseReference mRef ,  mdref ;
    FirebaseAuth mauth ;

    String  uid , msg , name ,MSG   ;
    EditText msgINPUT ;
    Button sendBTN ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_page);

        mauth = FirebaseAuth.getInstance();
        uid = mauth.getUid();


        Intent o = getIntent();
        name = o.getStringExtra("NAME");


        sendBTN= findViewById(R.id.sendBTN) ;
        msgINPUT = findViewById(R.id.mesINput);



        //send Query to FirebaseDatabase
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference("ChatSystem");
    //    mdref = FirebaseDatabase.getInstance().getReference("NewsFeed");
        mRef.keepSynced(true);



        //calling RecyclerView
        mrecyclerView = findViewById(R.id.recyclerView_For_chart);
        mrecyclerView.setHasFixedSize(true);

        //set layout as LinearLayout
        mlayoutManager = new LinearLayoutManager(this);
        mrecyclerView.setLayoutManager(mlayoutManager);


        FirebaseRecyclerAdapter<modelForChat , viewHolderForChat> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<modelForChat, viewHolderForChat>(
                        modelForChat.class,
                        R.layout.row_for_msg ,
                        viewHolderForChat.class,
                        mRef

                ) {
                    @Override
                    protected void populateViewHolder(viewHolderForChat vv, final modelForChat model, int i) {
                        vv.setDetails(getApplicationContext() ,model.getName() , model.getMsg() , model.getUid() , model.getPushid() );
                    }
                };


        //set adapter to recyclerview
        mrecyclerView.setAdapter(firebaseRecyclerAdapter);


        sendBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                sendMesg();
            }
        });

    }




    public  void sendMesg(){

       MSG =  msgINPUT.getText().toString();
        if(!TextUtils.isEmpty(MSG)){
            String ts =mRef.push().getKey() ;

            modelForChat uploadData = new modelForChat(uid , ts , name ,MSG );

            mRef.child(ts).setValue(uploadData).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {

                    msgINPUT.setText("");


                }
            });




        }
        else
        {
            Toast.makeText(getApplicationContext() , "Please Enter SomeThing ", Toast.LENGTH_SHORT)
                    .show();
        }





        msgINPUT.setHint("Enter the Msg");


    }









    }






