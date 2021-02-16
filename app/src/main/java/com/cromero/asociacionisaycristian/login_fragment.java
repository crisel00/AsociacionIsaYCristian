package com.cromero.asociacionisaycristian;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link login_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class login_fragment extends Fragment {
    Button bt_login, bt_registrar;
    EditText et_correo, et_contrasena;


    public login_fragment() {
        // Required empty public constructor
    }
    public static login_fragment newInstance(String param1, String param2) {
        login_fragment fragment = new login_fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
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
        return inflater.inflate(R.layout.fragment_login_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bt_login = getView().findViewById(R.id.bt_login);
        bt_registrar = getView().findViewById(R.id.bt_singUp);

        et_correo = getView().findViewById(R.id.et_loginCorreo);
        et_contrasena = getView().findViewById(R.id.et_loginPassword);

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String correo = et_correo.getText().toString();
                String contrasena = et_contrasena.getText().toString();
                if(correo.length()<5 || contrasena.length()<6){

                } else {
                    iniciaSesion(correo,contrasena);
                }
            }
        });

        bt_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.nav_loginToRegister);
            }
        });
    }


    Boolean exito = false;
    public boolean iniciaSesion(String correo, String contrasena){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(correo,contrasena).addOnCompleteListener(
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        exito = task.isSuccessful();
                        startActivity(new Intent(getContext(),TabbedActivity.class));
                    }
                }
        );
        return exito;
    }


}