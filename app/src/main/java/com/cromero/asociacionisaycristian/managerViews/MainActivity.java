package com.cromero.asociacionisaycristian.managerViews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.cromero.asociacionisaycristian.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        soyCristian();
        imprimirNombre();
    }

    public void soyCristian(){
        System.out.println("Hola, soy Cristian n-n");
    }

    public void imprimirNombre(){
        System.out.println("Isabel =D");

    }
}