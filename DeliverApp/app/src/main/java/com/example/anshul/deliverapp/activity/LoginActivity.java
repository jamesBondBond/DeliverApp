package com.example.anshul.deliverapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.anshul.deliverapp.R;
import com.example.anshul.deliverapp.application.AppController;
import com.example.anshul.deliverapp.model.login.LoginModel;
import com.example.anshul.deliverapp.services.GpsService;
import com.example.anshul.deliverapp.sharedprefs.PreferenceManager;
import com.example.anshul.deliverapp.utility.AppApi;
import com.example.anshul.deliverapp.utility.AppConstants;
import com.example.anshul.deliverapp.utility.Constants;
import com.example.anshul.deliverapp.utility.Utility;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anshul on 3/4/2017.
 */

public class LoginActivity extends AppCompatActivity {

    EditText etuserName,etPassword;
    Button btn_login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!PreferenceManager.getStringInPref(this, AppConstants.USER_ID,"").equalsIgnoreCase("")){
            Intent intent=new Intent(this,MainActivitySingleList.class);
            startActivity(intent);
            finish();
        }
        setContentView(R.layout.activity_login_new);

        etuserName=(EditText)findViewById(R.id.username);
        etPassword=(EditText)findViewById(R.id.password);

//        etuserName.setText("101");
//        etPassword.setText("abc@123");

        btn_login=(Button)findViewById(R.id.sign_in_button);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
//                        Uri.parse("http://maps.google.com/maps?daddr=28.419445,77.038155"));
//                startActivity(intent);

                if (PreferenceManager.getStringInPref(LoginActivity.this,AppConstants.App_CLOSE,"").equalsIgnoreCase("true")){
                    Utility.showToast(LoginActivity.this,"Not A valid user");
                    return;
                }


                String userName=etuserName.getText().toString().trim();
                String password=etPassword.getText().toString().trim();

                if (userName.equalsIgnoreCase("") || password.equalsIgnoreCase("")){
                    Utility.showToast(LoginActivity.this,"Please enter username and password");
                }else {
                    if (Utility.isInternetConnected(LoginActivity.this)) {
                        makeJsonObjReq(userName, password);
                    }else {
                        Utility.showToast(LoginActivity.this,"No internet connection");
                    }
                }


            }
        });

        Utility.getLocationOnOff(this);
        startService(new Intent(this, GpsService.class));

    }


    /**
     * Making json object request
     * */
    private void makeJsonObjReq(String userName,String paswword) {
        Utility.showProgrss("Login...",this);
        JSONObject jsonObject=new JSONObject();

        try {
            jsonObject.put("id", userName);
            jsonObject.put("password",paswword);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                AppApi.BASE_URL+"login.php", jsonObject,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Login", response.toString());
                        Utility.hideProgrss();

                        ObjectMapper mapper = new ObjectMapper();
                        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        try {
                            LoginModel loginModel = mapper.readValue(response.toString(), LoginModel.class);

                            if (loginModel.getSuccess()){

                                PreferenceManager.saveStringInPref(LoginActivity.this, AppConstants.USER_ID,loginModel.getUid());
                                Calendar today = Calendar.getInstance();

                                if (PreferenceManager.getLongInPref(LoginActivity.this,AppConstants.LOGIN_TIME,0)==0) {
                                    PreferenceManager.saveLongInPref(LoginActivity.this, AppConstants.LOGIN_TIME, today.getTimeInMillis());
                                }

                                Intent intent=new Intent(LoginActivity.this, MainActivitySingleList.class);
                                startActivity(intent);
                                finish();
                            }else {


                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }



                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Login", "Error: " + error.getMessage());
                Utility.hideProgrss();
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



}
