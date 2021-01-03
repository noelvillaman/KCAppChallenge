package com.software.leonwebmedia.kcappchallenge.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.software.leonwebmedia.kcappchallenge.R;
import com.software.leonwebmedia.kcappchallenge.model.ContactsResponse;

import java.util.ArrayList;

public class MultiViewTypeAdapter extends RecyclerView.Adapter {

    private ArrayList<ContactsResponse> dataSet;
    Context mContext;
    int total_types;

    public static class OthersTypeViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;
        ImageView imageView;
        TextView occupation;

        public OthersTypeViewHolder(View itemView) {
            super(itemView);

            this.nameTextView = itemView.findViewById(R.id.other_contact_adapter_name);
            this.imageView = itemView.findViewById(R.id.other_contact_image);
            this.occupation = itemView.findViewById(R.id.other_contact_adapter_ocupation);
        }
    }

    public static class FavoritesTypeViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;
        ImageView contactImageView;
        TextView occupation;
        ImageView starImageView;

        public FavoritesTypeViewHolder(View itemView) {
            super(itemView);

            this.nameTextView = itemView.findViewById(R.id.other_contact_adapter_name);
            this.contactImageView = itemView.findViewById(R.id.other_contact_image);
            this.occupation = itemView.findViewById(R.id.other_contact_adapter_ocupation);
            this.starImageView = itemView.findViewById(R.id.contact_star);
        }
    }

    public MultiViewTypeAdapter(ArrayList<ContactsResponse>data, Context context) {
        this.dataSet = data;
        this.mContext = context;
        total_types = dataSet.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        switch (viewType) {
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contacts_list_view, parent, false);
                return new OthersTypeViewHolder(view);
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contacts_list_view, parent, false);
                return new FavoritesTypeViewHolder(view);
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        Boolean favorite = dataSet.get(position).isFavorite();
        if (favorite) {
            return 0;
        } else {
            return 1;
        }
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int listPosition) {
        ContactsResponse object = dataSet.get(listPosition);
        if (object != null) {
            Boolean favorite = object.isFavorite();
            if (favorite) {
                ((OthersTypeViewHolder) holder).nameTextView.setText(object.getName());
                ((OthersTypeViewHolder) holder).occupation.setText(object.getCompanyName());
            } else if (!(favorite)) {
                ((FavoritesTypeViewHolder) holder).nameTextView.setText(object.getName());
                ((FavoritesTypeViewHolder) holder).occupation.setText(object.getCompanyName());
            }
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
