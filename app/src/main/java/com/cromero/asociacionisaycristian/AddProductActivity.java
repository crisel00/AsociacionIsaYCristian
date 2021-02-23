package com.cromero.asociacionisaycristian;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddProductActivity extends AppCompatActivity {

    Button bt_addProduct;
    EditText et_AddProductName, et_AddProductPrice, et_AddProductDescription, et_AddProductStock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_products);

        bt_addProduct = findViewById(R.id.bt_addProduct_confirm);

        bt_addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_AddProductName.getText().length() > 2 && et_AddProductPrice.getText().length() > 0 && et_AddProductStock.getText().length() > 0){

                } else {
                    Toast.makeText(getBaseContext(),getString(R.string.empty_fields),Toast.LENGTH_SHORT);
                }
            }
        });

    }
}