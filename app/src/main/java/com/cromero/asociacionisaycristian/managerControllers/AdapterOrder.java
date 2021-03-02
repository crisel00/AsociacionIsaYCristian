package com.cromero.asociacionisaycristian.managerControllers;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cromero.asociacionisaycristian.OrderLineActivity;
import com.cromero.asociacionisaycristian.R;
import com.cromero.asociacionisaycristian.models.Order;
import com.cromero.asociacionisaycristian.models.OrderLine;
import com.cromero.asociacionisaycristian.models.Product;
import com.cromero.asociacionisaycristian.models.Store;
import com.cromero.asociacionisaycristian.models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AdapterOrder extends RecyclerView.Adapter<AdapterOrder.AdapterOrderViewHolder> {
    private List<Order> orders;
    private Context context;
    private User user;
    //Database variables
    private FirebaseDatabase database;
    private DatabaseReference dbReference;
    private ValueEventListener eventListener;
    //Firebase variables
    private String userID;

    //AdapterOrder's constructor
    public AdapterOrder(List<Order> orders) {
        this.orders = orders;
    }

    @NonNull
    @Override
    public AdapterOrder.AdapterOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //The view is inflated
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_item, parent, false);

        //The view holder is created
        AdapterOrder.AdapterOrderViewHolder avh=new AdapterOrder.AdapterOrderViewHolder((itemView));
        return avh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterOrder.AdapterOrderViewHolder holder, int position) {
        Order orderItem = orders.get(position);

        int orderId = orderItem.getOrderId();
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
        holder.tv_orderId.setText(String.valueOf(orderId));
        holder.tv_orderDate.setText(formattedDate);
        holder.tv_orderStatus.setText(statusInString);

        //Each item will have an OnClickListener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Database initialization
                database = FirebaseDatabase.getInstance();
                dbReference = database.getReference().child("User").child(orderItem.getUserID());
                setEventListener();
                dbReference.addValueEventListener(eventListener);

                //When an item is pressed an option menu will be showed
                showDialog(v,orderItem);
            }
        });

    }

    @Override
    public int getItemCount() {
        return orders.size();
    }


    public class AdapterOrderViewHolder  extends RecyclerView.ViewHolder {
        //Layout items
        private TextView tv_orderId, tv_orderDate, tv_orderStatus;

        //ViewHolder constructor
        public AdapterOrderViewHolder(View itemView) {
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
    private void showDialog(View view,Order order) {
        //Initialization
        AlertDialog.Builder optionDialog = new AlertDialog.Builder(context);
        optionDialog.setTitle(String.valueOf(order.getOrderId()));

        CharSequence opciones[] = new CharSequence[0];

        switch (order.getStatus()){
            case 0:
                opciones = new CharSequence[]{context.getString(R.string.manage),context.getString(R.string.delete), context.getString(R.string.order_confirm)};
                break;
            case 1:
                opciones = new CharSequence[]{context.getString(R.string.manage)};
                break;
        }
        //OnClickMethod for each option
        optionDialog.setItems(opciones, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                switch (item) {
                    case 0:
                        Intent intent = new Intent(context, OrderLineActivity.class);
                        intent.putExtra("order",order);
                        context.startActivity(intent);
                        break;
                    case 1:
                        deleteConfirmation(view,order);
                        break;
                    case 2:
                        orderConfirmation(view,order);
                        break;
                }
            }
        });
        //Dialog creation
        AlertDialog alertDialog = optionDialog.create();
        alertDialog.show();
    }

    private void orderConfirmation(View view, Order order) {
        //Initialization
        AlertDialog.Builder alertDialogBu = new AlertDialog.Builder(context);
        alertDialogBu.setTitle(view.getResources().getText(R.string.order_confirm));
        alertDialogBu.setMessage(view.getResources().getText(R.string.are_you_sure) + String.valueOf(order.getOrderId()) + view.getResources().getText(R.string.cant_undo));

        //Positive option
        alertDialogBu.setPositiveButton( view.getResources().getText(R.string.accept), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                //aceptar pedido: 0.- Comprobar existencias--Hecho  1.-Cambiar estado pedido. 2.- Eliminar saldo del cliente. 3.-Eliminar stock

                dbReference = database.getReference().child("User").child(order.getUserID());

                Float totalPedido = order.getTotal();
                if(user == null){
                    setEventListener();
                    dbReference.addListenerForSingleValueEvent(eventListener);
                }

                //Comprueba saldo
                if(user.getBalance() < totalPedido){
                        Toast.makeText(context, R.string.not_enough_balance, Toast.LENGTH_SHORT).show();
                        return;
                }

                checkStockFromStore(order);


                Toast.makeText(context, "va bien", Toast.LENGTH_SHORT).show();
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


    public void checkStockFromStore(Order order){
        Boolean stock = true;
        stock = true;
        dbReference = database.getReference();
        dbReference.child("stores").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Boolean stock = true;
                Store store;
                Product prod;
                for(OrderLine line : order.getOrderLines()){
                    for(DataSnapshot snap : snapshot.getChildren()){
                        store = snap.getValue(Store.class);

                        assert store != null;
                        if(store.getIdStore().equals(line.getStore())){
                            prod = store.findProduct(line.getProduct().getIdProduct());
                            if(prod != null && prod.getStock() < line.getAmount()){
                                stock = false;
                                break;
                            }
                        }
                    }
                }

                if(stock){
                    user.setBalance(user.getBalance() - order.getTotal());



                    for(OrderLine line : order.getOrderLines()){
                        for(DataSnapshot snap : snapshot.getChildren()){
                            store = snap.getValue(Store.class);

                            assert store != null;
                            if(store.getIdStore().equals(line.getStore())){
                                prod = store.findProduct(line.getProduct().getIdProduct());
                                prod.setStock(prod.getStock() - line.getAmount());
                                store.editProducts(prod);
                                dbReference.child("stores").child(store.getIdStore()).setValue(store);
                            }
                        }
                    }

                    order.setStatus(1);
                    user.editOrder(order);
                    dbReference.child("User").child(order.getUserID()).setValue(user);

                } else{
                    Toast.makeText(context, R.string.not_enough_stock, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void deleteConfirmation(View view, Order order){
        //Initialization
        AlertDialog.Builder alertDialogBu = new AlertDialog.Builder(context);
        alertDialogBu.setTitle(view.getResources().getText(R.string.delete));
        alertDialogBu.setMessage(view.getResources().getText(R.string.are_you_sure) + String.valueOf(order.getOrderId()) + view.getResources().getText(R.string.cant_undo));

        //Positive option
        alertDialogBu.setPositiveButton( view.getResources().getText(R.string.accept), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                delete(order);
                Toast.makeText(context, String.valueOf(order.getOrderId()) + view.getResources().getText(R.string.is_deleted), Toast.LENGTH_SHORT).show();
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

    private void delete(Order order){
        Order myOrder;
        List<Order> ordersDeleted= new ArrayList<>();
        for(int i= user.getOrders().size(); i>0;  i--){
            myOrder=user.getOrders().get(i-1);
            if(myOrder.getOrderId()!=order.getOrderId()){
                ordersDeleted.add(myOrder);
            }
        }
        user.setOrders(ordersDeleted);
        dbReference.setValue(user);
    }

    //Database listener
    public void setEventListener(){
        eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    //The current user is extracted
                    user = dataSnapshot.getValue(User.class);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("onDataChange", "Error!", databaseError.toException());
            }
        };
    }


}
