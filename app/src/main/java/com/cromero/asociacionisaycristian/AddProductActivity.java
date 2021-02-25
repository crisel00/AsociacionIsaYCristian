package com.cromero.asociacionisaycristian;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cromero.asociacionisaycristian.models.Product;
import com.cromero.asociacionisaycristian.models.Store;
import com.google.firebase.database.FirebaseDatabase;

public class AddProductActivity extends AppCompatActivity {

    Button bt_addProduct,bt_cancel;
    EditText et_AddProductName, et_AddProductPrice, et_AddProductDescription, et_AddProductStock, et_AddProductId;
    Store store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_products);

        store = (Store) getIntent().getSerializableExtra("store");

        bt_addProduct = findViewById(R.id.bt_addProduct_confirm);
        bt_cancel = findViewById(R.id.bt_addProduct_cancel);

        et_AddProductId = findViewById(R.id.et_AddProductId);
        et_AddProductName = findViewById(R.id.et_AddProductName);
        et_AddProductPrice = findViewById(R.id.et_AddProductPrice);
        et_AddProductDescription = findViewById(R.id.et_AddProductDescription);
        et_AddProductStock = findViewById(R.id. et_AddProductStock);

        bt_addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id,name,description;
                Float price, stock;

                if(et_AddProductName.getText().length() > 2 && et_AddProductPrice.getText().length() > 0 && et_AddProductStock.getText().length() > 0){
                    id = et_AddProductId.getText().toString();
                    name = et_AddProductName.getText().toString();
                    description = et_AddProductDescription.getText().toString();
                    price = Float.parseFloat(et_AddProductPrice.getText().toString());
                    stock = Float.parseFloat(et_AddProductStock.getText().toString());

                    Boolean existe = false;
                    for(Product pr : store.getProducts()){
                        if(pr.getIdProduct().equals(id)){
                            existe = true;
                        }
                    }

                    if(!existe){
                        Product pr = new Product(id,name,description,price,stock);

                        store.addProduct(pr);

                        FirebaseDatabase.getInstance().getReference().child("stores").child(store.getIdStore()).setValue(store);

                        Toast.makeText(getBaseContext(),getString(R.string.product_added),Toast.LENGTH_LONG).show();

                        finish();
                    } else {
                        Toast.makeText(getBaseContext(),"La id ya existe, introduzca otra id", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(getBaseContext(),getString(R.string.empty_fields),Toast.LENGTH_SHORT);
                }
            }
        });

        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}