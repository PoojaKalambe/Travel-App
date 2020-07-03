package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;


public class kolhapur extends AppCompatActivity {


    CheckBox chk1,chk2;
    public int cnt=0;
    public int fare=0;
    CheckBox chk[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kolhapur);

        chk1 = (CheckBox) findViewById(R.id.ch6);
        chk2 = (CheckBox) findViewById(R.id.ch5);
        chk = new CheckBox[12];


        for (int i=0;i<12;i++){

            //chk[i]= (CheckBox) findViewById(R.id.chk[i]);
            chk[i].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    cnt++;
                }
            });
        }


        chk1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cnt++;
            }
        });

        chk2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cnt++;
            }
        });


        //price calculating

        if(cnt == 1){

            fare =400;

        }else if(cnt < 5 && cnt > 1){

            fare = 850;
        }else if(cnt < 10 && cnt > 4){

            fare = 1950;

        }else{

            fare = 2850;

        }

        //Intent intent = new Intent(getApplicationContext(),);
        //intent.putExtra("fare",fare);
        //startActivity(intent);

    }
}
