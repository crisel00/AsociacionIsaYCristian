package com.cromero.asociacionisaycristian;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
}