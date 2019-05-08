package com.metacodersbd.myapplication.NewsFeedSection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.metacodersbd.myapplication.PdfDownloaderSection.viewHolder_cse;
import com.metacodersbd.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

//

public class viewHolderNewsFeed extends RecyclerView.ViewHolder {


    CircleImageView imageView_pp ;

                View mview ;
    public viewHolderNewsFeed(@NonNull View itemView) {
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

    public  void  serDetails(Context ctx  ,String title, String image, String uid, String plink, String name ,String date ){

                        //assaging views
        TextView nameTv = mview.findViewById(R.id.name_newwsfeed);
        TextView titleTv = mview.findViewById(R.id.titletv);
        imageView_pp = mview.findViewById(R.id.image_profile_on_newsFeed);
        ImageView imagep = mview.findViewById(R.id.image_on_newsFeed);
        TextView dateView = mview.findViewById(R.id.rdateView);



                        // Loading views
        nameTv.setText(name);
        titleTv.setText(title);
        dateView.setText(date);
     //   Picasso.get().load(image).error(R.drawable.loadingdialoge).into(imagep);


        Glide.with(ctx).load(plink).into(imageView_pp);
        Glide.with(ctx).load(image).placeholder(R.drawable.loadingdialoge).diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.f).into(imagep);


      //  Picasso.get().load(plink).error(R.drawable.loadingdialoge).into(imageView_pp);


    }

    private  viewHolder_cse.ClickListener mClickListener;

    //interface to send callbacks
    public interface ClickListener{
       void onItemClmoick(View view, int position);
        void onItemLongClick(View view, int position);
    }
    public void setOnClickListener(viewHolder_cse.ClickListener clickListener){
        mClickListener = clickListener;
    }



}
