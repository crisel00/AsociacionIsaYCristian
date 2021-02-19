package com.cromero.asociacionisaycristian;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.cromero.asociacionisaycristian.controllers.AdapterProduct;
import com.cromero.asociacionisaycristian.controllers.AdapterStore;
import com.cromero.asociacionisaycristian.models.IdProduct;
import com.cromero.asociacionisaycristian.models.Product;

import java.util.ArrayList;

public class ProductsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);


        //RecyclerView initialization
        RecyclerView recView = (RecyclerView) findViewById(R.id.rv_Product);

        //Assignment of the Layout to the Recycler View
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recView.setLayoutManager(layoutManager);

        ArrayList<Product>products= new ArrayList<Product>();

        IdProduct idProduct = new IdProduct("Barra de pan blanco","Panaderia 1");
        Product product= new Product(idProduct,"PAN",(float) 0.70, (float) 20);
        products.add(product);
        idProduct = new IdProduct("Barra de pan integral","Panaderia 1");
         product= new Product(idProduct,"PAN",(float) 0.80, (float) 10);
        products.add(product);
         idProduct = new IdProduct("Croasant","Panaderia 1");
         product= new Product(idProduct,"PAN",(float) 1.70, (float) 20);
        products.add(product);
         idProduct = new IdProduct("Bollo de creama","Panaderia 1");
         product= new Product(idProduct,"PAN",(float) 0.90, (float) 20);
        products.add(product);
         idProduct = new IdProduct("Napolitana","Panaderia 1");
         product= new Product(idProduct,"Jugosa napolitana rellena de chocolate negro con leche y azúcar ",(float) 0.10, (float) 20);
        products.add(product);
         idProduct = new IdProduct("Pan redondo","Panaderia 1");
         product= new Product(idProduct,"PAN",(float) 0.70, (float) 20);
        products.add(product);
         idProduct = new IdProduct("Caña de chocholate","Panaderia 1");
         product= new Product(idProduct,"Ñamñam que rica está, chocolate, caramelo, azucar, diabetes, ñamñam",(float) 0.70, (float) 20);
        products.add(product);


        //Assignment of the Recycler View adapter with the user list
        AdapterProduct adapter = new AdapterProduct(products);
        recView.setAdapter(adapter);
    }
}