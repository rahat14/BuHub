package com.metacodersbd.myapplication.newFeedHistory;

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
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.metacodersbd.myapplication.NewsFeedSection.addStory;
import com.metacodersbd.myapplication.NewsFeedSection.modelForNewsFeed;
import com.metacodersbd.myapplication.NewsFeedSection.viewHolderNewsFeed;
import com.metacodersbd.myapplication.PdfDownloaderSection.viewHolder_cse;
import com.metacodersbd.myapplication.R;

public class newsFeedHistory extends AppCompatActivity {

    LinearLayoutManager mlayoutManager ;
    RecyclerView mrecyclerView ;
    FirebaseDatabase mFirebaseDatabase ;
    DatabaseReference mRef ,  mdref ;
    FirebaseAuth mauth ;
    FirebaseRecyclerAdapter<modelForNewsFeed , viewholderForHistory>firebaseRecyclerAdapter ;
    FirebaseRecyclerOptions<modelForNewsFeed> options ;


    Query firebaseSearchQuery ;
    String  uid ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed_history);
        mauth = FirebaseAuth.getInstance() ;
        uid = mauth.getUid();



        //send Query to FirebaseDatabase
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference("NewsFeed");
        mdref = FirebaseDatabase.getInstance().getReference("NewsFeed");
        mRef.keepSynced(true);

        //query to search own upload

      firebaseSearchQuery  = mRef.orderByChild("nuid").startAt(uid).endAt(uid + "\uf8ff");




        //calling RecyclerView
        mrecyclerView = findViewById(R.id.recyclerView_for_newsFeed_history);
        mrecyclerView.setHasFixedSize(true);
        //set layout as LinearLayout
        mlayoutManager = new LinearLayoutManager(this);
       mrecyclerView.setLayoutManager(mlayoutManager);




        //calling RecyclerView
        mrecyclerView = findViewById(R.id.recyclerView_for_newsFeed_history);
        mrecyclerView.setHasFixedSize(true);
        //set layout as LinearLayout
        mlayoutManager = new LinearLayoutManager(this);
        mrecyclerView.setLayoutManager(mlayoutManager);


       loadDataToFirbase();

    }

    private void loadDataToFirbase() {

        options = new FirebaseRecyclerOptions.Builder<modelForNewsFeed>().setQuery(mRef, modelForNewsFeed.class)
                .build();

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<modelForNewsFeed, viewholderForHistory>(options) {
            @Override
            protected void onBindViewHolder(@NonNull viewholderForHistory holder, final int position, @NonNull final modelForNewsFeed model) {

                holder.serDetails(getApplicationContext(), model.getNtitle(), model.getNimage(),
                        model.getNuid(), model.getPp_link(), model.getPname(), model.getDate(), model.getPushid());


                holder.dltebutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mdref.child(model.getPushid()).removeValue();
                    }
                });


            }

            @NonNull
            @Override
            public viewholderForHistory onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                //INflate the row
                Context context;
                View itemVIew = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_for_history, parent, false);

                viewholderForHistory viewHolder = new viewholderForHistory(itemVIew);

                //itemClicklistener


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
        FirebaseRecyclerAdapter<modelForNewsFeed , viewholderForHistory> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<modelForNewsFeed, viewholderForHistory>(
                        modelForNewsFeed.class,
                        R.layout.row_for_history ,
                        viewholderForHistory.class,
                        firebaseSearchQuery

                ) {
                    @Override
                    protected void populateViewHolder(viewholderForHistory vv, final modelForNewsFeed modelForNewsFeed, int i) {

                        vv.serDetails(getApplicationContext() , modelForNewsFeed.getNtitle() ,modelForNewsFeed.getNimage() ,
                                modelForNewsFeed.getNuid() ,modelForNewsFeed.getPp_link() , modelForNewsFeed.getPname() , modelForNewsFeed.getDate()  , modelForNewsFeed.getPushid());


                        vv.dltebutton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                mdref.child(modelForNewsFeed.getPushid()).removeValue();



                            }
                        });








                    }



                };






        //set adapter to recyclerview
        mrecyclerView.setAdapter(firebaseRecyclerAdapter);

    }
    */

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
