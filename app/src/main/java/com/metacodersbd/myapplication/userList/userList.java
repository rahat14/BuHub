package com.metacodersbd.myapplication.userList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
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

    }

    @Override
    protected void onStart() {
        super.onStart();

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

    }
}
