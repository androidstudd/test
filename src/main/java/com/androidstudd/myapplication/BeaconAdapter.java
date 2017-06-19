package com.androidstudd.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.ads.NativeExpressAdView;
import com.google.android.gms.ads.VideoController;

import java.util.HashMap;
import java.util.List;

public class BeaconAdapter extends RecyclerView.Adapter<BeaconAdapter.MyViewHolder> {

    OnItemClickListener onItemClickListener;

    public void setOnClickListenerCustom(OnItemClickListener onItemClickListenerCustom) {
    //    Log.d("onItemClickListenerCustom","onItemClickListenerCustom called");
        onItemClickListener = onItemClickListenerCustom;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private HashMap<String, BeaconModel> moviesList;

    public void updateHmap(HashMap<String, BeaconModel> hmap) {
        this.moviesList = hmap;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView uuid, major, minor, txtDeviceName, distance, lastUpdated;

        public MyViewHolder(View view) {
            super(view);
            uuid = (TextView) view.findViewById(R.id.uuid);
            major = (TextView) view.findViewById(R.id.major);
            minor = (TextView) view.findViewById(R.id.minor);
            txtDeviceName = (TextView) view.findViewById(R.id.txtDeviceName);
            distance = (TextView) view.findViewById(R.id.distance);
            lastUpdated = (TextView) view.findViewById(R.id.lastUpdated);
        }
    }


    public BeaconAdapter()
    {

    }

    public BeaconAdapter(HashMap<String, BeaconModel> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_beacon, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        BeaconModel movie = moviesList.get(moviesList.keySet().toArray()[position]);

        holder.uuid.setText(movie.getId1());
        holder.major.setText("Major :"+movie.getId2());
        holder.minor.setText("Minor :"+movie.getId3());
        holder.txtDeviceName.setText(movie.getBluetoothName());
        holder.distance.setText("Distance :"+movie.getDistance()+"");
        holder.lastUpdated.setText("Last Updated :"+movie.getLastUpdated());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(v,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return moviesList.keySet().toArray().length;
    }
}