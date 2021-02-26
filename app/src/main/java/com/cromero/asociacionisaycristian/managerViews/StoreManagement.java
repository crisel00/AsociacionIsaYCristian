package com.cromero.asociacionisaycristian.managerViews;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cromero.asociacionisaycristian.R;
import com.cromero.asociacionisaycristian.managerControllers.AdapterStore;
import com.cromero.asociacionisaycristian.models.Store;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class StoreManagement extends Fragment {

    ArrayList<Store> stores;
    DatabaseReference dbRefenrece = FirebaseDatabase.getInstance().getReference();
    RecyclerView recView;
    FloatingActionButton bt_addStore;

    public StoreManagement() {
        // Required empty public constructor
    }


    public static StoreManagement newInstance(String param1, String param2) {
        StoreManagement fragment = new StoreManagement();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_store_management, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bt_addStore = getActivity().findViewById(R.id.fab_addStore);

        //RecyclerView initialization
        recView = (RecyclerView) view.findViewById(R.id.rv_Store);

        //Assignment of the Layout to the Recycler View
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recView.setLayoutManager(layoutManager);

        stores = new ArrayList<Store>();

        listenStoreDatabase();

 /**       Store store= new Store("1","Panadería 1");
        stores.add(store);
         store= new Store("2","Panadería 2");
        stores.add(store);
         store= new Store("3","Panadería 3");
        stores.add(store);
         store= new Store("4","Panadería 4");
        stores.add(store);
         store= new Store("5","Panadería 5");
        stores.add(store);
         store= new Store("6","Panadería 6");
        stores.add(store);*/

        //Assignment of the Recycler View adapter with the user list
        AdapterStore adapter = new AdapterStore(stores);
        recView.setAdapter(adapter);

        bt_addStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),view_addStore.class));
            }
        });
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