package com.jose.birdwatchappv1.Presenter;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.jose.birdwatchappv1.R;
import com.jose.birdwatchappv1.View.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragmentPresenter extends Fragment implements MainPresenterInterface {

    private MainActivity mainView;

    public MainFragmentPresenter() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_fragment_presenter, container, false);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mainView = null;
    }

    // según la opción de menu elegida se inicia una actividad u otra
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
/*        switch (item.getItemId()) {
            case R.id.action_sighting:
                startActivity(new Intent(this, SightingActivity.class));
                return true;
            case R.id.action_challenges:
                startActivity(new Intent(this, ChallengesActivity.class));
                return true;
            case R.id.itemServiceStart:
                startService(new Intent(this, RefreshService.class));
                return true;
            case R.id.itemServiceStop:
                stopService(new Intent(this, RefreshService.class));
                return true;
            case R.id.action_purge:
                int rows = getContentResolver().delete(SightingContract.CONTENT_URI,
                        null, null);
                Toast.makeText(this, rows+" filas de la base de datos borradas",
                        Toast.LENGTH_LONG).show();
                return true;
            case R.id.action_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            default:
                return false;
        }
*/ return true;
    }

}
