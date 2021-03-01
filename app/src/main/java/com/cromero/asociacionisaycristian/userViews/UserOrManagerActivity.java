package com.cromero.asociacionisaycristian.userViews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cromero.asociacionisaycristian.R;
import com.cromero.asociacionisaycristian.managerViews.TabbedActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.cromero.asociacionisaycristian.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserOrManagerActivity extends AppCompatActivity {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_or_manager);

        getUser();

    }


    public void managerPart(View view) {

        startActivity(new Intent(this, TabbedActivity.class));
    }


    public void getUser (){
        String h = FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseDatabase.getInstance().getReference().child("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    user = snapshot.getValue(User.class);

                    if (user == null){
                        System.out.println("nulo");
                    } else{
                        System.out.println("YES");
                        System.out.println(user.getUid());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}