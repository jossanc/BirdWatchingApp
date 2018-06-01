package com.jose.birdwatchingapp.Presenter;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.jose.birdwatchingapp.Model.HttpReq;
import com.jose.birdwatchingapp.Utilities.HttpInterface;
import com.jose.birdwatchingapp.View.SettingsFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jose on 31/05/18.
 */

public class SettingsPresenter {
    private String TAG=SettingsPresenter.class.getSimpleName();
    private String TAG_AREA="areaName";
    private SettingsFragment view;
    private API api;
    private List<String> areaList= new ArrayList<>();
    private String password, areaName, userName;
    private SharedPreferences prefs;
    private SharedPreferences.Editor edit;

    public SettingsPresenter(SettingsFragment v){
        view= v;
        api= new API();
    }

    public void initData(String user,String area){
        addItemsOnSpinnerArea();
        userName=user;
        areaName=area;
        view.setTextUser(user);
        view.setTextArea(area);
    }

    public void addItemsOnSpinnerArea(){
        //get areasNames  desde el modelo..
        areaList.add("Seleccione un nuevo área");
        initializeSpinnerArea();
        view.addItemsOnSpinnerArea(areaList);
    }

    public void changeVisibility(){
        view.changeVisibility();
    }

    public void updateButton(String user,String area,String pass){
        this.userName=user;
        this.areaName=area;
        this.password=pass;
        view.setUpdateButton(false);
        view.setDeleteButton(false);
        updateUser();
        changePrefs();
        view.setUpdateButton(true);
        view.setDeleteButton(true);
        //gobackButton();
    }
    public void deleteButton(String user){
        userName=user;
        deleteUser();
        //gobackButton();
    }
    public void changePrefs(){
        prefs = PreferenceManager.getDefaultSharedPreferences(view.getActivity());
        edit = prefs.edit();
        edit.putString("username", userName);
        edit.putString("areaname",areaName);
    }

    public void deleteUser(){
        String url=api.get_url("url_user");
        String json;
        json = "{\"userName\":\""+userName+"\"}";
        Log.d(TAG,json);
        String[] urls = {"","",""};
        urls[0]="delete";
        urls[1]=url+userName;
        urls[2]=json;

        new HttpReq(new HttpInterface() {
            @Override
            public void onSuccess(final String result) {
                view.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        view.showMessage("Eliminado correctamente");
                        view.showMessage("Vuelva atrás para ver los cambios");
                        // onSightingSuccess();
                    }
                });

            }

            @Override
            public void onFail(final String result) {
                view.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        view.showMessage("No se pudo borrar en estos momentos");
                    }
                });
            }
        }).execute(urls);

    }

    public void updateUser() {
        String url;
        String json;
        if(password.isEmpty()){
            url=api.get_url("url_updatearea_user");
            json = "{\"userName\":\""+userName+"\",\"areaName\":\""+areaName+"\"}";
        }else if (!validate().contains("ok")) {
            onUpdateFailed(validate());
            return;
        }else {
            url=api.get_url("url_update_user");
            json = "{\"userName\":\"" + userName + "\",\"password\":\""+password+"\",\"areaName\":\"" + areaName + "\"}";
            Log.d(TAG,"json:  "+json);
        }
        //verificar aynctask y eso, httpinterface
        Log.d(TAG,json);
        Log.d(TAG,url+userName);
        String[] urls = {"","",""};
        urls[0]="put";
        urls[1]=url+userName;
        urls[2]=json;

        new HttpReq(new HttpInterface() {
            @Override
            public void onSuccess(final String result) {
                view.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        view.showMessage("Actualizado correctamente");
                        view.showMessage("Vuelva atrás para ver los cambios");
                    }
                });

            }

            @Override
            public void onFail(final String result) {
                view.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        view.showMessage("Actualizado correctamente");
                        view.showMessage("Vuelva atrás para ver los cambios");
                       // view.showMessage("No se pudo actualizar en estos momentos");
                    }
                });
            }
        }).execute(urls);

    }
    public void onUpdateFailed(String result) {
        view.showMessage(result);
        view.setUpdateButton(true);
    }

    public String validate() {
        String valid = "ok";

        if (password.length() < 4 || password.length()>15) {
            //_nameText.setError("at least 3 characters");
            valid = "La contraseña tiene que tener entre 4 y 15 caracteres";
        }

        return valid;
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

    public void parseAreas(String result) {
        String area;
        if (result.contains("<html>")) {
            result = null;
        }
        if (!result.isEmpty()) {
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
