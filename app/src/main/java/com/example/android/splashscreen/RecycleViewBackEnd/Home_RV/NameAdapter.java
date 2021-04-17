package com.example.android.splashscreen.RecycleViewBackEnd.Home_RV;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.splashscreen.R;

import java.util.ArrayList;

public class NameAdapter extends RecyclerView.Adapter<NameAdapter.NameHolder> {
    ArrayList<Name> names;

    public NameAdapter(ArrayList<Name> names) {
        this.names = names;
    }

    @NonNull
    @Override
    public NameAdapter.NameHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem,parent,false);
        return new NameHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NameAdapter.NameHolder holder, int position) {
        Name name = names.get(position);
        holder.bind(name);
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    class NameHolder extends RecyclerView.ViewHolder{
        TextView textName;
        Name name;
        public NameHolder (View itemview){
            super(itemview);
            textName = itemview.findViewById(R.id.custom_tv_name);
        }
        void bind(Name name){
            this.name = name;
            textName.setText(name.getName());
        }
    }

}
