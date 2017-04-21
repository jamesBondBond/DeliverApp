package com.example.anshul.deliverapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.anshul.deliverapp.R;
import com.example.anshul.deliverapp.adapters.DeliveryAdapter;
import com.example.anshul.deliverapp.adapters.SuccessAdapter;
import com.example.anshul.deliverapp.application.AppController;
import com.example.anshul.deliverapp.model.delivery.Contact;
import com.example.anshul.deliverapp.model.delivery.DeliverModel;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Anshul on 3/5/2017.
 */

public  class SuccessFragment extends Fragment implements Observer{
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    RecyclerView recyclerView;
    List<Contact> arrayList;
    private static final String ARG_SECTION_NUMBER = "section_number";
    SuccessAdapter mAdapter;
    ProgressBar progressBar;

    public SuccessFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static SuccessFragment newInstance(int sectionNumber) {
        SuccessFragment fragment = new SuccessFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
//        TextView textView = (TextView) rootView.findViewById(R.id.section_label);
//        textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        makeJsonObjReq();

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar=(ProgressBar)view.findViewById(R.id.progressbar);
        recyclerView=(RecyclerView)view.findViewById(R.id.recycler_view);

        arrayList=new ArrayList<>();
         mAdapter=new SuccessAdapter(arrayList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

    }


    @Override
    public void onResume() {
        super.onResume();

//        Utility.showToast(getActivity(),"C");


    }


    private void makeJsonObjReq() {
//        Utility.showProgrss("Login...",getActivity());
        progressBar.setVisibility(View.VISIBLE);

        JSONObject jsonObject=new JSONObject();

        try {
            jsonObject.put("rider_id", PreferenceManager.getStringInPref(getActivity(), AppConstants.USER_ID,""));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                AppApi.BASE_URL+"success.php", jsonObject,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Login", response.toString());
//                        Utility.hideProgrss();
                        progressBar.setVisibility(View.GONE);
                        ObjectMapper mapper = new ObjectMapper();
                        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        try {
                            DeliverModel deliverModel = mapper.readValue(response.toString(), DeliverModel.class);

                            if (deliverModel.getContacts()!=null &&deliverModel.getContacts().size()>0){
                                Log.d("Login", response.toString());
                                arrayList.addAll(deliverModel.getContacts());

//                                JobAdapter  mAdapter=new JobAdapter(pickUpModel.getContacts());
//                                JobAdapter  mAdapter=new JobAdapter(pickUpModel.getContacts());

//                                JobAdapter mAdapter=new JobAdapter(arrayList);
                                mAdapter.notifyDataSetChanged();
//                                recyclerView.setAdapter(mAdapter);
                            }else {
//                                Utility.showToast(getActivity(),"No Data Found");
                                Log.d("Login", response.toString());
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }



                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Login", "Error: " + error.getMessage());
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

    @Override
    public void update(Observable o, Object arg) {
        Utility.showToast(getActivity(),"Success");
    }
}