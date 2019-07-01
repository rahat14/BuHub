package com.metacodersbd.myapplication.RoutineActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.chrisbanes.photoview.PhotoView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.metacodersbd.myapplication.R;

public class RoutineActivity extends AppCompatActivity {

    TextView mExamName  , className  ;

    PhotoView examPhoto , classPhoto ;
    String dbname ;

    DatabaseReference databaseReference ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine);

        examPhoto = findViewById(R.id.examRoutin) ;
        classPhoto = findViewById(R.id.Class_Routine) ;
        mExamName = findViewById(R.id.examID);
        className = findViewById(R.id.classid);


        Intent i = getIntent() ;
        dbname = i.getStringExtra("DBNAME") ;

        databaseReference = FirebaseDatabase.getInstance().getReference("routine").child(dbname);








    }

    @Override
    protected void onStart() {
        super.onStart();


        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                modelForRoutine modelForRoutine = dataSnapshot.getValue(modelForRoutine.class);

                className.setText(modelForRoutine.getClassid());

                Glide.with(RoutineActivity.this).load(modelForRoutine.getExamRoutine())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(examPhoto);
                Glide.with(RoutineActivity.this).load(modelForRoutine.getClassRoutine()).diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(classPhoto);
                mExamName.setText(modelForRoutine.getExamID());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
