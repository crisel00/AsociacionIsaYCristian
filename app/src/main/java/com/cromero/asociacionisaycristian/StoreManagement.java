package com.cromero.asociacionisaycristian;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cromero.asociacionisaycristian.controllers.AdapterStore;
import com.cromero.asociacionisaycristian.models.Store;

import java.util.ArrayList;
import java.util.List;

public class StoreManagement extends Fragment {


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
        //RecyclerView initialization
        RecyclerView recView = (RecyclerView) view.findViewById(R.id.rv_Store);

        //Assignment of the Layout to the Recycler View
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recView.setLayoutManager(layoutManager);


        ArrayList<Store> stores=new ArrayList<Store>();
        Store store= new Store("1","Panadería 1");
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
        stores.add(store);
        //Assignment of the Recycler View adapter with the user list
        AdapterStore adapter = new AdapterStore(stores);
        recView.setAdapter(adapter);
    }
}