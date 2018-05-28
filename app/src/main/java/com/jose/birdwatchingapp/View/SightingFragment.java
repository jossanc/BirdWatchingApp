package com.jose.birdwatchingapp.View;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
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

import com.jose.birdwatchingapp.Presenter.SightingPresenter;
import com.jose.birdwatchingapp.R;

import java.util.List;

public class SightingFragment extends Fragment {
    private String TAG = SightingFragment.class.getSimpleName();
    private TextView Sdate,Sbird,Sarea;
    private Button updateButton, deleteButton;
    private Spinner spinnerBird, spinnerArea;
    private SightingPresenter presenter;

    public SightingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sighting,
                container, false);
        presenter = new SightingPresenter(this);
        updateButton = (Button) view.findViewById(R.id.btn_update);
        deleteButton = (Button) view.findViewById(R.id.btn_delete);
        spinnerBird = (Spinner) view.findViewById(R.id.spinnerBird);
        spinnerArea = (Spinner) view.findViewById(R.id.spinnerArea);
        Sbird = (TextView) view.findViewById(R.id.sightingBird);
        Sarea = (TextView) view.findViewById(R.id.areaName);
        Sdate = (TextView) view.findViewById(R.id.SightingDate);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bird = String.valueOf(spinnerBird.getSelectedItem());
                String area = String.valueOf(spinnerArea.getSelectedItem());
                if (bird.contains("Seleccione un nuevo ave") && area.contains("Seleccione un nuevo área")) {
                    showMessage("No se ha actualizado nada");
                    presenter.gobackButton();
                } else if (!area.contains("Seleccione un nuevo área") && !bird.contains("Seleccione un nuevo ave")){
                    showMessage("Se actualizará el ave y el área");
                    presenter.updateButton(bird, area,Sdate.getText().toString());
                }else if (area.contains("Seleccione un nuevo área")) {
                    presenter.updateButton(bird,Sarea.getText().toString(),Sdate.getText().toString());
                }else
                    presenter.updateButton(Sbird.getText().toString(),area,Sdate.getText().toString());
                    //cuando llega aquí solo queda esta posibilidad
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date= Sdate.getText().toString();
                presenter.deleteButton(date);
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

        String sigBird,sigArea,sigDate;
        Bundle extras = getActivity().getIntent().getExtras();
        if(extras == null) {
            sigBird=null;
            sigArea=null;
            sigDate=null;
        } else {
            sigBird=extras.getString("sightingbirdname");
            sigArea=extras.getString("sightingarea");
            sigDate=extras.getString("sightingdate");
        }
        Log.d(TAG,sigBird+sigArea+sigDate);
        presenter.initData(sigBird,sigArea,sigDate);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menusighting,menu);
    }

    // según la opción de menu elegida llamamos al presentador para que inicie una actividad u otra
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit_sighting:
                presenter.changeVisibility();
                return true;
            case R.id.action_cancel:
                String date= Sdate.getText().toString();
                presenter.deleteButton(date);
            default:
                return false;
        }
    }

    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }


    public void setUpdateButton(boolean vis){
        updateButton.setEnabled(vis);
    }
    public void setDeleteButton(boolean vis){
        deleteButton.setEnabled(vis);
    }
    public void changeVisibility(){
       // if(vis==false) {
        int visibility = spinnerArea.getVisibility();
        if (visibility==View.VISIBLE){
            spinnerArea.setVisibility(View.INVISIBLE);
            spinnerBird.setVisibility(View.INVISIBLE);
            updateButton.setVisibility(View.INVISIBLE);
            deleteButton.setVisibility(View.INVISIBLE);
        }else{
            spinnerArea.setVisibility(View.VISIBLE);
            spinnerBird.setVisibility(View.VISIBLE);
            updateButton.setVisibility(View.VISIBLE);
            deleteButton.setVisibility(View.VISIBLE);
        }
    }

    public void setTextBird(String name){
        Sbird.setText(name);
    }
    public void setTextArea(String area){
        Sarea.setText(area);
    }
    public void setTextDate(String date){
        Sdate.setText(date);
    }

    public void addItemsOnSpinnerBird(List<String> list) {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBird.setAdapter(dataAdapter);
    }

    public void addItemsOnSpinnerArea(List<String> list) {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerArea.setAdapter(dataAdapter);
    }

}