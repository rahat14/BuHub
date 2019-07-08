package com.metacodersbd.myapplication.PdfDownloaderSection;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.core.view.MenuItemCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.metacodersbd.myapplication.R;

public class english_pdf_list extends AppCompatActivity {
    LinearLayoutManager mlayoutManager ;
    RecyclerView mrecyclerView ;
    FirebaseDatabase mFirebaseDatabase ;
    DatabaseReference mRef ;
    GridLayoutManager mLayoutManager ;

    public ProgressBar cse_bar ;
    FirebaseRecyclerAdapter<Model , viewHolder_cse>firebaseRecyclerAdapter ;
    FirebaseRecyclerOptions<Model> options ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_english_pdf_list);


        cse_bar= (ProgressBar)findViewById(R.id.progressbar_ENG) ;

        Toast toast = Toast.makeText(getApplicationContext(), "Wait For The  Loading To End", Toast.LENGTH_LONG);
        toast.show();

        //calling RecyclerView
        mrecyclerView = findViewById(R.id.recyclerView_eng_pdf);
        mrecyclerView.setHasFixedSize(true);

        //set layout as LinearLayout
        mlayoutManager = new LinearLayoutManager(this);


         mLayoutManager = new GridLayoutManager(this , 2 );

        mrecyclerView.setLayoutManager(mLayoutManager);
        //send Query to FirebaseDatabase
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference("Pdf_db").child("ENGLISH");
        mRef.keepSynced(true);

        showData();
    }

    // now Implement the firebase search



    private void firebaseSearch(String searchtext) {



        String query = searchtext.toUpperCase() ;
        Query firebaseSearchQuery = mRef.orderByChild("title").startAt(query).endAt(query + "\uf8ff");

        options = new FirebaseRecyclerOptions.Builder<Model>().setQuery(firebaseSearchQuery , Model.class)
                .build() ;


        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Model, viewHolder_cse>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final viewHolder_cse holder, final int position, @NonNull Model model) {


                holder.SetDeatils(getApplicationContext() , model.getTitle() , model.getWriter() , model.getLink());
                Handler handler  = new Handler() ;
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        cse_bar.setVisibility(View.GONE);

                    }
                },3000);

            }

            @NonNull
            @Override
            public viewHolder_cse onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

                //INflate the row
                Context context;
                View itemVIew = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_for_cse_pdf, viewGroup, false);

                viewHolder_cse viewHolder = new viewHolder_cse(itemVIew);

                viewHolder_cse.setOnClickListener(new viewHolder_cse.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {


                        // data from views
                        String mlink = getItem(position).getLink() ;



                        // sending those data to new Activity

                        Intent intent = new Intent(view.getContext() , pdfViewer.class);

                        intent.putExtra("LINK", mlink); // put title
                        startActivity(intent);
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {



                    }
                });



                return viewHolder;
            }
        };

        mrecyclerView.setLayoutManager(mLayoutManager);
        firebaseRecyclerAdapter.startListening();
        //setting adapter

        mrecyclerView.setAdapter(firebaseRecyclerAdapter);

    }


    private  void showData(){

        options = new FirebaseRecyclerOptions.Builder<Model>().setQuery(mRef , Model.class)
                .build() ;

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Model, viewHolder_cse>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final viewHolder_cse holder, final int position, @NonNull Model model) {


                holder.SetDeatils(getApplicationContext() , model.getTitle() , model.getWriter() , model.getLink());
                Handler handler  = new Handler() ;
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        cse_bar.setVisibility(View.GONE);

                    }
                },3000);

            }

            @NonNull
            @Override
            public viewHolder_cse onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

                //INflate the row
                Context context;
                View itemVIew = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_for_cse_pdf, viewGroup, false);

                viewHolder_cse viewHolder = new viewHolder_cse(itemVIew);

                viewHolder_cse.setOnClickListener(new viewHolder_cse.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {


                        // data from views
                        String mlink = getItem(position).getLink() ;



                        // sending those data to new Activity

                        Intent intent = new Intent(view.getContext() , pdfViewer.class);

                        intent.putExtra("LINK", mlink); // put title
                        startActivity(intent);
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {



                    }
                });



                return viewHolder;
            }
        };

        mrecyclerView.setLayoutManager(mLayoutManager);
        firebaseRecyclerAdapter.startListening();
        //setting adapter

        mrecyclerView.setAdapter(firebaseRecyclerAdapter);
    }








    //creating a function which will load data to Recylceview via OnStart Override method
    @Override
    protected void onStart() {
        super.onStart();


        /*

        FirebaseRecyclerAdapter <Model , viewHolder_cse> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Model, viewHolder_cse>(

                        Model.class ,
                        R.layout.row_for_cse_pdf,
                        viewHolder_cse.class,
                        mRef



                ) {


                    @Override
                    protected void populateViewHolder(viewHolder_cse viewHolder, Model model, int position) {

                        viewHolder.SetDeatils(getApplicationContext() , model.getTitle() , model.getWriter() , model.getLink());
                        Handler handler  = new Handler() ;
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                cse_bar.setVisibility(View.GONE);

                            }
                        },3000);

                    }



                    @Override
                    public viewHolder_cse onCreateViewHolder(ViewGroup parent, int viewType) {

                        viewHolder_cse viewHolder_cse = super.onCreateViewHolder(parent,viewType) ;


                        viewHolder_cse.setOnClickListener(new viewHolder_cse.ClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {


                                // data from views
                                String mlink = getItem(position).getLink() ;



                                // sending those data to new Activity

                                Intent intent = new Intent(view.getContext() , pdfViewer.class);

                                intent.putExtra("LINK", mlink); // put title
                                startActivity(intent);
                            }

                            @Override
                            public void onItemLongClick(View view, int position) {



                            }
                        });


                        return viewHolder_cse ;
                    }
                };

//set adapter to recyclerview
        mrecyclerView.setAdapter(firebaseRecyclerAdapter);
*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate the menu ; this adds items to the actionBar

        getMenuInflater().inflate(R.menu.search_bar_menu , menu );
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                firebaseSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                firebaseSearch(newText); //filtering  as we Type
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id =item.getItemId();


        return super.onOptionsItemSelected(item);
    }
}
