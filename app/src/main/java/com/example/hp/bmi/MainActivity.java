package com.example.hp.bmi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tvPersonalDetails;
    TextInputLayout nameWrapper,phoneWrapper,ageWrapper;
    EditText etName,etAge,etphone;
    RadioGroup rgGender;
    RadioButton rbGender;
    Button btnRegister;
    SharedPreferences sp1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvPersonalDetails= (TextView) findViewById(R.id.tvPersonalDetails);
        nameWrapper= (TextInputLayout) findViewById(R.id.nameWrapper);
        phoneWrapper= (TextInputLayout) findViewById(R.id.phoneWrapper);
        ageWrapper= (TextInputLayout) findViewById(R.id.ageWrapper);
        etName= (EditText) findViewById(R.id.etName);
        etAge= (EditText) findViewById(R.id.etAge);
        etphone= (EditText) findViewById(R.id.etphone);
        rgGender= (RadioGroup) findViewById(R.id.rgGender);
        btnRegister= (Button) findViewById(R.id.btnRegister);


        nameWrapper.setHint("Name");
        phoneWrapper.setHint("Phone Number");
        ageWrapper.setHint("Age");

        sp1=getSharedPreferences("userDetails",MODE_PRIVATE);

        if((sp1.getBoolean("ne",false))==false)
        {
            btnRegister.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View view) {

                    boolean isValid=true;
                    String name=etName.getText().toString();
                    String a=etAge.getText().toString().trim();
                    a=a.trim();
                    int age=0;
                    String phone=etphone.getText().toString();
                    String gender="";

                    if(name.length() == 0)
                    {
                        nameWrapper.setError("Name cannot be empty");
                        isValid=false;
                    }
                    else {
                        nameWrapper.setErrorEnabled(false);
                    }

                    if(a.equals(""))
                    {
                        ageWrapper.setError("Age cannot be empty");
                        isValid=false;
                    }else
                    {
                        ageWrapper.setErrorEnabled(false);
                        age=Integer.parseInt(a);
                    }

                    if(phone.length()!=10)
                    {
                        phoneWrapper.setError("phone number not valid");
                        isValid=false;
                    }
                    else
                    {
                        phoneWrapper.setErrorEnabled(false);
                    }



                    int id=rgGender.getCheckedRadioButtonId();

                    if(id<0)
                    {
                        Toast.makeText(MainActivity.this, "Please Enter the details", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    rbGender= (RadioButton) findViewById(id);
                    gender= (String) rbGender.getText();

                    /// saving using shared Preference

                    SharedPreferences.Editor editor = sp1.edit();
                    editor.putString("name", name);
                    editor.putInt("age", age);
                    editor.putString("gender",gender);
                    editor.putString("phone", phone);
                    editor.putBoolean("ne",true);
                    editor.apply();

                    if(isValid) {
                        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                        startActivity(intent);
                        finish();
                    }


                }
            });

        }
        else
        {
            Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
            startActivity(intent);
            finish();

        }
 }
}
