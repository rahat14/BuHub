package com.metacodersbd.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.metacodersbd.myapplication.loginAcconuntSetup.accountSetupPage;
import com.metacodersbd.myapplication.loginAcconuntSetup.signIn_Controller;

import java.util.Timer;
import java.util.TimerTask;

public class splashScreen extends AppCompatActivity {
    private Timer timer;
    private ProgressBar progressBar;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        Intent i = new Intent(getApplicationContext(), signIn_Controller.class);
        startActivity(i);
        finish();
        // close this activity

        // Start lengthy operation in a background thread

    }




}
