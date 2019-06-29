package com.metacodersbd.myapplication.ChatSystemUniversal;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.metacodersbd.myapplication.PdfDownloaderSection.viewHolder_cse;
import com.metacodersbd.myapplication.R;

import de.hdodenhof.circleimageview.CircleImageView;


public class viewHolderForTeacherList extends RecyclerView.ViewHolder {

    CircleImageView imageView_pp;
    public Button dltebutton;

    View mview;



    public viewHolderForTeacherList(@NonNull View itemView) {
        super(itemView);

        mview = itemView;

        //item click
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                mClickListener.onItemClick(v , getAdapterPosition());

            }
        });

        //item long click
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mClickListener.onItemClick(v , getAdapterPosition());

                return true;
            }
        });
    }



    //setting details for row loaded into recycle view ROw

    public void setDetails(Context ctx, String name , String dept , String pplinK , String id , String notificationID ) {

        //assaging views
        TextView nameTv = mview.findViewById(R.id.rnameOfTeacher);
        TextView deptTv = mview.findViewById(R.id.rdeptOfTeacher);
        imageView_pp = mview.findViewById(R.id.image_teacher) ;


        // Loading views
        nameTv.setText(name);
        deptTv.setText(dept);
        Glide.with(ctx).load(pplinK).into(imageView_pp);



    }


    private static viewHolderForTeacherList.ClickListener mClickListener;

    //interface to send callbacks
    public interface ClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }
    public static void setOnClickListener(viewHolderForTeacherList.ClickListener clickListener){
        mClickListener = clickListener;
    }


}
