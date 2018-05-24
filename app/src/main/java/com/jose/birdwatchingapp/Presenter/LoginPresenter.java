package com.jose.birdwatchingapp.Presenter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.jose.birdwatchingapp.Model.HttpReq;
import com.jose.birdwatchingapp.Model.User;
import com.jose.birdwatchingapp.Utilities.HttpInterface;
import com.jose.birdwatchingapp.View.LoginFragment;
import com.jose.birdwatchingapp.View.MainActivity;
import com.jose.birdwatchingapp.View.SignupActivity;

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
    private String TAG_USER = "userName";
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
        view.setLoginButton(false);
        //login();
        onSuccessF();
        view.setLoginButton(true);
    }

    public void linkSignup(){

        Intent intent = new Intent(view.getActivity(), SignupActivity.class);
        view.startActivityForResult(intent, REQUEST_SIGNUP);
    }

    public void onSuccessF(){
        prefs = PreferenceManager.getDefaultSharedPreferences(view.getActivity());
        edit = prefs.edit();
        edit.putString("username", userName);
        edit.commit();
        Log.d(TAG,"usuario guardado en preferencias "+userName);
        view.startActivity(new Intent(view.getActivity(), MainActivity.class));
    }


    public void parseUser(String result) {
        if (result.contains("<html>")) {
            result = null;
            Log.d(TAG,"user obtenido: "+user.getUserName());
        }
        if (result != null) {
            try {
                Log.d(TAG, "User: " + result);
                // Checking for SUCCESS TAG
                JSONObject c = new JSONObject(result);
                String name = c.getString(TAG_USER);
                if (name != null) {
                    // data found
                    user.setUserName(name);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else
            Log.d(TAG, "Resultado del get User" + result);
    }
    public void login(){
        Log.d(TAG,"Certificando usuario..");

        String url=api.get_url("url_login");
        String json = "{\"userName\":\""+userName+"\",\"password\":\""+password+"\"}";
        String[] urls={"","",""};

        urls[0] = "post";
        urls[1] = url;
        urls[2] = json;
        Log.d(TAG,url+userName+password+"json    "+json);

        new HttpReq(new HttpInterface() {
            @Override
            public void onSuccess(final String result) {
                view.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //view.showMessage(result);
                        parseUser(result);
                        if(user.getUserName().contains(userName)){
                            Log.d(TAG,"usuario correcto");
                            view.showMessage("Bienvenido");
                            onSuccessF();
                        }else {
                            Log.d(TAG, "usuario incorrecto" + result + " " + userName);
                            view.showMessage("Credenciales incorrectas");
                        }
                    }
                });

            }

            @Override
            public void onFail(final String result) {
                view.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        view.showMessage("Error al verificar el usuario, por favor, intentelo de nuevo mas tarde");
                        Log.d(TAG, "error al verificar el usuario >" + result);
                    }
                });
            }
        }).execute(urls);
    }

}
