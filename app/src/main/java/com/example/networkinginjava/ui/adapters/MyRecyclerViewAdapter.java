package com.example.networkinginjava.ui.adapters;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.networkinginjava.api.model.DataItem;
import com.example.networkinginjava.databinding.ListOfShopsBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private List<DataItem> listItem;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ListOfShopsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        DataItem dataItem = listItem.get(position);

        holder.binding.shopNameTv.setText(dataItem.getLastName());
        holder.binding.emailTv.setText(dataItem.getEmail());
        holder.binding.nameTv.setText(dataItem.getFirstName());

        Picasso.get().load(dataItem.getAvatar()).into(holder.binding.firstImageView);
        Picasso.get().load(dataItem.getAvatar()).into(holder.binding.secondImageView);



    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public void updateAdapterList(List<DataItem> dataItems){
        this.listItem = dataItems;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ListOfShopsBinding binding;

        public ViewHolder(ListOfShopsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

