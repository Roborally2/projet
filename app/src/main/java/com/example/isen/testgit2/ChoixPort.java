package com.example.isen.testgit2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;


public class ChoixPort extends Activity {

    TextView textResponse;
    CheckBox pret;
    EditText editTextAddress, editTextPort, editTextUsername;
    Button buttonConnect, buttonClear;
    String rep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_port);

        editTextAddress = (EditText)findViewById(R.id.address);
        editTextPort = (EditText)findViewById(R.id.port);
        buttonConnect = (Button)findViewById(R.id.connect);
        buttonClear = (Button)findViewById(R.id.clear);
        textResponse = (TextView)findViewById(R.id.response);
        editTextUsername = (EditText)findViewById(R.id.username);
        pret = (CheckBox)findViewById(R.id.checkBoxpret);

        buttonConnect.setOnClickListener(buttonConnectOnClickListener);

        buttonClear.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {
                textResponse.setText("");
            }});
    }


    OnClickListener buttonConnectOnClickListener =
            new OnClickListener(){

                @Override
                public void onClick(View arg0) {
                    MyClientTask myClientTask = new MyClientTask(
                            editTextAddress.getText().toString(),
                            Integer.parseInt(editTextPort.getText().toString()),editTextUsername.getText().toString());
                    myClientTask.execute();
                }};

    public class MyClientTask extends AsyncTask<Void, Void, Void> {

        String dstAddress;
        int dstPort;
        String dstUsername;
        String response = "";

        MyClientTask(String addr, int port, String user){
            dstAddress = addr;
            dstPort = port;
            dstUsername = user;
        }

        @Override
        protected Void doInBackground(Void... arg0) {

            Socket socket = null;

            try {
                socket = new Socket(dstAddress, dstPort);

                ByteArrayOutputStream byteArrayOutputStream =
                        new ByteArrayOutputStream(1024);
                byte[] buffer = new byte[1024];

                int bytesRead;
                InputStream inputStream = socket.getInputStream();



    /*
     * notice:
     * inputStream.read() will block if no data return
     */
                while ((bytesRead = inputStream.read(buffer)) != -1){
                    byteArrayOutputStream.write(buffer, 0, bytesRead);
                    response += byteArrayOutputStream.toString("UTF-8");
                }

            } catch (UnknownHostException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                response = "UnknownHostException: " + e.toString();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                response = "IOException: " + e.toString();
            }finally{
                if(socket != null){
                    try {
                        socket.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            textResponse.setText(response);
            rep=response;
            super.onPostExecute(result);

            /*if(rep.equals("connect")){
                HttpClient client = new DefaultHttpClient();
                HttpPost post = new HttpPost("http://localhost/android/reception.php");
                String msg ="coucou";
                Log.i("LEZ", msg);
                if(msg.length()>0){
                    try{
                        List<NameValuePair> donnees = new ArrayList<NameValuePair>(1);
                        donnees.add(new BasicNameValuePair("message", msg));
                        post.setEntity(new UrlEncodedFormEntity(donnees));
                        client.execute(post);
                        Log.d("tag","ok");
                    }
                    catch(ClientProtocolException e){
                        e.printStackTrace();
                    }
                    catch(IOException ev){
                        ev.printStackTrace();
                    }
                }
                else{
                    Log.d("tag2","echec");
                }
                Intent choix_port = new Intent(ChoixPort.this,roborally.class);
                startActivity(choix_port);

            }*/
        }

    }

}
