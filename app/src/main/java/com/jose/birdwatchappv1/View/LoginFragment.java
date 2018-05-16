package com.jose.birdwatchappv1.View;

import android.app.Fragment;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jose.birdwatchappv1.R;

public class LoginFragment extends Fragment {

    private static final String TAG = "LoginFragment";
    private static final int REQUEST_SIGNUP = 0;
    EditText editUserName, editPassword;
    Button buttonLogin;
    TextView linkSignUp;
    SharedPreferences prefs;
    SharedPreferences.Editor edit;

    public LoginFragment() {
        // Required empty public constructor
    }

/*
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
          //  mParam1 = getArguments().getString(ARG_PARAM1);
           // mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login,
                container, false);
        // Enlazar views  
        editUserName = (EditText) view.findViewById(R.id.input_user);
        editPassword = (EditText) view.findViewById(R.id.input_password);
        buttonLogin = (Button) view.findViewById(R.id.btn_login);
        linkSignUp = (TextView) view.findViewById(R.id.link_signup);
        buttonLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String name = editUserName.getText().toString();
                String pass = editPassword.getText().toString();
                new login().execute(name,pass);
            }
        });
        linkSignUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getActivity(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });


        //cogemos el valor de las preferencias del Area y lo pasamos al layout de Sighting

        return view;
    }
    /* Función llamada al pulsar el botón
    public void onClick(View v) {

        String name = editUserName.getText().toString();
        String pass = editPassword.getText().toString();
        new PostTask().execute(name, pass);

    }*/

    private final class login extends AsyncTask<String, Void, String> {
        // Llamada al empezar
        @Override
        protected String doInBackground(String... params) {

            Log.d(TAG, "Login");

            if (validate()!="ok") {
                onLoginFailed();
                return validate();
            }


            String name = params[0];
            String password = params[1];
           // final Location localizacion=null;
            Log.d(TAG, "onClicked " + name + " " + password);
            ContentValues values = new ContentValues();
            values.clear();
            // comprobar pass y acceder a Main activity
            prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
            edit = prefs.edit();
            edit.putString("username", name);
            edit.commit();
            //String aaa= prefs.getString("username","");
            //Log.d(TAG,"prefs:"+aaa);

            //meter el usuario en preferencias, para luego cogerlo en el resto de actividades
            Log.d(TAG,"Accediendo...");
            return "Bienvenido";
        }
        // Llamada cuando la actividad en background ha terminado
        @Override
        protected void onPostExecute(String result) {
            // Acción al completar el envio del avistamiento
            super.onPostExecute(result);
            if (result != "Bienvenido"){
                Toast.makeText(LoginFragment.this.getActivity(), result, Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(LoginFragment.this.getActivity(), result, Toast.LENGTH_LONG).show();
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        }
    }


    public void onLoginFailed() {
        //Toast.makeText(getActivity(), "Fallo al acceder", Toast.LENGTH_LONG).show();
        Log.e(TAG,"fallo de credenciales");
        buttonLogin.setEnabled(true);
    }

    public String validate() {
        String valid = "ok";
        String user = editUserName.getText().toString();
        String password = editPassword.getText().toString();

        if (user.isEmpty() || user.length() < 4 || user.length() > 10) {
           // editUserName.setError("Introduce un usuario válido");
            valid = "El usuario no es correcto";
        } else if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            //editPassword.setError("between 4 and 10 alphanumeric characters");
            valid = "La contraseña no es correcta";
        } else {
            //editPassword.setError(null);
        }

        return valid;
    }

}
