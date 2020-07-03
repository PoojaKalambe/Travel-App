package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class Dashboard_Main extends AppCompatActivity {


    private ImageView bus,bus1,airplane,train,cycle,bike;
    private android.view.View.OnClickListener View;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard__main);


        ImageView profile=findViewById(R.id.profile);
        ImageView flight=findViewById(R.id.flight);
        ImageView schedule=findViewById(R.id.schedule);
        ImageView contactus=findViewById(R.id.contactus);
        // ImageView cycle=findViewById(R.id.cycle);
        //ImageView bike=findViewById(R.id.bike);


        profile.setOnClickListener(new android.view.View.OnClickListener() {
                                       @Override
                                       public void onClick(android.view.View v) {
                                           Intent intent=new Intent(Dashboard_Main.this, Main2Activity.class);
                                           startActivity(intent);
                                       }
                                   }


        );

        flight.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(android.view.View v) {
                                          Intent intent=new Intent(Dashboard_Main.this, Dashboard1.class);
                                          startActivity(intent);
                                      }
                                  }


        );

        schedule.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(android.view.View v) {
                                            Intent intent=new Intent(Dashboard_Main.this, Main2Activity.class);
                                            startActivity(intent);
                                        }
                                    }


        );


        contactus.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(android.view.View v) {
                                             Intent intent=new Intent(Dashboard_Main.this, Main2Activity.class);
                                             startActivity(intent);
                                         }
                                     }


        );






    }


    public void getLogin(View view){

        Intent intent = new Intent(getApplicationContext(), Login.class);
        intent.putExtra("action","dashboard");
        startActivity(intent);

    }
}
