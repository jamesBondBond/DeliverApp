package com.example.anshul.deliverapp.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.anshul.deliverapp.R;
import com.example.anshul.deliverapp.application.AppController;
import com.example.anshul.deliverapp.model.PushModel;

import com.example.anshul.deliverapp.model.delivery.Contact;
import com.example.anshul.deliverapp.sharedprefs.PreferenceManager;
import com.example.anshul.deliverapp.utility.AppApi;
import com.example.anshul.deliverapp.utility.AppConstants;
import com.example.anshul.deliverapp.utility.Utility;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anshul on 3/5/2017.
 */

public class JobDetailsDeliveryActivity extends AppCompatActivity {
    String strReasonName;
    TextView tv_custname,tv_custno,tv_custaddr,tv_itemdesc,tv_specialinstrn;
    Button btn_success,btn_fail;
    Contact contact;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobdetails);

      /*  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);*/

        tv_custname=(TextView)findViewById(R.id.detail_custname);
        tv_custno=(TextView)findViewById(R.id.detail_custNo);
        tv_custaddr=(TextView)findViewById(R.id.detail_custaddress);
        tv_itemdesc=(TextView)findViewById(R.id.detail_itemdesc);
        tv_specialinstrn=(TextView)findViewById(R.id.detail_specialinstn);
        btn_success=(Button)findViewById(R.id.button_sucess);
        btn_fail=(Button)findViewById(R.id.button_fail);

        Intent i = getIntent();
        contact = (Contact) i.getSerializableExtra(AppConstants.DATA);

        tv_custname.setText(contact.getDropCustName());
        tv_custno.setText(contact.getDropCustNo());
        tv_custaddr.setText(contact.getDropAddress());
        tv_itemdesc.setText(contact.getItemDescription());
        tv_specialinstrn.setText(contact.getSpacialInstruction());

        Log.i("ss",contact.toString());

        btn_success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Utility.isInternetConnected(JobDetailsDeliveryActivity.this)){
                    strReasonName="";
                    makeJsonObjReq("1");
                }else {
                    Utility.showToast(JobDetailsDeliveryActivity.this,"No Internet Connection");
                }

//                showAlert("1");
            }
        });
//{"rider_id":"1001","job_id":"101","job_status":"1","job_type":"D","job_failed_reason":""}
        btn_fail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            AppController.getOnserver().notifyObservers();
                if (Utility.isInternetConnected(JobDetailsDeliveryActivity.this)){
                    showAlert("0");
                }else {
                    Utility.showToast(JobDetailsDeliveryActivity.this,"No Internet Connection");
                }


//                makeJsonObjReq("0");
            }
        });
    }


    /**
     * Making json object request
     * */
    private void makeJsonObjReq(String status) {
        Utility.showProgrss("Login...",this);
        JSONObject jsonObject=new JSONObject();

        try {
            jsonObject.put("rider_id", PreferenceManager.getStringInPref(JobDetailsDeliveryActivity.this,AppConstants.USER_ID,""));
            jsonObject.put("job_id",contact.getJobId());
            jsonObject.put("job_status",status);
            jsonObject.put("job_type",contact.getJobType());
            jsonObject.put("job_failed_reason",strReasonName);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                AppApi.BASE_URL+"pushstatus.php", jsonObject,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Login", response.toString());
                        Utility.hideProgrss();

                        ObjectMapper mapper = new ObjectMapper();
                        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        try {
                            PushModel pushModel = mapper.readValue(response.toString(), PushModel.class);

                            if (pushModel.getSuccess()){

                               Utility.showToast(JobDetailsDeliveryActivity.this,"Sucess");
                                finish();
                            }else {
                                Utility.showToast(JobDetailsDeliveryActivity.this,"Failes to sync");

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



    public void showAlert(final String status){

        AlertDialog.Builder builderSingle = new AlertDialog.Builder(JobDetailsDeliveryActivity.this);

        builderSingle.setTitle("Select One Reason:-");

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(JobDetailsDeliveryActivity.this, android.R.layout.select_dialog_singlechoice);
        arrayAdapter.add("Did not pick up the call");
        arrayAdapter.add("Refused");
        arrayAdapter.add("Not At Home");
        arrayAdapter.add("Location Not found");


        builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

       /* builderSingle.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                String strName = arrayAdapter.getItem(which);
                Utility.showToast(JobDetailsActivity.this,strName);
                dialog.dismiss();
            }
        });*/

        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                 strReasonName = arrayAdapter.getItem(which);
                if (Utility.isInternetConnected(JobDetailsDeliveryActivity.this)){
                    makeJsonObjReq(status);

                }else {
                    Utility.showToast(JobDetailsDeliveryActivity.this,"No Internet Connection");
                }
             /*   AlertDialog.Builder builderInner = new AlertDialog.Builder(JobDetailsActivity.this);
                builderInner.setMessage(strName);
                builderInner.setTitle("Your Selected Item is");
                builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,int which) {
                        dialog.dismiss();
                    }
                });
                builderInner.show();*/
            }
        });
        builderSingle.create().show();
    }
}
