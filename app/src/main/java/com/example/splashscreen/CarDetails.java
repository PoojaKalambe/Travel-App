package com.example.splashscreen;


import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class CarDetails extends AppCompatActivity {

    EditText carname,carnumber,cartype,seat,driname,drimobile,ratekm,carsource,cardestination;
    Button btn;

    // String Carname,Carnumber,Cartype,Seat,Driname,Drimobile,Rate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);

        btn= (Button)findViewById(R.id.bookCar);

        carname = (EditText) findViewById(R.id.cname);
        carnumber = (EditText) findViewById(R.id.cnumber);
        cartype = (EditText) findViewById(R.id.ctype);
        seat = (EditText) findViewById(R.id.seat);
        driname = (EditText) findViewById(R.id.dname);
        drimobile = (EditText) findViewById(R.id.dmobile);
        ratekm = (EditText) findViewById(R.id.rate);
        carsource = (EditText) findViewById(R.id.carSource);
        cardestination = (EditText) findViewById(R.id.CarDestination);

        btn.setOnClickListener(new View.OnClickListener() {

            //database btnclick

            @Override
            public void onClick(View v) {

                //setting all the querry string parameter

                Constants.URL_REGISTER = Constants.URL_REGISTER+""+queryString();
                System.out.println(Constants.URL_REGISTER);
                StringRequest stringRequest = new StringRequest(Request.Method.POST,
                        Constants.URL_REGISTER,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //Hide Progress bar
                                //o/p
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    //Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
                                    //modify if wifi changes


                                    if(jsonObject.getString("error").equals("true")){
                                        //clearAllSelection();
                                        Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
                                        Constants.URL_REGISTER = "http://192.168.43.248:80/CarTravel/";
                                    }else{
                                        Constants.URL_REGISTER = "http://192.168.43.248:80/CarTravel/";
                                        Intent intent = new Intent(getApplicationContext(),PassengerDetails.class);
                                        intent.putExtra("cardetails",jsonObject.getString("message"));
                                        startActivity(intent);

                                    }

                                    //passing response to display


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();

                            }
                        }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<>();
                        params.put("user","rk");
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);
            }

            private String queryString() {
                //getSeat_category();
                String querry = "sample.php?car_name="+carname+"car_number"+carnumber+"car_type"+cartype+"seat"+seat+"Driver_name"+driname+"Driver_mobile"+drimobile+"rate_km"+ratekm+"source"+ carsource+"destination"+cardestination;

                return querry;
            }

             /* @Override
          public void onClick(View v) {

                movetoPassenger();
            }

            private void movetoPassenger()
            {
                Intent intent = new Intent(CarDetails.this, PassengerDetails.class);
                startActivity(intent);
            }

            public void btregister(View view){

                Carname = carname.getText().toString();
                Carnumber = carnumber.getText().toString();
                Cartype = cartype.getText().toString();
                Seat = seat.getText().toString();
                Driname = driname.getText().toString();
                Drimobile = drimobile.getText().toString();
                Rate = ratekm.getText().toString();

                String method ="carDetials";

            }*/


        });


    }


}
