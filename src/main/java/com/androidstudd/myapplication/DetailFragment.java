package com.androidstudd.myapplication;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private BeaconModel mParam1;
    Random r;

    private TextView txtDistance,txtBluetoothName,txtBluetoothAddress,txtId1,txtId2,txtId3,txtPower,txtRssi;

    private LinearLayout layoutDistance;
    private InterstitialAd mInterstitialAd;


    //int[] colorArray = {#ffffff,2};

   // private OnFragmentInteractionListener mListener;

    public DetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment DetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailFragment newInstance(BeaconModel param1) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, (Serializable) param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = (BeaconModel) getArguments().getSerializable(ARG_PARAM1);
         //  Log.d("mParam1.getId1",mParam1.getId1());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getAds();
        txtDistance = (TextView) getView().findViewById(R.id.txtDistance);
        txtBluetoothName = (TextView) getView().findViewById(R.id.txtBluetoothName);
        txtId1 = (TextView) getView().findViewById(R.id.txtId1);
        txtId2 = (TextView) getView().findViewById(R.id.txtId2);
        txtId3 = (TextView) getView().findViewById(R.id.txtId3);
        txtPower = (TextView) getView().findViewById(R.id.txtPower);
        txtRssi = (TextView) getView().findViewById(R.id.txtRssi);
        txtBluetoothAddress = (TextView) getView().findViewById(R.id.txtBluetoothAddress);
        layoutDistance = (LinearLayout) getView().findViewById(R.id.layoutDistance);

        setData(mParam1);

    }

    private void setData(BeaconModel beaconModel) {

        if(txtDistance.getText() != null)
        {
            if(!txtDistance.getText().toString().trim().equals(beaconModel.getDistance()+""))
            {
                //Log.d("txtDistance.getText().toString()",txtDistance.getText().toString());
                //Log.d("beaconModel.getDistance()",beaconModel.getDistance()+"");

                int[] rainbow = getActivity().getResources().getIntArray(R.array.rainbow);
                r = new Random();
                int i1 = r.nextInt(12);

                layoutDistance.setBackgroundColor(rainbow[i1]);
            }
        }

        txtDistance.setText(beaconModel.getDistance()+"");
        txtBluetoothName.setText("Bluetooth name: "+beaconModel.getBluetoothName());
        txtId1.setText("UUID: "+beaconModel.getId1());
        txtId2.setText("Major: "+beaconModel.getId2());
        txtId3.setText("Minor: "+beaconModel.getId3());
        txtPower.setText("Tx Power: "+beaconModel.getTxPower()+"");
        txtRssi.setText("RSSI: "+beaconModel.getRssi()+"");
        txtBluetoothAddress.setText("Bluetooth address: "+beaconModel.getBluetoothAddress()+"");

    }

    public void setDataForHashMap(BeaconModel hmap) {
        setData(hmap);
    }

    void getAds()
    {
        // Prepare the Interstitial Ad
        mInterstitialAd = new InterstitialAd(getActivity());
        // Insert the Ad Unit ID
        mInterstitialAd.setAdUnitId("ca-app-pub-6208718630398549/3637572510");

        // Request for Ads
        AdRequest adRequest = new AdRequest.Builder()
                .build();

        // Load ads into Interstitial Ads
        mInterstitialAd.loadAd(adRequest);

        // Prepare an Interstitial Ad Listener
        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                // Call displayInterstitial() function
                displayInterstitial();
            }
        });
    }

    public void displayInterstitial() {
        // If Ads are loaded, show Interstitial else show nothing.
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }

}
