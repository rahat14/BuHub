package com.metacodersbd.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class signIn_Controller extends AppCompatActivity {

    EditText usermail, userpass;
    String mail, pass;
    Button signin;
    TextView Reg_text;
    private FirebaseAuth mauth;
    ProgressBar login_progrss  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.signin_fragment);


        //connenting with firebase
        mauth = FirebaseAuth.getInstance();

        //connecting with the Xml
        usermail = (EditText) findViewById(R.id.mail_signIn);
        userpass = (EditText) findViewById(R.id.password_signIn);
        signin = (Button) findViewById(R.id.button_signIn);
        Reg_text = (TextView) findViewById(R.id.regBtn);
        login_progrss = (ProgressBar)findViewById(R.id.progressBar_login);

        //setting progress bar
        login_progrss.setVisibility(View.INVISIBLE);


        //setting their action -->
        Reg_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i  = new Intent(getApplicationContext()  , RegisterFragment.class );
                startActivity(i);



            }
        });



        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mail = usermail.getText().toString() ;
                pass = userpass.getText().toString();


                if(!TextUtils.isEmpty(mail)|| !TextUtils.isEmpty(pass) ){
                    login_progrss.setVisibility(View.VISIBLE);
                    LogInAccount(mail , pass);



                }
                else
                {

                    Toast.makeText(getApplicationContext(), "Please Enter Email Or Password " ,Toast.LENGTH_SHORT)
                            .show();

                    login_progrss.setVisibility(View.INVISIBLE);
                }








            }
        });






    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        //FirebaseUser currentUser = mauth.getCurrentUser();

   //     if (currentUser != null) {

           // Intent i = new Intent(getApplicationContext(), homePage.class);
        //    startActivity(i);


//        }


    }
     public void LogInAccount(String uMail , String upass){

        mauth.signInWithEmailAndPassword(uMail , upass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){


                            Intent i = new Intent(getApplicationContext() , homePage.class);
                            startActivity(i);
                            finish();



                        }

                        else
                        {
                            String e ;
                            login_progrss.setVisibility(View.INVISIBLE);
                            e= task.getException().getMessage() ;
                            Toast.makeText(getApplicationContext() , " Error :" + e , Toast.LENGTH_SHORT  )
                                    .show();
                        }


                    }
                });






     }


}
