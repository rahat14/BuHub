package com.metacodersbd.myapplication.PdfDownloaderSection;


import android.app.AlertDialog;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.metacodersbd.myapplication.R;

import java.util.Calendar;
import java.util.Date;

public class pdfViewerByDpartment extends AppCompatActivity {

    Button cse ,law ,addBtn;
    Button bba ,eng , reqBtn ;
    public DatabaseReference mref ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer_by_dpartment);

        mref = FirebaseDatabase.getInstance().getReference("Pdf_db");

        cse = (Button)findViewById(R.id.csePdfButton);
        bba = (Button)findViewById(R.id.bbapdfButton);
        eng = (Button)findViewById(R.id.englishPdfButton);
        addBtn = findViewById(R.id.ADDpdfButton);
        law = (Button)findViewById(R.id.lawPdfButton);
        reqBtn = (Button)findViewById(R.id.REQButton);




        //setting onclick lister

        cse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), cse_pdf_list.class);
                startActivity(i);
            }
        });
        bba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), bba_pdf_list.class);
              startActivity(i);
             //   Toast.makeText(getApplicationContext(), "Coming Soon !!" , Toast.LENGTH_SHORT).show();

            }
        });

        law.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent i = new Intent(getApplicationContext(), law_padf_list.class);
              startActivity(i);
             //   Toast.makeText(getApplicationContext(), "Coming Soon !!" , Toast.LENGTH_SHORT).show();
            }
        });


        eng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent i = new Intent(getApplicationContext(), english_pdf_list.class);
                startActivity(i);
               // Toast.makeText(getApplicationContext(), "Coming Soon !!" , Toast.LENGTH_SHORT).show();
            }
        });

addBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        Intent i = new Intent(getApplicationContext(), AddPdfActivity.class);
        startActivity(i);

    }
});


        reqBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Adding dialog box which will send data to firebase

                final AlertDialog dialogBuilder = new AlertDialog.Builder(pdfViewerByDpartment.this).create();
                LayoutInflater inflater = pdfViewerByDpartment.this.getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.custom_dialog, null);

                final EditText WriterName = (EditText) dialogView.findViewById(R.id.edt_writer);
                final  EditText Bookname  = (EditText)dialogView.findViewById(R.id.BookName_edit);
                Button submitButton = (Button) dialogView.findViewById(R.id.buttonSubmit);
                Button button2 = (Button) dialogView.findViewById(R.id.buttonCancel);
                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogBuilder.dismiss();
                    }
                });
                submitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // DO SOMETHINGS
                        String name = Bookname.getText().toString();
                        String writer = WriterName.getText().toString() ;


                        if(!TextUtils.isEmpty(name)|| !TextUtils.isEmpty(writer)){

                            Date currentTime = Calendar.getInstance().getTime();
                            String time = currentTime.toString();

                            sendDataToFirebase(name , writer ,time);
                                dialogBuilder.dismiss();
                                Toast.makeText(getApplicationContext() , "Thanks!! For The Suggestion We wil Add it soon" ,
                                        Toast.LENGTH_SHORT).show();

                        }
                        else {

                            Toast.makeText(getApplicationContext(), "Please Enter Some Thing " , Toast.LENGTH_SHORT).show();

                        }



                    }
                });
                dialogBuilder.setView(dialogView);

                dialogBuilder.show();
                dialogBuilder.setCanceledOnTouchOutside(false);

            }
        });


    }

    public void sendDataToFirebase(String Bookname , String writerName , String  time ){


                    model_For_upload model = new model_For_upload(Bookname , writerName );


                        mref.child("BOOK_REQ").child(time).setValue(model);


    }




}
