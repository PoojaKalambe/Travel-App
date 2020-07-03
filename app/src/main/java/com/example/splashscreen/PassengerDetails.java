package com.example.splashscreen;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
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

import java.lang.reflect.Member;
import java.util.HashMap;
import java.util.Map;

public class PassengerDetails extends AppCompatActivity {

    Button btn;
    EditText Leadername,Mobile, Email,member;
    DatePicker date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_details);

        btn = (Button) findViewById(R.id.submit1);
        Leadername = (EditText) findViewById(R.id.leadername);
        Mobile = (EditText) findViewById(R.id.mobile);
        Email = (EditText) findViewById(R.id.email);
        member = (EditText) findViewById(R.id.member);
        date = (DatePicker) findViewById(R.id.calendarView3);

        btn.setOnClickListener(new View.OnClickListener() {

            //database btnclick

            @Override
            public void onClick(View v) {

                //setting all the querry string parameter

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
                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                        intent.putExtra("PassengerDetails", jsonObject.getString("message"));
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
                String querry = "sample.php?leader_name=" + Leadername + "mobile" + Mobile + "email" + Email + "member" + member + "Date" + date;

                return querry;
            }
        });
    }
}
