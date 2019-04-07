package com.metacodersbd.myapplication.PdfDownloaderSection;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.metacodersbd.myapplication.R;

public class viewHolder_cse extends RecyclerView.ViewHolder {

                View mview ;


    public viewHolder_cse(View itemView) {
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
    public  void  SetDeatils(Context ctx , String title , String writer , String link ){

                    // assagianing VIews
                    TextView  mtitle_tv = mview.findViewById(R.id.title_row_cse);
                    TextView   mwriter = mview.findViewById(R.id.wrightername_row_cse) ;
                    String get_Link ;

                    //seting data to views ;

                        mtitle_tv.setText(title);
                        mwriter.setText(writer);
                        get_Link = link ;




    }


private  viewHolder_cse.ClickListener mClickListener;

    //interface to send callbacks
    public interface ClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }
    public void setOnClickListener(viewHolder_cse.ClickListener clickListener){
        mClickListener = clickListener;
    }


}
