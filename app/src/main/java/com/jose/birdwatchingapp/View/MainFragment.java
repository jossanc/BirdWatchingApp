package com.jose.birdwatchingapp.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jose.birdwatchingapp.Presenter.MainPresenter;
import com.jose.birdwatchingapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {
    private static final String TAG=MainFragment.class.getSimpleName();
    private Button btn_birds,btn_sightings,btn_mysightings,btn_newsighting;
    private MainPresenter mainPresenter;
    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_main, container, false);
        btn_birds= (Button) view.findViewById(R.id.btn_birds);
        btn_sightings= (Button) view.findViewById(R.id.btn_sightings);
        btn_mysightings= (Button) view.findViewById(R.id.btn_mysightings);
        btn_newsighting= (Button) view.findViewById(R.id.btn_newsightings);

        mainPresenter = new MainPresenter(this);
        setHasOptionsMenu(true);

        btn_birds.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });
        btn_sightings.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mainPresenter.menu("action_sightings");
            }
        });
        btn_mysightings.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mainPresenter.menu("action_my_sightings");
            }
        });
        btn_newsighting.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mainPresenter.menu("action_sighting");
            }
        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
    }

    // según la opción de menu elegida llamamos al presentador para que inicie una actividad u otra
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sighting:
                mainPresenter.menu("action_sighting");
                return true;
            case R.id.action_sightings:
                mainPresenter.menu("action_sightings");
                return true;
            case R.id.action_my_sightings:
                mainPresenter.menu("action_my_sightings");
                return true;
            case R.id.action_challenges:
                mainPresenter.menu("action_challenges");
                return true;
            case R.id.action_achievements:
                mainPresenter.menu("action_achievements");
                return true;
            case R.id.action_settings:
                mainPresenter.menu("action_settings");
                return true;
            case R.id.action_orderby:
                //orderBy();
                return true;
            default:
                return false;
        }
    }
}
