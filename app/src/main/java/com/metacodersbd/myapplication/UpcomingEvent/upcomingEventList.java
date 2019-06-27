package com.metacodersbd.myapplication.UpcomingEvent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.facebook.share.widget.ShareDialog;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.metacodersbd.myapplication.R;

public class upcomingEventList extends AppCompatActivity {

    LinearLayoutManager mlayoutManager;
    RecyclerView mrecyclerView;
    FirebaseDatabase mFirebaseDatabase ;
    DatabaseReference mRef , likeRef;
    FirebaseAuth mauth ;
    ShareDialog shareDialogue;
    String uid  ;
    String searchText ;
    EditText pass ;
    Button login ;
    String PASS ;
    ProgressBar pbar ;



    public FirebaseRecyclerAdapter<model, viewHolderForEventList> firebaseRecyclerAdapter ;
    public FirebaseRecyclerOptions<model> options ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_event_list);





        //send Query to FirebaseDatabase
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference("EventList");

        mRef.keepSynced(true);



        //   searchText = i.getStringExtra("BG");


        //calling RecyclerView
        mrecyclerView = findViewById(R.id.recylerViewEventList);
        mrecyclerView.setHasFixedSize(true);

        //set layout as LinearLayout
        mlayoutManager = new LinearLayoutManager(this);
        mrecyclerView.setLayoutManager(mlayoutManager);
        mlayoutManager.setReverseLayout(true);
        mlayoutManager.setStackFromEnd(true);

        //  showData();




    }
    private void showData() {




        options = new FirebaseRecyclerOptions.Builder<model>().setQuery(mRef , model.class)
                .build() ;

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<model, viewHolderForEventList>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final viewHolderForEventList holder, final int position, @NonNull model model) {

                //  holder.setIsRecyclable(false); // dunno why if it crash then remove it
// String postID , uid , needer  , loc , timee , datee , bg ,phone, purl ;

               // String  pushId , String id ,String titte ,String desc ,String date ,String month,String fee ,String venu
                holder.setViewToRow(getApplicationContext(), model.getPushId(), model.getId(),
                        model.getTitte(), model.getDesc(), model.getDate() , model.getMonth() , model.getFee() , model.getVenu());



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
            public viewHolderForEventList onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

                //INflate the row
                Context context;
                View itemVIew = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_for_upcoming_event, viewGroup, false);

                viewHolderForEventList viewHolder = new viewHolderForEventList(itemVIew);





                return viewHolder;
            }
        };

        mrecyclerView.setLayoutManager(mlayoutManager);

        firebaseRecyclerAdapter.startListening();

        //setting adapter

        mrecyclerView.setAdapter(firebaseRecyclerAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.add_event, menu);
        MenuItem item = menu.findItem(R.id.addEvent);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addEvent:
                // Not implemented here


                openDialogue() ;


                return false;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openDialogue() {

        Dialog  dialog = new Dialog(upcomingEventList.this) ;
        dialog.setContentView(R.layout.login_dialogue_for_create_event);

        pass = dialog.findViewById(R.id.etPassword) ;
        login = dialog.findViewById(R.id.btnLogin) ;
        pbar = dialog.findViewById(R.id.progressBarDialogue);
        pbar.setVisibility(View.GONE);

        dialog.show();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pbar.setVisibility(View.VISIBLE);

                final String Pass = pass.getText().toString();


                DatabaseReference mref  = FirebaseDatabase.getInstance().getReference("adminPass");

                                mref.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                        modelForPAss model = dataSnapshot.getValue(modelForPAss.class);


                                        try {
                                            PASS =model.getPass() ;
                                            if (PASS.contains(Pass)){

                                                Intent i = new Intent(upcomingEventList.this , addEvent.class) ;
                                                startActivity(i);


                                            }
                                            else {
                                                pbar.setVisibility(View.GONE);

                                                Toast.makeText(upcomingEventList.this , "PassWord Wrong"  , Toast.LENGTH_LONG).show();
                                                pass.setText("");
                                            }

                                        }
                                        catch (Exception e ){

                                            Toast.makeText(upcomingEventList.this , "Error : "+ e.getMessage()  , Toast.LENGTH_LONG).show();
                                            pass.setText("");

                                            pbar.setVisibility(View.GONE);

                                        }


                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {


                                        pbar.setVisibility(View.GONE);
                                    }
                                });




            }
        });



    }


    @Override
    protected void onStart() {
        super.onStart();
        showData();
    }
}
