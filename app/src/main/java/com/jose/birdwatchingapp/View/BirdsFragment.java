package com.jose.birdwatchingapp.View;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jose.birdwatchingapp.Presenter.API;
import com.jose.birdwatchingapp.Presenter.BirdsAdapter;
import com.jose.birdwatchingapp.Presenter.BirdsPresenter;
import com.jose.birdwatchingapp.R;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the
 * interface.
 */
public class BirdsFragment extends Fragment {

    private static final String TAG = BirdsFragment.class.getSimpleName();
    private BirdsPresenter presenter;
    private API api;
    private String choice;
    private TextView toolbar;
    private RecyclerView mRecyclerView;
    private BirdsAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_birds, container, false);
        rootView.setTag(TAG);
        api = new API();

        // BEGIN_INCLUDE(initializeRecyclerView)
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerViewBird);
        toolbar = (TextView) rootView.findViewById(R.id.toolbar_title);
        // END_INCLUDE(initializeRecyclerView)

        Bundle extras = getActivity().getIntent().getExtras();
        if (extras == null) {
            choice = null;
        } else {
            choice = extras.getString("choice");
        }
        Log.d(TAG, "choice :  " + choice);
        presenter = new BirdsPresenter(this);
        initData();
        setTitle();
        setHasOptionsMenu(true);

        return rootView;
    }

    /**
     * Generates Strings for RecyclerView's adapter. This data would come
     * from a remote server.
     */
    private void setTitle() {
        switch (choice) {
            case "commonName":
                toolbar.setText("Aves por nombre común");
                break;
            case "scientificName":
                toolbar.setText("Aves por nombre científico");
                break;
            case "le":
                toolbar.setText("Aves por provincia: León");
                break;
            case "pa":
                toolbar.setText("Aves por provincia: Palencia");
                break;
            case "bu":
                toolbar.setText("Aves por provincia: Burgos");
                break;
            case "se":
                toolbar.setText("Aves por provincia: Segovia");
                break;
            case "so":
                toolbar.setText("Aves por provincia: Soria");
                break;
            case "av":
                toolbar.setText("Aves por provincia: Ávila");
                break;
            case "sa":
                toolbar.setText("Aves por provincia: Salamanca");
                break;
            case "za":
                toolbar.setText("Aves por provincia: Zamora");
                break;
            case "va":
                toolbar.setText("Aves por provincia: Valladolid");
                break;
            case "areas":
                toolbar.setText("Aves por provincia: Valladolid");
                break;
            case "inv":
                toolbar.setText("Aves por temporada: Invierno");
                break;
            case "pri":
                toolbar.setText("Aves por temporada: Primavera");
                break;
            case "ver":
                toolbar.setText("Aves por temporada: Verano");
                break;
            case "oto":
                toolbar.setText("Aves por temporada: Otoño");
                break;
            case "seasons":
                toolbar.setText("Aves por temporada: Verano");
                break;
            default:
        }
        toolbar.setTextSize(20);
    }

    private void initData() {
        String url;
        switch (choice) {
            case "commonName":
                url = api.get_url("url_all_birds");
                break;
            case "scientificName":
                url = api.get_url("url_birds_bysn");
                break;
            case "le":
                url = api.get_url("url_birds_byarea");
                url=url+"León";
                break;
            case "pa":
                url = api.get_url("url_birds_byarea");
                url=url+"Palencia";
                break;
            case "bu":
                url = api.get_url("url_birds_byarea");
                url=url+"Burgos";
                break;
            case "se":
                url = api.get_url("url_birds_byarea");
                url=url+"Segovia";
                break;
            case "so":
                url = api.get_url("url_birds_byarea");
                url=url+"Soria";
                break;
            case "av":
                url = api.get_url("url_birds_byarea");
                url=url+"Ávila";
                break;
            case "sa":
                url = api.get_url("url_birds_byarea");
                url=url+"Salamanca";
                break;
            case "za":
                url = api.get_url("url_birds_byarea");
                url=url+"Zamora";
                break;
            case "va":
                url = api.get_url("url_birds_byarea");
                url=url+"Valladolid";
                break;
            case "areas":
                url = api.get_url("url_birds_byarea");
                url=url+"Valladolid";
                break;
            case "inv":
                url = api.get_url("url_birds_byseason");
                url=url+"Invierno";
                break;
            case "pri":
                url = api.get_url("url_birds_byseason");
                url=url+"Primavera";
                break;
            case "ver":
                url = api.get_url("url_birds_byseason");
                url=url+"Verano";
                break;
            case "oto":
                url = api.get_url("url_birds_byseason");
                url=url+"Otoño";
                break;
            case "seasons":
                url = api.get_url("url_birds_byseason");
                url=url+"Verano";
                break;
            default:
                url = "";

        }
        presenter.getBirds(url);
    }

    public void loadBirds(String result) {
        //llamra a async task y parsear datos
        mAdapter = new BirdsAdapter(result);
        // Set SightingsAdapter as the adapter for RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    }

    public void showMessage(String message) {
        Toast.makeText(this.getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        switch (choice){
            case "le":
            case "pa":
            case "bu":
            case "se":
            case "so":
            case "av":
            case "sa":
            case "za":
            case "va":
            case "areas":
                inflater.inflate(R.menu.menu_birds_areas, menu);
                return;
            case "inv":
            case "pri":
            case "ver":
            case "oto":
            case "seasons":
                inflater.inflate(R.menu.menu_birds_seasons, menu);
                return;
            default:
                return;
        }
    }

    // según la opción de menu elegida llamamos al presentador para que inicie una actividad u otra
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (choice){
            case "le":
            case "pa":
            case "bu":
            case "se":
            case "so":
            case "av":
            case "sa":
            case "za":
            case "va":
            case "areas":
                switch (item.getItemId()) {
                    case R.id.action_le:
                        presenter.menuAreas("action_le");
                        return true;
                    case R.id.action_pa:
                        presenter.menuAreas("action_pa");
                        return true;
                    case R.id.action_bu:
                        presenter.menuAreas("action_bu");
                        return true;
                    case R.id.action_so:
                        presenter.menuAreas("action_so");
                        return true;
                    case R.id.action_av:
                        presenter.menuAreas("action_av");
                        return true;
                    case R.id.action_se:
                        presenter.menuAreas("action_se");
                        return true;
                    case R.id.action_sa:
                        presenter.menuAreas("action_sa");
                        return true;
                    case R.id.action_za:
                        presenter.menuAreas("action_za");
                        return true;
                    case R.id.action_va:
                        presenter.menuAreas("action_va");
                        return true;
                    default:
                        return false;
                }
            case "inv":
            case "pri":
            case "ver":
            case "oto":
            case "seasons":
                switch (item.getItemId()) {
                case R.id.action_inv:
                    presenter.menuSeasons("action_inv");
                    return true;
                case R.id.action_pri:
                    presenter.menuSeasons("action_pri");
                    return true;
                case R.id.action_ver:
                    presenter.menuSeasons("action_ver");
                    return true;
                case R.id.action_oto:
                    presenter.menuSeasons("action_oto");
                    return true;
                default:
                    return false;
            }
            default:
                return true;
        }
    }
}
