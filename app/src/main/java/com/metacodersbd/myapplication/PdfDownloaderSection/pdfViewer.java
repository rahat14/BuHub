package com.metacodersbd.myapplication.PdfDownloaderSection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.metacodersbd.myapplication.R;


public class pdfViewer extends AppCompatActivity {

    LinearLayout main ;

String url ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer);
        url = getIntent().getStringExtra("LINK");



   //     String link = "https://firebasestorage.googleapis.com/v0/b/buhub-a94bc.appspot.com/o/Pdf_CSE%2FAtmel2468%20.pdf?alt=media&token=41c58ade-edc7-48f8-bedd-53dbcb8cf430" ;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse(url), "application/pdf");
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        Intent intent1 = Intent.createChooser(intent, "Open With");
        try {
            startActivity(intent1);
        } catch (ActivityNotFoundException e) {

            Toast.makeText(getApplicationContext(), "Error" + e.getMessage() , Toast.LENGTH_SHORT).show();
        }
/*
        Toast.makeText(getApplicationContext(), "We Are Trying Hard To Load Data", Toast.LENGTH_LONG).show();

         main =(LinearLayout)findViewById(R.id.layout_spinner);
        bar = (ProgressBar)findViewById(R.id.progressBar_pdfViewer);

        pdfView=(PDFView) findViewById(R.id.pdfView);
        //
        // url = "https://firebasestorage.googleapis.com/v0/b/buhub-a94bc.appspot.com/o/Pdf_CSE%2FAtmel2468%20.pdf?alt=media&token=41c58ade-edc7-48f8-bedd-53dbcb8cf430";

            url = getIntent().getStringExtra("LINK");

        new RetrievePDFStream().execute(url);

    }
    class RetrievePDFStream extends AsyncTask<String,Void,InputStream> {
        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream = null;

            try {

                URL urlx = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) urlx.openConnection();
                if (urlConnection.getResponseCode() == 200) {
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());

                }
            } catch (IOException e) {
                Toast.makeText(getApplicationContext() , "EROR:"+e ,Toast.LENGTH_SHORT).show();

                return null;
            }
            return inputStream;

        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            bar.setVisibility(INVISIBLE);
            main.setVisibility(LinearLayout.GONE);

           pdfView.fromStream(inputStream).load();

        }*/
    }
    }
