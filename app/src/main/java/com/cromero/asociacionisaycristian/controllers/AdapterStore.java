package com.cromero.asociacionisaycristian.controllers;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cromero.asociacionisaycristian.R;
import com.cromero.asociacionisaycristian.models.Store;

import java.util.ArrayList;

public class AdapterStore extends RecyclerView.Adapter<AdapterStore.AdapterStoreViewHolder> {
    private ArrayList<Store> stores;
    private Context context;

    //AdapterStore's constructor
    public AdapterStore(ArrayList<Store> stores){

        this.stores = stores;
    }

    @NonNull
    @Override
    public AdapterStoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //The view is inflated
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.store_item,parent,false);

        //The view holder is created
        AdapterStoreViewHolder avh=new AdapterStoreViewHolder((itemView));
        return avh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterStoreViewHolder holder, int position) {
        //The store data are put into the layout
        Store storeItem= stores.get(position);
        holder.tv_nameStore.setText(storeItem.getNameStore());
        holder.tv_idStore.setText(storeItem.getIdStore());

        //Each item will have an OnClickListener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*//When an item is pressed the chat activity will be launched
                Intent intent= new Intent(context, Chat.class);
                intent.putExtra("uidContact",storeItem.uid);
                context.startActivity(intent);*/
            }
        });

    }

    @Override
    public int getItemCount() {
        return stores.size();
    }


    public class AdapterStoreViewHolder extends RecyclerView.ViewHolder{
        //Layout items
        private TextView tv_nameStore,tv_idStore;

        //ViewHolder constructor
        public  AdapterStoreViewHolder(View itemView){
            super(itemView);

            //The context is given the correct value
            context = itemView.getContext();

            //Layout items initialization
            tv_nameStore=(TextView) itemView.findViewById(R.id.tv_nameStore);
            tv_idStore=(TextView) itemView.findViewById(R.id.tv_idStore);
        }
    }
}