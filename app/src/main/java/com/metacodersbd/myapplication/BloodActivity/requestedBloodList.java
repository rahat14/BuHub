package com.metacodersbd.myapplication.BloodActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.share.widget.ShareDialog;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.metacodersbd.myapplication.R;
import com.metacodersbd.myapplication.loginAcconuntSetup.getProfile;

public class requestedBloodList extends AppCompatActivity {

    LinearLayoutManager mlayoutManager;
    RecyclerView mrecyclerView;
    FirebaseDatabase mFirebaseDatabase ;
    DatabaseReference mRef , likeRef;
    FirebaseAuth mauth ;
    ShareDialog shareDialogue;
    String uid  ;
    String searchText ;

    public FirebaseRecyclerAdapter<modelForBloodReq, viewHolderForReqBlood> firebaseRecyclerAdapter ;
    public FirebaseRecyclerOptions<modelForBloodReq> options ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requested_blood_list);


        //send Query to FirebaseDatabase
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference("BloodReq");

        mRef.keepSynced(true);



     //   searchText = i.getStringExtra("BG");


        //calling RecyclerView
        mrecyclerView = findViewById(R.id.reclyclerViewForAllReqBlood);
        mrecyclerView.setHasFixedSize(true);

        //set layout as LinearLayout
        mlayoutManager = new LinearLayoutManager(this);
        mrecyclerView.setLayoutManager(mlayoutManager);
        mlayoutManager.setReverseLayout(true);
        mlayoutManager.setStackFromEnd(true);

      //  showData();


    }

    private void showData() {




        options = new FirebaseRecyclerOptions.Builder<modelForBloodReq>().setQuery(mRef , modelForBloodReq.class)
                .build() ;

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<modelForBloodReq, viewHolderForReqBlood>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final viewHolderForReqBlood holder, final int position, @NonNull modelForBloodReq model) {

                //  holder.setIsRecyclable(false); // dunno why if it crash then remove it
// String postID , uid , needer  , loc , timee , datee , bg ,phone, purl ;


                holder.setDataToView(getApplicationContext(), model.getPostID(), model.getUid(),
                        model.getNeeder(), model.getLoc(), model.getTimee() , model.getDatee() , model.getBg() , model.getPhone()
                , model.getPurl());



/*
                final String name = getItem(position).getUser_name();
                final String dept =getItem(position).getUser_dpt() ;
                final  String imagelink = getItem(position).getUser_image();
                final  String phn = getItem(position).getUser_phn();
                final  String bgroup = getItem(position).getUser_bloodgroup();
*/
// love button function



            }

            @NonNull
            @Override
            public viewHolderForReqBlood onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

                //INflate the row
                Context context;
                View itemVIew = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_for_blood_req, viewGroup, false);

                viewHolderForReqBlood viewHolder = new viewHolderForReqBlood(itemVIew);





                return viewHolder;
            }
        };

        mrecyclerView.setLayoutManager(mlayoutManager);

        firebaseRecyclerAdapter.startListening();

        //setting adapter

        mrecyclerView.setAdapter(firebaseRecyclerAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();

      showData();

    }
}




