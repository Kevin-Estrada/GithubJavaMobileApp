package com.example.kevinestradagithubapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {

    final static String TAG = "GM_Challenge";
    final static String PUBLIC_REPO = "https://api.github.com/repos/twbs/bootstrap/commits";
    //final static String PUBLIC_REPO =  "https://pastebin.com/raw/Em972E5s";

    // NOT SAFE TO STORE PERSONAL API TOKEN IN CODE
    final static String APITOKEN = "2ff98c0cbbe2565893293b3e6277321c1919f95d";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        volleyGet();
    }

    public void volleyGet() {
        Log.d(TAG,"getting user info");

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, PUBLIC_REPO, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ((TextView)findViewById(R.id.jsonData)).setText("Response is: "+ response.toString());
                Log.d(TAG,"call succeeded");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ((TextView)findViewById(R.id.jsonData)).setText("That didn't work!");
                Log.d(TAG,"call didnt succeed");
            }
        });

        requestQueue.add(jsonArrayRequest);
    }
}