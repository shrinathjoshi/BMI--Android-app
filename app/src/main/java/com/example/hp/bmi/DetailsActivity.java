package com.example.hp.bmi;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.IntegerRes;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsActivity extends AppCompatActivity {

    SharedPreferences sp1;
    TextView tvWelcome,tvHeight,tvWeight,tvFeet,tvInches;
    EditText etWeight;
    Spinner spFeet,spInches;
    Button btnCalculate,btnViewHistory;
    TextInputLayout WeightWrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        final DatabaseHandler db=new DatabaseHandler(this);


        tvWelcome= (TextView) findViewById(R.id.tvWelcome);
        sp1=getSharedPreferences("userDetails",MODE_PRIVATE);
        String name=sp1.getString("name","");
        tvWelcome.setText("Welcome "+name);


        tvWeight= (TextView) findViewById(R.id.tvWeight);
        tvFeet= (TextView) findViewById(R.id.tvFeet);
        tvInches= (TextView) findViewById(R.id.tvInches);
        tvHeight= (TextView) findViewById(R.id.tvHeight);
        etWeight= (EditText) findViewById(R.id.etWeight);
        spFeet= (Spinner) findViewById(R.id.spFeet);
        spInches= (Spinner) findViewById(R.id.spInches);
        btnCalculate= (Button) findViewById(R.id.btnCalculate);
        btnViewHistory= (Button) findViewById(R.id.btnViewHistory);
        WeightWrapper= (TextInputLayout) findViewById(R.id.WeightWrapper);

        final Integer feet[]={0,1,2,3,4,5,6,7,8,9,10,11,12};
        final Integer inches[]={0,1,2,3,4,5,6,7,8,9,10,11,12};

        int total_feet,total_inches;

        ArrayAdapter<Integer> adapter1=new ArrayAdapter<Integer>(this,android.R.layout.simple_list_item_1,feet);
        ArrayAdapter<Integer> adapter2=new ArrayAdapter<Integer>(this,android.R.layout.simple_list_item_1,inches);

        spFeet.setAdapter(adapter1);
        spInches.setAdapter(adapter2);
/*
        spFeet.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                    Object feet=adapterView.getItemAtPosition(i).toString();
            //        int position=spFeet.getSelectedItemPosition();
              //      feet=feet.trim();
                //    int feet_1=Integer.parseInt(feet);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spInches.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object inches=adapterView.getItemAtPosition(i).toString();
            //    inches=inches.trim();
              //  int inches_1=Integer.parseInt(inches);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
*/

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                int pos_feet=spFeet.getSelectedItemPosition();
                int pos_inches=spInches.getSelectedItemPosition();

                int feet1=feet[pos_feet];
                int inches1=inches[pos_inches];
                double bmi=0;

                double cm=feet1*30.48;
                double dm=inches1*2.54;
                double t_cm=cm+dm;
                String weight=etWeight.getText().toString();
                weight=weight.trim();

                boolean isValid=false;


                if(feet1==0&&inches1==0)
                {
                    Snackbar.make(view,"Please Enter proper length",Snackbar.LENGTH_LONG).show();
                    return;
                }
                if(weight.equals(""))
                {
                    WeightWrapper.setError("Please enter valid weight");
                }
                else
                {
                    isValid=true;
                    WeightWrapper.setErrorEnabled(false);
                    int t_weight=Integer.parseInt(weight);
                    t_cm=t_cm/100;
                    bmi=t_weight/(t_cm*t_cm);
                }


                if(isValid)
                {
                    Intent intent =new Intent(DetailsActivity.this,DisplayActivity.class);
                    intent.putExtra("bmi1",bmi);
                    startActivity(intent);

                }
            }
        });

        btnViewHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent history=new Intent(DetailsActivity.this,ViewHistory.class);
                startActivity(history);


            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.About:
                Snackbar.make(findViewById(android.R.id.content),"Developed by Shrinath",Snackbar.LENGTH_LONG).show();
                break;

            case R.id.Webiste:
                Snackbar.make(findViewById(android.R.id.content),"Clicked Website",Snackbar.LENGTH_LONG).show();
                break;

            case R.id.Follow:
                Snackbar.make(findViewById(android.R.id.content),"Clicked Follow",Snackbar.LENGTH_LONG).show();
                break;

            case R.id.Settings:
                Snackbar.make(findViewById(android.R.id.content),"Clicked Follow",Snackbar.LENGTH_LONG).show();
                break;

            case R.id.Contact:
                Snackbar.make(findViewById(android.R.id.content),"Clicked Follow",Snackbar.LENGTH_LONG).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed()
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("Do you want to close this Application?");
        builder.setCancelable(false);


        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });


        builder.setNeutralButton("Canel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog alert=builder.create();
        alert.setTitle("Exit");
        alert.show();

    }

}
