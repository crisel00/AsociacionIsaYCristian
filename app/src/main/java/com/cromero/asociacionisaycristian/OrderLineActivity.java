package com.cromero.asociacionisaycristian;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.cromero.asociacionisaycristian.managerControllers.AdapterOrder;
import com.cromero.asociacionisaycristian.managerControllers.AdapterOrderLine;
import com.cromero.asociacionisaycristian.models.Order;
import com.cromero.asociacionisaycristian.models.OrderLine;
import com.cromero.asociacionisaycristian.models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OrderLineActivity extends AppCompatActivity {

    private RecyclerView recView;
    private ValueEventListener eventListener;

    Order order;
    private DatabaseReference dbReference;
    private User selectedUser;
    private ArrayList<OrderLine> lines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_line);

        //Recycler view initialization
        recView = (RecyclerView) findViewById(R.id.rv_orderLines);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recView.setLayoutManager(layoutManager);

        //Order is recovered
        order = (Order) getIntent().getSerializableExtra("order");


        //Database initialization
       dbReference = FirebaseDatabase.getInstance().getReference().child("User").child(order.getUserID());
       setEventListener();
       dbReference.addValueEventListener(eventListener);
    }

    public void setEventListener(){
        eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    //The current user is extracted
                    selectedUser = dataSnapshot.getValue(User.class);
                    //Orders list is refilled
                    lines = order.getOrderLines();

                    //Assignment of the Recycler View adapter with the product list
                    AdapterOrderLine adapter = new AdapterOrderLine(order,lines);
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