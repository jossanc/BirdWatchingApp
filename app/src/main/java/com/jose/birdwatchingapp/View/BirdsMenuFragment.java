package com.jose.birdwatchingapp.View;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jose.birdwatchingapp.Presenter.BirdsMenuPresenter;
import com.jose.birdwatchingapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BirdsMenuFragment extends Fragment {
    private BirdsMenuPresenter presenter;
    private Button btn_cn,btn_sn,btn_areas,btn_seasons;

    public BirdsMenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_birds_menu, container, false);
        btn_cn= (Button) view.findViewById(R.id.btn_cn);
        btn_sn= (Button) view.findViewById(R.id.btn_sn);
        btn_areas= (Button) view.findViewById(R.id.btn_areas);
        btn_seasons= (Button) view.findViewById(R.id.btn_seasons);


        presenter = new BirdsMenuPresenter(this);

        btn_cn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                presenter.menu("action_commonName");

            }
        });
        btn_sn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                presenter.menu("action_scientificName");
            }
        });
        btn_areas.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                presenter.menu("action_areas");
            }
        });
        btn_seasons.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                presenter.menu("action_seasons");
            }
        });

        return view;
    }

}
