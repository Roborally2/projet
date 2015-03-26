package com.example.isen.testgit2;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class test extends ActionBarActivity {
    private TextView reception, traitement, powerdown, patientez;
    private Button Btn_Power, Btn_non;

    private Joueur robot;
    protected Client mClient;

    ArrayList<Integer> dataPrioriteDeck = new ArrayList<Integer>();
    ArrayList<Integer> dataPrioriteSelected = new ArrayList<Integer>();
    ArrayList<String> dataActionDeck = new ArrayList<String>();
    ArrayList<String> dataActionSelected = new ArrayList<String>();
    ArrayList<String> informations = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        reception = (TextView) findViewById(R.id.textView4);
        traitement = (TextView) findViewById(R.id.textView5);
        powerdown = (TextView) findViewById(R.id.textView6);
        patientez = (TextView) findViewById(R.id.textView7);
        patientez.setActivated(false);

        Intent intent = getIntent();
        Bundle extras = getIntent().getExtras();
        this.mClient = Client.getInstance();
        new connectTask().execute("");


        Btn_Power = (Button) findViewById(R.id.button);
        Btn_Power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Btn_Power.setBackgroundColor(Color.CYAN);
                mClient.sendMessage("retour_powerdowntrue");
                patientez.setActivated(true);
                mClient.sendMessage("distribuer");


            }
        });
        Btn_non = (Button) findViewById(R.id.button2);
        Btn_non.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Btn_non.setBackgroundColor(Color.CYAN);

                mClient.sendMessage("retour_powerdownfalse");
                patientez.setActivated(true);
                mClient.sendMessage("distribuer");
            }
        });

    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test, menu);
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


            mClient.run();
            return null;
        }



        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            if (dataActionDeck.size()!=9){
                dataActionDeck.add(values[0]);
            }
            else if (dataPrioriteDeck.size()!=9){
                dataPrioriteDeck.add(Integer.parseInt(values[0]));
            }
            else if(informations.size()!=4){
                informations.add(values[0]);
            }
            else if(values[0].equals("/continuer")){
                Intent intent = new Intent(test.this, roborally.class);
                intent.putStringArrayListExtra("dataActionDeck", dataActionDeck);
                intent.putIntegerArrayListExtra("dataPrioriteDeck", dataPrioriteDeck);
                intent.putIntegerArrayListExtra("dataPrioriteSelected", dataPrioriteSelected);
                intent.putStringArrayListExtra("dataActionSelected", dataActionSelected);
                intent.putStringArrayListExtra("information",informations);
                intent.putExtra("joueur",robot);
                startActivity(intent);
            }
        }
    }
}
