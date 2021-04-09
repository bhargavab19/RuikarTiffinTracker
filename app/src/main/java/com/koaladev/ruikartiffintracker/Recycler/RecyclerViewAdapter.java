package com.koaladev.ruikartiffintracker.Recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.koaladev.ruikartiffintracker.Model.Record;
import com.koaladev.ruikartiffintracker.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Record> recordList;

    public RecyclerViewAdapter(Context context, ArrayList<Record> recordList) {
        this.context = context;
        this.recordList = recordList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.view_records,parent,false);

        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        Record record=recordList.get(position);

        holder.date.setText(record.getDate());
        holder.lunch.setText(record.getLunch());
        holder.dinner.setText(record.getDinner());
        holder.quantity.setText(String.valueOf(record.getQuantity()));
        holder.rate.setText(String.valueOf(record.getRate()));

    }

    @Override
    public int getItemCount() {
        return recordList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView date;
        public TextView lunch;
        public TextView dinner;
        public TextView quantity;
        public TextView rate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            date=itemView.findViewById(R.id.txtDate);
            lunch=itemView.findViewById(R.id.txtLunch);
            dinner=itemView.findViewById(R.id.txtDinner);
            quantity=itemView.findViewById(R.id.txtQuantity);
            rate=itemView.findViewById(R.id.txtRate);

        }

        @Override
        public void onClick(View v) {

        }
    }
}
