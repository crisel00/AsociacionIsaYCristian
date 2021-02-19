package com.cromero.asociacionisaycristian.controllers;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cromero.asociacionisaycristian.ProductsActivity;
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
        Store storeItem= stores.get(position);
        String idStore=storeItem.getIdStore();
        String nameStore=storeItem.getNameStore();
        //The store data are put into the layout
        holder.tv_nameStore.setText(nameStore);
        holder.tv_idStore.setText(idStore);

        //Each item will have an OnClickListener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //When an item is pressed an option menu will be showed
                showDialog(v,idStore,nameStore);

               /* Intent intent= new Intent(context, Chat.class);
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

    //OptionDialog creation method
    private void showDialog(View view,String idStore, String nameStore){
        //Initialization
        AlertDialog.Builder optionDialog = new AlertDialog.Builder(context);
        optionDialog.setTitle(nameStore);

        //Options creation
        CharSequence opciones[] = {view.getResources().getText(R.string.manage),view.getResources().getText(R.string.delete)};
        //OnClickMethod for each option
        optionDialog.setItems(opciones, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                switch(item){
                    case 0:
                        Intent intent= new Intent(context, ProductsActivity.class);
                        intent.putExtra("idStore",idStore);
                        context.startActivity(intent);
                        break;
                    case 1:
                        deleteConfirmation(view, idStore,nameStore);
                        break;
                }
            }
        });
        //Dialog creation
        AlertDialog alertDialog = optionDialog.create();
        alertDialog.show();
    }

    private void deleteConfirmation(View view,String idStore, String nameStore){
        //Initialization
        AlertDialog.Builder alertDialogBu = new AlertDialog.Builder(context);
        alertDialogBu.setTitle(view.getResources().getText(R.string.delete));
        alertDialogBu.setMessage(view.getResources().getText(R.string.are_you_sure) + nameStore + view.getResources().getText(R.string.cant_undo));

        //Positive option
        alertDialogBu.setPositiveButton( view.getResources().getText(R.string.accept), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, nameStore + view.getResources().getText(R.string.is_deleted), Toast.LENGTH_SHORT).show();
            }
        });
        //Negative option
        alertDialogBu.setNegativeButton(view.getResources().getText(R.string.cancel), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, view.getResources().getText(R.string.cancelled), Toast.LENGTH_SHORT).show();
            }
        });
        //Dialog creation
        AlertDialog alertDialog = alertDialogBu.create();
        alertDialog.show();
    }
}