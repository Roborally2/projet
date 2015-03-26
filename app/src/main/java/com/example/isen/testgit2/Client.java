package com.example.isen.testgit2;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by isen on 28/01/2015.
 */
public class Client {
    private static Socket firstSocket=null;
    private static Client firstInstance=null;
    private String serverMessage;
    public static  String SERVERIP ; // your computer IP
    // address
    public static final int SERVERPORT = 2222;
    private OnMessageReceived mMessageListener = null;
    private boolean mRun = false;
    PrintWriter out;
    BufferedReader in;

    /**
     * Constructor of the class. OnMessagedReceived listens for the messages
     * received from server
     */
    public Client(OnMessageReceived listener) {
        mMessageListener = listener;
        if (firstInstance==null){
            firstInstance=this;
        }
    }


    public static Client getInstance() {
        	        return firstInstance;
            }

    public static void setInstance(Client client){
        firstInstance=client;
    }

    /*public Client(Parcel in){
        String[] strData = new String[2];
        boolean[] boolData = new boolean[1];
        in.readStringArray(strData);
        this.serverMessage = strData[1];
        this.SERVERIP = strData[0];
        in.readBooleanArray(boolData);
        this.mRun = boolData[0];
    }*/







   /*public static final Parcelable.Creator<Client> CREATOR= new Parcelable.Creator<Client>() {

        @Override
        public Client createFromParcel(Parcel source) {
            return new Client(source);  //using parcelable constructor
        }

        @Override
        public Client[] newArray(int size) {
            return new Client[size];
        }
    };*/
    /**
     * Sends the message entered by client to the server
     *
     * @param message
     *            text entered by client
     */
    public void sendMessage(String message) {
        if (out != null && !out.checkError()) {
            out.println(message);
            out.flush();
        }
    }

    public void stopClient() {
        mRun = false;
    }

    public void run() {

        mRun = true;

        try {

            // here you must put your computer's IP address.
            InetAddress serverAddr = InetAddress.getByName(SERVERIP);
            Log.e("serverAddr", serverAddr.toString());
            Log.e("TCP Client", "C: Connecting...");

            // create a socket to make the connection with the server
            if(firstSocket==null){
                firstSocket = new Socket(serverAddr, SERVERPORT);
            }

            Log.e("TCP Server IP", SERVERIP);
            try {

                // send the message to the server
                out = new PrintWriter(new BufferedWriter(
                        new OutputStreamWriter(firstSocket.getOutputStream())), true);

                Log.e("TCP Client", "C: Sent.");

                Log.e("TCP Client", "C: Done.");

                // receive the message which the server sends back
                in = new BufferedReader(new InputStreamReader(
                        firstSocket.getInputStream()));

                // in this while the client listens for the messages sent by the
                // server
                while (mRun) {
                    serverMessage = in.readLine();

                    if (serverMessage != null && mMessageListener != null) {
                        // call the method messageReceived from MyActivity class
                        mMessageListener.messageReceived(serverMessage);
                    }
                    serverMessage = null;

                }

                Log.e("RESPONSE FROM SERVER", "S: Received Message: '"
                        + serverMessage + "'");

            } catch (Exception e) {

                Log.e("TCP", "S: Error", e);

            } finally {
                // the socket must be closed. It is not possible to reconnect to
                // this socket
                // after it is closed, which means a new socket instance has to
                // be created.
                //firstSocket.close();
            }

        } catch (Exception e) {

            Log.e("TCP", "C: Error", e);

        }

    }

    /*@Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{this.serverMessage, this.SERVERIP});
        dest.writeBooleanArray(new boolean[]{this.mRun});
    }*/

    // Declare the interface. The method messageReceived(String message) will
    // must be implemented in the MyActivity
    // class at on asynckTask doInBackground
    public interface OnMessageReceived {
        public void messageReceived(String message);
    }
}
