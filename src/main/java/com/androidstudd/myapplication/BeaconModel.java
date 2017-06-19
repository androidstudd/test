package com.androidstudd.myapplication;

import org.altbeacon.beacon.Beacon;

import java.io.Serializable;

/**
 * Created by intelligrape on 22/2/17.
 */

public class BeaconModel implements Serializable{
    private String id1, id2, id3, bluetoothAddress, bluetoothName, lastUpdated;
    private int txPower, rssi;
    private double distance;

    public BeaconModel(Beacon beacon, String lastTymUpdated){
        this.id1 = beacon.getId1().toString();
        this.id2 = beacon.getId2().toString();
        this.id3 = beacon.getId3().toString();
        this.bluetoothAddress = beacon.getBluetoothAddress();
        this.bluetoothName = beacon.getBluetoothName();
        this.txPower = beacon.getTxPower();
        this.rssi = beacon.getRssi();
        this.distance = beacon.getDistance();
        this.lastUpdated = lastTymUpdated;
    }

    public void setId1(String id1) {
        this.id1 = id1;
    }

    public String getId1() {
        return id1;
    }

    public void setId2(String id2) {
        this.id2 = id2;
    }

    public String getId2() {
        return id2;
    }

    public void setId3(String id3) {
        this.id3 = id3;
    }

    public String getId3() {
        return id3;
    }

    public void setBluetoothAddress(String bluetoothAddress) {
        this.bluetoothAddress = bluetoothAddress;
    }

    public String getBluetoothAddress() {
        return bluetoothAddress;
    }

    public void setBluetoothName(String bluetoothName) {
        this.bluetoothName = bluetoothName;
    }

    public String getBluetoothName() {
        return bluetoothName;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getDistance() {
        return distance;
    }

    public void setRssi(int rssi) {
        this.rssi = rssi;
    }

    public int getRssi() {
        return rssi;
    }

    public void setTxPower(int txPower) {
        this.txPower = txPower;
    }

    public int getTxPower() {
        return txPower;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }
}
