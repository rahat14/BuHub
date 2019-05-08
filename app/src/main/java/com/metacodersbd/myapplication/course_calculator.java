package com.metacodersbd.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class course_calculator extends AppCompatActivity {

    int waver = 0,credit = 0;
    public EditText credit_num;
    public EditText waver_pr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_calculator);
        credit_num = (EditText)findViewById(R.id.credit_id);
        waver_pr = (EditText)findViewById(R.id.waver_id);

    }
    public int credit(){

        String price_of_credit = credit_num.getText().toString();
        credit = Integer.parseInt(price_of_credit);
        return credit;

    }
    public int waver(){
        String waver_pr_credit = waver_pr.getText().toString();
        waver = Integer.parseInt(waver_pr_credit);
        waver = 2000-(20*waver);
        return waver;
    }

    public void calcu(View view){

        int num_of_credit;
        int waver_parcentise;
        int total;
        num_of_credit = credit();
        waver_parcentise = waver();

        total = num_of_credit*waver_parcentise;
        credit_ammount(total);
        credit_num.setText("");
        waver_pr.setText("");

    }

    public void credit_ammount(int amount){

        TextView total_credit_amount = (TextView)findViewById(R.id.amount);
        total_credit_amount.setText(NumberFormat.getCurrencyInstance().format(amount));

    }


}
