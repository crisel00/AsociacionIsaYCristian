package com.cromero.asociacionisaycristian.userViews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cromero.asociacionisaycristian.R;
import com.cromero.asociacionisaycristian.managerViews.TabbedActivity;
import com.cromero.asociacionisaycristian.userControllers.User_TabbedActivity;

public class UserOrManagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_or_manager);
    }

    public void userPart(View view) {
        startActivity(new Intent(this, User_TabbedActivity.class));
    }

    public void managerPart(View view) {
        startActivity(new Intent(this, TabbedActivity.class));
    }
}