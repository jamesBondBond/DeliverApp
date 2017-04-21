package com.example.anshul.deliverapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anshul.deliverapp.R;
import com.example.anshul.deliverapp.activity.JobDetailsActivity;
import com.example.anshul.deliverapp.activity.JobDetailsDeliveryActivity;
import com.example.anshul.deliverapp.model.delivery.Contact;
import com.example.anshul.deliverapp.utility.AppConstants;
import com.example.anshul.deliverapp.utility.Utility;

import java.util.List;

/**
 * Created by Anshul on 3/5/2017.
 */

public class DeliveryAdapter extends RecyclerView.Adapter<DeliveryAdapter.MyViewHolder> {


    List<Contact> arrayList;
    Context mContext;

    public DeliveryAdapter(Context context, List<Contact> arrayList1){

        mContext=context;
        this.arrayList=arrayList1;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_list_item, parent, false);

        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Contact contact = arrayList.get(position);
        holder.job_id.setText("Job Id:- "+contact.getJobId());
        holder.job_pincode.setText("Pincode:- "+contact.getDropPincode());
        holder.job_address.setText(contact.getDropAddress());

        holder.iv_map_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                            Uri.parse("http://maps.google.com/maps?daddr="+contact.getDropLatLong()));
                    intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                    mContext.startActivity(intent);

                }catch (Exception e){
                    try {
                        Utility.showToast(mContext,"Device Do Not support Map");
                        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                                Uri.parse("http://maps.google.com/maps?daddr="+contact.getDropLatLong()));
                        mContext.startActivity(intent);

                    }catch (Exception e1){
                        Utility.showToast(mContext,"No support");
                    }

                }


            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                mainActivity.getLocation();

                Intent intent=new Intent(mContext, JobDetailsDeliveryActivity.class);
                intent.putExtra(AppConstants.JOB_TYPE,AppConstants.PICK_UP);
                intent.putExtra(AppConstants.DATA,arrayList.get(position));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView job_address, job_id,job_pincode;
        ImageView iv_map_icon;

        public MyViewHolder(View itemView) {
            super(itemView);
            job_address = (TextView) itemView.findViewById(R.id.job_address);
            job_id = (TextView) itemView.findViewById(R.id.job_id);
            job_pincode=(TextView)itemView.findViewById(R.id.job_pincode);
            iv_map_icon=(ImageView)itemView.findViewById(R.id.iv_navigate);

        }

    }
}
