package com.androidstudd.myapplication;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.NativeExpressAdView;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.VideoOptions;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class MonitoringActivity extends Activity implements BeaconConsumer ,RecycleFragment.OnFragmentInteractionListener {

	private BeaconManager beaconManager;
	private RecycleFragment recycleFragment;
	HashMap<String, BeaconModel> hmap = null;
	FragmentTransaction ft;
	FragmentManager fm;
	int position = 0;
	private AdView mAdView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_monitoring);

		beaconManager = BeaconManager.getInstanceForApplication(this);
		beaconManager.getBeaconParsers().add(new BeaconParser().
				setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24"));
		beaconManager.bind(this);
		hmap = new HashMap<>();

		recycleFragment = new RecycleFragment();
		fm = getFragmentManager();
		ft = fm.beginTransaction();
		ft.add(R.id.listcontainer, new RecycleFragment());
		ft.commit();

		mAdView = (AdView) findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder().build();
		mAdView.loadAd(adRequest);
	}

	@Override
	public void onBeaconServiceConnect() {
		beaconManager.setRangeNotifier(new RangeNotifier() {
			@Override
			public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
			//Fragment currentFragment = getFragmentManager().findFragmentById(R.id.listcontainer);
			//if (currentFragment instanceof RecycleFragment) {
			//	Log.v("", "your Fragment is Visible");

				for (Beacon beacon : beacons) {
//					Log.d("beacon.getBluetoothAddress().toString()",beacon.getBluetoothAddress().toString());
//					Log.d("beacon.getId1().toString()",beacon.getId1().toString());
//					Log.d("beacon.getId2().toString()",beacon.getId2().toString());
//					Log.d("beacon.getId3().toString()",beacon.getId3().toString());
//					Log.d("beacon.getBluetoothName().toString()",beacon.getBluetoothName().toString());
//					Log.d("beacon.getTxPower().toString()",beacon.getTxPower()+"");
//					Log.d("beacon.getRssi().toString()",beacon.getRssi()+"");
//					Log.d("beacon.getDistance().toString()",beacon.getDistance()+"");
//					Log.d("Last updated",getCurrentTimeStamp());
					logToDisplay (beacon,getCurrentTimeStamp());
				}
		//	}
			}
		});

		try {
			beaconManager.startRangingBeaconsInRegion(new Region("myRangingUniqueId", null, null, null));
		} catch (RemoteException e) {   }
	}

	private static String getCurrentTimeStamp() {
		Locale locale = new Locale("es", "ES");
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS", locale);
		Date now = new Date();
		return sdf.format(now);
	}

	@Override
	public void onPause() {
		super.onPause();
		if (beaconManager.isBound(this)) beaconManager.setBackgroundMode(true);
	}

	@Override
	public void onResume() {
		super.onResume();
		if (beaconManager.isBound(this)) beaconManager.setBackgroundMode(false);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		beaconManager.unbind(this);
	}

	private void logToDisplay(final Beacon beacon, final String lastTymUpdated) {

		runOnUiThread(new Runnable() {
			  @Override
			  public void run() {
				  BeaconModel beaconModel = new BeaconModel(beacon,lastTymUpdated);

				  hmap.put(beaconModel.getId2(), beaconModel);
				 // Log.d("hmap.size()",hmap.size()+"");

				  try {
					  RecycleFragment curFrag = (RecycleFragment)
							  getFragmentManager().findFragmentById(R.id.listcontainer);

					  if(curFrag == null) {
						  // The user hasn't viewed this tab yet
						  DetailFragment detailFrag = (DetailFragment)
								  getFragmentManager().findFragmentByTag("detailFragment");
						  if(detailFrag == null) {
							  // The user hasn't viewed this tab yet
						  } else {
							  // Here's your data is a custom function you wrote to receive data as a fragment
							  detailFrag.setDataForHashMap(hmap.get(hmap.keySet().toArray()[position]));
						  }
					  } else {
						  // Here's your data is a custom function you wrote to receive data as a fragment
						  curFrag.setDataForHashMap(hmap);
					  }

				  }
				  catch(ClassCastException e)
				  {
					  DetailFragment detailFrag = (DetailFragment)
								  getFragmentManager().findFragmentByTag("detailFragment");
					  if(detailFrag == null) {
						  // The user hasn't viewed this tab yet
					  } else {
							  // Here's your data is a custom function you wrote to receive data as a fragment
						  detailFrag.setDataForHashMap(hmap.get(hmap.keySet().toArray()[position]));
					  }
				  }
			  }
		  });
	}

	@Override
	public void onFragmentInteraction(int position) {
		this.position = position;
		FragmentTransaction ft = fm.beginTransaction();
		ft.replace(R.id.listcontainer, DetailFragment.newInstance(hmap.get(hmap.keySet().toArray()[position])),"detailFragment");
		ft.addToBackStack("detailFragment");
		ft.commit();
	}
}