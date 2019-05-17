package com.metacodersbd.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.crashlytics.android.Crashlytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.metacodersbd.myapplication.ChatSystemUniversal.chatPage;
import com.metacodersbd.myapplication.NewsFeedSection.newsFeed;
import com.metacodersbd.myapplication.PdfDownloaderSection.pdfViewerByDpartment;
import com.metacodersbd.myapplication.RoutineActivity.RoutineActivity;
import com.metacodersbd.myapplication.loginAcconuntSetup.getProfile;
import com.metacodersbd.myapplication.loginAcconuntSetup.signIn_Controller;
import com.metacodersbd.myapplication.CgpaCalculator.ReminderActivity;
import com.metacodersbd.myapplication.userList.userList;
import com.metacodersbd.myapplication.weatherManagement.Function;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.util.Locale;

import androidx.cardview.widget.CardView;
import de.hdodenhof.circleimageview.CircleImageView;
import io.fabric.sdk.android.Fabric;


public class homePage extends AppCompatActivity {
ImageView cgpameter  ;
CardView prfoileBtn  , logout , pdfDownloader ,newsfeed_btn ,nottification ,ROutine_btn ,userList_btn, ChatRoom  ;
FirebaseUser user ;
FirebaseAuth mauth ;
String uid ;
    DatabaseReference db  ;

    int ct = 0  ;
Button BLOOD ;
ImageView Batch_Meter ;
String city = "Comilla,bd";
String OPEN_WEATHER_MAP_API = "d8f0dc68c7e7caed6908dc6f9edcef61";
TextView  currentWeather , weatherIcon ,  detailsField , name , dpartmentField , TotalCount;
Typeface weatherFont ;
CircleImageView circleImageView ;
String cgpaFirebase , batchFirebase , url  , dpart_Firebase;
public  static   String pimageLink  ,naaam  ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_home_page);


        mauth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser() ;
        uid = user.getUid() ;

       // printKeyHash();
//nottification

        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .setNotificationOpenedHandler(new notificationOpenHandler())
                .autoPromptLocation(true)
                .init();



        //linking the id
        logout=(CardView) findViewById(R.id.logOut);
        circleImageView = (CircleImageView) findViewById(R.id.image_homepage) ;
        name = (TextView) findViewById(R.id.Homepage_name);
        dpartmentField= (TextView) findViewById(R.id.Homepage_Dpt);
        Batch_Meter = (ImageView)findViewById(R.id.batch_meter_homePage);
        cgpameter = (ImageView) findViewById(R.id.cgpa_meter);
        currentWeather=(TextView)findViewById(R.id.current_temperature_field) ;
        weatherIcon =(TextView)findViewById(R.id.weather_icon);
        detailsField = (TextView) findViewById(R.id.description);
        prfoileBtn =(CardView) findViewById(R.id.profile_btn) ;

        pdfDownloader = (CardView) findViewById(R.id.pdf_downloader) ;
        newsfeed_btn =(CardView) findViewById(R.id.new_fedd);
        nottification = (CardView) findViewById(R.id.notifi_cation);
        TotalCount = (TextView)findViewById(R.id.totalCount);
        userList_btn = findViewById(R.id.userList);
         // BLOOD = findViewById(R.id.blodd_btn_home);
        ROutine_btn = findViewById(R.id.Routine_activity);
        ChatRoom = findViewById(R.id.chatSystem);



