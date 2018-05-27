package com.jose.birdwatchingapp.View;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private TextView birdN,birdSN, birdF, birdE;
    private TextView birdPRI,birdVER,birdOTO,birdINV;
    private TextView tableLE,tablePA,tableBU, tableSO, tableSE, tableAV, tableSA, tableZA, tableVA;


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

        birdINV = (TextView ) view.findViewById(R.id.table_inv);
        birdVER = (TextView ) view.findViewById(R.id.table_ver);
        birdOTO = (TextView ) view.findViewById(R.id.table_oto);
        birdPRI = (TextView ) view.findViewById(R.id.table_pri);


        tableLE = (TextView ) view.findViewById(R.id.table_Le);
        tablePA = (TextView ) view.findViewById(R.id.table_pa);
        tableBU = (TextView ) view.findViewById(R.id.table_bu);
        tableSO = (TextView ) view.findViewById(R.id.table_so);
        tableSE = (TextView ) view.findViewById(R.id.table_se);
        tableAV = (TextView ) view.findViewById(R.id.table_av);
        tableSA = (TextView ) view.findViewById(R.id.table_sa);
        tableZA = (TextView ) view.findViewById(R.id.table_za);
        tableVA = (TextView ) view.findViewById(R.id.table_va);


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
 /*       setSTTPri(true);
        setSTTVer(true);
        setSTTOto(true);
        setSTTInv(true);

        setSTTva(true);
        setSTTza(true);
        setSTTle(true);
        setSTTpa(true);
        setSTTbu(true);
        setSTTso(true);
        setSTTse(true);
        setSTTav(true);
        setSTTsa(true);*/

        presenter.addItemsOnSeason();
        presenter.addItemsOnArea();
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

    public void setSTTPri(boolean vis){
            birdPRI.setText("PRIMAVERA");
            //birdPRI.setPaintFlags(birdPRI.getPaintFlags());
    }

    public void setSTTVer(boolean vis){

            birdVER.setText("VERANO");
    }

    public void setSTTOto(boolean vis){
        birdOTO.setText("OTOÑO");//setPaintFlags(birdOTO.getPaintFlags() | Paint.LINEAR_TEXT_FLAG);
    }

    public void setSTTInv(boolean vis){
        /*if(vis)
            //birdPRI.setPaintFlags(birdPRI.getPaintFlags() | Paint.LINEAR_TEXT_FLAG);
            birdINV.setVisibility(View.INVISIBLE);
        else {
            birdINV.setVisibility(View.VISIBLE);
        */    birdINV.setText("INVIERNO");
        //birdPRI.setPaintFlags(birdPRI.getPaintFlags());
     }

    public void setSTTle(boolean vis){
        /*if(vis == true)
           // tableLE.setPaintFlags(tableLE.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        Log.d(TAG,"");
        else*/
            tableLE.setText("LE");
            //tableLE.setPaintFlags(tableLE.getPaintFlags() | Paint.LINEAR_TEXT_FLAG);
    }

    public void setSTTpa(boolean vis){
        /*if(vis == true)
//            tablePA.setPaintFlags(tablePA.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            Log.d(TAG,"");
        else*/
            tablePA.setText("PA");
    }

    public void setSTTbu(boolean vis){
        /*if(vis == true)
            //tableBU.setPaintFlags(tableBU.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            Log.d(TAG,"");
        else*/
            tableBU.setText("BU");//setPaintFlags(tableBU.getPaintFlags() | Paint.LINEAR_TEXT_FLAG);
    }

    public void setSTTso(boolean vis){
        /*if(vis == true)
        //    tableSO.setPaintFlags(tableSO.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        Log.d(TAG,"");

        else*/
            tableSO.setText("SO");//setPaintFlags(tableSO.getPaintFlags() | Paint.LINEAR_TEXT_FLAG);
    }

    public void setSTTse(boolean vis){
            tableSE.setText("SE");
    }

    public void setSTTav(boolean vis){
            tableAV.setText("AV");}

    public void setSTTsa(boolean vis){
            tableSA.setText("SA");
    }

    public void setSTTza(boolean vis){
            tableZA.setText("ZA");}

    public void setSTTva(boolean vis){
            tableVA.setText("VA");
    }

    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }
    public void loadSeason(List<String> seasonList){
        Log.d(TAG,"count season:"+seasonList.size());
        for (int i=0;i<seasonList.size();i++){
            if(seasonList.get(i).contains("Primavera")){
                setSTTPri(false);
            }else if(seasonList.get(i).contains("Verano")){
                setSTTVer(false);
            }else if(seasonList.get(i).contains("Otoño")){
                setSTTOto(false);
            }else if(seasonList.get(i).contains("Invierno")){
                setSTTInv(false);
            }
        }
    }
    public void loadArea(List<String> areaList){

        for (int i=0;i<areaList.size();i++){
            if(areaList.get(i).contains("León")){
                setSTTle(false);
            }else if(areaList.get(i).contains("Palencia")){
                setSTTpa(false);
            }else if(areaList.get(i).contains("Burgos")){
                setSTTbu(false);
            }else if(areaList.get(i).contains("Soria")){
                setSTTso(false);
            }else if(areaList.get(i).contains("Segovia")){
                setSTTse(false);
            }else if(areaList.get(i).contains("Ávila")){
                setSTTav(false);
            }else if(areaList.get(i).contains("Salamanca")){
                setSTTsa(false);
            }else if(areaList.get(i).contains("Zamora")){
                setSTTza(false);
            }else if(areaList.get(i).contains("Valladolid")){
                setSTTva(false);
            }
        }
    }
}
