package com.cromero.asociacionisaycristian;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class UserManagement extends Fragment {
    public UserManagement() {
        // Required empty public constructor
    }

    public static UserManagement newInstance(String param1, String param2) {
        UserManagement fragment = new UserManagement();
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
        return inflater.inflate(R.layout.fragment_user_management, container, false);
    }
}