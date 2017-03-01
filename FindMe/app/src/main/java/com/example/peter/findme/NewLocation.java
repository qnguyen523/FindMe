package com.example.peter.findme;

import android.location.Location;

import java.io.Serializable;

/**
 * Created by Peter on 12/4/16.
 */
// this class is needed to send location over networking
public class NewLocation implements Serializable
{
    public String provider;
    public double latitude;
    public double longitude;
    public double altitude;
    public NewLocation(Location location)
    {
        provider=location.getProvider();
        longitude=location.getLongitude();
        altitude=location.getAltitude();
        latitude=location.getLatitude();
    }
    // for testing
    public String toString()
    {
        return "Longitude: "+longitude+"\nLatitude: "+latitude;
    }
}