//linking font
        weatherFont = Typeface.createFromAsset(getAssets(), "weathericonsregularwebfont.ttf");
        weatherIcon.setTypeface(weatherFont);

        taskLoadUp(city);
        loadingToDataFromFirebase();

        getTotalcountOFUsers();



        //setting up on click listener
        ChatRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent o = new Intent(getApplicationContext() , chatPage.class);
                o.putExtra("NAME", naaam) ;
                o.putExtra("Image" ,pimageLink);
                startActivity(o);
            }
        });

        userList_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent o = new Intent(getApplicationContext() , userList.class);
                startActivity(o);
            }
        });

        ROutine_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ROutine = new Intent(getApplicationContext(), RoutineActivity.class);
                startActivity(ROutine);
            }
        });


        newsfeed_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bv = new Intent(getApplicationContext() , newsFeed.class);
                startActivity(bv);
            }
        });


        pdfDownloader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), pdfViewerByDpartment.class);
                startActivity(i);


            }
        });

        nottification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent g = new Intent(getApplicationContext(), ReminderActivity.class) ;
                startActivity(g);



            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           logoutFuntion();
            }
        });


        prfoileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), Profile.class);
                i.putExtra("UID", uid);
                startActivity(i);


            }
        });












    }

    private void printKeyHash() {

        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.metacodersbd.myapplication",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature :info.signatures){


                String algorithm;
                MessageDigest md  = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("Keyhash", Base64.encodeToString(md.digest(),Base64.DEFAULT));
            }

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void onStart() {
        super.onStart();
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
        canvas.drawText(percentage +"Th", bitmap.getWidth() / 2, (bitmap.getHeight() - mTextPaint.ascent()) / 2, mTextPaint);
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

                Toast.makeText(getApplicationContext(), "Error, Server Failed", Toast.LENGTH_SHORT).show();
            }
            catch (NullPointerException e ){


                Toast.makeText(getApplicationContext(), "Error, Server Is Too Busy ", Toast.LENGTH_SHORT).show();

                Toast.makeText(getApplicationContext(), "Error, Weather  Server Too Busy ", Toast.LENGTH_SHORT).show();

            }


        }
    }



         public void  loadingToDataFromFirebase(){

             FirebaseDatabase database = FirebaseDatabase.getInstance() ;
             final DatabaseReference mRef = database.getReference("Users").child(uid);
             mRef.keepSynced(true);

             mRef.addValueEventListener(new ValueEventListener() {
                 @Override
                 public void onDataChange(@NonNull DataSnapshot dataSnapshot) {



                     getProfile model = dataSnapshot.getValue(getProfile.class);


                     cgpaFirebase = model.getCgpa();
                     batchFirebase = model.getUser_batch();

                     dpart_Firebase = model.getUser_dpt();

                     //setting the value to the views

                     naaam = model.getUser_name() ;
                     name.setText(model.getUser_name());
                     dpartmentField.setText(dpart_Firebase);
                     url = model.getUser_image() ;
                     pimageLink = url ;
/*
                    Picasso.get().load(url).placeholder(R.drawable.plaementpro).error(R.drawable.plaementpro)
                             .noFade()
                             .into(circleImageView);

*/
                     Glide.with(homePage.this)
                             .load(url)
                             .skipMemoryCache(true)
                             .diskCacheStrategy(DiskCacheStrategy.ALL)
                             .dontAnimate()
                             .crossFade()
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

         public  void logoutFuntion(){

             FirebaseAuth.getInstance().signOut();
             sentToLoginpage();

         }

         public  void sentToLoginpage(){

        Intent i = new Intent(getApplicationContext() , signIn_Controller.class);
        startActivity(i);
            finish();

         }
    public class notificationOpenHandler implements  OneSignal.NotificationOpenedHandler {
        @Override
        public void notificationOpened(OSNotificationOpenResult result) {
            String title = result.notification.payload.title;
            String desc = result.notification.payload.body;

            Intent intent = new Intent(getApplicationContext(), pdfViewerByDpartment.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }



    }




    public  String sendData(){


        return pimageLink ;
    }
    public  String sendName(){

        return  naaam ;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            System.exit(0);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    public  void getTotalcountOFUsers(){

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

// i used the single or the value.. depending if you want to keep track
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.e(dataSnapshot.getKey(),dataSnapshot.getChildrenCount() + "");

                if(dataSnapshot.getKey().equals("Users")){


                    TotalCount.setText( "Total Users " + dataSnapshot.getChildrenCount());

                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }







}








