package com.example.peter.findme;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import android.support.v4.app.*;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.*;

import java.io.*;
import java.net.*;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener
{

    private GoogleMap mMap;
    LocationManager locationManager;
    String provider;
    ServerSocket serverSocket;
    protected int getLayoutId()
    {
        return R.layout.activity_maps;
    }
    // run this thread
    public void run()
    {
        if(true)
        {
            //try
            {
                // get the local port
                System.out.println("Waiting for client on port " +
                        serverSocket.getLocalPort() + "...");
                // this code crashes the MapsActivity
                //Socket clientServer = serverSocket.accept();
                /*
                // need to connect to client
                System.out.println("Just connected to " + clientServer.getRemoteSocketAddress());
                // to read input from client
                ObjectInputStream inFromClient = new ObjectInputStream(clientServer.getInputStream());
                // to print output to client
                ObjectOutputStream outToClient = new ObjectOutputStream(clientServer.getOutputStream());
                // read the object student from client
                newLocation = (NewLocation) inFromClient.readObject();
                // to debug
                System.out.println(newLocation);
                // done debug
                // draw map now
                //map.onCreate(new Bundle());
                //map.drawMap(newLocation);
                //outToClient.writeObject(map);
                clientServer.close();
                 */
            }
           /* catch(SocketTimeoutException s)
            {
                System.out.println("Socket timed out!");
                //break;
            }*/
            /*
            catch(IOException e)
            {
                e.printStackTrace();
                //break;
            }*/
            /*
            catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            }*/
        }
    }
    // main method
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // this thread is to handle server on networking
        int port = 8000;
        try
        {
            serverSocket = new ServerSocket(port);
            // 10 seconds for waiting
            serverSocket.setSoTimeout(10000);
            this.run();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        // done thread
        setContentView(R.layout.activity_maps);
        // thread
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        if (mMap == null)
        {
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
            // to test the app
            if (mMap != null)
            {
                // if mMap is not null, take SJSU location
                LatLng sjsu = new LatLng(37.336864, -121.881195);
                mMap.addMarker(new MarkerOptions().position(sjsu).title("Peter 1: Server").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
            }
        }
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        provider = locationManager.getBestProvider(new Criteria(), false);
        System.out.println("in onCreate2");

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = locationManager.getLastKnownLocation(provider);

        if (location != null)
        {
            onLocationChanged(location);
        }
        //System.out.println("hihi");
        //System.out.println(location.toString());
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        if (mMap == null)
        {
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
            // to test the app
            if (mMap != null)
            {
                // if mMap is not null, take SJSU location
                LatLng sjsu = new LatLng(37.336864, -121.881195);
                mMap.addMarker(new MarkerOptions().position(sjsu).title("Peter 1: Server").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
            }
        }

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(provider, 400, 1, (LocationListener) this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    // this class play the server
    public void onMapReady(GoogleMap googleMap)
    {
        mMap = googleMap;
        // Add a marker in SJSU and move the camera
        LatLng sjsu = new LatLng(37.336864, -121.881195);
        mMap.addMarker(new MarkerOptions().position(sjsu).title("Peter 1: Server").snippet("Location: San Jose State University").zIndex(1.0f).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sjsu));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.336864, -121.881195), 15));
        // share location
        boolean shareLocation = getIntent().getBooleanExtra("share", false);
        if(shareLocation)
        {
            handleNetwork();
        }
    }
    public void handleNetwork()
    {
        // to test this app with SJ airport location
        Location location = new Location("SJ Airport Client");
        location.setLatitude(37.365289);
        location.setLongitude(-121.923960);
        NewLocation newLocation = new NewLocation(location);
        //onLocationChanged(location);
        drawMap(newLocation);
    }
    // draw another location on the current map
    public void drawMap(NewLocation newLocation)
    {
        LatLng newLatLng = new LatLng(newLocation.latitude, newLocation.longitude);
        //String
        mMap.addMarker(new MarkerOptions().position(newLatLng).title("Peter 2: at Mineta San José Airport").snippet("Current Speed: 65 mph").zIndex(1.0f).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(newLatLng));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(newLatLng, 12));
    }
    // this method is to change your location when you are moving
    @Override
    public void onLocationChanged(Location location)
    {
        Double lat = location.getLatitude();
        Double lng = location.getLongitude();

        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lng)).title("Your location"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 12));
    }
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }
    @Override
    public void onProviderEnabled(String provider) {

    }
    @Override
    public void onProviderDisabled(String provider) {

    }
    @Override
    public void onPause() {
        super.onPause();
        System.out.println("in onPause");
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.removeUpdates(this);
    }
}
