package com.cromero.asociacionisaycristian;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cromero.asociacionisaycristian.models.Store;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class view_addStore extends AppCompatActivity {

    Button bt_add;
    EditText et_storeId,et_storeName;

    String storeId, storeName;

    DatabaseReference dbRoot = FirebaseDatabase.getInstance().getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_add_store);

        et_storeId = findViewById(R.id.et_AddStoreId);
        et_storeName = findViewById(R.id.et_addStoreName);

        bt_add = findViewById(R.id.bt_addStore_confirm);

        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addStore();
                finish();
            }
        });
    }

    public void addStore(){
        storeId = et_storeId.getText().toString();
        storeName = et_storeName.getText().toString();

        Store store = new Store(storeId,storeName);

        dbRoot.child("stores").child(storeId).setValue(store);


    }

}