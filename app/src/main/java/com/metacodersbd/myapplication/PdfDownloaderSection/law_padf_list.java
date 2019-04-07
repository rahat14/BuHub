package com.metacodersbd.myapplication.PdfDownloaderSection;

import android.content.Intent;
import android.os.Handler;
import androidx.core.view.MenuItemCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.metacodersbd.myapplication.R;

public class law_padf_list extends AppCompatActivity {

    LinearLayoutManager mlayoutManager ;
    RecyclerView mrecyclerView ;
    FirebaseDatabase mFirebaseDatabase ;
    DatabaseReference mRef ;
    public ProgressBar cse_bar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_law_padf_list);


        cse_bar= (ProgressBar)findViewById(R.id.progressbar_LAW) ;

        Toast toast = Toast.makeText(getApplicationContext(), "Wait For The  Loading To End", Toast.LENGTH_LONG);
        toast.show();

        //calling RecyclerView
        mrecyclerView = findViewById(R.id.recyclerView_law_pdf);
        mrecyclerView.setHasFixedSize(true);

        //set layout as LinearLayout
        mlayoutManager = new LinearLayoutManager(this);


        GridLayoutManager mLayoutManager = new GridLayoutManager(this , 2 );

        mrecyclerView.setLayoutManager(mLayoutManager);
        //send Query to FirebaseDatabase
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference("Pdf_db").child("LAW");
        mRef.keepSynced(true);

    }

    // now Implement the firebase search
    public  void firebaseSearch(String searchtext){

        String query = searchtext.toUpperCase() ;
        Query firebaseSearchQuery = mRef.orderByChild("title").startAt(query).endAt(query + "\uf8ff");


        FirebaseRecyclerAdapter<Model , viewHolder_cse> firebaseRecyclerAdapter =

                new FirebaseRecyclerAdapter<Model, viewHolder_cse>(

                        Model.class ,
                        R.layout.row_for_cse_pdf,
                        viewHolder_cse.class,
                        firebaseSearchQuery



                ) {


                    @Override
                    protected void populateViewHolder(viewHolder_cse viewHolder, Model model, int position) {

                        viewHolder.SetDeatils(getApplicationContext() , model.getTitle() , model.getWriter() , model.getLink());


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

    }










    //creating a function which will load data to Recylceview via OnStart Override method
    @Override
    protected void onStart() {
        super.onStart();

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
