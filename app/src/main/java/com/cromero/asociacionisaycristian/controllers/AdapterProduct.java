package com.cromero.asociacionisaycristian.controllers;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cromero.asociacionisaycristian.EditProductActivity;
import com.cromero.asociacionisaycristian.R;
import com.cromero.asociacionisaycristian.models.Product;
import com.cromero.asociacionisaycristian.models.Store;

import java.util.ArrayList;

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.AdapterProductViewHolder> {
    private ArrayList<Product> products;
    private  Store store;
    private Context context;

    //AdapterStore's constructor
    public AdapterProduct(ArrayList<Product> products, Store store) {
        this.products = products;
        this.store=store;
    }

    @NonNull
    @Override
    public AdapterProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //The view is inflated
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_item, parent, false);

        //The view holder is created
        AdapterProductViewHolder avh = new AdapterProductViewHolder((itemView));
        return avh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterProductViewHolder holder, int position) {
        Product productItem = products.get(position);

        String idProduct= productItem.getIdProduct();
        String nameProduct= productItem.getProductName();
        String description = productItem.getDescription();
        Float price = productItem.getPrice();
        Float stock = productItem.getStock();

        //The store data are put into the layout
        holder.tv_nameProduct.setText(nameProduct);
        holder.tv_priceProduct.setText(price.toString() + "€");
        holder.tv_descriptionProduct.setText(description);
        holder.tv_stock.setText(stock.toString());

        //Each item will have an OnClickListener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //When an item is pressed an option menu will be showed
               showDialog(v, idProduct, nameProduct);
            }
        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }


    public class AdapterProductViewHolder extends RecyclerView.ViewHolder {
        //Layout items
        private TextView tv_nameProduct, tv_priceProduct,tv_descriptionProduct, tv_stock;

        //ViewHolder constructor
        public AdapterProductViewHolder(View itemView) {
            super(itemView);

            //The context is given the correct value
            context = itemView.getContext();

            //Layout items initialization
            tv_nameProduct = (TextView) itemView.findViewById(R.id.tv_nameProduct);
            tv_priceProduct = (TextView) itemView.findViewById(R.id.tv_priceProduct);
            tv_descriptionProduct = (TextView) itemView.findViewById(R.id.tv_descriptionProduct);
            tv_stock = (TextView) itemView.findViewById(R.id.tv_stock);
        }
    }

    //OptionDialog creation method
    private void showDialog(View view, String idProduct, String nameProduct) {
        //Initialization
        AlertDialog.Builder optionDialog = new AlertDialog.Builder(context);
        optionDialog.setTitle(nameProduct);

        //Options creation
        CharSequence opciones[] = {view.getResources().getText(R.string.edit), view.getResources().getText(R.string.delete)};
        //OnClickMethod for each option
        optionDialog.setItems(opciones, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                switch (item) {
                    case 0:
                        Intent intent =new Intent(context, EditProductActivity.class);
                        intent.putExtra("idProduct",idProduct);
                        intent.putExtra("store", store);
                        context.startActivity(intent);
                        break;
                    case 1:
                        deleteConfirmation(view, idProduct, nameProduct);
                        break;
                }
            }
        });
        //Dialog creation
        AlertDialog alertDialog = optionDialog.create();
        alertDialog.show();
    }

    private void deleteConfirmation(View view, String idProduct, String nameProduct) {
        //Initialization
        AlertDialog.Builder alertDialogBu = new AlertDialog.Builder(context);
        alertDialogBu.setTitle(view.getResources().getText(R.string.delete));
        alertDialogBu.setMessage(view.getResources().getText(R.string.are_you_sure) + nameProduct + view.getResources().getText(R.string.cant_undo));

        //Positive option
        alertDialogBu.setPositiveButton(view.getResources().getText(R.string.accept), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, nameProduct + view.getResources().getText(R.string.is_deleted), Toast.LENGTH_SHORT).show();
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
