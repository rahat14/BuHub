package com.metacodersbd.myapplication.CgpaCalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.metacodersbd.myapplication.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ReminderActivity extends AppCompatActivity {
   FloatingActionButton btnDisplay;
    FloatingActionButton btnAdd;
    String a = "" ;
    Spinner spinner ;
    EditText edti2 ;
    int total = 0  ;
    int creadit = 0 ;
    Double cgpa=0.00 ;
    String scgpa, scredit ;
    ImageView cgpaMeter , CreaditMeter ;
    Double gradePoint = 0.00 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        btnAdd = (FloatingActionButton) findViewById(R.id.btnAdd);
        btnDisplay = (FloatingActionButton) findViewById(R.id.btnDisplay);
        cgpaMeter =(ImageView)findViewById(R.id.cgpa_meter_calculator);
        CreaditMeter =(ImageView)findViewById(R.id.credit_meter_cal);





        //Setting the ArrayAdapter data on the Spinner

        //  MyLayoutOperation.add(this, btnAdd);
     //   MyLayoutOperation.display(this, btnDisplay);




        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                display();

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addButton();
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

// Inflate the menu; this adds items to the action bar if it is present.
      //  getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    public  void display(){

        LinearLayout scrollViewlinerLayout = (LinearLayout) findViewById(R.id.linearLayoutForm);

        java.util.ArrayList<String> msg = new ArrayList<String>();




        for (int i = 0; i < scrollViewlinerLayout.getChildCount(); i++)
        {
            LinearLayout innerLayout = (LinearLayout) scrollViewlinerLayout.getChildAt(i);
            EditText edit = (EditText) innerLayout.findViewById(R.id.editDescricao2Credit);
            edti2= (EditText) innerLayout.findViewById(R.id.editDescricao_gpa);
            scgpa= edti2.getText().toString().toUpperCase();
            scredit = edit.getText().toString();
         int     singleCredit ;
      Double singleCgpa ;

            if(!TextUtils.isEmpty(scgpa)|| (!TextUtils.isEmpty(scredit)))
            {
                singleCredit = Integer.parseInt(scredit);
                creadit = creadit + Integer.parseInt(scredit) ;

                switch (scgpa) {
                    case "A+":

                        cgpa =  4.00;
                        break;
                    case "A":

                        cgpa =  3.75;
                        break;
                    case "A-":
                        cgpa =  3.50;
                        break;
                    case "B+":
                        cgpa =  3.25;
                        break;
                    case "B":
                        cgpa =  3.00;
                        break;
                    case "C+":

                        cgpa =  2.5;
                        break;
                    case "C":
                        cgpa =  2.25;
                        break;
                    case "D":
                        cgpa =  2.00;
                        break;
                    default:

                        Toast.makeText(getApplicationContext(), "Error Plzz Enter Right Format CGPA " + scgpa, Toast.LENGTH_LONG).show();
                        break;
                }




                gradePoint = gradePoint + (cgpa * singleCredit) ; // calculation logic





            }
            else {

                Toast.makeText(getApplicationContext(),"Error  Try Again " ,Toast.LENGTH_LONG).show();
                recreate();

            }

        }
     //   Toast t = Toast.makeText(getApplicationContext(), "cgpa = "+ cgpa +"Credit "+creadit , Toast.LENGTH_SHORT);
      //  t.show();

        Bitmap bitmap =setCredit(getApplicationContext(),creadit) ; // showing the cgpa
        cgpaMeter.setImageBitmap(bitmap);

        Bitmap bit = setgpa(getApplicationContext() , gradePoint , creadit) ;
        CreaditMeter.setImageBitmap(bit);
        cgpa = 0.0 ;
        gradePoint = 0.00 ;
        creadit = 0 ;
        try  {
            InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {

        }
    }

    public  void addButton(){




        final LinearLayout linearLayoutForm = (LinearLayout)findViewById(R.id.linearLayoutForm);
        final LinearLayout newView = (LinearLayout)getLayoutInflater().inflate(R.layout.field, null);

        newView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        ImageButton btnRemove = (ImageButton) newView.findViewById(R.id.btnRemove);

        btnRemove.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                linearLayoutForm.removeView(newView);
            }
        });
        linearLayoutForm.addView(newView);
    }


    private Bitmap setgpa(Context context, double percentage  ,int creadit) {

        int width = 400;
        int height = 400;
        int stroke = 30;
        int padding = 1;
        float fin ;
        float density = context.getResources().getDisplayMetrics().density;
        if(percentage==0){
            fin=0 ;
        }
        else {
            double p = percentage ;
            double cr = creadit;
            percentage = percentage /cr ;
            DecimalFormat num = new DecimalFormat("#.00");
           percentage = Double.parseDouble(num.format(percentage));
        Toast.makeText(getApplicationContext(),"cgpa"+ percentage + "creadit "+ cr  + "totalGradepint "+ p, Toast.LENGTH_SHORT).show();

        if (percentage <= 2.20) {

                fin = 150;
            } else if (percentage <= 2.80) {

                fin = 175;
            } else {

                fin = (35 * (float) percentage) + 135;


            }
        }



        //Paint for arc stroke.
        Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG | Paint.DITHER_FLAG | Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(stroke);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        //paint.setStrokeJoin(Paint.Join.ROUND);
        //paint.setPathEffect(new CornerPathEffect(10) );

        //Paint for text values.
        Paint mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize((int) (context.getResources().getDimension(R.dimen.widget_text_large_value) / density));
        mTextPaint.setAlpha((int) 255);

        mTextPaint.setColor(Color.argb(255, 9, 198, 223));
        mTextPaint.setTextAlign(Paint.Align.CENTER);

        final RectF arc = new RectF();
        arc.set((stroke/2) + padding, (stroke/2) + padding, width-padding-(stroke/2), height-padding-(stroke/2));

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        //First draw full arc as background.
        paint.setColor(Color.argb(75, 4, 78, 87));
        canvas.drawArc(arc, 135, 275, false, paint);
        //Then draw arc progress with actual value.
        paint.setColor(Color.argb(255, 9, 198, 223));
        canvas.drawArc(arc, 135, fin, false, paint);
        //Draw text value.
        canvas.drawText(percentage +"", bitmap.getWidth() / 2, (bitmap.getHeight() - mTextPaint.ascent()) / 2, mTextPaint);
        //Draw widget title.
        mTextPaint.setTextSize((int) (context.getResources().getDimension(R.dimen.widget_text_large_title) / density));
        canvas.drawText(context.getString(R.string.widget_text_arc_battery), bitmap.getWidth() / 2, bitmap.getHeight()-(stroke+padding), mTextPaint);

        return  bitmap;
    }



    private Bitmap setCredit(Context context, int percentage) {

        int width = 400;
        int height = 400;
        int stroke = 30;
        int padding = 1;
        int fin = 250 ;
        float density = context.getResources().getDisplayMetrics().density;
        if (percentage== 0 ){

            fin  = 0 ;


        }
        else if (percentage > 8 ){
            fin = 175 ;
        }


        else
        {
            fin = 240 ;
        }


        //Paint for arc stroke.
        Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG | Paint.DITHER_FLAG | Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(stroke);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        //paint.setStrokeJoin(Paint.Join.ROUND);
        //paint.setPathEffect(new CornerPathEffect(10) );

        //Paint for text values.
        Paint mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize((int) (context.getResources().getDimension(R.dimen.widget_text_large_value) / density));
        mTextPaint.setAlpha((int) 255);

        mTextPaint.setColor(Color.argb(255, 9, 198, 223));
        mTextPaint.setTextAlign(Paint.Align.CENTER);

        final RectF arc = new RectF();
        arc.set((stroke/2) + padding, (stroke/2) + padding, width-padding-(stroke/2), height-padding-(stroke/2));

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        //First draw full arc as background.
        paint.setColor(Color.argb(75, 4, 78, 87));
        canvas.drawArc(arc, 135, 275, false, paint);
        //Then draw arc progress with actual value.
        paint.setColor(Color.argb(255, 9, 198, 223));
        canvas.drawArc(arc, 135, fin, false, paint);
        //Draw text value.
        canvas.drawText(percentage +"", bitmap.getWidth() / 2, (bitmap.getHeight() - mTextPaint.ascent()) / 2, mTextPaint);
        //Draw widget title.
        mTextPaint.setTextSize((int) (context.getResources().getDimension(R.dimen.widget_text_large_title) / density));
        canvas.drawText(context.getString(R.string.widget_text_arc_Credit), bitmap.getWidth() / 2, bitmap.getHeight()-(stroke+padding), mTextPaint);

        return  bitmap;
    }

    @Override
    protected void onStart() {

        Bitmap bitmap =setCredit(getApplicationContext(),0) ;
        cgpaMeter.setImageBitmap(bitmap);

        Bitmap bit = setgpa(getApplicationContext() , 0.00 ,0 ) ;
        CreaditMeter.setImageBitmap(bit);
        super.onStart();
    }
}





