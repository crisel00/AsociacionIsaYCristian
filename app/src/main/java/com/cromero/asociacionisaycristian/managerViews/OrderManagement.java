package com.cromero.asociacionisaycristian.managerViews;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cromero.asociacionisaycristian.R;
import com.cromero.asociacionisaycristian.managerControllers.AdapterOrder;
import com.cromero.asociacionisaycristian.models.Order;
import com.cromero.asociacionisaycristian.models.OrderLine;
import com.cromero.asociacionisaycristian.models.Product;
import com.cromero.asociacionisaycristian.models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class OrderManagement extends Fragment {
    private RecyclerView recView;
    private ValueEventListener eventListener;
    //Database variables
    private FirebaseDatabase database;
    private DatabaseReference dbReference;
    //
    private ArrayList<Order> orders;
    User user;
    public OrderManagement() {

        // Required empty public constructor
    }


    public static OrderManagement newInstance() {
        OrderManagement fragment = new OrderManagement();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order_management, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //RecyclerView initialization
        recView = (RecyclerView) view.findViewById(R.id.rv_Order);

        //Assignment of the Layout to the Recycler View
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recView.setLayoutManager(layoutManager);

        orders= new ArrayList<Order>();

        //Database initialization and assignment of listener.
        dbReference = FirebaseDatabase.getInstance().getReference().child("User");
        setEventListener();
        dbReference.addValueEventListener(eventListener);
    }
    //Database listener
    public void setEventListener(){
        eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for (DataSnapshot xUser : dataSnapshot.getChildren() ){
                        user  = xUser.getValue(User.class);
                        orders.addAll(user.getOrders());
                    }
                    //Assignment of the Recycler View adapter with the product list
                    AdapterOrder adapter = new AdapterOrder(orders);
                    recView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("onDataChange", "Error!", databaseError.toException());
            }
        };
    }
}