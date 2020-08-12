package com.assignment.com;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.internal.$Gson$Preconditions;
import com.leo.simplearcloader.SimpleArcLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewClick {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    SimpleArcLoader simpleArcLoader;
    RecyclerViewClick recyclerViewClick;
    private TextView result;
    private List<ListItem> listItems;
    private static final String URL = RecyclerViewClick.URL;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        result = findViewById(R.id.textViewResult);
        simpleArcLoader = findViewById(R.id.loader);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();
        loadRecyclerView();
        simpleArcLoader.start();

        if(!isConnected()){
            new android.app.AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("No Internet ..")
                    .setMessage("Please check your Internet Connection.")
                    .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    })
                    .show();
        }

    }


    private boolean isConnected(){
        ConnectivityManager connectivityManager  = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo net  = connectivityManager.getActiveNetworkInfo();
        return net!=null && net.isConnected();
    }


    private void loadRecyclerView() {



        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                URL,
                null,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        System.out.println(response.toString());
                        try {
                            for(int i =0;i<response.toString().length();i++) {
                                JSONObject mainResponse = new JSONObject(response.toString());
                                JSONArray resultsArray = mainResponse.getJSONArray("results");
                                JSONObject getPosition = new JSONObject(resultsArray.get(i).toString());
                                JSONObject name = new JSONObject(getPosition.getString("name").toString());
                                JSONObject date = new JSONObject(getPosition.getString("dob").toString());
                                JSONObject picture = new JSONObject(getPosition.getString("picture").toString());
                                JSONObject location = new JSONObject(getPosition.getString("location").toString());
                                JSONObject printObject = resultsArray.getJSONObject(i);
                                ListItem item = new ListItem(name.getString("title")+". "+name.getString("first")+" "+name.getString("last"), location.getString("country"),getPosition.getString("phone"),date.getString("date").substring(0,10),picture.getString("large"),printObject.getString("email"));
                                listItems.add(item);
                            }

                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapter = new MyAdapter(listItems,getApplicationContext(), recyclerViewClick);
                        recyclerView.setAdapter(adapter);
                        simpleArcLoader.stop();
                        simpleArcLoader.setVisibility(View.GONE);


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(MainActivity.this, "Error please try again", Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue requestQueue =Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);


    }


    @Override
    public void onItemClick(int position) {

    }
}



