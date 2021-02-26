package com.cromero.asociacionisaycristian.controllers;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cromero.asociacionisaycristian.ProductsActivity;
import com.cromero.asociacionisaycristian.R;
import com.cromero.asociacionisaycristian.models.Order;
import com.cromero.asociacionisaycristian.models.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class AdapterOrderLine extends RecyclerView.Adapter<AdapterOrderLine.AdapterOrderLineViewHolder> {
    private ArrayList<Order> orders;
    private Context context;
    //Database variables
    private FirebaseDatabase database;
    private DatabaseReference dbReference;

    //AdapterOrder's constructor
    public AdapterOrderLine(ArrayList<Order> orders) {
        this.orders = orders;

        //Database initialization
        database = FirebaseDatabase.getInstance();
        dbReference = database.getReference().child("User");

    }

    @NonNull
    @Override
    public AdapterOrderLine.AdapterOrderLineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //The view is inflated
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.orderline_item, parent, false);

        //The view holder is created
        AdapterOrderLine.AdapterOrderLineViewHolder avh=new AdapterOrderLine.AdapterOrderLineViewHolder((itemView));
        return avh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterOrderLine.AdapterOrderLineViewHolder holder, int position) {
        Order orderItem = orders.get(position);

        String orderId = orderItem.getOrderId();
        Date orderDate = orderItem.getOrderDate();
        int orderStatus = orderItem.getStatus();

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(orderDate);

        String statusInString="";
        switch (orderStatus){
            case 0:
                statusInString= holder.itemView.getContext().getResources().getText(R.string.outstanding).toString();
                break;
            case 1:
                statusInString=holder.itemView.getContext().getResources().getText(R.string.collected).toString();
                break;
        }

        //The order data are put into the layout
        holder.tv_orderId.setText(orderId.toString());
        holder.tv_orderDate.setText(formattedDate);
        holder.tv_orderStatus.setText(statusInString);

        /*//Each item will have an OnClickListener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(v) {
                //Database initialization
                database = FirebaseDatabase.getInstance();
                dbReference = database.getReference().child("User").child(orderItem.getUid());

                //When an item is pressed an option menu will be showed
                showDialog(v,orderItem);
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return orders.size();
    }


    public class AdapterOrderLineViewHolder  extends RecyclerView.ViewHolder {
        //Layout items
        private TextView tv_orderId, tv_orderDate, tv_orderStatus;

        //ViewHolder constructor
        public AdapterOrderLineViewHolder(View itemView) {
            super(itemView);

            //The context is given the correct value
            context = itemView.getContext();

            //Layout items initialization
            tv_orderId = (TextView) itemView.findViewById(R.id.tv_orderId);
            tv_orderDate = (TextView) itemView.findViewById(R.id.tv_orderDate);
            tv_orderStatus = (TextView) itemView.findViewById(R.id.tv_orderStatus);
        }
    }

    //OptionDialog creation method
    private void showDialog(View view, User user) {
        //Initialization
        AlertDialog.Builder optionDialog = new AlertDialog.Builder(context);
        optionDialog.setTitle(user.getUserName());

        //Options creation
        CharSequence opciones[] = {view.getResources().getText(R.string.viewOrders),view.getResources().getText(R.string.setBalance)};
        //OnClickMethod for each option
        optionDialog.setItems(opciones, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                switch (item) {
                    case 0:
                        Intent intent = new Intent(context, ProductsActivity.class);
                        intent.putExtra("user",user);
                        context.startActivity(intent);
                        break;
                    case 1:
                        grantBalanceDialog(view, user);
                        break;
                }
            }
        });
        //Dialog creation
        AlertDialog alertDialog = optionDialog.create();
        alertDialog.show();
    }

    private void grantBalanceDialog(View view, User user) {
        //Initialization
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(user.getEmail());
        alertDialog.setMessage(view.getResources().getText(R.string.setBalance));

        //Layout
        final EditText input = new EditText(context);
        input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        alertDialog.setView(input);

        //Accept
        alertDialog.setPositiveButton(view.getResources().getText(R.string.accept),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        float balance = Float.parseFloat(input.getText().toString());
                        float oldBalance= user.getBalance();
                        user.setBalance(oldBalance+balance);
                        dbReference.setValue(user);
                    }
                });
        //Cancel
        alertDialog.setNegativeButton(view.getResources().getText(R.string.cancel),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertDialog.show();
    }
}

