package com.jose.birdwatchingapp.View;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.jose.birdwatchingapp.Presenter.SightingPresenter;
import com.jose.birdwatchingapp.R;

import java.util.List;


public class SightingFragment extends Fragment {

    private String TAG = SightingFragment.class.getSimpleName();
    private Button sightingButton;
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
        sightingButton = (Button) view.findViewById(R.id.btn_signup);
        spinnerBird = (Spinner) view.findViewById(R.id.spinnerBird);
        spinnerArea = (Spinner) view.findViewById(R.id.spinnerArea);
        presenter.addItemsOnSpinnerBird();
        presenter.addItemsOnSpinnerArea();
        sightingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bird = String.valueOf(spinnerBird.getSelectedItem());
                String area = String.valueOf(spinnerArea.getSelectedItem());
                presenter.sightingButton(bird, area);
            }
        });
        return view;
    }

    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    public void setSightingButton(boolean vis) {
        sightingButton.setEnabled(vis);
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