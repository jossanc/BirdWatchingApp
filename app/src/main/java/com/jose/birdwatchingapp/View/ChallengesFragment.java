package com.jose.birdwatchingapp.View;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jose.birdwatchingapp.Presenter.ChallengesAdapter;
import com.jose.birdwatchingapp.Presenter.ChallengesPresenter;
import com.jose.birdwatchingapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChallengesFragment extends Fragment {
    private static final String TAG = ChallengesFragment.class.getSimpleName();
    private ChallengesPresenter presenter;
    protected RecyclerView mRecyclerView;
    protected ChallengesAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_challenges, container, false);
        rootView.setTag(TAG);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerViewChallenge);

        presenter = new ChallengesPresenter(this);
        initData();
        return rootView;
    }

    /**
     * Generates Strings for RecyclerView's adapter. This data would usually come
     * from a local content provider or remote server.
     */
    private void initData() {
        presenter.getChallenges();
    }

    public void loadChallenges(String result) {
        //llamar a async task y parsear datos
        Log.d(TAG,result);
        mAdapter = new ChallengesAdapter(result);
        // Set SightingsAdapter as the adapter for RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        Log.d(TAG,"adaptador cargado");
    }

    public void showMessage(String message) {
        Toast.makeText(this.getActivity(), message, Toast.LENGTH_LONG).show();
    }
}
