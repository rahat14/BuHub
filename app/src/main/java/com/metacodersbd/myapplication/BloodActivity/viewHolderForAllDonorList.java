package com.metacodersbd.myapplication.BloodActivity;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.metacodersbd.myapplication.R;

public class viewHolderForAllDonorList extends RecyclerView.ViewHolder {

    View mview ;
    Button triigerDiagoue ;



    public viewHolderForAllDonorList(View itemView) {
        super(itemView);

        mview = itemView ;

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
    public  void  SetDeatils(Context ctx , String name , String deptname  , String bgroup , String phnNumber , String pplink  ){

        // assagianing VIews
        TextView mName_tv = mview.findViewById(R.id.nameRow);
        TextView mdeptName = mview.findViewById(R.id.deptNameRow) ;
        TextView mPhoneNum = mview.findViewById(R.id.phnNumberRow);
        TextView mgroupName = mview.findViewById(R.id.bldGroupRow) ;
        ImageView imageOnRow = mview.findViewById(R.id.imageIDRow);
        triigerDiagoue = mview.findViewById(R.id.triggerDialogueRow);



        String get_Link ;

        //seting data to views ;

        mName_tv.setText(name);
        mdeptName.setText(deptname);
        mPhoneNum.setText(phnNumber);
        mgroupName.setText(bgroup);


        Glide.with(ctx).load(pplink).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageOnRow) ;



        //get_Link = link ;




    }


    private static viewHolderForAllDonorList.ClickListener mClickListener;

    //interface to send callbacks
    public interface ClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }
    public static void setOnClickListener(viewHolderForAllDonorList.ClickListener clickListener){
        mClickListener = clickListener;
    }



}
