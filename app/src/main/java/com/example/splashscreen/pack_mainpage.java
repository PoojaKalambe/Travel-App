package com.example.splashscreen;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class pack_mainpage extends AppCompatActivity {

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pack_mainpage);

        btn= (Button)findViewById(R.id.bookCar);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                movetoPassenger();
            }

            private void movetoPassenger()
            {
                Intent intent = new Intent(pack_mainpage.this, pack_dashboard.class);
                startActivity(intent);
            }
        });
    }
}
