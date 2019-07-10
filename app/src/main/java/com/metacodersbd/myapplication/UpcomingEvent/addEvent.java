package com.metacodersbd.myapplication.UpcomingEvent;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.metacodersbd.myapplication.R;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Scanner;

public class addEvent extends AppCompatActivity {
    String amPmChecker ;
    Spinner  spinner  ;

    TextView datePkr_dialog,mdate,timePkr_dialog,time;
    EditText evntName, event_details, feeEdit , venuEidt;
    DatePickerDialog  datePickerDialog ;
    String uid ;
    FirebaseAuth mauth ;
    ProgressBar mbar ;

    MaterialButton button ;
    String title , description , date = "" , timeS = "" , location , dept , fee  , Month  ;
    int MONTH ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_event);
        mauth = FirebaseAuth.getInstance();
        uid = mauth.getUid();
        getSupportActionBar().hide();


        mdate = findViewById(R.id.start_date);
        datePkr_dialog  = findViewById(R.id.strt_datePkr_dialog);
        time = findViewById(R.id.time);
        timePkr_dialog = findViewById(R.id.timePkr_dialog);
        evntName = (EditText)findViewById(R.id.evntName);
        event_details = (EditText)findViewById(R.id.event_details);
        button = findViewById(R.id.submitBtn) ;
        spinner = findViewById(R.id.DeptName) ;
        venuEidt = findViewById(R.id.eVenue);
        feeEdit = findViewById(R.id.eFee) ;
        mbar = findViewById(R.id.progresBarEvent);







        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext() , android.R.layout.simple_spinner_item
                ,getResources().getStringArray(R.array.DPTarray));
        adapter.setDropDownViewResource(android.R.layout.simple_selectable_list_item);
        spinner.setAdapter(adapter);
        mbar.setVisibility(View.INVISIBLE);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mbar.setVisibility(View.VISIBLE);
                dept = spinner.getSelectedItem().toString().toUpperCase() ;
                title  = evntName.getText().toString().trim();
                description = event_details.getText().toString().trim() ;
                fee = feeEdit.getText().toString();
                location = venuEidt.getText().toString();


                if(!TextUtils.isEmpty(title) && !TextUtils.isEmpty(description) && !TextUtils.isEmpty(date) && !TextUtils.isEmpty(timeS)
                        && !dept.contains("CHOOSE DEPARTMENT") && !TextUtils.isEmpty(fee)  && !TextUtils.isEmpty(location)  )

                {
                    sendDataToFirebase(title , description , date , timeS ,location , dept ,fee)  ;

                }
                else {
                    mbar.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext() , "Plzz Fill It Properly ", Toast.LENGTH_LONG)
                            .show();



                }




            }
        });




        datePkr_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleDate();

            }
        });

        timePkr_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleTime();

            }
        });

    }

    private void sendDataToFirebase(String title, String description, String date, String timeS, String location, String dept , String feee) {

        if (MONTH == 1 ){
            Month = "January";

        }
        else if(MONTH == 2 ){

            Month = "February";

        }
        else if(MONTH == 3 ){

            Month = "March";
        }
        else if(MONTH == 4 ){

            Month = "April";
        }
        else if(MONTH == 5 ){
            Month = "May";
        }
        else if(MONTH == 6 ){
            Month = "June";
        }
        else if(MONTH == 7 ){
            Month = "July";
        }
        else if(MONTH == 8 ){
            Month = "August";
        }
        else if(MONTH == 9 ){
            Month = "September";
        }
        else if(MONTH == 10 ){
            Month = "October";
        }else if(MONTH == 11 ){
            Month = "November";
        }
        else if(MONTH ==12 ){
            Month = "December";

        }

        DatabaseReference mref   = FirebaseDatabase.getInstance().getReference("EventList");
        String pushid = mref.push().getKey();

        model  model  = new model(pushid , uid , title , description , date , Month, fee , location,timeS , dept) ;

        mref.child(pushid).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                sendNotification();


                finish();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                mbar.setVisibility(View.INVISIBLE);

                Toast.makeText(getApplicationContext() ,"Error : "+ e.getMessage()  , Toast.LENGTH_LONG)

                        .show();

            }
        }) ;






    }


    private  void  handleTime(){


        Calendar calendar = Calendar.getInstance();

        int HOUR = calendar.get(Calendar.HOUR);
        int MINUTE = calendar.get(Calendar.MINUTE);


        TimePickerDialog timePickerDialog = new TimePickerDialog(this , R.style.TimePickerTheme, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int HOUR, int MINUTE) {


                if (HOUR >= 12){
                    amPmChecker = "AM";

                }
                else  {
                    amPmChecker= "PM";
                }

                 timeS = HOUR+":"+MINUTE + " "+ amPmChecker;
                time.setText(timeS);


            }
        }, HOUR , MINUTE,false);
        timePickerDialog.show();

    }

    private void handleDate(){
        Calendar c = Calendar.getInstance();

        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);

        datePickerDialog = new DatePickerDialog(addEvent.this,R.style.TimePickerTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                date = dayOfMonth+"" ;
                MONTH = month+1 ;
                mdate.setText(dayOfMonth + "/"+(month+1)+"/"+year);


            }
        } ,year , month , day);

        datePickerDialog.show();



    }



    private void sendNotification() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                int SDK_INT = android.os.Build.VERSION.SDK_INT;
                if (SDK_INT > 8) {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                            .permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    //    String send_email;

                    //This is a Simple Logic to Send Notification different Device Programmatically....
                    //   send_email = "admin@gmail.com";


                    try {
                        String jsonResponse;

                        URL url = new URL("https://onesignal.com/api/v1/notifications");
                        HttpURLConnection con = (HttpURLConnection) url.openConnection();
                        con.setUseCaches(false);
                        con.setDoOutput(true);
                        con.setDoInput(true);

                        con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                        con.setRequestProperty("Authorization", "Basic MjE4ZmFhMjUtMDJjYS00NTVmLTgxYzUtYTdmOTg3NmRiNTJl");
                        con.setRequestMethod("POST");


                        String strJsonBody = "{"
                                + "\"app_id\": \"d5bfd3e2-586c-4087-8aba-1af62f81e4ec\","
                                + "\"included_segments\": [\"Subscribed Users\"],"
                                + "\"data\": {\"foo\": \"bar\"},"
                                + "\"contents\": {\"en\": \"New Event Available!!Check The Event List\"}"
                                + "}";


                        System.out.println("strJsonBody:\n" + strJsonBody);

                        byte[] sendBytes = strJsonBody.getBytes("UTF-8");
                        con.setFixedLengthStreamingMode(sendBytes.length);

                        OutputStream outputStream = con.getOutputStream();
                        outputStream.write(sendBytes);

                        int httpResponse = con.getResponseCode();
                        System.out.println("httpResponse: " + httpResponse);

                        if (httpResponse >= HttpURLConnection.HTTP_OK
                                && httpResponse < HttpURLConnection.HTTP_BAD_REQUEST) {
                            Scanner scanner = new Scanner(con.getInputStream(), "UTF-8");
                            jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
                            scanner.close();
                        } else {
                            Scanner scanner = new Scanner(con.getErrorStream(), "UTF-8");
                            jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
                            scanner.close();
                        }
                        System.out.println("jsonResponse:\n" + jsonResponse);

                    } catch (Throwable t) {
                        t.printStackTrace();
                    }
                }
            }
        });
    }


}
