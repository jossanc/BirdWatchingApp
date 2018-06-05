package com.jose.birdwatchingapp.View;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jose.birdwatchingapp.Presenter.AchievementsAdapter;
import com.jose.birdwatchingapp.Presenter.AchievementsPresenter;
import com.jose.birdwatchingapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AchievementsFragment extends Fragment {

    private static final String TAG = AchievementsFragment.class.getSimpleName();
    private AchievementsPresenter presenter;
    private TextView title_toolbar;
    private String title_fragment="Logros conseguidos";
    protected RecyclerView mRecyclerView;
    protected AchievementsAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize dataset, this data would usually come from a local content provider or
        // remote server.
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_achievements, container, false);
        rootView.setTag(TAG);

        // BEGIN_INCLUDE(initializeRecyclerView)
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerViewAchievement);
        // END_INCLUDE(initializeRecyclerView)
        title_toolbar = (TextView) rootView.findViewById(R.id.toolbar_title);
        title_toolbar.setText(title_fragment);

        presenter=new AchievementsPresenter(this);
        initData();
        return rootView;
    }


    /**
     * Generates Strings for RecyclerView's adapter. This data would usually come
     * from a local content provider or remote server.
     */
    private void initData() {
       presenter.getAchievements();
    }
    public void loadAchievements(String result){
        //llamra a async task y parsear datos
        mAdapter = new AchievementsAdapter(result);
        // Set SightingsAdapter as the adapter for RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    }
    public void showMessage(String message){
        Toast.makeText(this.getActivity(),message,Toast.LENGTH_LONG).show();
    }
}
