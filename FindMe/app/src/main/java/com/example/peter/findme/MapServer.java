package com.example.peter.findme;

/**
 * Created by Peter on 12/12/16.
 */
/**
 * I tried both Socket and Parceable to send geolocation to each other. Since I
 * don't have actual devices to test, it is very difficult to continue. also,
 * emulators do not support geolocation.
 */
import java.io.*;
import java.net.*;
import android.os.Bundle;
import android.location.Location;
public class MapServer extends Thread
{
    public MapsActivity map;
    public static MapsActivity staticMap;
    public NewLocation newLocation;
    private ServerSocket serverSocket;
    // constructor
    public MapServer(MapsActivity aMap, int port) throws IOException
    {
        map = aMap;
        staticMap = aMap;
        serverSocket = new ServerSocket(port);
        // 100 seconds
        serverSocket.setSoTimeout(10000);
    }
    public void run()
    {
        while(true)
        {
            try
            {
                // get the local port
                System.out.println("Waiting for client on port " +
                        serverSocket.getLocalPort() + "...");
                Socket clientServer = serverSocket.accept();
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
                map.drawMap(newLocation);
                //outToClient.writeObject(map);
                clientServer.close();
            }
            catch(SocketTimeoutException s)
            {
                System.out.println("Socket timed out!");
                break;
            }
            catch(IOException e)
            {
                e.printStackTrace();
                break;
            }
            catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            }
        }
    }
    // main method
    public static void main(String[] args)
    {
        try
        {
            Thread mapServer = new MapServer(staticMap, 8000);
            mapServer.start();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
