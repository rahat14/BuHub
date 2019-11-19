package com.metacodersbd.myapplication.ChatSystemUniversal;

import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.metacodersbd.myapplication.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class allDeptChat extends Fragment {

    View view;

    public allDeptChat() {

    }

    LinearLayoutManager mlayoutManager;
    RecyclerView mrecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef, mdref;
    FirebaseAuth mauth;

    List<modelForChat> chatList;
    AdapterChat adapterChat;

    String uid, msg, name ="", MSG, pplink = "null" , dpartment ,DATE ;
    public EditText msgINPUT;
    public ImageButton sendBTN;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mauth = FirebaseAuth.getInstance();
        uid = mauth.getUid();
        view = inflater.inflate(R.layout.activity_chat_page, container, false);
        chatRoom  activity = (chatRoom) getActivity();

        pplink = activity.getMyimage() ;
        name = activity.getMyName()  ;
        dpartment = activity.getMyDpart() ;







        sendBTN = view.findViewById(R.id.sendBTN);
        msgINPUT =view.findViewById(R.id.mesINput);


        //send Query to FirebaseDatabase
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference("ChatSystem").child(dpartment);
        //    mdref = FirebaseDatabase.getInstance().getReference("NewsFeed");
        mRef.keepSynced(true);


        //calling RecyclerView
        mrecyclerView =view.findViewById(R.id.recyclerView_For_chart);
        mrecyclerView.setHasFixedSize(true);

        //set layout as LinearLayout
        mlayoutManager = new LinearLayoutManager(getContext());
        mlayoutManager.setStackFromEnd(true);
        mrecyclerView.setHasFixedSize(true);
        mrecyclerView.setLayoutManager(mlayoutManager);

        readMeg();


        sendBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                sendMesg();
            }
        });

        return view ;
    }

    private void readMeg() {


        chatList = new ArrayList<>();
        DatabaseReference mref = FirebaseDatabase.getInstance().getReference("ChatSystem").child(dpartment);
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                chatList.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    modelForChat chat = ds.getValue(modelForChat.class);


                    chatList.add(chat);


                }
                // adaptar
                adapterChat = new AdapterChat(getContext(), chatList);
                adapterChat.notifyDataSetChanged();

                //set adapter
                mrecyclerView.setAdapter(adapterChat);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


    public void sendMesg() {

        MSG = msgINPUT.getText().toString().trim();
        String delegate = "hh:mm aaa";
         String    Time = String.valueOf(DateFormat.format(delegate, Calendar.getInstance().getTime()));

        DATE = Time +" " +new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());


        if (!TextUtils.isEmpty(MSG)) {
            String ts = mRef.push().getKey();

            modelForChat uploadData = new modelForChat(uid, ts, name, MSG, DATE, pplink);

            mRef.child(ts).setValue(uploadData).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {

                    msgINPUT.setText("");


                }
            });


        } else {
            Toast.makeText(getContext(), "Please Enter SomeThing ", Toast.LENGTH_SHORT)
                    .show();
        }


        msgINPUT.setHint("Enter the Msg");


    }
}
