package com.metacodersbd.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.metacodersbd.myapplication.loginAcconuntSetup.getProfile;
import com.metacodersbd.myapplication.weatherManagement.Function;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import io.fabric.sdk.android.Fabric;


public class homePage extends AppCompatActivity {
ImageView cgpameter  ;
Button prfoileBtn , bloodBtn ;
FirebaseUser user ;
FirebaseAuth mauth ;
String uid ;
ImageView Batch_Meter ;
String city = "London,uk";
String OPEN_WEATHER_MAP_API = "d8f0dc68c7e7caed6908dc6f9edcef61";
TextView  currentWeather , weatherIcon ,  detailsField , name , dpartmentField;
Typeface weatherFont ;
CircleImageView circleImageView ;
String cgpaFirebase , batchFirebase , url  , dpart_Firebase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_home_page);
        mauth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser() ;
        uid = user.getUid() ;






        //linking the id
        circleImageView = (CircleImageView) findViewById(R.id.image_homepage) ;
        name = (TextView) findViewById(R.id.Homepage_name);
        dpartmentField= (TextView) findViewById(R.id.Homepage_Dpt);
        Batch_Meter = (ImageView)findViewById(R.id.batch_meter_homePage);
        cgpameter = (ImageView) findViewById(R.id.cgpa_meter);
        currentWeather=(TextView)findViewById(R.id.current_temperature_field) ;
        weatherIcon =(TextView)findViewById(R.id.weather_icon);
        detailsField = (TextView) findViewById(R.id.description);
        prfoileBtn =(Button)findViewById(R.id.profile_btn) ;
        bloodBtn = (Button)findViewById(R.id.blood_btn) ;

