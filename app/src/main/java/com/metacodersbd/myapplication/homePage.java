package com.metacodersbd.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Base64;
import android.util.Log;
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
import com.metacodersbd.myapplication.BloodActivity.BloodPage;
import com.metacodersbd.myapplication.ChatSystemUniversal.chatRoom;
import com.metacodersbd.myapplication.FaceDetection.LivePreviewActivity;
import com.metacodersbd.myapplication.NewsFeedSection.newsFeed;
import com.metacodersbd.myapplication.PdfDownloaderSection.pdfViewerByDpartment;
import com.metacodersbd.myapplication.RoutineActivity.RoutineActivity;
import com.metacodersbd.myapplication.UpcomingEvent.upcomingEventList;
import com.metacodersbd.myapplication.loginAcconuntSetup.getProfile;
import com.metacodersbd.myapplication.loginAcconuntSetup.signIn_Controller;
import com.metacodersbd.myapplication.CgpaCalculator.ReminderActivity;
import com.metacodersbd.myapplication.weatherManagement.Function;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;
import org.json.JSONException;
import org.json.JSONObject;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import de.hdodenhof.circleimageview.CircleImageView;
import io.fabric.sdk.android.Fabric;


public class homePage extends AppCompatActivity {
    private static final int PERMISSION_REQUESTS = 1;
    private static final String TAG = "CHOOSE";
    ImageView cgpameter  ;
CardView prfoileBtn , blood_btn , logout , pdfDownloader ,newsfeed_btn ,nottification ,ROutine_btn ,userList_btn, ChatRoom , ebentLIST  ;
FirebaseUser user ;
FirebaseAuth mauth ;
String uid ;
TextView Batch_Meter ;
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


        // checing internetConnected Or Not

        if (!isConnected(homePage.this)){

            final Dialog dialog = new Dialog(homePage.this) ;
            dialog.setContentView(R.layout.no_internet_dialgue) ;
            dialog.setTitle("No Internet!!");


            Button  ok = dialog.findViewById(R.id.ok) ;

            Button exut = dialog.findViewById(R.id.exit) ;

            dialog.show();



            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            exut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    homePage.this.finishAffinity();

                }
            });


        }




        //linking the id
        logout=(CardView) findViewById(R.id.logOut);
        circleImageView = (CircleImageView) findViewById(R.id.image_homepage) ;
        name = (TextView) findViewById(R.id.Homepage_name);
        dpartmentField= (TextView) findViewById(R.id.Homepage_Dpt);
        Batch_Meter = (TextView) findViewById(R.id.batch_meter_homePage);
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
        blood_btn = findViewById(R.id.blodSystemCardView);
        ROutine_btn = findViewById(R.id.Routine_activity);
        ChatRoom = findViewById(R.id.chatSystem);
        ebentLIST = findViewById(R.id.eventList);


        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        boolean fstart = prefs.getBoolean("firstStart", true);
        if (fstart) {

            show_Dialog_after_Install();
        }
        if (!allPermissionsGranted()) {
            getRuntimePermissions();
        }


//linking font
        weatherFont = Typeface.createFromAsset(getAssets(), "weathericonsregularwebfont.ttf");
        weatherIcon.setTypeface(weatherFont);



       loadWeather(city);
        loadingToDataFromFirebase();

        getTotalcountOFUsers();




        //setting up on click listener


        ebentLIST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i  = new Intent(getApplicationContext() , upcomingEventList.class);
                startActivity(i);


            }
        });

        blood_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               Intent i  = new Intent(getApplicationContext() , BloodPage.class);
               startActivity(i);






            }
        });


        ChatRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent o = new Intent(getApplicationContext() , chatRoom.class);
                o.putExtra("NAME", naaam) ;
                o.putExtra("IMAGE" ,pimageLink);
                o.putExtra("BATCH" , batchFirebase);
                o.putExtra("DPARTMENT" ,dpart_Firebase );
                startActivity(o);
            }
        });



        userList_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                getRuntimePermissions();
                String action;
                Intent i = new Intent(getApplicationContext()  , LivePreviewActivity.class);
                startActivity(i);




            }
        });



       

        ROutine_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ROutine = new Intent(getApplicationContext(), RoutineActivity.class);
                ROutine.putExtra("DBNAME" ,dpart_Firebase );
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

        FirebaseUser muser = mauth.getCurrentUser() ;
        if (muser== null){



            Intent o  = new Intent(getApplicationContext() , signIn_Controller.class);
            startActivity(o);
            finish();

        }




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




// batch to meter





    public void loadWeather(String query) {
        if (Function.isNetworkAvailable(getApplicationContext())) {
            DownloadWeather task = new DownloadWeather();
            task.execute(query);
        } else {
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
        }
    }


    class  DownloadWeather extends AsyncTask<String , Void , String > {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... strings) {
            String xml = Function.excuteGet("https://api.openweathermap.org/data/2.5/weather?q=Comilla,bd" +
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
                   // DateFormat df = DateFormat.getDateTimeInstance();
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


                Toast.makeText(getApplicationContext(), "Error, Server is   Too Busy ", Toast.LENGTH_SHORT).show();

             //   Toast.makeText(getApplicationContext(), "Error, Weather  Server Too Busy ", Toast.LENGTH_SHORT).show();

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
                     Batch_Meter.setText(batchFirebase);
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
                  //   batchNumber =Integer.parseInt(batchFirebase) ;

                     Bitmap bitmap =getWidgetBitmap(getApplicationContext(),cgp) ;
                     cgpameter.setImageBitmap(bitmap);










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
    public  String sendDpt(){


        return  dpart_Firebase ;

    }



    public  void getTotalcountOFUsers(){

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

// i used the single or the value.. depending if you want to keep track
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //Log.e(dataSnapshot.getKey(),dataSnapshot.getChildrenCount() + "");

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

    // networkavailablity

    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if ((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting()))
                return true;
            else return false;
        } else
            return false;
    }

    // trigger dialoge after install
    private void show_Dialog_after_Install() {


        new AlertDialog.Builder(this)
                .setTitle("Hey Buians  !!")
                .setMessage("Thanks For Installing The Beta Of The  App. Feel Free To Use It")
                .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create().show();
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this ,R.style.AlertDialogTheme);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        homePage.this.finishAffinity();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }


    private String[] getRequiredPermissions() {
        try {
            PackageInfo info =
                    this.getPackageManager()
                            .getPackageInfo(this.getPackageName(), PackageManager.GET_PERMISSIONS);
            String[] ps = info.requestedPermissions;
            if (ps != null && ps.length > 0) {
                return ps;
            } else {
                return new String[0];
            }
        } catch (Exception e) {
            return new String[0];
        }
    }

    private boolean allPermissionsGranted() {
        for (String permission : getRequiredPermissions()) {
            if (!isPermissionGranted(this, permission)) {
                return false;
            }
        }
        return true;
    }

    private void getRuntimePermissions() {
        List<String> allNeededPermissions = new ArrayList<>();
        for (String permission : getRequiredPermissions()) {
            if (!isPermissionGranted(this, permission)) {
                allNeededPermissions.add(permission);
            }
        }

        if (!allNeededPermissions.isEmpty()) {
            ActivityCompat.requestPermissions(
                    this, allNeededPermissions.toArray(new String[0]), PERMISSION_REQUESTS);
        }
    }

    private static boolean isPermissionGranted(Context context, String permission) {
        if (ContextCompat.checkSelfPermission(context, permission)
                == PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "Permission granted: " + permission);
            return true;
        }
        Log.i(TAG, "Permission NOT granted: " + permission);
        return false;
    }




}








