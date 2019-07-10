package com.metacodersbd.myapplication.UpcomingEvent;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.metacodersbd.myapplication.BloodActivity.viewHolderForReqBlood;
import com.metacodersbd.myapplication.R;

public class viewHolderForEventList extends RecyclerView.ViewHolder {

    View mView ;

    LinearLayout colorID ;


    public viewHolderForEventList(@NonNull View itemView) {

        super(itemView);
        mView = itemView ;





    }

    public  void setViewToRow(Context ctx, String pushId, String id, String titte, String desc, String date, String month, String fee, String venu, String time, String dpt){

        TextView title =mView.findViewById(R.id.ttileOnRow) ;
        TextView description = mView.findViewById(R.id.descOnRow);
        TextView dateTv = mView.findViewById(R.id.dateOnRow);
        TextView monTv =mView.findViewById(R.id.monOnROw) ;
        TextView feeTv = mView.findViewById(R.id.feeOnRow);
        TextView venuTv = mView.findViewById(R.id.venuOnRow);
        TextView timeView = mView.findViewById(R.id.TimeOnRow);
        colorID = mView.findViewById(R.id.colorChangeId);



//setting data to view ;

        title.setText(titte);
        description.setText(desc);
        dateTv.setText(date);
        monTv.setText(month);
        feeTv.setText(fee);
        venuTv.setText(venu);
        timeView.setText("Start Time :" + time);

        if ( dpt.contains("BBA")){


            colorID.setBackgroundColor(Color.parseColor("#673ab7"));


        }
        else if (dpt.contains("ENG")){

            colorID.setBackgroundColor(Color.parseColor("#ffc107"));
        }

        else if (dpt.contains("LAW")){

            colorID.setBackgroundColor(Color.parseColor("#2196f3"));
        }

    }





}
