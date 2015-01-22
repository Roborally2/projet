package com.example.isen.testgit2;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class SelectedCards extends ActionBarActivity {

    private ListView selectedCards;
    private ListView handCards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_cards);

        selectedCards = (ListView) findViewById(R.id.listViewSelectedCards);
        handCards = (ListView) findViewById(R.id.listViewHandCards);

        List<String> dataAction = new ArrayList<String>();
        dataAction.add("Tourner à gauche");
        dataAction.add("Tourner à droite");
        dataAction.add("Demi-tour");

        List<Integer> dataPriorite = new ArrayList<Integer>();
        dataPriorite.add(50);
        dataPriorite.add(250);
        dataPriorite.add(550);


        StringAdapter adapter = new StringAdapter(getApplicationContext(), dataAction,dataPriorite);
        // On dit à la ListView de se remplir via cet adapter
        handCards.setAdapter(adapter);
        /*
         * Si vos données changent, penser à utiliser
         * la fonction adapter.notifyDataSetChanged();
        * qui aura pour effet de notifier le changement de données
        * et de recharger la liste automatiquement.
        */
        adapter.notifyDataSetChanged();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_selected_cards, menu);
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
