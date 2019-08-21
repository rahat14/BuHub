package com.metacodersbd.myapplication.profilePackage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeErrorDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeSuccessDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.metacodersbd.myapplication.R;
import com.metacodersbd.myapplication.homePage;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class editProfilePage extends AppCompatActivity {

    EditText profileName  , contactNum , cgpa , Batch , BG;
    Button submitBtn ;
    String profilename , contactnum ,CGPA , batch , bg ;
    String uid , link  ;
    DatabaseReference mref ;
    CircleImageView imageView ;
    TextView profileNameTv ;
    FirebaseAuth mauth ;



    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_page);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        mauth =FirebaseAuth.getInstance();
        uid = mauth.getUid() ;

        mref = FirebaseDatabase.getInstance().getReference("Users");






        //init view
        profileNameTv = findViewById(R.id.profile_name);
        profileName = findViewById(R.id.mail_profile);
        contactNum = findViewById(R.id.phoneNumberProfile);
        cgpa =  findViewById(R.id.cgpaEditProfile);
        Batch = findViewById(R.id.batchNumEdit);
        BG = findViewById(R.id.bloodGroupEdit);
        imageView = findViewById(R.id.image_profile);
        submitBtn = findViewById(R.id.buttonSubmitProfile);

        // getting the data from the previous activitiy

   /*
        i.putExtra("NAME" ,name.getText().toString()) ;
        i.putExtra("PHONE", Phone.getText().toString());
        i.putExtra("CGPA", cgpa.getText().toString());
        i.putExtra("BGG", Blood_Group.getText().toString());
        i.putExtra("MAIL", mail_address.getText().toString());
        i.putExtra("BATCH", batch_name.getText().toString());
        i.putExtra("URL",url);
*/
        Intent i = getIntent();

        profileName.setText( i.getStringExtra("NAME") , TextView.BufferType.EDITABLE);
        contactNum.setText(i.getStringExtra("PHONE"));
        cgpa.setText(i.getStringExtra("CGPA"));
        Batch.setText(i.getStringExtra("BATCH"));
        BG.setText(i.getStringExtra("BGG"));
        link = i.getStringExtra("URL") ;

        Glide.with(getApplicationContext()).load(link).error(R.drawable.f).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
        profileNameTv.setText( i.getStringExtra("NAME"));





        //click  on the submit button
                submitBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        profilename = profileName.getText().toString() ;
                        contactnum = contactNum.getText().toString() ;
                        CGPA = cgpa.getText().toString();
                        batch = Batch.getText().toString();
                        bg = BG.getText().toString().toLowerCase() ;


                    // checking if all the next r not empty
                        if(TextUtils.isEmpty(profilename ) ||TextUtils.isEmpty(contactnum ) ||TextUtils.isEmpty( CGPA) || TextUtils.isEmpty(batch ) || TextUtils.isEmpty(bg))
                        {
                            bg = org.apache.commons.lang3.StringUtils.replace(bg, " ", "");

                            Toast.makeText(getApplicationContext(), "Plzz Fill The Form Properly", Toast.LENGTH_SHORT)
                                    .show();


                        }

                        else {

                            UpdateTheData(profilename , contactnum, CGPA , batch, bg) ;
                        }


                    }
                });



                // setting the Touch listener

        BG.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {

                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (BG.getRight() - BG.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        // edittextview_confirmpassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

                        BG.getText().clear();

                        return true;
                    }
                }else{

                    BG.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);

                }


                return false;
            }
        });




        Batch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {

                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (Batch.getRight() - Batch.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        // edittextview_confirmpassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

                        Batch.getText().clear();

                        return true;
                    }
                }else{

                    Batch.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);

                }


                return false;
            }
        });




        cgpa.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {

                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (cgpa.getRight() - cgpa.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        // edittextview_confirmpassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

                        cgpa.getText().clear();

                        return true;
                    }
                }else{

                    cgpa.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);

                }


                return false;
            }
        });



        contactNum.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {

                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (contactNum.getRight() - contactNum.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        // edittextview_confirmpassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

                        contactNum.getText().clear();

                        return true;
                    }
                }else{

                    contactNum.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);

                }


                return false;
            }
        });







        profileName.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (profileName.getRight() - profileName.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        // edittextview_confirmpassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

                        profileName.getText().clear();

                        return true;
                    }
                }else{

                  profileName.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);

                }

                return false;
            }
        });





    }

    private void UpdateTheData(String profilename, String contactnum, String cgpa, String batch, String bg) {

                mref.child(uid).child("user_name").setValue(profilename);
                mref.child(uid).child("user_batch").setValue(batch);
                mref.child(uid).child("user_bloodgroup").setValue(bg);
                 mref.child(uid).child("user_phn").setValue(contactnum).addOnCompleteListener(new OnCompleteListener<Void>() {
                     @Override
                     public void onComplete(@NonNull Task<Void> task) {

                         new AwesomeSuccessDialog(editProfilePage.this)
                                 .setTitle("Your Data Updated!!")
                                 .setMessage("Your Data Has Been Updated")
                                 .setColoredCircle(R.color.dialogSuccessBackgroundColor)
                                 .setDialogIconAndColor(R.drawable.ic_dialog_info, R.color.white)
                                 .setCancelable(false)
                                 .setPositiveButtonText(getString(R.string.dialog_yes_button))
                                 .setPositiveButtonbackgroundColor(R.color.dialogSuccessBackgroundColor)
                                 .setPositiveButtonTextColor(R.color.white)
                                 .setPositiveButtonClick(new Closure() {
                                     @Override
                                     public void exec() {

                                         //click

                                         Intent i = new Intent(getApplicationContext() , homePage.class);
                                         startActivity(i);

                                     }
                                 })

                                 .show();

                     }
                 })
                         .addOnFailureListener(new OnFailureListener() {
                             @Override
                             public void onFailure(@NonNull Exception e) {

                                 new AwesomeErrorDialog(getApplicationContext())
                                         .setTitle("Update Error !!")
                                         .setMessage("SomeThing Went Wrong With Server Try Again Later !! ")
                                         .setColoredCircle(R.color.dialogErrorBackgroundColor)
                                         .setDialogIconAndColor(R.drawable.ic_dialog_error, R.color.white)
                                         .setCancelable(true).setButtonText(getString(R.string.dialog_ok_button))
                                         .setButtonBackgroundColor(R.color.dialogErrorBackgroundColor)
                                         .setButtonText(getString(R.string.dialog_ok_button))
                                         .setErrorButtonClick(new Closure() {
                                             @Override
                                             public void exec() {

                                                 // click
                                        //        new AwesomeErrorDialog(getApplicationContext())
                                                     //   .hide();

                                                 finish();
                                             }
                                         })
                                         .show();
                             }







                         });







    }
}
