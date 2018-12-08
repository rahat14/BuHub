package com.metacodersbd.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class RegisterFragment extends AppCompatActivity {
EditText mail , name , pass  , phn ;
Button Acbutton ;
ProgressBar reg_progress ;
String Rmail , Rpass , Rphn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_fragment);



        //connecting with the layout with id
        mail = (EditText)findViewById(R.id.RegMail) ;
        name = (EditText)findViewById(R.id.RegName );
        phn = (EditText) findViewById(R.id.RegPhn);
        pass = (EditText) findViewById(R.id.RegPass) ;
        Acbutton = (Button)findViewById(R.id.button_reg) ;
        reg_progress = (ProgressBar)findViewById(R.id.preogressbar_register);








    }
}
