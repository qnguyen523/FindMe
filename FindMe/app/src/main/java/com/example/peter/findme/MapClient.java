package com.example.peter.findme;

/**
 * Created by Peter on 12/12/16.
 */
import android.location.Location;
import java.io.*;
import java.net.*;
public class MapClient
{
    public static void main(String[] args)
    {
        MapsActivity map = new MapsActivity();
        String serverName = "MapClient";
        int port = 8000;
        // to test this app with SJ airport location
        Location location = new Location("SJ Airport");
        location.setLatitude(37.365289);
        location.setLongitude(-121.923960);
        NewLocation newLocation = new NewLocation(location);
        try
        {
            System.out.println("Before sending: "+newLocation);
            System.out.println("Connecting to " + serverName + " on port " + port);
            Socket clientSocket = new Socket(serverName, port);
            System.out.println("Just connected to " + clientSocket.getRemoteSocketAddress());
            // Create the input & output streams to the server
            ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream inFromServer = new ObjectInputStream(clientSocket.getInputStream());
            // send newLocation to MapServer
            outToServer.writeObject(newLocation);
            // newLocation = (NewLocation)inFromServer.readObject();
            //System.out.println(newLocation);
            clientSocket.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
