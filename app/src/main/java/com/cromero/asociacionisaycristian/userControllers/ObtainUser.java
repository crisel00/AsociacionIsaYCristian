package com.cromero.asociacionisaycristian.userControllers;

import android.util.Log;

import com.cromero.asociacionisaycristian.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ObtainUser {
    private static ValueEventListener userEventListener;
    private static User user;
    private static FirebaseAuth mAuth;
    private static String uid;
    private static FirebaseDatabase database;
    private static DatabaseReference dbUser;
    private static boolean ready=false;

    public static User getUser() {
        //Initialization of Firebase Authentication
        mAuth = FirebaseAuth.getInstance();
        uid= mAuth.getCurrentUser().getUid();
        //Database initialization
        database = FirebaseDatabase.getInstance();
        dbUser= database.getReference().child("User").child("bDeJAeYaFhbp5lTNEDTR4It1SVE3");

        setUserEventListener();
        dbUser.addValueEventListener(userEventListener);

        try {
            Thread.sleep(1000);
            ready=true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return user;
    }

    //Database listener
    public static void setUserEventListener(){
        userEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(ready){
                if(dataSnapshot.exists()){
                    //The current user is extracted
                    user= dataSnapshot.getValue(User.class);
                }}
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("onDataChange", "Error!", databaseError.toException());
            }
        };
    }
}
