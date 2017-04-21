package com.example.anshul.deliverapp.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.anshul.deliverapp.application.AppController;
import com.example.anshul.deliverapp.sharedprefs.PreferenceManager;
import com.example.anshul.deliverapp.utility.AppApi;
import com.example.anshul.deliverapp.utility.AppConstants;
import com.example.anshul.deliverapp.utility.Constants;
import com.example.anshul.deliverapp.utility.Utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;

/**
 * Created by gjs331 on 5/20/2015.
 */
public class GpsScheduler extends IntentService {

   public GpsScheduler(){
        super("GpsScheduler");
    }
    public GpsScheduler(String name) {

        super(name);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        HashMap<String,String> map = Utility.getLocationScheduler(AppController.getInstance());



        String longitude=map.get(Constants.LONGITUDE);

        if (map.size()!=0 && longitude!=null && !longitude.equalsIgnoreCase("0.0")) {
            Calendar cal = Calendar.getInstance();



            JSONObject finalRequestJson = new JSONObject();

            try {
                finalRequestJson.put("lat",map.get(Constants.LATITUDE));
                finalRequestJson.put("long",map.get(Constants.LONGITUDE));
                finalRequestJson.put("rider_id", PreferenceManager.getStringInPref(this, AppConstants.USER_ID,""));


                System.out.println("track: " + finalRequestJson.toString());

                JsonObjectRequest pushRequest = new JsonObjectRequest(Request.Method.POST, AppApi.BASE_URL+"latlongpush.php", finalRequestJson, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        System.out.println("track log response: " + jsonObject.toString());


                        // Start call log scheduler

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        volleyError.printStackTrace();

                        // Start call log scheduler
                    }
                });
                AppController.getInstance().addToRequestQueue(pushRequest);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        }
//        Toast.makeText(this, map.get(Constants.PROVIDER), Toast.LENGTH_LONG).show();

//        Log.i("provoider",map.get(Constants.PROVIDER)+" "+map.get(Constants.LONGITUDE));

    }


