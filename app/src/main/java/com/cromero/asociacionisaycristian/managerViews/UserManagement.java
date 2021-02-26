package com.cromero.asociacionisaycristian.managerViews;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cromero.asociacionisaycristian.R;
import com.cromero.asociacionisaycristian.managerControllers.AdapterUser;
import com.cromero.asociacionisaycristian.models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class UserManagement extends Fragment {
    private ValueEventListener eventListener;
    private RecyclerView recView;
    //Database variables
    private FirebaseDatabase database;
    private DatabaseReference dbReference;
    //
    private  ArrayList<User> users;

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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //RecyclerView initialization
        recView = (RecyclerView) view.findViewById(R.id.rv_user);

        //Assignment of the Layout to the Recycler View
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recView.setLayoutManager(layoutManager);

        users = new ArrayList<User>();

        //Database initialization
        database = FirebaseDatabase.getInstance();
        dbReference = database.getReference().child("User");


        //EventListener asignation
        setEventListener();
        dbReference.addValueEventListener(eventListener);
    }

    //Database listener
    public void setEventListener(){
        eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    users.clear();
                    //The users are added to the recycler view
                    Iterable<DataSnapshot> datos = dataSnapshot.getChildren();
                    for(DataSnapshot snap: datos){
                        users.add(snap.getValue(User.class));
                    }
                    //Assignment of the Recycler View adapter with the user list
                    AdapterUser adapter = new AdapterUser(users);
                    recView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("onDataChange", "Error!", databaseError.toException());
            }
        };
    }
}