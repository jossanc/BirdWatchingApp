package com.jose.birdwatchingapp.View;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jose.birdwatchingapp.Presenter.BirdPresenter;
import com.jose.birdwatchingapp.R;

import java.util.List;


public class BirdFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TAG=BirdFragment.class.getSimpleName();
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private BirdPresenter presenter;
    private TextView birdN;
    private TextView birdSN;
    private TextView birdF;
    private TextView birdE;
    private Spinner spinnerSeason;
    private Spinner spinnerArea;


    public BirdFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BirdFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BirdFragment newInstance(String param1, String param2) {
        BirdFragment fragment = new BirdFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
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
        View view =inflater.inflate(R.layout.fragment_bird, container, false);
        birdN = (TextView ) view.findViewById(R.id.birdName);
        birdSN = (TextView ) view.findViewById(R.id.birdSN);
        birdF = (TextView ) view.findViewById(R.id.birdF);
        birdE = (TextView ) view.findViewById(R.id.birdE);
        spinnerSeason = (Spinner) view.findViewById(R.id.spinnerSeason);
        spinnerArea = (Spinner) view.findViewById(R.id.spinnerArea);

        presenter = new BirdPresenter(this);
        initData();
        return view;

    }
    public void initData(){
        //asignar valores del elemento seleccionado a la vista

        String birdname,birdscientificname,birdfamily,birdecosystem;
            Bundle extras = getActivity().getIntent().getExtras();
            if(extras == null) {
                //user= null;
                birdname=null;
                birdscientificname=null;
                birdfamily=null;
                birdecosystem=null;
            } else {
                //user= extras.getString("username");
                birdname=extras.getString("birdname");
                birdscientificname=extras.getString("birdscientificname");
                birdfamily=extras.getString("birdfamily");
                birdecosystem=extras.getString("birdecosystem");
            }

        presenter.initData(birdname,birdscientificname,birdfamily,birdecosystem);
        presenter.addItemsOnSpinnerSeason();
        presenter.addItemsOnSpinnerArea();
    }

    public void setBirdN(String bird) {
        birdN.setText(bird);
    }

    public void setBirdSN(String birdS) {
        birdSN.setText(birdS);
    }

    public void setBirdF(String bfamily) {
        birdF.setText(bfamily);
    }

    public void setBirdE(String ecoS) {
        birdE.setText(ecoS);
    }

    public void addItemsOnSpinnerSeaason(List<String> list) {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSeason.setAdapter(dataAdapter);
    }

    public void addItemsOnSpinnerArea(List<String> list) {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerArea.setAdapter(dataAdapter);
    }

    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }
}
