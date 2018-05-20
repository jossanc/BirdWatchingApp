package com.jose.birdwatchingapp.Presenter;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import com.jose.birdwatchingapp.Model.API;
import com.jose.birdwatchingapp.Model.HttpReq;
import com.jose.birdwatchingapp.Model.User;
import com.jose.birdwatchingapp.Utilities.HttpInterface;
import com.jose.birdwatchingapp.View.LoginFragment;
import com.jose.birdwatchingapp.View.MainActivity;
import com.jose.birdwatchingapp.View.SignupActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jose on 20/05/18.
 */

public class LoginPresenter {
    private String TAG = LoginPresenter.class.getSimpleName();
    private LoginFragment view;
    private String userName;
    private String password;
    private SharedPreferences prefs;
    private SharedPreferences.Editor edit;
    private API api;
    private String url;
    private String TAG_USER = "userName";
    private String TAG_PASSWORD = "password";
    private String TAG_AREA = "areaName";
    private User user;
    private static final int REQUEST_SIGNUP = 0;

    public LoginPresenter(LoginFragment view) {
        this.view = view;
        api = new API();
        user = new User();
    }

    public void loginButton(String name, String pass) {
        userName = name;
        password = pass;
        new login().execute();
    }

    public void linkSignup(){

        Intent intent = new Intent(view.getActivity(), SignupActivity.class);
        view.startActivityForResult(intent, REQUEST_SIGNUP);
    }

    private final class login extends AsyncTask<String, Void, String> {
        // Llamada al empezar
        @Override
        protected String doInBackground(String... params) {

            Log.d(TAG, "Login");
            switch (validate()){
                case "ok":
                    // final Location localizacion=null;
                    Log.d(TAG, "onClicked " + userName + " " + password);
                    ContentValues values = new ContentValues();
                    values.clear();
                    // comprobar pass y acceder a Main activity
                    prefs = PreferenceManager.getDefaultSharedPreferences(view.getActivity());
                    edit = prefs.edit();
                    edit.putString("userName", userName);
                    edit.commit();
                    //String aaa= prefs.getString("username","");
                    //Log.d(TAG,"prefs:"+aaa);

                    //meter el usuario en preferencias, para luego cogerlo en el resto de actividades
                    Log.d(TAG, "Accediendo...");
                    return "Bienvenido";
                case "pass incorrecta":
                    return "Contraseña incorrecta";
                case "usuario incorrecto":
                    return "Usuario incorrecto";
                default:
                    onLoginFailed();
                    return validate();
            }

        }

        // Llamada cuando la actividad en background ha terminado
        @Override
        protected void onPostExecute(String result) {
            // Acción al completar el envio del avistamiento
            super.onPostExecute(result);
            if (result != "Bienvenido") {
                view.showMessage(result);
            } else {
                view.showMessage(result);
                view.startActivity(new Intent(view.getActivity(), MainActivity.class));
            }
        }
    }


    public void onLoginFailed() {
        //Toast.makeText(getActivity(), "Fallo al acceder", Toast.LENGTH_LONG).show();
        Log.e(TAG, "fallo de credenciales");
        view.setLoginpButton(true);
    }

    public String validate() {
        final String[] valid = {"ok"};
        String[] urls = {"", "", ""};
        url = api.get_url("url_user");
        urls[0] = "get";
        urls[1] = url;

        if (userName.isEmpty() || userName.length() < 4 || userName.length() > 10) {
            valid[0] = "usuario incorrecto";
        } else if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            valid[0] = "pass incorrecta";
        } else {
            new HttpReq(new HttpInterface() {
                @Override
                public void onSuccess(final String result) {
                    Log.d(TAG, result);
                    parseUser(result);
                    //validar credenciales
                    if (user.getUserName().contains(userName) && user.getPassword().contains(password)){
                        //es correcto
                        valid[0] ="ok";
                        Log.d(TAG,"usuario correcto");
                    }else if( user.getUserName().contains(userName)) {
                        valid[0] = "pass incorrecta";
                    }else valid[0] ="usuario incorrecto";
                }
                @Override
                public void onFail(final String result) {
                    view.getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            view.showMessage(result);
                        }
                    });
                    Log.d(TAG, result);
                }
            }).execute(urls);

        }

        return valid[0];
    }

    public void parseUser(String result) {
        if (result.contains("<html>")) {
            result = null;
            user.setUserName("jose");
            user.setPassword("jose");
            Log.d(TAG,user.getUserName()+"pass:  "+user.getPassword());
        }
        if (result != null) {
            try {
                JSONArray data = new JSONArray(result);
                Log.d(TAG, "User: " + result);
                // Checking for SUCCESS TAG
                JSONObject c = data.getJSONObject(0);
                String success = c.getString(TAG_USER);
                if (success != null) {
                    // data found
                    String name = success;
                    String pass = c.getString(TAG_PASSWORD);
                    String area = c.getString(TAG_AREA);
                    user.setUserName(name);
                    user.setPassword(pass);
                    user.setAreaName(area);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else
            Log.d(TAG, "Resultado del get User" + result);
    }

}
