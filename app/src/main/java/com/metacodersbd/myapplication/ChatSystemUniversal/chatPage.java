package com.metacodersbd.myapplication.ChatSystemUniversal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.metacodersbd.myapplication.NewsFeedSection.modelForNewsFeed;
import com.metacodersbd.myapplication.NewsFeedSection.viewHolderNewsFeed;
import com.metacodersbd.myapplication.R;
import com.metacodersbd.myapplication.newFeedHistory.viewholderForHistory;

import java.util.ArrayList;
import java.util.List;

public class chatPage extends AppCompatActivity {
    LinearLayoutManager mlayoutManager ;
    RecyclerView mrecyclerView ;
    FirebaseDatabase mFirebaseDatabase ;
    DatabaseReference mRef ,  mdref ;
    FirebaseAuth mauth ;

    List<modelForChat>chatList ;
    AdapterChat adapterChat ;

    String  uid , msg , name ,MSG  , pplink  ;
    EditText msgINPUT ;
    ImageButton sendBTN ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_page);

        mauth = FirebaseAuth.getInstance();
        uid = mauth.getUid();


        Intent o = getIntent();
        name = o.getStringExtra("NAME");
        pplink = o.getStringExtra("Image");



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
        mlayoutManager.setStackFromEnd(true);
        mrecyclerView.setHasFixedSize(true);
        mrecyclerView.setLayoutManager(mlayoutManager);

        readMeg() ;


        sendBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                sendMesg();
            }
        });


    }

    private void readMeg() {


        chatList = new ArrayList<>();
        DatabaseReference mref = FirebaseDatabase.getInstance().getReference("ChatSystem");
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                chatList.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren() ){

                    modelForChat chat = ds.getValue(modelForChat.class) ;


                        chatList.add(chat) ;



                }
                // adaptar
                adapterChat = new AdapterChat(chatPage.this  , chatList) ;
                adapterChat.notifyDataSetChanged();

                //set adapter
                mrecyclerView.setAdapter(adapterChat) ;


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


    public  void sendMesg(){

       MSG =  msgINPUT.getText().toString();


        if(!TextUtils.isEmpty(MSG)){
            String ts =mRef.push().getKey() ;

            modelForChat uploadData = new modelForChat(uid , ts , name ,MSG , "s122",pplink  );

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






