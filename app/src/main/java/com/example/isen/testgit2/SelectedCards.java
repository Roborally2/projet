package com.example.isen.testgit2;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class SelectedCards extends ActionBarActivity {

    private ListView selectedCards;
    private ListView handCards;
    private Button buttonBack;
    private Client mClient;


    private ArrayList<Integer> dataPrioriteDeck = new ArrayList<>();
    private ArrayList<Integer> dataPrioriteSelected = new ArrayList<>();
    private ArrayList<String> dataActionDeck = new ArrayList<>();
    private ArrayList<String> dataActionSelected = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_selected_cards);

        Intent intent = getIntent();

        selectedCards = (ListView) findViewById(R.id.listViewSelectedCards);
        handCards = (ListView) findViewById(R.id.listViewHandCards);

        buttonBack = (Button) findViewById(R.id.buttonBack);

        this.mClient = Client.getInstance();

        if (intent != null) {
            //this.mClient=getIntent().getParcelableExtra("client");

            dataActionDeck = intent.getStringArrayListExtra("dataActionDeck");
            dataActionSelected = intent.getStringArrayListExtra("dataActionSelected");
            dataPrioriteDeck = intent.getIntegerArrayListExtra("dataPrioriteDeck");
            dataPrioriteSelected = intent.getIntegerArrayListExtra("dataPrioriteSelected");
            if(dataActionSelected==null){
                dataActionSelected = new ArrayList<>();
            }
            if(dataPrioriteSelected==null){
                dataPrioriteSelected = new ArrayList<>();
            }
            if(dataActionDeck == null){
                dataActionDeck = new ArrayList<>();
            }
            if(dataPrioriteDeck==null){
                dataPrioriteDeck= new ArrayList<>();
            }



        }

        final StringAdapter adapter = new StringAdapter(getApplicationContext(), dataActionDeck,dataPrioriteDeck);
        final StringAdapter adapter2 = new StringAdapter(getApplicationContext(), dataActionSelected,dataPrioriteSelected);
        // On dit à la ListView de se remplir via cet adapter
        handCards.setAdapter(adapter);
        selectedCards.setAdapter(adapter2);
        /*
         * Si vos données changent, penser à utiliser
         * la fonction adapter.notifyDataSetChanged();
        * qui aura pour effet de notifier le changement de données
        * et de recharger la liste automatiquement.
        */
        adapter.notifyDataSetChanged();
        adapter2.notifyDataSetChanged();

        handCards.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (dataActionSelected!= null && dataActionSelected.size() >= 5) {

                    Toast.makeText(getApplicationContext(),"Vous avez déjà selectionné 5 cartes", Toast.LENGTH_SHORT).show();
                }
                else {
                    dataActionSelected.add(dataActionDeck.get(position));
                    dataPrioriteSelected.add(dataPrioriteDeck.get(position));

                    dataActionDeck.remove(position);
                    dataPrioriteDeck.remove(position);
                    adapter.notifyDataSetChanged();
                    adapter2.notifyDataSetChanged();

                }

            }
        });
        selectedCards.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dataActionDeck.add(dataActionSelected.get(position));
                dataPrioriteDeck.add(dataPrioriteSelected.get(position));

                dataActionSelected.remove(position);
                dataPrioriteSelected.remove(position);
                adapter.notifyDataSetChanged();
                adapter2.notifyDataSetChanged();

            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getBaseContext(), roborally.class);
                //intent.putExtra("client",mClient);
                intent.putStringArrayListExtra("dataActionDeck", dataActionDeck);
                intent.putIntegerArrayListExtra("dataPrioriteDeck",dataPrioriteDeck);
                intent.putIntegerArrayListExtra("dataPrioriteSelected",dataPrioriteSelected);
                intent.putStringArrayListExtra("dataActionSelected",dataActionSelected);
                startActivity(intent);

            }
        });

    }
    /*
    @Override
    protected void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntegerArrayList("dataPrioriteDeck", dataPrioriteDeck);
        outState.putIntegerArrayList("dataPrioriteSelected" , (ArrayList<Integer>) dataPrioriteSelected);

        outState.putStringArrayList("dataActionDeck",  dataActionDeck);

        outState.putStringArrayList("dataActionSelected" , (ArrayList<String>) dataActionSelected);

    }



    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        dataPrioriteDeck = savedInstanceState.getIntegerArrayList("dataPrioriteDeck");
        dataActionDeck = savedInstanceState.getStringArrayList("dataActionDeck");
        dataActionSelected = savedInstanceState.getStringArrayList("dataActionSelected");
        dataPrioriteSelected = savedInstanceState.getIntegerArrayList("dataPrioriteSelected");


        final StringAdapter adapter = new StringAdapter(getApplicationContext(), dataActionDeck,dataPrioriteDeck);
        final StringAdapter adapter2 = new StringAdapter(getApplicationContext(), dataActionSelected,dataPrioriteSelected);
        // On dit à la ListView de se remplir via cet adapter
        handCards.setAdapter(adapter);
        selectedCards.setAdapter(adapter2);
        /*
         * Si vos données changent, penser à utiliser
         * la fonction adapter.notifyDataSetChanged();
        * qui aura pour effet de notifier le changement de données
        * et de recharger la liste automatiquement.
        */
    /*
        adapter.notifyDataSetChanged();
        adapter2.notifyDataSetChanged();

        handCards.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (dataActionSelected.size() >= 5) {

                    Toast.makeText(getApplicationContext(),"Vous avez déjà selectionné 5 cartes", Toast.LENGTH_SHORT).show();
                }
                else {
                    dataActionSelected.add(dataActionDeck.get(position));
                    dataPrioriteSelected.add(dataPrioriteDeck.get(position));

                    dataActionDeck.remove(position);
                    dataPrioriteDeck.remove(position);
                    adapter.notifyDataSetChanged();
                    adapter2.notifyDataSetChanged();

                }

            }
        });
        selectedCards.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dataActionDeck.add(dataActionSelected.get(position));
                dataPrioriteDeck.add(dataPrioriteSelected.get(position));

                dataActionSelected.remove(position);
                dataPrioriteSelected.remove(position);
                adapter.notifyDataSetChanged();
                adapter2.notifyDataSetChanged();

            }
        });
    }
*/
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
