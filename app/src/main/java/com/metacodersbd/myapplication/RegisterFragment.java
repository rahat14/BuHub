package com.metacodersbd.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterFragment extends AppCompatActivity {
EditText mail , name , pass  , phn ;
Button Acbutton ;
ProgressBar reg_progress ;
String Rmail , Rpass , Rphn , Rname;
FirebaseAuth mAtuh ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_fragment);
        mAtuh = FirebaseAuth.getInstance() ;


        //connecting with the layout with id
        mail = (EditText)findViewById(R.id.RegMail) ;
        name = (EditText)findViewById(R.id.RegName );
        phn = (EditText) findViewById(R.id.RegPhn);
        pass = (EditText) findViewById(R.id.RegPass) ;
        Acbutton = (Button)findViewById(R.id.button_reg) ;
        reg_progress = (ProgressBar)findViewById(R.id.preogressbar_register);
            reg_progress.setVisibility(View.INVISIBLE);

        //listening to the buttons
        Acbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getting the data from the views

                Rmail = mail.getText().toString() ;
                Rpass = pass.getText().toString() ;
                Rphn = phn.getText().toString() ;
                Rname =name.getText().toString() ;

                //checking all the text r empty or not

                if(!TextUtils.isEmpty(Rmail) || !TextUtils.isEmpty(Rpass) || !TextUtils.isEmpty(Rphn) || !TextUtils.isEmpty(Rname)){
                        reg_progress.setVisibility(View.VISIBLE);
                    createAccount(Rname , Rmail , Rpass , Rphn);





                }
                else {


                    Toast.makeText(getApplicationContext() , "Please Enter The Right Value ",Toast.LENGTH_SHORT)
                    .show();

                }








            }
        });









    }


    public  void  createAccount(String  user  , String usermail , String userpass , String phn  ){
                    final    String aname =user ;
                      final  String aphn= phn ;

                            mAtuh.createUserWithEmailAndPassword(usermail , userpass)
                                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                          if(task.isSuccessful()){

                                              Intent i  = new Intent(getApplicationContext() , accountSetupPage.class);
                                              i.putExtra("name", aname) ;
                                             i.putExtra("phn",aphn);

                                              startActivity(i);
                                              finish();


                                          }
                                          else {
                                              reg_progress.setVisibility(View.INVISIBLE);
                                              String e = task.getException().getMessage() ;
                                              Toast.makeText(getApplicationContext() , "Error"+e , Toast.LENGTH_SHORT)
                                                      .show();

                                          }


                                        }
                                    });



    }


}
