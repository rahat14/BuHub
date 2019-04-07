package com.metacodersbd.myapplication.newFeedHistory;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.metacodersbd.myapplication.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class viewholderForHistory extends RecyclerView.ViewHolder{


    CircleImageView imageView_pp  ;
    public  Button dltebutton ;

    View mview ;
    public viewholderForHistory(@NonNull View itemView) {
        super(itemView);

        mview = itemView ;



    }

    //setting details for row loaded into recycle view ROw

    public  void  serDetails(Context ctx  , String title, String image, String uid, String plink, String name , String date , String pushId ){

        //assaging views
        TextView nameTv = mview.findViewById(R.id.name_newwsfeed_history);
        TextView titleTv = mview.findViewById(R.id.titletv_history);
        imageView_pp = mview.findViewById(R.id.image_profile_on_newsFeed_history);
        ImageView imagep = mview.findViewById(R.id.image_on_newsFeed_history);
        TextView dateView = mview.findViewById(R.id.rdateView_history);
         dltebutton = mview.findViewById(R.id.dlteButton_history);





        // Loading views
        nameTv.setText(name);
        titleTv.setText(title);
        dateView.setText(date);
        //   Picasso.get().load(image).error(R.drawable.loadingdialoge).into(imagep);



        Glide.with(ctx).load(plink).into(imageView_pp);
        Glide.with(ctx).load(image).placeholder(R.drawable.loadingdialoge).diskCacheStrategy(DiskCacheStrategy.ALL).into(imagep);




        //  Picasso.get().load(plink).error(R.drawable.loadingdialoge).into(imageView_pp);


    }



}
