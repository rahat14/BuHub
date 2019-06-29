package com.metacodersbd.myapplication.ChatSystemUniversal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.metacodersbd.myapplication.R;
import com.metacodersbd.myapplication.contactWithTeacher.chatWithTeacher;


public class teacherChat extends Fragment {
    View view;

    public teacherChat() {

    }
    LinearLayoutManager mlayoutManager ;
    RecyclerView mrecyclerView ;
    FirebaseDatabase mFirebaseDatabase ;
    DatabaseReference mRef ,  mdref ;
    FirebaseAuth mauth ;
    FirebaseRecyclerAdapter<modelForTeacherList, viewHolderForTeacherList> firebaseRecyclerAdapter ;
    FirebaseRecyclerOptions<modelForTeacherList> options ;


    Query firebaseSearchQuery ;
    String  uid ,batch   , dpt  ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.teacher_chat_list, container, false);


        mauth = FirebaseAuth.getInstance() ;
        uid = mauth.getUid();


        chatRoom activity = (chatRoom) getActivity();

         batch = activity.getMyBatch() ;
         dpt = activity .getMyDpart() ;

        batch = dpt + " "+ batch ;


        //append teacher list here in recycelr view ...
        //send Query to FirebaseDatabase
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference("teacherList");
        mdref = FirebaseDatabase.getInstance().getReference("teacherList");
        mRef.keepSynced(true);

        //query to search own upload

        firebaseSearchQuery  = mRef.orderByChild("name").startAt(uid).endAt(uid + "\uf8ff");



        //calling RecyclerView
        mrecyclerView = view.findViewById(R.id.reclyclerViewForTEacherList);
        mrecyclerView.setHasFixedSize(true);
        //set layout as LinearLayout
        mlayoutManager = new LinearLayoutManager(getContext());
        mrecyclerView.setLayoutManager(mlayoutManager);



            loadDataToFirbase();

        return view;
    }
    private void loadDataToFirbase() {

        options = new FirebaseRecyclerOptions.Builder<modelForTeacherList>().setQuery(mRef, modelForTeacherList.class)
                .build();

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<modelForTeacherList, viewHolderForTeacherList>(options) {
            @Override
            protected void onBindViewHolder(@NonNull viewHolderForTeacherList holder, final int position, @NonNull final modelForTeacherList model) {

                holder.setDetails(getContext(), model.getName() , model.getDept() , model.getPplink() , model.getId() , model.getNotificationID());




            }

            @NonNull
            @Override
            public viewHolderForTeacherList onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                //INflate the row
                Context context;
                View itemVIew = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_for_teacher_list, parent, false);

                viewHolderForTeacherList viewHolder = new viewHolderForTeacherList(itemVIew);


                viewHolderForTeacherList.setOnClickListener(new viewHolderForTeacherList.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {


                        // data from views
                        String Name = getItem(position).getName() ;
                        String id = getItem(position).getId() ;





                        Intent i = new Intent(getContext() , chatWithTeacher.class);
                        i.putExtra("name",Name ) ;
                        i.putExtra("id", id) ;
                        i.putExtra("batch", batch ) ;


                        startActivity(i);


                    }

                    @Override
                    public void onItemLongClick(View view, int position) {

                    }
                });

                //itemClicklistener


                return viewHolder;
            }

        };
        mrecyclerView.setLayoutManager(mlayoutManager);
        firebaseRecyclerAdapter.startListening();
        //setting adapter

        mrecyclerView.setAdapter(firebaseRecyclerAdapter);
    }
}
