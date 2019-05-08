package com.metacodersbd.myapplication.userList;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.metacodersbd.myapplication.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;





    public class viewholderForUserList extends RecyclerView.ViewHolder {


        CircleImageView imageView_pp ;

        View mview ;
        public viewholderForUserList(@NonNull View itemView) {
            super(itemView);

            mview = itemView ;


        }

        //setting details for row loaded into recycle view ROw

        public  void  setdetails(Context ctx  , String name, String image ){

            //assaging views
            TextView nameTv = mview.findViewById(R.id.name_userlist);
            imageView_pp = mview.findViewById(R.id.image_profile_userList);



            // Loading views
            nameTv.setText(name);

            //   Picasso.get().load(image).error(R.drawable.loadingdialoge).into(imagep);

            Glide.with(ctx).load(image).placeholder(R.drawable.loadingdialoge).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView_pp);


            //  Picasso.get().load(plink).error(R.drawable.loadingdialoge).into(imageView_pp);


        }



    }