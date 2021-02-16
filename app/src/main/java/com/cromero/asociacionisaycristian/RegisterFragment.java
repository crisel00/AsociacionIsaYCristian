package com.cromero.asociacionisaycristian;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.Executor;

public class RegisterFragment extends Fragment {
    private FirebaseAuth mAuth;
    private DatabaseReference dbReference;
    private FirebaseDatabase database;

    Button bt_register;
    private EditText et_username;
    private EditText et_email;
    private EditText et_password;

    public RegisterFragment() {
        // Required empty public constructor
    }

    public static RegisterFragment newInstance() {
        RegisterFragment fragment = new RegisterFragment();
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
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bt_register= getView().findViewById(R.id.bt_register);
        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewAccount();
            }
        });

    }

    //This method will be launched when the user press the Register button
    private void createNewAccount(){
        String name = et_username.getText().toString();
        String email = et_email.getText().toString();
        String password = et_password.getText().toString();

        //If all the text fields are complete the user is register
        if(!TextUtils.isEmpty(name)&& !TextUtils.isEmpty(email)&& !TextUtils.isEmpty(password)){
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener((Executor) this,
                            task -> {
                                //if the account is created correctly the user is redirected to the main activity
                                if (task.isSuccessful()) {
                                    //The user is sent a verification Email
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    verifyEmail(user);

                                    /*//The user is created and added to the database
                                    String uid = user.getUid();
                                    User userObject= new User(uid,email,name,phone,user.getProviderId());
                                    dbReference.child("User").child(uid).setValue(userObject);*/

                                    updateUI(user);
                                } else {
                                    //if it fails the user is informed
                                    updateUI(null);
                                }
                            });
        }

    }

    //Method to redirect the user to the main activity. If the register failed it won't redirect the user
    private void updateUI(FirebaseUser account){
        if(account != null){
            Toast.makeText(getContext(),getResources().getText(R.string.succes_register),Toast.LENGTH_LONG).show();
            startActivity(new Intent(getContext(),MainActivity.class));
        }else{
            Toast. makeText(getContext(),getResources().getText(R.string.error_register),Toast. LENGTH_SHORT);
        }

    }
    //Method for the verification of the user email
    private void verifyEmail(FirebaseUser user){
        user.sendEmailVerification().addOnCompleteListener((Executor) this,
                task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(getContext(), getResources().getText(R.string.succes_email), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getContext(), getResources().getText(R.string.error_email), Toast.LENGTH_LONG).show();
                    }
                });
    }

}