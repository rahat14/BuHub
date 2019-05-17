package com.metacodersbd.myapplication.NewsFeedSection;

import android.content.Context;
import android.content.Intent;

import com.facebook.CallbackManager;
import com.facebook.share.Share;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
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
import java.util.HashMap;
import java.util.List;

public class newsFeed extends AppCompatActivity {


    LinearLayoutManager mlayoutManager;
    RecyclerView mrecyclerView;
    FirebaseDatabase mFirebaseDatabase ;
    DatabaseReference mRef , likeRef;
    FloatingActionButton fav_Add_Button;
    FirebaseAuth mauth ;
    ShareDialog   shareDialogue;
      String uid  ;
    CallbackManager callbackManager ;

    private  int selectedPosition = -1 ;
    public    int likeCOunt  = 0 ;
   public FirebaseRecyclerAdapter<modelForNewsFeed , viewHolderNewsFeed>firebaseRecyclerAdapter ;
   public FirebaseRecyclerOptions<modelForNewsFeed> options ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);
        mauth = FirebaseAuth.getInstance() ;
        uid = mauth.getUid() ;


//init fb
         callbackManager = CallbackManager.Factory.create();
        shareDialogue = new ShareDialog(this ) ;

        //send Query to FirebaseDatabase
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference("NewsFeed");
        likeRef = mFirebaseDatabase.getReference("NewsFeed");


        mRef.keepSynced(true);
        likeRef.keepSynced(true);


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

        showData();

    }

    private  void showData(){

        options = new FirebaseRecyclerOptions.Builder<modelForNewsFeed>().setQuery(mRef , modelForNewsFeed.class)
                .build() ;

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<modelForNewsFeed, viewHolderNewsFeed>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final viewHolderNewsFeed holder, final int position, @NonNull modelForNewsFeed model) {

                holder.setIsRecyclable(false); // dunno why if it crash then remove it

                holder.serDetails(getApplicationContext(), model.getNtitle(), model.getNimage(),
                        model.getNuid(), model.getPp_link(), model.getPname(), model.getDate() , model.getPushid());



                final String db = getItem(position).getPushid();
                final String like =getItem(position).getLikeCount() ;
                final  String imagelink = getItem(position).getNimage();
                final  String tile = getItem(position).getNtitle();


                likeCOunt = Integer.valueOf(like);


                likeRef.child(db).child("likers").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                        if (dataSnapshot.hasChild(uid)){

                            holder.loveBtn.setImageDrawable(getResources().getDrawable(R.drawable.redlove));
                            holder.loveBtn.setTag("DONE");

                        }
                        else {

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                }) ;



                //share Button function
                holder.shareBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Uri fullTitle = Uri.parse(imagelink);

                        ShareLinkContent linkContent  = new ShareLinkContent.Builder()
                                .setQuote(tile)
                                .setContentUrl(fullTitle)
                                .build();
                        if(ShareDialog.canShow(ShareLinkContent.class)){

                        shareDialogue.show(linkContent);

                        }
                        else {

                        }



                    }
                });


// love button function
                holder.loveBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        if (holder.loveBtn.getTag().equals("ok") ){

                            likeCOunt = likeCOunt+1 ;

                            holder.loveBtn.setImageDrawable(getResources().getDrawable(R.drawable.redlove));
                            holder.loveBtn.setTag("DONE");

                          //  Toast.makeText(getApplicationContext(), "Nuid "+ db  + " " +like, Toast.LENGTH_SHORT).show();

                              UpdateLike(likeCOunt , db);



                        }
                        // do something
                        else{

                            Toast.makeText(getApplicationContext(), "You All Ready Liked This Item  " , Toast.LENGTH_SHORT).show();
                           // holder.loveBtn.setImageDrawable(getResources().getDrawable(R.drawable.lovereat3));
                        }


                    }
                });


            }

            @NonNull
            @Override
            public viewHolderNewsFeed onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

                //INflate the row
                Context context;
                View itemVIew = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_for_newsfeed_new, viewGroup, false);

                viewHolderNewsFeed viewHolder = new viewHolderNewsFeed(itemVIew);





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
    }

    private void UpdateLike(int likeCOunt , String db ) {

        String s = String.valueOf(likeCOunt) ;
        HashMap e = new HashMap() ;
        HashMap uidMap = new HashMap() ;
        e.put("likeCount" ,s );
        uidMap.put("uids", uid);


        DatabaseReference mref = FirebaseDatabase.getInstance().getReference("NewsFeed").child(db);
        mref.updateChildren(e);
        mref.child("likers").child(uid).setValue(uid);



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
