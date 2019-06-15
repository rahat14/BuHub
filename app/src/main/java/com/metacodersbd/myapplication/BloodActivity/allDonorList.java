package com.metacodersbd.myapplication.BloodActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.metacodersbd.myapplication.NewsFeedSection.modelForNewsFeed;
import com.metacodersbd.myapplication.NewsFeedSection.viewHolderNewsFeed;
import com.metacodersbd.myapplication.R;
import com.metacodersbd.myapplication.loginAcconuntSetup.getProfile;

public class allDonorList extends AppCompatActivity {

    LinearLayoutManager mlayoutManager;
    RecyclerView mrecyclerView;
    FirebaseDatabase mFirebaseDatabase ;
    DatabaseReference mRef , likeRef;
    FirebaseAuth mauth ;
    ShareDialog shareDialogue;
    String uid  ;

    public FirebaseRecyclerAdapter<getProfile, viewHolderForAllDonorList> firebaseRecyclerAdapter ;
    public FirebaseRecyclerOptions<getProfile> options ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_donor_list);

        //send Query to FirebaseDatabase
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference("Users");

        mRef.keepSynced(true);

        //calling RecyclerView
        mrecyclerView = findViewById(R.id.reclyclerViewForAllDonorList);
        mrecyclerView.setHasFixedSize(true);

        //set layout as LinearLayout
        mlayoutManager = new LinearLayoutManager(this);
        mrecyclerView.setLayoutManager(mlayoutManager);
        mlayoutManager.setReverseLayout(true);
        mlayoutManager.setStackFromEnd(true);

        showData();


    }
    private  void showData(){

        options = new FirebaseRecyclerOptions.Builder<getProfile>().setQuery(mRef , getProfile.class)
                .build() ;

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<getProfile, viewHolderForAllDonorList>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final viewHolderForAllDonorList holder, final int position, @NonNull getProfile model) {

                //  holder.setIsRecyclable(false); // dunno why if it crash then remove it

                holder.SetDeatils(getApplicationContext(), model.getUser_name(), model.getUser_dpt(),
                        model.getUser_bloodgroup(), model.getUser_phn(), model.getUser_image());




                final String name = getItem(position).getUser_name();
                final String dept =getItem(position).getUser_dpt() ;
                final  String imagelink = getItem(position).getUser_image();
                final  String phn = getItem(position).getUser_phn();
                final  String bgroup = getItem(position).getUser_bloodgroup();

// love button function
                holder.triigerDiagoue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        // trigger thet dialgoue here

                        triggerDialogue(name , dept  , imagelink , phn , bgroup) ;


                    }
                });


            }

            @NonNull
            @Override
            public viewHolderForAllDonorList onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

                //INflate the row
                Context context;
                View itemVIew = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_for_all_donor, viewGroup, false);

                viewHolderForAllDonorList viewHolder = new viewHolderForAllDonorList(itemVIew);





                return viewHolder;
            }
        };

        mrecyclerView.setLayoutManager(mlayoutManager);

        firebaseRecyclerAdapter.startListening();

        //setting adapter

        mrecyclerView.setAdapter(firebaseRecyclerAdapter);

    }

    private void triggerDialogue(String name, String dept, String imagelink, String phn, String bgroup) {



    }
}
