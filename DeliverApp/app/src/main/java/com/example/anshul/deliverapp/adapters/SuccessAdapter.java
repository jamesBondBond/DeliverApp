package com.example.anshul.deliverapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anshul.deliverapp.R;
import com.example.anshul.deliverapp.application.AppController;
import com.example.anshul.deliverapp.model.delivery.Contact;

import java.util.List;

/**
 * Created by Anshul on 3/5/2017.
 */

public class SuccessAdapter extends RecyclerView.Adapter<SuccessAdapter.MyViewHolder> {


    List<Contact> arrayList;
    public SuccessAdapter(List<Contact> arrayList1){
        this.arrayList=arrayList1;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_list_item, parent, false);

        return new MyViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Contact contact = arrayList.get(position);
        holder.job_id.setText("Job Id:- " +contact.getJobId());
        holder.job_pincode.setText("Pincode:- "+contact.getDropPincode());
        holder.job_address.setText(contact.getDropAddress());
        holder.iv_map_icon.setVisibility(View.INVISIBLE);

        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AppController.getOnserver().notifyObservers();
            }
        });*/
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
