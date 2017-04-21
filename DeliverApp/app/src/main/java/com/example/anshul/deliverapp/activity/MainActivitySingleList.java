package com.example.anshul.deliverapp.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.anshul.deliverapp.R;
import com.example.anshul.deliverapp.adapters.JobAdapter;
import com.example.anshul.deliverapp.application.AppController;
import com.example.anshul.deliverapp.model.pickup.Contact;
import com.example.anshul.deliverapp.model.pickup.PickUpModel;
import com.example.anshul.deliverapp.sharedprefs.PreferenceManager;
import com.example.anshul.deliverapp.utility.AppApi;
import com.example.anshul.deliverapp.utility.AppConstants;
import com.example.anshul.deliverapp.utility.Utility;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by anshul.g on 4/14/2017.
 */

public class MainActivitySingleList extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Contact> arrayList;
    JobAdapter mAdapter;
    ImageView iv_logout;
    TextView tv_date,tv_routeid;
    ProgressBar progressBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       setContentView(R.layout.activity_main_singlelist);

        if (Utility.getDaysDiff(this)){
            Intent intent=new Intent(this,LoginActivity.class);
            startActivity(intent);
            finish();
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tv_date=(TextView)findViewById(R.id.date);
        tv_routeid=(TextView)findViewById(R.id.routeid);
        iv_logout=(ImageView)findViewById(R.id.iv_logout);
        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        progressBar=(ProgressBar)findViewById(R.id.progressbar);

        arrayList=new ArrayList<>();
        mAdapter=new JobAdapter(this,arrayList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        Utility.startTrackScheduler(this);

        iv_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLogoutAlertDialog();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Utility.getLocationOnOff(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        makeJsonObjReq();
    }

    private void makeJsonObjReq() {
        progressBar.setVisibility(View.VISIBLE);

//        Utility.showProgrss("Login...",getActivity());
        JSONObject jsonObject=new JSONObject();

        try {
            jsonObject.put("rider_id", PreferenceManager.getStringInPref(this, AppConstants.USER_ID,""));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                AppApi.BASE_URL+"route.php", jsonObject,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Login", response.toString());
//                        Utility.hideProgrss();
                        progressBar.setVisibility(View.GONE);

                        ObjectMapper mapper = new ObjectMapper();
                        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        try {
                            PickUpModel pickUpModel = mapper.readValue(response.toString(), PickUpModel.class);

                            if (pickUpModel.getContacts()!=null &&pickUpModel.getContacts().size()>0){
                                Log.d("list", response.toString());
                                tv_date.setText("Date:- " +pickUpModel.getContacts().get(0).getDate());
                                tv_routeid.setText("Route Id:- "+pickUpModel.getContacts().get(0).getRouteId());
                                arrayList.clear();
                                arrayList.addAll(pickUpModel.getContacts());

//                                JobAdapter  mAdapter=new JobAdapter(pickUpModel.getContacts());

//                                JobAdapter mAdapter=new JobAdapter(arrayList);
                                mAdapter.notifyDataSetChanged();
//                                recyclerView.setAdapter(mAdapter);
                            }else {

                                Log.d("Login", response.toString());
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }



                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("list", "Error: " + error.getMessage());
//                Utility.hideProgrss();
                progressBar.setVisibility(View.GONE);

            }
        }) {

            /**
             * Passing some request headers
             * */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }


        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq);

        // Cancelling request
        // ApplicationController.getInstance().getRequestQueue().cancelAll(tag_json_obj);
    }

    /**
     * Show logout alert dialog
     */
    private void showLogoutAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivitySingleList.this);
        builder.setTitle("Are You Sure?");
        builder.setMessage("Do you wnat to logout?");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Delete database
                SharedPreferences.Editor editor = getSharedPreferences(AppConstants.M_SHARED_PREFERENCE_FILE, Context.MODE_PRIVATE).edit();
                editor.clear();
                editor.commit();

                Utility.cancelAlarm(MainActivitySingleList.this);

                Intent intent = new Intent(MainActivitySingleList.this,LoginActivity.class);
                finish();
                startActivity(intent);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
