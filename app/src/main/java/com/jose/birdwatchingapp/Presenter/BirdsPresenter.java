package com.jose.birdwatchingapp.Presenter;

import android.content.Intent;
import android.util.Log;

import com.jose.birdwatchingapp.Model.HttpReq;
import com.jose.birdwatchingapp.Utilities.HttpInterface;
import com.jose.birdwatchingapp.View.BirdsActivity;
import com.jose.birdwatchingapp.View.BirdsFragment;

/**
 * Created by jose on 12/05/18.
 */

public class BirdsPresenter {


    private String TAG = BirdsPresenter.class.getSimpleName();
    private BirdsFragment view;

    public BirdsPresenter(BirdsFragment fragView) {
        Log.d(TAG, "enlazando con el presentador");
        view = fragView;
    }


    public void getBirds(String url) {
        Log.d(TAG, "Obteniendo las aves");
        String[] urls = {"", "", ""};
        urls[0] = "get";
        urls[1] = url;

        new HttpReq(new HttpInterface() {
            @Override
            public void onSuccess(final String result) {
                view.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //view.showMessage(result);
                        view.loadBirds(result);
                    }
                });
            }

            @Override
            public void onFail(final String result) {
                view.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //view.showMessage(result);
                    }
                });
            }
        }).execute(urls);
    }

    public void menuSeasons(String id) {

        //controlamos la entrada de las opciones del menú, e iniciamos la actividad correspondiente
        String op;
        Intent intent = new Intent(view.getActivity(), BirdsActivity.class);
        switch (id) {
            case "action_inv":
                op = "inv";
                intent.putExtra("choice", op);
                view.startActivity(intent);
                view.getActivity().finish();
                return;
            case "action_pri":
                op = "pri";
                intent.putExtra("choice", op);
                view.startActivity(intent);
                view.getActivity().finish();
                return;
            case "action_ver":
                op = "ver";
                intent.putExtra("choice", op);
                view.startActivity(intent);
                view.getActivity().finish();
                return;
            case "action_oto":
                op = "oto";
                intent.putExtra("choice", op);
                view.startActivity(intent);
                view.getActivity().finish();
                return;
            default:
                return;
        }
    }

    public void menuAreas(String id) {

        //controlamos la entrada de las opciones del menú, e iniciamos la actividad correspondiente
        String op;
        Intent intent = new Intent(view.getActivity(), BirdsActivity.class);
        switch (id) {
            case "action_le":
                op = "le";
                intent.putExtra("choice", op);
                view.startActivity(intent);
                view.getActivity().finish();
                return;
            case "action_pa":
                op = "pa";
                intent.putExtra("choice", op);
                view.startActivity(intent);
                view.getActivity().finish();
                return;
            case "action_bu":
                op = "bu";
                intent.putExtra("choice", op);
                view.startActivity(intent);
                view.getActivity().finish();
                return;
            case "action_so":
                op = "so";
                intent.putExtra("choice", op);
                view.startActivity(intent);
                view.getActivity().finish();
                return;
            case "action_av":
                op = "av";
                intent.putExtra("choice", op);
                view.startActivity(intent);
                view.getActivity().finish();
                return;
            case "action_se":
                op = "se";
                intent.putExtra("choice", op);
                view.startActivity(intent);
                view.getActivity().finish();
                return;
            case "action_sa":
                op = "sa";
                intent.putExtra("choice", op);
                view.startActivity(intent);
                view.getActivity().finish();
                return;
            case "action_za":
                op = "za";
                intent.putExtra("choice", op);
                view.startActivity(intent);
                view.getActivity().finish();
                return;
            case "action_va":
                op = "va";
                intent.putExtra("choice", op);
                view.startActivity(intent);
                view.getActivity().finish();
                return;
            default:
                return;
        }
    }
}