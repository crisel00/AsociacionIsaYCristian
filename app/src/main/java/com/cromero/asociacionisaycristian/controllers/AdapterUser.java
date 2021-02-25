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

import com.cromero.asociacionisaycristian.ProductsActivity;
import com.cromero.asociacionisaycristian.R;
import com.cromero.asociacionisaycristian.models.User;

import java.util.ArrayList;

public class AdapterUser extends RecyclerView.Adapter<AdapterUser.AdapterUserViewHolder> {
    private ArrayList<User> users;
    private Context context;

    //AdapterUser's constructor
    public AdapterUser(ArrayList<User> users) {

        this.users = users;

    }

    @NonNull
    @Override
    public AdapterUser.AdapterUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //The view is inflated
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_item, parent, false);

        //The view holder is created
        AdapterUser.AdapterUserViewHolder avh = new AdapterUser.AdapterUserViewHolder((itemView));
        return avh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterUser.AdapterUserViewHolder holder, int position) {
        User userItem = users.get(position);
        String username = userItem.getUserName();
        String userEmail = userItem.getEmail();
        float userBalance = userItem.getBalance();
        //The store data are put into the layout
        holder.tv_username.setText(username);
        holder.tv_userEmail.setText(userEmail);
        holder.tv_userBalance.setText(String.valueOf(userBalance)+"€");

        //Each item will have an OnClickListener
       /* holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //When an item is pressed an option menu will be showed
                showDialog(v,idStore,nameStore);
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return users.size();
    }


    public class AdapterUserViewHolder extends RecyclerView.ViewHolder {
        //Layout items
        private TextView tv_username, tv_userEmail, tv_userBalance;

        //ViewHolder constructor
        public AdapterUserViewHolder(View itemView) {
            super(itemView);

            //The context is given the correct value
            context = itemView.getContext();

            //Layout items initialization
            tv_username = (TextView) itemView.findViewById(R.id.tv_username);
            tv_userEmail = (TextView) itemView.findViewById(R.id.tv_userEmail);
            tv_userBalance = (TextView) itemView.findViewById(R.id.tv_userBalance);
        }
    }

    //OptionDialog creation method
    private void showDialog(View view, String idStore, String nameStore) {
        //Initialization
        AlertDialog.Builder optionDialog = new AlertDialog.Builder(context);
        optionDialog.setTitle(nameStore);

        //Options creation
        CharSequence opciones[] = {view.getResources().getText(R.string.manage), view.getResources().getText(R.string.delete)};
        //OnClickMethod for each option
        optionDialog.setItems(opciones, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                switch (item) {
                    case 0:
                        Intent intent = new Intent(context, ProductsActivity.class);
                        intent.putExtra("idStore", idStore);
                        context.startActivity(intent);
                        break;
                    case 1:
                        deleteConfirmation(view, idStore, nameStore);
                        break;
                }
            }
        });
        //Dialog creation
        AlertDialog alertDialog = optionDialog.create();
        alertDialog.show();
    }

    private void deleteConfirmation(View view, String idStore, String nameStore) {
        //Initialization
        AlertDialog.Builder alertDialogBu = new AlertDialog.Builder(context);
        alertDialogBu.setTitle(view.getResources().getText(R.string.delete));
        alertDialogBu.setMessage(view.getResources().getText(R.string.are_you_sure) + nameStore + view.getResources().getText(R.string.cant_undo));

        //Positive option
        alertDialogBu.setPositiveButton(view.getResources().getText(R.string.accept), new DialogInterface.OnClickListener() {
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