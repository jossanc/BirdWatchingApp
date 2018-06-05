package com.jose.birdwatchingapp.View;


import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jose.birdwatchingapp.Presenter.SettingsPresenter;
import com.jose.birdwatchingapp.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    private SettingsPresenter presenter;
    private String TAG = SettingsFragment.class.getSimpleName();
    private TextView Suser,Spassword,Sarea;
    private Button updateButton, deleteButton;
    private Spinner spinnerArea;
    private SharedPreferences prefs;
    private String userName, areaName;

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings,
                container, false);
        presenter = new SettingsPresenter(this);
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        userName=prefs.getString("username","");
        areaName=prefs.getString("areaname","");

        updateButton = (Button) view.findViewById(R.id.btn_update);
        deleteButton = (Button) view.findViewById(R.id.btn_delete);
        spinnerArea = (Spinner) view.findViewById(R.id.spinnerArea);
        Suser = (TextView) view.findViewById(R.id.settingsUser);
        Sarea = (TextView) view.findViewById(R.id.areaName);
        Spassword = (TextView) view.findViewById(R.id.settings_password);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String area = String.valueOf(spinnerArea.getSelectedItem());
                String password=Spassword.getText().toString();
                if (area.contains("Seleccione un nuevo área")) {
                    showMessage("No se ha actualizado el area");
                    //presenter.gobackButton();
                    presenter.updateButton(userName,areaName,password);
                }else
                    presenter.updateButton(userName,area,password);
                //cuando llega aquí solo queda esta posibilidad
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.deleteButton(userName);
            }
        });
        setUpdateButton(false);
        setDeleteButton(false);
        initData();
        setUpdateButton(true);
        setDeleteButton(true);

        setHasOptionsMenu(true);
        changeVisibility();
        return view;
    }

    public void initData(){
        //asignar valores del elemento seleccionado a la vista

        presenter.initData(userName,areaName);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_settings,menu);
    }

    // según la opción de menu elegida llamamos al presentador para que inicie una actividad u otra
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit_user:
                presenter.changeVisibility();
                return true;
            default:
                return false;
        }
    }

    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }


    public void setDeleteButton(boolean vis){
        deleteButton.setEnabled(vis);
    }
    public void setUpdateButton(boolean vis){
        updateButton.setEnabled(vis);
    }
    public void changeVisibility(){
        // if(vis==false) {
        int visibility = spinnerArea.getVisibility();
        if (visibility==View.VISIBLE){
            spinnerArea.setVisibility(View.INVISIBLE);
            updateButton.setVisibility(View.INVISIBLE);
            Spassword.setVisibility(View.INVISIBLE);
        }else{
            spinnerArea.setVisibility(View.VISIBLE);
            updateButton.setVisibility(View.VISIBLE);
            Spassword.setVisibility(View.VISIBLE);
        }
    }

    public void setTextUser(String name){
        Suser.setText(name);
    }
    public void setTextArea(String area){
        Sarea.setText(area);
    }
   // public void setTextPassword(String pass){
     //   Spassword.setText(pass);
    //}


    public void addItemsOnSpinnerArea(List<String> list) {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerArea.setAdapter(dataAdapter);
    }
}
