package com.example.hp.bmi;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class DisplayActivity extends AppCompatActivity {

    TextView tvshowBmi,tvUnderweight,tvOverweight,tvNoraml,tvObese;
    Button btnSave,btnShare,btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        tvshowBmi= (TextView) findViewById(R.id.tvshowBmi);
        tvOverweight= (TextView) findViewById(R.id.tvOvererweight);
        tvNoraml= (TextView) findViewById(R.id.tvNormal);
        tvUnderweight= (TextView) findViewById(R.id.tvUnderweight);
        tvObese= (TextView) findViewById(R.id.tvObese);

        btnBack= (Button) findViewById(R.id.btnBack);
        btnSave= (Button) findViewById(R.id.btnSave);
        btnShare= (Button) findViewById(R.id.btnShare);

        String condition ;
        Bundle bundle = this.getIntent().getExtras();

        Double bmi=bundle.getDouble("bmi1");
        bmi =Double.parseDouble(new DecimalFormat("##.##").format(bmi));

        final double bmi1=bmi;


        if(bmi<=18.50)
        {
            tvUnderweight.setTextColor(Color.parseColor("#ff0000"));
            condition="UnderWeight";
        }
        else
        {
            if(bmi <=25)
            {
                tvNoraml.setTextColor(Color.parseColor("#ff0000"));
                condition="Normal";
            }
            else
            {
                if(bmi<=30)
                {
                    tvOverweight.setTextColor(Color.parseColor("#ff0000"));
                    condition="OverWeight";
                }
                else
                {
                    tvObese.setTextColor(Color.parseColor("#ff0000"));
                    condition="Obese";
                }
            }

        }
        tvshowBmi.setText("Your BMI is "+bmi+"\nYou are "+ condition);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("plain/text");
                startActivity(intent);
            }
        });


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                DatabaseHandler db=new DatabaseHandler(getApplicationContext());


                db.addEntry(bmi1);


            }
        });
    }
}
