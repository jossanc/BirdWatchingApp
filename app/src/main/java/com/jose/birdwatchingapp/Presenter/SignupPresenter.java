package com.jose.birdwatchingapp.Presenter;

import android.util.Log;

import com.jose.birdwatchingapp.Model.HttpReq;
import com.jose.birdwatchingapp.Utilities.HttpInterface;
import com.jose.birdwatchingapp.View.SignupFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jose on 20/05/18.
 */

public class SignupPresenter {
    private String TAG= SignupPresenter.class.getSimpleName();
    private String TAG_AREA="areaName";
    private SignupFragment view;
    private String name;
    private String password;
    private String password2;
    private String areaName;
    private API api;

    private List<String> areaList= new ArrayList<String>();

    public SignupPresenter(SignupFragment fragment) {
        view=fragment;
        api= new API();
    }

    public void addItemsOnSpinner(){
        //get areasNames  desde el modelo..
        areaList.add("Elige un área");
        //areaList.add("Zamora");
        initializeSpinnerArea();
        view.addItemsOnSpinner(areaList);

    }
    public void initializeSpinnerArea(){
        String url=api.get_url("url_areas");
        String[] urls = {"","",""};
        urls[0]="get";
        urls[1]=url;

        new HttpReq(new HttpInterface() {
            @Override
            public void onSuccess(final String result) {
                view.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        view.showMessage(result);
                        parseAreas(result);
                    }
                });

            }

            @Override
            public void onFail(final String result) {
                view.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        view.showMessage(result);
                    }
                });
            }
        }).execute(urls);
    }

    public void signupButton(String name, String pass, String pass2,String area){
        this.name=name;
        password=pass;
        password2=pass2;
        areaName=area;
        signup();
    }
    public void loginLink(){
        view.getActivity().finish();
    }
    //pasar a asynctask, o mirarlo
    public String signup() {
        Log.d(TAG, "Signup");
        String result="Gracias por registrarte";
        if (validate()!="ok") {
            onSignupFailed(validate());
            return validate();
        }
        view.setSignupButton(false);
        Log.d(TAG,name+password+areaName);

        // TODO: Implement your own signup logic here.
        //TODO: progress dialog, algo parecido
        String url=api.get_url("url_register_user");
        String json;
        //verificar aynctask y eso, httpinterface
        json = "{\"userName\":\""+name+"\",\"password\":\""+password+"\",\"areaName\":\""+areaName+"\"}";
        Log.d(TAG,json);
        String[] urls = {"","",""};
        urls[0]="post";
        urls[1]=url;
        urls[2]=json;

        new HttpReq(new HttpInterface() {
            @Override
            public void onSuccess(final String result) {
                view.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        view.showMessage("Registrado correctamente");
                        Log.d(TAG,result);
                        //view.loadSightings(result);
                        //TODO user registrado...
                    }
                });

            }

            @Override
            public void onFail(final String result) {
                view.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        view.showMessage("No pudo registrarse en estos momentos");
                    }
                });
            }
        }).execute(urls);

        onSignupSuccess(result);
        return  result;
    }


    public void onSignupSuccess(String result) {
        view.setSignupButton(true);
        //view.showMessage(result);
        view.getActivity().finish();
    }

    public void onSignupFailed(String result) {
        view.showMessage(result);
        view.setSignupButton(true);
    }

    public String validate() {
        String valid = "ok";

        if (name.isEmpty() || name.length() < 4) {
            //_nameText.setError("at least 3 characters");
            valid = "El usuario tiene que tener entre 4 y 10 caracteres";
        } else if (password.isEmpty() || password2.isEmpty() || password.length() < 4 || password.length() > 10) {
            //_emailText.setError("enter a valid password");
            valid = "La contraseña tiene que tener entre 4 y 10 caracteres";
        } else if (!password.contentEquals(password2)){
            valid = "La contraseña no coincide";
        }

        return valid;
    }
    public void parseAreas(String result) {
        String area;
        if (result.contains("<html>")) {
            result = null;
        }
        if (result != null) {
            try {
                JSONArray data = new JSONArray(result);
                Log.d(TAG, "areas: " + result);
                // Checking for SUCCESS TAG
                JSONObject a = data.getJSONObject(0);
                String success = a.getString(TAG_AREA);
                if (success != null) {
                    // data found
                    for(int i=0;i<data.length();i++) {
                        JSONObject c = data.getJSONObject(i);
                        area = c.getString(TAG_AREA);
                        areaList.add(area);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else
            Log.d(TAG, "Resultado del get areas " + result);
    }


}
