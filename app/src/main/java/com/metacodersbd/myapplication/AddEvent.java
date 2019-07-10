package com.metacodersbd.myapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class AddEvent extends AppCompatActivity {

    TextView datePkr_dialog,mdate,timePkr_dialog,time;
    EditText evntName, event_details,eVenue ,  eFee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_event);

       mdate = findViewById(R.id.start_date);
       datePkr_dialog  = findViewById(R.id.strt_datePkr_dialog);
       time = findViewById(R.id.time);
       timePkr_dialog = findViewById(R.id.timePkr_dialog);
       evntName = (EditText)findViewById(R.id.evntName);
       event_details = (EditText)findViewById(R.id.event_details);
       eFee = (EditText)findViewById(R.id.eVenue);
       eVenue = (EditText)findViewById(R.id.eVenue);




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

            private  void  handleTime(){
                Calendar calendar = Calendar.getInstance();

                int HOUR = calendar.get(Calendar.HOUR);
                int MINUTE = calendar.get(Calendar.MINUTE);


                TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int HOUR, int MINUTE) {

                       String Time = HOUR+":"+MINUTE;
                       time.setText(Time);

                    }
                }, HOUR , MINUTE,false);
                timePickerDialog.show();

            }

            private void handleDate(){
              Calendar calendar = Calendar.getInstance();

                int DAY_OF_MONTH = calendar.get(Calendar.DAY_OF_MONTH);
                int MONTH = calendar.get(Calendar.MONTH);
                int YEAR = calendar.get(Calendar.YEAR);

                DatePickerDialog  datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int DAY_OF_MONTH, int MONTH, int YEAR) {

                        MONTH = MONTH+1;
                        String date = DAY_OF_MONTH+"/"+MONTH+"/"+YEAR;
                        mdate.setText(date);

                    }
                },DAY_OF_MONTH,MONTH,YEAR);
                datePickerDialog.show();



            }



}
