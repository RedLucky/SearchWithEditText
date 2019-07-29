package com.bangkukayu.tolongrek.searchwithinputtext;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bangkukayu.tolongrek.searchwithinputtext.model.Service;

import java.util.ArrayList;

public class RecyclerAdapterService extends RecyclerView.Adapter<RecyclerAdapterService.ViewHolder> {
    private Context mContext;
    private ArrayList<Service> services;
    private Intercator intercator;

    public RecyclerAdapterService(Context mContext, ArrayList<Service> services, Intercator intercator) {
        this.mContext = mContext;
        this.services = services;
        this.intercator = intercator;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_service, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewResult.setText(services.get(position).getName_service());
        holder.textViewResult.setOnClickListener(v -> intercator.onSelected(services.get(position)));
    }

    @Override
    public int getItemCount() {
        return services.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewResult;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewResult = itemView.findViewById(R.id.text_item);
        }
    }
}
