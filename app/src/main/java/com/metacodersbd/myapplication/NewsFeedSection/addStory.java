package com.metacodersbd.myapplication.NewsFeedSection;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import id.zelory.compressor.Compressor;

import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.metacodersbd.myapplication.R;
import com.metacodersbd.myapplication.customDiagloueClass3;
import com.metacodersbd.myapplication.homePage;
import com.onesignal.OneSignal;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;
import java.util.UUID;


public class addStory extends AppCompatActivity {
    String url  ,nam  ;
    EditText title,description ;
    Button  upld_btn ;
    ImageView image ;
    String mTitle = "  " , mDescription = "  ";
    customDiagloueClass3 viewDialog;
    Uri postImageUri  = null ;
    Boolean isPressed = false ;

    String User_id ;
    private DatabaseReference UserRef  ;
    private  StorageReference storageRef ;
    private FirebaseAuth mauth ;
    String DATE ,Time  ;
    private boolean isChanged = false;
    public ProgressBar pb ;

    private Bitmap compressedImageFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_story);
        mauth = FirebaseAuth.getInstance() ;
        User_id = mauth.getCurrentUser().getUid() ;
        UserRef = FirebaseDatabase.getInstance().getReference("NewsFeed");
        storageRef = FirebaseStorage.getInstance().getReference("newsFeed_pic") ;
        viewDialog = new customDiagloueClass3(this);

        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();

        homePage  p = new homePage() ;
        url = p.sendData() ;
        nam = p.sendName() ;









        //setting up viewes ;
        title = findViewById(R.id.input_title);
        upld_btn = findViewById(R.id.add_stroy_uploadBtn);
        image = findViewById(R.id.imageView_add_story);




        //setting up listenre

        upld_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                mTitle = title.getText().toString();

                String delegate = "hh:mm aaa";
                Time = String.valueOf(DateFormat.format(delegate, Calendar.getInstance().getTime()));

                DATE = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                Time = Time + " "+DATE ;


                if (!isPressed){


                    String ts =UserRef.push().getKey() ;
                    // uploading data t ofirebase
                    modelForNewsFeed uploadModel = new modelForNewsFeed(mTitle, "",User_id ,url , nam ,Time , ts , "0");
                    //adding coomment directory .
                    UserRef.child(ts).setValue(uploadModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            sendNotification();
                            finish();

                        }
                    });

                }

                else if (!TextUtils.isEmpty(mTitle) && postImageUri != null) {
                    sendNotification();

                    final String randomName = UUID.randomUUID().toString();

                    // PHOTO UPLOAD
                    File newImageFile = new File(postImageUri.getPath());

                    try {

                        compressedImageFile = new Compressor(addStory.this)
                                .setMaxHeight(920)
                                .setMaxWidth(920)
                                .setQuality(40)
                                .compressToBitmap(newImageFile);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    compressedImageFile.compress(Bitmap.CompressFormat.JPEG, 40, baos);
                    byte[] imageData = baos.toByteArray();
                    UploadTask filePath = storageRef.child(randomName+User_id + ".jpg").putBytes(imageData);
                    filePath.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            viewDialog.hideDialog();

                            sendTonextAcitvity();
                            viewDialog.hideDialog();

                            Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                            while (!uriTask.isSuccessful()) ;
                            Uri downloaduri = uriTask.getResult();



                            String ts =UserRef.push().getKey() ;


// uploading data t ofirebase
                            modelForNewsFeed uploadModel = new modelForNewsFeed(mTitle, downloaduri.toString(),User_id ,url , nam ,Time , ts,"0");

                            UserRef.child(ts).setValue(uploadModel);
                            //adding coomment directory .




                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {


                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();


                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            viewDialog.showDialog();
                        }
                    });



                } else {
                    Toast.makeText(getApplicationContext(), "Please Select image or add image Name ", Toast.LENGTH_SHORT).show();
                }
            }
        });


        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isPressed = true ;

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                    if (ContextCompat.checkSelfPermission(addStory.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                        ActivityCompat.requestPermissions(addStory.this,
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

                    } else {

                        BringImagePicker();

                    }

                } else {

                    BringImagePicker();

                }

            }

        });

    }


    private void BringImagePicker () {

        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .setInitialCropWindowPaddingRatio(0)
                .setCropShape(CropImageView.CropShape.RECTANGLE) //shaping the image
                .start(addStory.this);

    }


    @Override
    protected void onActivityResult ( int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {

                postImageUri = result.getUri();
                image.setImageURI(postImageUri);

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {

                Exception error = result.getError();
                Toast.makeText(this, error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }

    }
    public void showCustomLoadingDialog(View view) {
        //..show gif and hide after 5 seconds

        viewDialog.showDialog();

    }
    public void  sendTonextAcitvity(){

        Intent o = new Intent(getApplicationContext() , newsFeed.class);
        finish();
        startActivity(o);


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
                                + "\"contents\": {\"en\": \"New Story Added!!\"}"
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