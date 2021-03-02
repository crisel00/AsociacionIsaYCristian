package com.cromero.asociacionisaycristian;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.cromero.asociacionisaycristian.managerControllers.AdapterOrder;
import com.cromero.asociacionisaycristian.models.Order;
import com.cromero.asociacionisaycristian.models.OrderLine;
import com.cromero.asociacionisaycristian.models.Product;
import com.cromero.asociacionisaycristian.models.Store;
import com.cromero.asociacionisaycristian.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

public class UserOrdersActivity extends AppCompatActivity {


    private RecyclerView recView;
    private String userID;
    private ValueEventListener eventListener;
    private User selectedUser;
    private ArrayList<Order> orders;
    private DatabaseReference dbReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_orders);

        recView = (RecyclerView) findViewById(R.id.rv_userOrders);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recView.setLayoutManager(layoutManager);

        userID = getIntent().getStringExtra("user");


        dbReference = FirebaseDatabase.getInstance().getReference().child("User").child(userID);
        setEventListener();
        dbReference.addValueEventListener(eventListener);
    }

    //Database listener
    public void setEventListener(){
        eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                orders.clear();
                if(dataSnapshot.exists()){
                    //The current user is extracted
                    selectedUser = dataSnapshot.getValue(User.class);
                    //Orders list is refilled
                    orders = (ArrayList<Order>) selectedUser.getOrders();


                    //Assignment of the Recycler View adapter with the product list
                    AdapterOrder adapter = new AdapterOrder(selectedUser.getOrders());
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