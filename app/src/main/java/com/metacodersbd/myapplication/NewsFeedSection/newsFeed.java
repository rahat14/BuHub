package com.metacodersbd.myapplication.NewsFeedSection;

import android.content.Intent;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.metacodersbd.myapplication.PdfDownloaderSection.pdfViewer;
import com.metacodersbd.myapplication.PdfDownloaderSection.viewHolder_cse;
import com.metacodersbd.myapplication.R;
import com.metacodersbd.myapplication.newFeedHistory.newsFeedHistory;

import java.util.ArrayList;
import java.util.List;

public class newsFeed extends AppCompatActivity {


    LinearLayoutManager mlayoutManager;
    RecyclerView mrecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;
    FloatingActionButton fav_Add_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);


        //send Query to FirebaseDatabase
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference("NewsFeed");

        mRef.keepSynced(true);


        fav_Add_Button = (FloatingActionButton) findViewById(R.id.fab_add);

        //adding button listeners

        fav_Add_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), addStory.class);
                startActivity(i);


            }
        });


        //calling RecyclerView
        mrecyclerView = findViewById(R.id.recyclerView_for_newsFeed);
        mrecyclerView.setHasFixedSize(true);

        //set layout as LinearLayout
        mlayoutManager = new LinearLayoutManager(this);
        mrecyclerView.setLayoutManager(mlayoutManager);
        mlayoutManager.setReverseLayout(true);
        mlayoutManager.setStackFromEnd(true);

    }


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<modelForNewsFeed, viewHolderNewsFeed> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<modelForNewsFeed, viewHolderNewsFeed>(
                        modelForNewsFeed.class,
                        R.layout.row,
                        viewHolderNewsFeed.class,
                        mRef


                ) {
                    @Override
                    protected void populateViewHolder(viewHolderNewsFeed viewHolderNewsFeed, modelForNewsFeed modelForNewsFeed, int i) {

                        viewHolderNewsFeed.serDetails(getApplicationContext(), modelForNewsFeed.getNtitle(), modelForNewsFeed.getNimage(),
                                modelForNewsFeed.getNuid(), modelForNewsFeed.getPp_link(), modelForNewsFeed.getPname(), modelForNewsFeed.getDate());

                    }


                    @Override
                    public viewHolderNewsFeed onCreateViewHolder(ViewGroup parent, int viewType) {

                        viewHolderNewsFeed viewHolderNewsFeed = super.onCreateViewHolder(parent, viewType);

                        viewHolderNewsFeed.setOnClickListener(new viewHolder_cse.ClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {


                                //doing something on click

                            }

                            @Override
                            public void onItemLongClick(View view, int position) {

                            }
                        });


                        return viewHolderNewsFeed;
                    }
                };


        //set adapter to recyclerview
        mrecyclerView.setAdapter(firebaseRecyclerAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem item = menu.findItem(R.id.Profile);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Profile:
                // Not implemented here
                Intent o = new Intent(getApplicationContext(), newsFeedHistory.class);
                startActivity(o);


                return false;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);

    }
}
