package com.metacodersbd.myapplication.PdfDownloaderSection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.CubeGrid;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.metacodersbd.myapplication.R;

import java.util.UUID;

public class AddPdfActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference UserRef  ;
    FirebaseStorage storage ;
    CardView upload , fireUpload;
    EditText name ,  writer ;

    SpinKitView spinKitView ;

Uri pdfuri ;
String ts = "default";
ImageView  imagev ;
String Name , writer_Name ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_pdf_activity);

            imagev = (ImageView) findViewById(R.id.dImageONPDF);
        upload = (CardView)findViewById(R.id.uploadBtn_pdf) ;
        fireUpload = (CardView)findViewById(R.id.fireUPLOAD) ;
        name = (EditText)findViewById(R.id.PDF_NAME) ;
        writer = (EditText)  findViewById(R.id.PDF_Writer_NAME);

        spinKitView = findViewById(R.id.spin_kit);




        Sprite doubleBounce = new CubeGrid();
        spinKitView.setIndeterminateDrawable(doubleBounce);

        spinKitView.setVisibility(View.GONE);


        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
        UserRef = FirebaseDatabase.getInstance().getReference("Pdf_db").child("CSE");

        fireUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Name = name.getText().toString();
                writer_Name = writer.getText().toString();


                if(pdfuri != null && !TextUtils.isEmpty(Name) && !TextUtils.isEmpty(writer_Name)){


                    spinKitView.setVisibility(View.VISIBLE);

                    uploadfile(pdfuri);
                }

                else {
                    Toast.makeText(getApplicationContext() , "Pllz Select A file  Or ENter THe Feild Properly " , Toast.LENGTH_SHORT).show();

                }



            }
        });



        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){


            selectPdf();

}
else {

    ActivityCompat.requestPermissions(AddPdfActivity.this , new  String[]{Manifest.permission.READ_EXTERNAL_STORAGE},9);

}





            }
        });


    }

    private void uploadfile(Uri pdfuri) {
        final String randomName = UUID.randomUUID().toString();
        StorageReference storageReference = storage.getReference("Pdf_CSE");

        UploadTask filepaath = storageReference.child(randomName).putFile(pdfuri);

        filepaath.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                //do success >>>>>>>>>>>>>
                imagev.setImageResource(R.drawable.addpdf);
                Toast.makeText(getApplicationContext(), " Success !!File Uploaded ", Toast.LENGTH_LONG).show();
             name.getText().clear();
             writer.getText().clear();
                spinKitView.setVisibility(View.INVISIBLE);



                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isSuccessful()) ;
                Uri downloaduri = uriTask.getResult();

                modelForPdfUpload model = new modelForPdfUpload(Name , downloaduri.toString() , writer_Name);

                 ts =UserRef.push().getKey() ;
                UserRef.child(ts).setValue(model);


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {



            }
        });




    }


    private void selectPdf() {

        Intent i = new Intent();
        i.setType("application/pdf");
        i.setAction(Intent.ACTION_GET_CONTENT); //
        startActivityForResult(i , 86);

        imagev.setImageResource(R.drawable.checkmark);




    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {


        if(requestCode==9 && grantResults[0]==PackageManager.PERMISSION_GRANTED)

        {
                selectPdf();

        }
        else {
            Toast.makeText(getApplicationContext() , "Pllz Giver Permission" , Toast.LENGTH_SHORT).show();

        }




}
    @Override
    protected void onActivityResult ( int requestCode, int resultCode, Intent data){


if(requestCode == 86 && resultCode== RESULT_OK && data!= null){

            pdfuri =data.getData();



}
else {
    Toast.makeText(getApplicationContext() , "Plz Select A File" , Toast.LENGTH_SHORT).show();

}


        }




}
