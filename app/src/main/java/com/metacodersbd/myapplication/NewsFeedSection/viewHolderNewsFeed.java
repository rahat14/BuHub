package com.metacodersbd.myapplication.NewsFeedSection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.metacodersbd.myapplication.PdfDownloaderSection.viewHolder_cse;
import com.metacodersbd.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

//

public class viewHolderNewsFeed extends RecyclerView.ViewHolder {


    CircleImageView imageView_pp ;
    public  ImageButton loveBtn ;
    public    ImageView imagep ;
    public  ImageButton shareBtn ;
    TextView likeVIew ;





    View mview ;
    public viewHolderNewsFeed(@NonNull View itemView) {
        super(itemView);

        mview = itemView ;



        loveBtn = mview.findViewById(R.id.loveReacBtn);
        shareBtn = mview.findViewById(R.id.shareButton);













    }

    //setting details for row loaded into recycle view ROw

    public  void  serDetails(Context ctx  ,String title, String image, String uid, String plink, String name ,String date , String pushid , String likeCOunt ){

                        //assaging views
        TextView nameTv = mview.findViewById(R.id.name_newwsfeed);
        TextView titleTv = mview.findViewById(R.id.titletv);
        imageView_pp = mview.findViewById(R.id.image_profile_on_newsFeed);
        imagep = mview.findViewById(R.id.image_on_newsFeed);
        TextView dateView = mview.findViewById(R.id.rdateView);
         likeVIew = mview.findViewById(R.id.likeCountView) ;






                        // Loading views

        nameTv.setText(name);
        titleTv.setText(title);
        dateView.setText(date);
        likeVIew.setText(likeCOunt);
     //   Picasso.get().load(image).error(R.drawable.loadingdialoge).into(imagep);

        if(image.equals("null")){

            imagep.setVisibility(View.GONE);
        }
        else {
            imagep.setVisibility(View.VISIBLE);
            Glide.with(ctx).load(image).error(R.drawable.f).diskCacheStrategy(DiskCacheStrategy.ALL).into(imagep);

        }

        Glide.with(ctx).load(plink).error(R.drawable.plaementpro).into(imageView_pp);


    //    Picasso.get().load(image).placeholder(R.drawable.loadingdialoge).error(R.drawable.loadingdialoge).into(imagep);
      //  Picasso.get().load(plink).error(R.drawable.loadingdialoge).into(imageView_pp);


    }






}
