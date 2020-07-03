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

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class CarMainActivity extends AppCompatActivity {

    public Button btn;

    EditText s1,d1;
    DatePicker date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_main);

        s1=(EditText)findViewById(R.id.source);
        d1= (EditText)findViewById(R.id.Destination);
        //date=(DatePicker)findViewById(R.id.calendarView2);



        btn= (Button)findViewById(R.id.button3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
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
                                    Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
                                    //modify if wifi changes


                                    if (jsonObject.getString("error").equals("true")) {
                                        //clearAllSelection();
                                        Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                                        Constants.URL_REGISTER = "http://192.168.43.248:80/CarTravel/";
                                    } else {
                                        Constants.URL_REGISTER = "http://192.168.43.248:80/CarTravel/";
                                        Intent intent = new Intent(getApplicationContext(), ListOfAvailableCar.class);
                                        intent.putExtra("carname", jsonObject.getString("message"));
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
                String querry = "search_car.php?source="+s1.getText().toString()+"&destination="+d1.getText().toString();

                return querry;
            }
           /* @Override
            public void onClick(View v) {

                movetoAvailablecar();
            }

            private void movetoAvailablecar()
            {
                Intent intent = new Intent(MainActivity.this, ListOfAvailableCar.class);
                startActivity(intent);
            }*/
        });
    }
}
