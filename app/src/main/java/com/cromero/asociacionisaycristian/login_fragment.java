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
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
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

    private static final int RC_GOOGLE_API = 1;
    //variables
    Button bt_login, bt_registrar, bt_googleSingIn;
    EditText et_correo, et_contrasena;
    FirebaseAuth mAuth;
    GoogleSignInClient client_google;

    public login_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment login_fragment.
     */
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
                if(correo.length()<5 || contrasena.length()<5){

                } else {
                    if(iniciaSesion(correo,contrasena)){
                        Toast.makeText(getContext(), R.string.sesion_ok, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getContext(),TabbedActivity.class));
                    }

                }
            }
        });

        bt_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.nav_loginToRegister);
            }
        });

        //todo Inicializar login google
        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        client_google = GoogleSignIn.getClient(getContext(), gso);

        mAuth = FirebaseAuth.getInstance();

        bt_googleSingIn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent singIntent = client_google.getSignInIntent();
                startActivityForResult(singIntent, RC_GOOGLE_API);
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
                    }
                }
        );
        return exito;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_GOOGLE_API){
            Task<GoogleSignInAccount> gTask = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = gTask.getResult(ApiException.class);
                if(mAuth.getCurrentUser() != null){();} else{
                    Toast.makeText(getContext(),"Error al iniciar sesion", Toast.LENGTH_LONG).show();
                }
            } catch (ApiException e) {
                Toast.makeText(getContext(),e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }
    }
}