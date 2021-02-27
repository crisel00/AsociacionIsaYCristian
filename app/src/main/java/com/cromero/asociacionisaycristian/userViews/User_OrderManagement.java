package com.cromero.asociacionisaycristian.userViews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.cromero.asociacionisaycristian.R;

public class User_OrderManagement extends AppCompatActivity {

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
                finish();
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
        setContentView(R.layout.activity_user__order_management);
    }
}