package com.metacodersbd.myapplication.loginAcconuntSetup;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.metacodersbd.myapplication.R;
import com.metacodersbd.myapplication.homePage;

import de.hdodenhof.circleimageview.CircleImageView;


public class accountSetupPage extends AppCompatActivity {
    String photoLink ;
    CheckBox   checkBg;
    EditText BatchNum ;
    CircleImageView image ;
    Spinner dropdownMenu  ,Bg  ;
    String dpt ;
    String Name , batch_number ;
    String Phn  ,batch1 ="UnDefined" ;
    String BloodGroup = "NOT AVAILABLE " ;
    String Cgpa="0.00" ;
    ArrayAdapter<CharSequence>adapter ;
    ArrayAdapter<CharSequence>bloodAdapter ;
    EditText Cgpa_fill ;
    Button uploadBtn ;
    Uri mFilePathUri ;
    StorageReference mStorageReference ;
    DatabaseReference mDatabaseReference ;
    ProgressDialog mprogressDialog ;
    String user_id ;
    FirebaseAuth mauth ;
    String a ;
    String databasearent_name = "Users" ;
    String mStoragePath = "All_PP/";
    String mDatabasePath = databasearent_name ;
    String bllod;

    // image request code for
    int IMAGE_REQUEST_CODE = 5  ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setup_page);
        mauth = FirebaseAuth.getInstance() ;
       user_id =mauth.getCurrentUser().getUid().toString();
        //saving the data came from other activitiy

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
          Name = extras.getString("name");
             Phn = extras.getString("phn");

            //The key argument here must match that used in the other activity
        }


        //declareing by id
      checkBg = (CheckBox)findViewById(R.id.checkbox1) ;
      Bg =(Spinner) findViewById(R.id.BG) ;
      dropdownMenu = (Spinner) findViewById(R.id.dropDown) ;
      uploadBtn = (Button)findViewById(R.id.buttonComplete) ;
      BatchNum = (EditText)findViewById(R.id.batchnumber) ;
      image = (CircleImageView) findViewById(R.id.imageBtn);
      Cgpa_fill =(EditText)findViewById(R.id.Cgpa_edit) ;


      // intinal spinner
        adapter = ArrayAdapter.createFromResource(this , R.array.DPT_array ,android.R.layout.simple_spinner_item );
           adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
           dropdownMenu.setAdapter(adapter);
           dropdownMenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
               @Override
               public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    dpt = parent.getItemAtPosition(position).toString() ;
                  //  Toast.makeText(getApplicationContext(), i , Toast.LENGTH_SHORT).show();

               }

               @Override
               public void onNothingSelected(AdapterView<?> parent) {

               }
           });

           //blood.....spinner.....

        bloodAdapter = ArrayAdapter.createFromResource(this ,R.array.Blood_Group,android.R.layout.simple_spinner_item);

        bloodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Bg.setAdapter(bloodAdapter);
        Bg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bllod = parent.getItemAtPosition(position).toString() ;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        // setting on clilck listener
        Bg.setVisibility(View.GONE);
        checkBg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBg.isChecked()){

                    Bg.setVisibility(View.VISIBLE);

                }
                else
                    Bg.setVisibility(View.GONE);



            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //accessing the gallery

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Your Profile Picture") , IMAGE_REQUEST_CODE);


            }
        });

        //setting the upload btn

                    uploadBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {







                            if(checkBg.isChecked()){

                               BloodGroup = bllod;


                            }
                            else {
                                BloodGroup ="Unavailable" ;
                            }


                            // calling the method to upload image to firebase


                            uploadToFirebae();
//,..................................................................................................

//....................................................................................

                        }
                    });

                    //assaign firebase Stroage  Intace to storage object
        mStorageReference = FirebaseStorage.getInstance().getReference() ;


        //assaigning  reference  instance  with the root databas e
        mDatabaseReference = FirebaseDatabase.getInstance().getReference(mDatabasePath);



        //pregress dialog
        mprogressDialog = new ProgressDialog(accountSetupPage.this  );

    }
    private  void uploadToFirebae(){
        //check the image path  uri is empty or not ;
        if(mFilePathUri != null){

            mprogressDialog.setTitle("Image is Uploading...........");
            mprogressDialog.show();

            //create a scond storage
            final StorageReference storageReference2nd = mStorageReference.child(mStoragePath+System.currentTimeMillis()
            +"."
            +getFileExtention(mFilePathUri)) ;


            // ADDING THE ONSUCCESS LISTENER

            storageReference2nd.putFile(mFilePathUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {



                            //get all the value
                            String user_n = Name ;
                            String user_ph = Phn ;
                            String dpart = dpt ;
                            String blood = BloodGroup ;
                            String batchNam = BatchNum.getText().toString() ;
                            Cgpa= Cgpa_fill.getText().toString();


                            mprogressDialog.dismiss();
                            //show toast
                            sentToHome();

                            //ki

                            Task<Uri>uriTask = taskSnapshot.getStorage().getDownloadUrl() ;
                            while (!uriTask.isSuccessful());
                            Uri downloaduri = uriTask.getResult();

                            accountSetupUploadModel imageUploadInfo = new accountSetupUploadModel(user_n ,user_ph ,dpart ,batchNam,blood ,downloaduri.toString(),Cgpa  );
                            //geting image upload id
                            String imageUploadid = mDatabaseReference.push().getKey() ;

                            //adding imge upload
                           mDatabaseReference.child(user_id).setValue(imageUploadInfo);


                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    // hide progrees bar
                    mprogressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    mprogressDialog.setTitle(" Uploading ...........");
                }
            });




        }
        else {
            Toast.makeText(getApplicationContext(), "Please Select image or add image Name ", Toast.LENGTH_SHORT).show();
        }


    }

    //method to get the selecte image file exentension
    private String getFileExtention(Uri uri) {
        ContentResolver contentResolver = getContentResolver() ;
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        //returning file extension

        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_REQUEST_CODE
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {
            mFilePathUri = data.getData();
            try {
                // getting image  into bitmap

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), mFilePathUri);

                image.setImageBitmap(bitmap);

            }
            catch (Exception e ){
                Toast.makeText(this, e.getMessage(),Toast.LENGTH_LONG).show();
            }
        }

    }


public  void sentToHome(){

    Intent gotohome = new Intent(getApplicationContext() , homePage.class) ;
    startActivity(gotohome);
    finish();



}




}