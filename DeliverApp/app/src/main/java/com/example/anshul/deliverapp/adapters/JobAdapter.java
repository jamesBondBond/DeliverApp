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

import com.example.anshul.deliverapp.activity.JobDetailsActivity;
import com.example.anshul.deliverapp.activity.MainActivity;
import com.example.anshul.deliverapp.R;
import com.example.anshul.deliverapp.model.pickup.Contact;
import com.example.anshul.deliverapp.utility.AppConstants;
import com.example.anshul.deliverapp.utility.Utility;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Anshul on 3/5/2017.
 */

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.MyViewHolder> {


    List<Contact> arrayList;
    Context mContext;
    public JobAdapter(Context context,  List<Contact> arrayList1){
        this.arrayList=arrayList1;
        mContext=context;
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
        holder.job_id.setText("Job Id:-"+contact.getJobId());
        holder.job_pincode.setText("PinCode:-"+contact.getPickupPincode());
        holder.job_address.setText(contact.getPickupAddress());
        holder.iv_map_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                            Uri.parse("http://maps.google.com/maps?daddr="+contact.getPickupLatLong()));
                    intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                    mContext.startActivity(intent);

                }catch (Exception e){
                    try {
                        Utility.showToast(mContext,"Device Do Not support Map");
                        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                                Uri.parse("http://maps.google.com/maps?daddr="+contact.getPickupLatLong()));
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

               Intent intent=new Intent(mContext, JobDetailsActivity.class);
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
