package com.metacodersbd.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;


public class homePage extends AppCompatActivity {
ImageView cgpameter  ;
Button prfoileBtn ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_home_page);


        //linking the id
        cgpameter = (ImageView) findViewById(R.id.cgpa_meter);
        Bitmap bitmap =getWidgetBitmap(getApplicationContext(),3.70) ;
        cgpameter.setImageBitmap(bitmap);
        prfoileBtn =(Button)findViewById(R.id.profile_btn) ;



        //setting up on click listener

        prfoileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), Profile.class);
                startActivity(i);


            }
        });











    }


    private Bitmap getWidgetBitmap(Context context, double percentage) {

        int width = 400;
        int height = 400;
        int stroke = 30;
        int padding = 5;
        float fin ;
        float density = context.getResources().getDisplayMetrics().density;
        if(percentage <=2.20){

            fin =  150;
        }
        else if(percentage<=2.80){

            fin =175;
        }

        else{

            fin = (35*(float)percentage)+135 ;


        }


        //Paint for arc stroke.
        Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG | Paint.DITHER_FLAG | Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(stroke);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        //paint.setStrokeJoin(Paint.Join.ROUND);
        //paint.setPathEffect(new CornerPathEffect(10) );

        //Paint for text values.
        Paint mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize((int) (context.getResources().getDimension(R.dimen.widget_text_large_value) / density));
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setTextAlign(Paint.Align.CENTER);

        final RectF arc = new RectF();
        arc.set((stroke/2) + padding, (stroke/2) + padding, width-padding-(stroke/2), height-padding-(stroke/2));

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        //First draw full arc as background.
        paint.setColor(Color.argb(75, 255, 255, 255));
        canvas.drawArc(arc, 135, 275, false, paint);
        //Then draw arc progress with actual value.
        paint.setColor(Color.WHITE);
        canvas.drawArc(arc, 135, fin, false, paint);
        //Draw text value.
        canvas.drawText(percentage +"", bitmap.getWidth() / 2, (bitmap.getHeight() - mTextPaint.ascent()) / 2, mTextPaint);
        //Draw widget title.
        mTextPaint.setTextSize((int) (context.getResources().getDimension(R.dimen.widget_text_large_title) / density));
        canvas.drawText(context.getString(R.string.widget_text_arc_battery), bitmap.getWidth() / 2, bitmap.getHeight()-(stroke+padding), mTextPaint);

        return  bitmap;
    }
    
}
