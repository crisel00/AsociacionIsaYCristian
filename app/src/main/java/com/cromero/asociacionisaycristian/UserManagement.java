package com.cromero.asociacionisaycristian;

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

import com.cromero.asociacionisaycristian.controllers.AdapterProduct;
import com.cromero.asociacionisaycristian.controllers.AdapterUser;
import com.cromero.asociacionisaycristian.models.Product;
import com.cromero.asociacionisaycristian.models.Store;
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

        User user=new User("a@a.a", "patata","01");
        user.setBalance(0);
        users.add(user);
        user=new User("b@b.b", "AAAA","02");
        user.setBalance(1000);
        users.add(user);
        user=new User("b@111.awds", "patata","03");
        user.setBalance((long)80.9);
        users.add(user);
        user=new User("asdsd@sd.a", "patata","04");
        user.setBalance((long)90.0129);
        users.add(user);
        user=new User("fas@fsaf.sfaf", "patata","05");
        user.setBalance((long)78.9);
        users.add(user);

        //Database initialization
        database = FirebaseDatabase.getInstance();
        dbReference = database.getReference().child("User");


        //Assignment of the Recycler View adapter with the user list
        AdapterUser adapter = new AdapterUser(users);
        recView.setAdapter(adapter);
    }

    //Database listener
    public void setEventListener(){
        eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    //The current store is extracted
                    /*selectedStore = dataSnapshot.getValue(Store.class);
                    //Products list is refilled
                    users= (ArrayList<Product>) dataSnapshot.getValue(Store.class);

                    //Assignment of the Recycler View adapter with the product list
                    AdapterProduct adapter = new AdapterProduct(products,selectedStore);
                    recView.setAdapter(adapter);*/

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("onDataChange", "Error!", databaseError.toException());
            }
        };
    }
}