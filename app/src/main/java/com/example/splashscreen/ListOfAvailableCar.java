package com.example.splashscreen;


import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Size;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ListOfAvailableCar extends AppCompatActivity {

    Button btn, btn2;
    TextView car_name[];
    //ConstraintLayout master;
    LinearLayout master;

    // String cname,cnumber,ctype,dname,dmobile,seat,rate,sorc,desti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_available_car);

        //  master = (ConstraintLayout) findViewById(R.id.master);
        master = (LinearLayout)findViewById(R.id.master_layout);

        String carlist[] = getIntent().getStringExtra("carname").split(" ");

        // String response = getIntent().getStringExtra("cardetails");

        //TextView array initalisation
        car_name = new TextView[carlist.length];

        for (int i = 0; i < carlist.length; i++) {

            car_name[i] = new TextView(getApplicationContext());
            car_name[i].setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,(100 / carlist.length)));
            car_name[i].setText(carlist[i]);
            car_name[i].setTextColor(Color.parseColor("#04125E"));
            car_name[i].setTextSize(30);
            //car_name[i].setPadding(200,0,0,0);
            car_name[i].setGravity(Gravity.CENTER_HORIZONTAL);
            car_name[i].setId(i);
            car_name[i].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0,10));


            //parsing the json data


            car_name[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int z= v.getId();
                    sendData(z);

                }

            });

            master.addView(car_name[i]);

        }
    }

    public void sendData(int n){

        String car = car_name[n].getText().toString();


        Constants.URL_REGISTER = Constants.URL_REGISTER + "" + queryString();
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
                            Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
                            //modify if wifi changes


                            if (jsonObject.getString("error").equals("true")) {
                                //clearAllSelection();
                                Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                                Constants.URL_REGISTER = "http://192.168.43.248:80/CarTravel/";
                            } else {
                                Constants.URL_REGISTER = "http://192.168.43.248:80/CarTravel/";
                                Intent intent = new Intent(getApplicationContext(), CarDetails.class);
                                intent.putExtra("carlist", jsonObject.getString("message"));
                                startActivity(intent);

                                Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
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

                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user", "rk");
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    private String queryString() {
        //getSeat_category();
        String querry = "cardetails.php?car_name="+car_name;

        return querry;
    }

}



        /*
        btn= (Button)findViewById(R.id.car1);
        btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Constants.URL_REGISTER = Constants.URL_REGISTER + "" + queryString();
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


                                    if (jsonObject.getString("error").equals("true")) {
                                        //clearAllSelection();
                                        Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                                        Constants.URL_REGISTER = "http://192.168.43.248:80/CarTravel/";
                                    } else {
                                        Constants.URL_REGISTER = "http://192.168.43.248:80/CarTravel/";
                                        Intent intent = new Intent(getApplicationContext(), CarDetails.class);
                                        intent.putExtra("ListOfAvailableCar", jsonObject.getString("message"));
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

                                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("user", "rk");
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);
            }

            private String queryString() {
                //getSeat_category();
                String querry = "sample.php?car1=" +btn;

                return querry;
            }

           /* @Override
            public void onClick(View v) {

                movetoCarDetails();
            }

            private void movetoCarDetails()
            {
                Intent intent = new Intent(ListOfAvailableCar.this, CarDetails.class);
                startActivity(intent);
            }
        });

       }
}*/
