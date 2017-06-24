package com.example.hp.bmi;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewHistory extends AppCompatActivity {

    TextView tvResult;
    ListView lvlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_history);


        tvResult= (TextView) findViewById(R.id.tvResult);
        lvlist= (ListView) findViewById(R.id.lvList);

        Intent i=getIntent();
        String data=i.getStringExtra("data");
        //     tvResult.setText(data);

        DatabaseHandler db=new DatabaseHandler(this);

        ArrayList<String> thelist=new ArrayList<String>();
        Cursor c=db.getInfo();

        if(c.getCount()==0)
        {
            Toast.makeText(this, "No record", Toast.LENGTH_SHORT).show();
        }
        else {
            while(c.moveToNext())
            {
                int id=c.getInt(0);
                String datetime=c.getString(2);
                String bmi=c.getString(1);

                String msg="Id :- "+id+"\n Date and Time:- "+datetime+"\n Your BMI was :-"+bmi;

                thelist.add(msg);
                ListAdapter adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,thelist);
                lvlist.setAdapter(adapter);
            }
        }

    }
}