//linking font
        weatherFont = Typeface.createFromAsset(getAssets(), "weathericonsregularwebfont.ttf");
        weatherIcon.setTypeface(weatherFont);

        taskLoadUp(city);
        loadingToDataFromFirebase();





        //setting up on click listener

        prfoileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), Profile.class);
                i.putExtra("UID", uid);
                startActivity(i);


            }
        });

        bloodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {






            }
        });











    }


    private Bitmap getWidgetBitmap(Context context, double percentage) {

        int width = 400;
        int height = 400;
        int stroke = 30;
        int padding = 1;
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
        mTextPaint.setAlpha((int) 255);

        mTextPaint.setColor(Color.argb(255, 9, 198, 223));
        mTextPaint.setTextAlign(Paint.Align.CENTER);

        final RectF arc = new RectF();
        arc.set((stroke/2) + padding, (stroke/2) + padding, width-padding-(stroke/2), height-padding-(stroke/2));

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        //First draw full arc as background.
        paint.setColor(Color.argb(75, 4, 78, 87));
        canvas.drawArc(arc, 135, 275, false, paint);
        //Then draw arc progress with actual value.
        paint.setColor(Color.argb(255, 9, 198, 223));
        canvas.drawArc(arc, 135, fin, false, paint);
        //Draw text value.
        canvas.drawText(percentage +"", bitmap.getWidth() / 2, (bitmap.getHeight() - mTextPaint.ascent()) / 2, mTextPaint);
        //Draw widget title.
        mTextPaint.setTextSize((int) (context.getResources().getDimension(R.dimen.widget_text_large_title) / density));
        canvas.drawText(context.getString(R.string.widget_text_arc_battery), bitmap.getWidth() / 2, bitmap.getHeight()-(stroke+padding), mTextPaint);

        return  bitmap;
    }



    private Bitmap getWidgetBitmapBatch(Context context, int percentage) {

        int width = 400;
        int height = 400;
        int stroke = 30;
        int padding = 1;
       int fin = 250 ;
        float density = context.getResources().getDisplayMetrics().density;
            if (percentage< 8 ){

                fin  = 270 ;


            }
            else if (percentage > 8 ){
                fin = 175 ;
            }

            else
            {
                fin = 240 ;
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
        mTextPaint.setAlpha((int) 255);

        mTextPaint.setColor(Color.argb(255, 9, 198, 223));
        mTextPaint.setTextAlign(Paint.Align.CENTER);

        final RectF arc = new RectF();
        arc.set((stroke/2) + padding, (stroke/2) + padding, width-padding-(stroke/2), height-padding-(stroke/2));

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        //First draw full arc as background.
        paint.setColor(Color.argb(75, 4, 78, 87));
        canvas.drawArc(arc, 135, 275, false, paint);
        //Then draw arc progress with actual value.
        paint.setColor(Color.argb(255, 9, 198, 223));
        canvas.drawArc(arc, 135, fin, false, paint);
        //Draw text value.
        canvas.drawText(percentage +"th", bitmap.getWidth() / 2, (bitmap.getHeight() - mTextPaint.ascent()) / 2, mTextPaint);
        //Draw widget title.
        mTextPaint.setTextSize((int) (context.getResources().getDimension(R.dimen.widget_text_large_title) / density));
        canvas.drawText(context.getString(R.string.widget_text_arc_batch), bitmap.getWidth() / 2, bitmap.getHeight()-(stroke+padding), mTextPaint);

        return  bitmap;
    }

    public void taskLoadUp(String query) {
        if (Function.isNetworkAvailable(getApplicationContext())) {
            DownloadWeather task = new DownloadWeather();
            task.execute(query);
        } else {
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
        }
    }


    class DownloadWeather extends AsyncTask<String , Void , String > {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... strings) {
            String xml = Function.excuteGet("http://api.openweathermap.org/data/2.5/weather?q=Comilla,bd" +
                    "&units=metric&appid=" + OPEN_WEATHER_MAP_API);

            return xml;
        }

        @Override
        protected void onPostExecute(String xml) {

            try {

                JSONObject json = new JSONObject(xml);

                if (json != null) {

                    JSONObject details = json.getJSONArray("weather").getJSONObject(0);
                    JSONObject main = json.getJSONObject("main");
                    DateFormat df = DateFormat.getDateTimeInstance();
                    weatherIcon.setText(Html.fromHtml(Function.setWeatherIcon(details.getInt("id"),
                            json.getJSONObject("sys").getLong("sunrise") * 1000,
                            json.getJSONObject("sys").getLong("sunset") * 1000)));
                    currentWeather.setText(String.format("%.2f", main.getDouble("temp")) + "°");
                    detailsField.setText(details.getString("description").toUpperCase(Locale.US));


                }

            } catch (JSONException e) {

                Toast.makeText(getApplicationContext(), "Error, Check City", Toast.LENGTH_SHORT).show();
            }


        }
    }



         public void  loadingToDataFromFirebase(){

             FirebaseDatabase database = FirebaseDatabase.getInstance() ;
             final DatabaseReference mRef = database.getReference("Users").child(uid);

             mRef.addValueEventListener(new ValueEventListener() {
                 @Override
                 public void onDataChange(@NonNull DataSnapshot dataSnapshot) {



                     getProfile model = dataSnapshot.getValue(getProfile.class);
                     cgpaFirebase = model.getCgpa();
                     batchFirebase = model.getUser_batch();
                     dpart_Firebase = model.getUser_dpt();

                     //setting the value to the views
                     name.setText(model.getUser_name());
                     dpartmentField.setText(dpart_Firebase);
                     url = model.getUser_image() ;
                     Picasso.get().load(url).placeholder(R.drawable.plaementpro).error(R.drawable.plaementpro)
                             .noFade()
                             .into(circleImageView);
                     //converting the string to double
                     double cgp ;
                     int batchNumber ;

                     cgp = Double.parseDouble(cgpaFirebase);
                     batchNumber =Integer.parseInt(batchFirebase) ;

                     Bitmap bitmap =getWidgetBitmap(getApplicationContext(),cgp) ;
                     cgpameter.setImageBitmap(bitmap);

                     Bitmap bit = getWidgetBitmapBatch(getApplicationContext() , batchNumber) ;
                     Batch_Meter.setImageBitmap(bit);








                 }

                 @Override
                 public void onCancelled(@NonNull DatabaseError databaseError) {

                 }

             });









         }

    }













    

