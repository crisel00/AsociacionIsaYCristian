package com.cromero.asociacionisaycristian;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cromero.asociacionisaycristian.models.Product;
import com.cromero.asociacionisaycristian.models.Store;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class EditProductActivity extends AppCompatActivity {
    //Layout items
    private EditText et_AddProductId,et_AddProductName,et_AddProductPrice,et_AddProductDescription,et_AddProductStock;
    private Button bt_addProduct_confirm,bt_addProduct_cancel;

    //Database
    private DatabaseReference dbReference;
    private FirebaseDatabase database;

    //Objects
    private Product productObject;
    private Store store;
    private String idProduct;
    List<Product> products;
    List<Product> editedProducts= new ArrayList<Product>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_products);

        //Intents extracted
        Intent intent= getIntent();
        productObject = (Product) intent.getSerializableExtra("product");
        store= (Store) intent.getSerializableExtra("store");
        idProduct=productObject.getIdProduct();

        //Firebase database initialization
        database= FirebaseDatabase.getInstance();
        dbReference=database.getReference().child("stores");

        //Layout item initialization
        et_AddProductId= findViewById(R.id.et_AddProductId);
        et_AddProductName= findViewById(R.id.et_AddProductName);
        et_AddProductPrice= findViewById(R.id.et_AddProductPrice);
        et_AddProductDescription= findViewById(R.id.et_AddProductDescription);
        et_AddProductStock= findViewById(R.id.et_AddProductStock);
        bt_addProduct_confirm= findViewById(R.id.bt_addProduct_confirm);
        bt_addProduct_cancel= findViewById(R.id.bt_addProduct_cancel);
        //The id edit text is disabled
        et_AddProductId.setEnabled(false);

        et_AddProductId.setText(idProduct);
        et_AddProductName.setText(productObject.getProductName());
        et_AddProductPrice.setText(productObject.getPrice().toString());
        et_AddProductDescription.setText(productObject.getDescription());
        et_AddProductStock.setText(productObject.getStock().toString());
        bt_addProduct_cancel.setText(getString(R.string.edit));


        bt_addProduct_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_AddProductName.getText().length() > 2 && et_AddProductPrice.getText().length() > 0 && et_AddProductStock.getText().length() > 0){
                    String name= String.valueOf(et_AddProductName.getText());
                    float price= Float.parseFloat(String.valueOf(et_AddProductPrice.getText()));
                    String description= String.valueOf(et_AddProductDescription.getText());
                    float stock=Float.parseFloat(String.valueOf(et_AddProductStock.getText()));

                    //A product with the new data is created and added to the store
                    Product product=new Product(idProduct,name,description,price,stock);
                    store.editProducts(product);

                    //The store is added to the database
                    String id=store.getIdStore();
                     dbReference.child(id).setValue(store);

                     Toast.makeText(getBaseContext(),getString(R.string.product_edited),Toast.LENGTH_LONG).show();
                     finish();
                } else {
                    Toast.makeText(getBaseContext(),getString(R.string.empty_fields),Toast.LENGTH_SHORT);
                }
            }
        });
    }
}