package com.cromero.asociacionisaycristian;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class EditProductActivity extends AppCompatActivity {
    private EditText et_AddProductId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_products);

        //The id edit text is disable
        et_AddProductId= findViewById(R.id.et_AddProductId);
        et_AddProductId.setEnabled(false);
    }
}