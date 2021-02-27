package com.cromero.asociacionisaycristian.userViews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.cromero.asociacionisaycristian.R;
import com.cromero.asociacionisaycristian.managerControllers.AdapterStore;
import com.cromero.asociacionisaycristian.models.Store;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class User_StoreSelection extends AppCompatActivity {
    ArrayList<Store> stores;
    DatabaseReference dbRefenrece = FirebaseDatabase.getInstance().getReference();
    RecyclerView recView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {// Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()) {
            case R.id.menitem_store_selection:
                finish();
                startActivity(new Intent(this, User_StoreSelection.class));
                break;
            case R.id.menitem_view_orders:
                startActivity(new Intent(this, User_OrderManagement.class));
                break;
            case R.id.menitem_logout:
                Toast.makeText(this,"te la creiste we",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_user__store_selection);

        Toast.makeText(this,"aaaaaaaa",Toast.LENGTH_LONG).show();

        //RecyclerView initialization
        recView = (RecyclerView) findViewById(R.id.rv_userStore);

        //Assignment of the Layout to the Recycler View
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recView.setLayoutManager(layoutManager);

        stores = new ArrayList<Store>();

        listenStoreDatabase();

        //Assignment of the Recycler View adapter with the user list
        AdapterStore adapter = new AdapterStore(stores);
        recView.setAdapter(adapter);
    }

    public void listenStoreDatabase (){
        dbRefenrece.child("stores").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    stores.clear();
                    Iterable<DataSnapshot> datos = snapshot.getChildren();
                    for(DataSnapshot snap: datos){
                        stores.add(snap.getValue(Store.class));
                    }
                    AdapterStore adapter = new AdapterStore(stores);
                    recView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}