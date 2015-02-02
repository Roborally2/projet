package com.example.isen.testgit2;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;


public class roborally extends ActionBarActivity {


    private Button powerDown;
    private TextView numberLife;
    private TextView numberDegats;
    private TextView chrono;
    private Button optionCards;
    private Button listCards;
    private CheckBox isReady;


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




        if (extras == null) {
            //On récupére nos cartes via le serveur

            dataActionDeck.add("Tourner à gauche");
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
            dataPrioriteDeck.add(550);
        }
        else {
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

                Intent intent = new Intent(roborally.this,SelectedCards.class);
                intent.putStringArrayListExtra("dataActionDeck", dataActionDeck);
                intent.putIntegerArrayListExtra("dataPrioriteDeck",dataPrioriteDeck);
                intent.putIntegerArrayListExtra("dataPrioriteSelected",dataPrioriteSelected);
                intent.putStringArrayListExtra("dataActionSelected",dataActionSelected);

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


                }
                else {


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
}
