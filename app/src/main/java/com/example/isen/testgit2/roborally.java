package com.example.isen.testgit2;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class roborally extends ActionBarActivity {


    private Button powerDown;
    private TextView numberLife;
    private TextView numberDegats;
    private TextView chrono;
    private Button optionCards;
    private Button listCards;
    private CheckBox isReady;
    private Client mClient;


    ArrayList<Integer> dataPrioriteDeck = new ArrayList<Integer>();
    ArrayList<Integer> dataPrioriteSelected = new ArrayList<Integer>();
    ArrayList<String> dataActionDeck = new ArrayList<String>();
    ArrayList<String> dataActionSelected = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roborally);

        powerDown = (Button) findViewById(R.id.buttonPowerDown);
        numberLife = (TextView) findViewById((R.id.textViewNumberLife));
        numberDegats = (TextView) findViewById(R.id.textViewNumberDegats);
        chrono = (TextView) findViewById(R.id.textViewChrono);
        optionCards = (Button) findViewById(R.id.buttonSelectedCards);
        listCards = (Button) findViewById(R.id.buttonListCards);
        isReady = (CheckBox) findViewById(R.id.checkBoxIsReady);


        Intent intent = getIntent();
        Bundle extras = getIntent().getExtras();
        this.mClient = getIntent().getParcelableExtra("client");

        new connectTask().execute("");

        if (extras.getStringArrayList("dataActionDeck") == null && extras.getStringArrayList("dataActionSelected") == null) {
            //On récupére nos cartes via le serveur

            this.mClient = getIntent().getParcelableExtra("client");

            /*dataActionDeck.add("Tourner à gauche");
            dataActionDeck.add("Tourner à droite");
            dataActionDeck.add("Demi-tour");
            dataActionDeck.add("Tourner à gauche");
            dataActionDeck.add("Tourner à droite");
            dataActionDeck.add("Demi-tour");


            dataPrioriteDeck.add(50);
            dataPrioriteDeck.add(250);
            dataPrioriteDeck.add(550);
            dataPrioriteDeck.add(50);
            dataPrioriteDeck.add(250);
            dataPrioriteDeck.add(550);*/
        } else {
            this.mClient = getIntent().getParcelableExtra("client");

            dataActionDeck = extras.getStringArrayList("dataActionDeck");
            dataActionSelected = extras.getStringArrayList("dataActionSelected");
            dataPrioriteDeck = extras.getIntegerArrayList("dataPrioriteDeck");
            dataPrioriteSelected = extras.getIntegerArrayList("dataPrioriteSelected");

        }


        optionCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        listCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(roborally.this, SelectedCards.class);
                intent.putExtra("client", mClient);
                intent.putStringArrayListExtra("dataActionDeck", dataActionDeck);
                intent.putIntegerArrayListExtra("dataPrioriteDeck", dataPrioriteDeck);
                intent.putIntegerArrayListExtra("dataPrioriteSelected", dataPrioriteSelected);
                intent.putStringArrayListExtra("dataActionSelected", dataActionSelected);

                startActivity(intent);

            }
        });
        powerDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        isReady.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isReady.isChecked()) {
                    if (dataActionSelected.size()==5 && dataPrioriteSelected.size()==5) {
                        //on envoie
                    } else {
                        Toast.makeText(getApplicationContext(), "Veuillez sélectionner 5 cartes !", Toast.LENGTH_SHORT).show();
                    }

                } else {


                }

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_roborally, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class connectTask extends AsyncTask<String, String, Client> {

        @Override
        protected Client doInBackground(String... message) {

            //we create a Client object and
           /*Client = new Client(new Client.OnMessageReceived() {
                @Override
                //here the messageReceived method is implemented
                public void messageReceived(String message) {
                    //this method calls the onProgressUpdate
                    publishProgress(message);
                }
            });*/
            mClient.run();

            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);


        }
    }









}
