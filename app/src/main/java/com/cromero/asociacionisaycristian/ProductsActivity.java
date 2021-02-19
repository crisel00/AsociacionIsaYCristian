package com.cromero.asociacionisaycristian;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.cromero.asociacionisaycristian.controllers.AdapterProduct;
import com.cromero.asociacionisaycristian.models.Product;

import java.util.ArrayList;

public class ProductsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        //The storeId is recovered
        Intent intent = getIntent();
        String storeId= intent.getStringExtra("storeId");
        Toast.makeText(this,storeId,Toast.LENGTH_LONG);

        //RecyclerView initialization
        RecyclerView recView = (RecyclerView) findViewById(R.id.rv_Product);

        //Assignment of the Layout to the Recycler View
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recView.setLayoutManager(layoutManager);


        //Assignment of the Recycler View adapter with the user list
        //AdapterProduct adapter = new AdapterProduct(products);
        //recView.setAdapter(adapter);
    }
}