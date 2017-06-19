package com.androidstudd.myapplication;

import java.util.ArrayList;
import java.util.Collection;

import android.app.Application;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.Identifier;
import org.altbeacon.beacon.MonitorNotifier;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.powersave.BackgroundPowerSaver;
import org.altbeacon.beacon.startup.BootstrapNotifier;
import org.altbeacon.beacon.startup.RegionBootstrap;

public class BeaconReferenceApplication extends Application /*implements BootstrapNotifier*/ {
	private static final String TAG = "BeaconReferenceApp";
	private RegionBootstrap regionBootstrap;
	private BackgroundPowerSaver backgroundPowerSaver;
	private boolean haveDetectedBeaconsSinceBoot = false;
	private MonitoringActivity monitoringActivity = null;
	private BeaconManager mBeaconManager;
	private Region mAllBeaconsRegion;
	private BackgroundPowerSaver mBackgroundPowerSaver;
	private RegionBootstrap mRegionBootstrap;
	private BeaconManager beaconManager;



	public void onCreate() {
		super.onCreate();

/*
		beaconManager = BeaconManager.getInstanceForApplication(getApplicationContext());
*/


		//Region region1 = new Region("myRangingUniqueId", null, null, null);

		/*ArrayList<Identifier> ids0 = new ArrayList<>(1);
		ids0.add(Identifier.parse("B9407F30-F5F8-466E-AFF9-25556B57FE6D"));
		ids0.add(Identifier.parse("55957"));
		ids0.add(Identifier.parse("34167"));

		Region region0 = new Region("region0", ids0);


		ArrayList<Identifier> ids1 = new ArrayList<>(1);
		ids1.add(Identifier.parse("B9407F30-F5F8-466E-AFF9-25556B57FE6D"));
		ids1.add(Identifier.parse("11531"));
		ids1.add(Identifier.parse("39"));

		Region region1 = new Region("region1", ids1);

		ArrayList<Region> regions = new ArrayList<>(2);
		regions.add(region0);
		regions.add(region1);


		mBeaconManager = BeaconManager.getInstanceForApplication(this);
		mBackgroundPowerSaver = new BackgroundPowerSaver(this);
		mRegionBootstrap = new RegionBootstrap(this, regions);

		mBeaconManager.getBeaconParsers().add(new BeaconParser().
				setBeaconLayout("m:2-3=beac,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25"));    //iBeacons
*/


	}

	/*@Override
	public void didEnterRegion(Region arg0) {
		// In this example, this class sends a notification to the user whenever a Beacon
		// matching a Region (defined above) are first seen.
		Log.d("bbbbb", "did enter region.");
		if (!haveDetectedBeaconsSinceBoot) {
			Log.d(TAG, "auto launching MainActivity");

			// The very first time since boot that we detect an beacon, we launch the
			// MainActivity
			Intent intent = new Intent(this, MonitoringActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			// Important:  make sure to add android:launchMode="singleInstance" in the manifest
			// to keep multiple copies of this activity from getting created if the user has
			// already manually launched the app.
			this.startActivity(intent);
			haveDetectedBeaconsSinceBoot = true;
		} else {
			if (monitoringActivity != null) {
				// If the Monitoring Activity is visible, we log info about the beacons we have
				// seen on its display
				monitoringActivity.logToDisplay("I see a beacon again" );
			} else {
				// If we have already seen beacons before, but the monitoring activity is not in
				// the foreground, we send a notification to the user on subsequent detections.
				Log.d(TAG, "Sending notification.");
				sendNotification();
			}
		}


	}

	@Override
	public void didExitRegion(Region region) {
		if (monitoringActivity != null) {
			monitoringActivity.logToDisplay("I no longer see a beacon.");
		}
	}

	@Override
	public void didDetermineStateForRegion(int state, Region region) {
		if (monitoringActivity != null) {
			monitoringActivity.logToDisplay("I have just switched from seeing/not seeing beacons: " + state);
		}
	}

	private void sendNotification() {
		NotificationCompat.Builder builder =
				(NotificationCompat.Builder) new NotificationCompat.Builder(this)
						.setContentTitle("Beacon Reference Application")
						.setContentText("An beacon is nearby.")
						.setSmallIcon(R.drawable.ic_launcher);

		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		stackBuilder.addNextIntent(new Intent(this, MonitoringActivity.class));
		PendingIntent resultPendingIntent =
				stackBuilder.getPendingIntent(
						0,
						PendingIntent.FLAG_UPDATE_CURRENT
				);
		builder.setContentIntent(resultPendingIntent);
		NotificationManager notificationManager =
				(NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.notify(1, builder.build());
	}

	public void setMonitoringActivity(MonitoringActivity activity) {
		this.monitoringActivity = activity;
	}
*/
}