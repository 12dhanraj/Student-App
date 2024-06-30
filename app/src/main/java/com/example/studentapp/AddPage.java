package com.example.studentapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class AddPage extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5,e6,e7,e8;
    AppCompatButton b1,b2;
    String apiUrl="https://courseapplogix.onrender.com/addstudents";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_page);
        e1=(EditText) findViewById(R.id.fname);
        e2=(EditText) findViewById(R.id.lname);
        e3=(EditText) findViewById(R.id.clg);
        e4=(EditText) findViewById(R.id.dob);
        e5=(EditText) findViewById(R.id.course);
        e6=(EditText) findViewById(R.id.mobno);
        e7=(EditText) findViewById(R.id.email);
        e8=(EditText) findViewById(R.id.address);
        b1=(AppCompatButton) findViewById(R.id.gbbtn);
        b2=(AppCompatButton) findViewById(R.id.add);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getFirstname=e1.getText().toString();
                String getLastname=e2.getText().toString();
                String getCollege=e3.getText().toString();
                String getDateofbirth=e4.getText().toString();
                String getCourse=e5.getText().toString();
                String getMobileno=e6.getText().toString();
                String getEMail=e7.getText().toString();
                String getAddress=e8.getText().toString();
                JSONObject student=new JSONObject();
                try {
                    student.put("firstname",getFirstname);
                    student.put("lastname",getLastname);
                    student.put("college",getCollege);
                    student.put("dob",getDateofbirth);
                    student.put("course",getCourse);
                    student.put("mobile",getMobileno);
                    student.put("email",getEMail);
                    student.put("address",getAddress);

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(
                        Request.Method.POST,
                        apiUrl,
                        student,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(getApplicationContext(), "Added Successfully", Toast.LENGTH_LONG).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_LONG).show();
                    }
                }
                );
                RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(jsonObjectRequest);
            }
        });
    }
}