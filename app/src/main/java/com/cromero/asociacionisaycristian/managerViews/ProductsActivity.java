package com.cromero.asociacionisaycristian.managerViews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.cromero.asociacionisaycristian.R;
import com.cromero.asociacionisaycristian.managerControllers.AdapterProduct;
import com.cromero.asociacionisaycristian.models.Product;
import com.cromero.asociacionisaycristian.models.Store;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProductsActivity extends AppCompatActivity {
    private ValueEventListener eventListener;
    private RecyclerView recView;
    //Object variables
    private Store selectedStore;
    String idStore;
    private Intent intent;
    private ArrayList<Product> products;
    //Database variables
    private FirebaseDatabase database;
    private  DatabaseReference dbReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        //The storeId is recovered
        intent = getIntent();
        idStore= intent.getStringExtra("idStore");

        //FloatingActionButton Listener
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_addProduct);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getBaseContext(), AddProductActivity.class);
                intent.putExtra("store",selectedStore);
                startActivity(intent);
            }
        });


        //RecyclerView and list initialization
        recView = (RecyclerView) findViewById(R.id.rv_Product);
        products=new ArrayList<>();

        //Assignment of the Layout to the Recycler View
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recView.setLayoutManager(layoutManager);

        //Database initialization
        database = FirebaseDatabase.getInstance();
        dbReference = database.getReference().child("stores").child(idStore);

        //EventListener asignation
        setEventListener();
        dbReference.addValueEventListener(eventListener);
    }

    //Database listener
    public void setEventListener(){
         eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    //The current store is extracted
                    selectedStore = dataSnapshot.getValue(Store.class);
                    //Products list is refilled
                    products= (ArrayList<Product>) selectedStore.getProducts();

                    //Assignment of the Recycler View adapter with the product list
                    AdapterProduct adapter = new AdapterProduct(products,selectedStore);
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