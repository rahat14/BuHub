package com.metacodersbd.myapplication.BloodActivity;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.metacodersbd.myapplication.R;

import java.sql.Time;

import de.hdodenhof.circleimageview.CircleImageView;

public class viewHolderForReqBlood extends RecyclerView.ViewHolder {

    View  mview ;
    Button number ;


    public viewHolderForReqBlood(@NonNull View itemView) {

        super(itemView);
        mview = itemView ;



    }


    public  void setDataToView(Context context , String postID  , String  uid , String needer , String loc , String timee
                 , String datee , String bg   , String phone , String purl  ){


        //setting the view
        TextView name = (TextView) mview.findViewById(R.id.rowNameReqBlood);
        CircleImageView image = mview.findViewById(R.id.rowImageReqBlood);
        TextView Loc = mview.findViewById(R.id.rowLocationReqBlood);
        Button bld = mview.findViewById(R.id.rowBloodGroupReqBlood);
         number = mview.findViewById(R.id.rowPhoneNumBlood);
        TextView date  = mview.findViewById(R.id.rowTimeReqBlood) ;


        //setting data to view  ;

        name.setText(needer);
        Loc.setText(loc);
        bld.setText(bg);
        number.setText(phone);
        date.setText("Time :"+ timee +" " +  datee);


//glide the profile image

        Glide.with(context).load(purl).diskCacheStrategy(DiskCacheStrategy.ALL).into(image) ;

    }
    private static viewHolderForReqBlood.ClickListener mClickListener;

    //interface to send callbacks
    public interface ClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }




}
