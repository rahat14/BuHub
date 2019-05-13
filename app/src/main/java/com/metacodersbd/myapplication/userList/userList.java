package com.metacodersbd.myapplication.userList;

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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.metacodersbd.myapplication.NewsFeedSection.modelForNewsFeed;
import com.metacodersbd.myapplication.NewsFeedSection.viewHolderNewsFeed;
import com.metacodersbd.myapplication.PdfDownloaderSection.viewHolder_cse;
import com.metacodersbd.myapplication.R;
import com.metacodersbd.myapplication.loginAcconuntSetup.getProfile;

public class userList extends AppCompatActivity {

    LinearLayout linearLayout ;

    LinearLayoutManager mlayoutManager ;
    RecyclerView mrecyclerView ;
    FirebaseDatabase mFirebaseDatabase ;
    DatabaseReference mRef ;
    FloatingActionButton fav_Add_Button ;
    FirebaseRecyclerAdapter<getProfile , viewholderForUserList>firebaseRecyclerAdapter ;
    FirebaseRecyclerOptions<getProfile>options ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);



        //send Query to FirebaseDatabase
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference("Users");
        mRef.keepSynced(true);


        //calling RecyclerView
        mrecyclerView = findViewById(R.id.recyclerVIew_userList) ;
        mrecyclerView.setHasFixedSize(true);

        //set layout as LinearLayout
        mlayoutManager = new LinearLayoutManager(this);
        mrecyclerView.setLayoutManager(mlayoutManager);
        
        showData() ; 

    }

    private  void showData(){

        options = new FirebaseRecyclerOptions.Builder<getProfile>().setQuery(mRef , getProfile.class)
                .build() ;

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<getProfile, viewholderForUserList>(options) {
            @Override
            protected void onBindViewHolder(@NonNull viewholderForUserList holder, final int position, @NonNull getProfile model) {

            }

            @NonNull
            @Override
            public viewholderForUserList onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

                //INflate the row
                Context context;
                View itemVIew = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_foruserlist, viewGroup, false);

                viewholderForUserList viewHolder = new viewholderForUserList(itemVIew);






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

        /*
        FirebaseRecyclerAdapter<getProfile , viewholderForUserList> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<getProfile, viewholderForUserList>(
                        getProfile.class,
                        R.layout.row_foruserlist ,
                        viewholderForUserList.class,
                        mRef



                ) {
                    @Override
                    protected void populateViewHolder(viewholderForUserList viewHolderNewsFeed, getProfile modelForNewsFeed, int i) {

                        viewHolderNewsFeed.setdetails(getApplicationContext() , modelForNewsFeed.getUser_name() , modelForNewsFeed.getUser_image());

                    }

                };






        //set adapter to recyclerview
        mrecyclerView.setAdapter(firebaseRecyclerAdapter);
        
        */

    }
}
