package com.example.isen.testgit2;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private ListView mList;
    private ArrayList<String> arrayList;
    private ArrayList<String> arrayList2;
    private ArrayList<Integer> arrayList3;
    private ArrayList<String> arrayListInformations;
    private MyCustomAdapter mAdapter;
    private MyCustomAdapter mAdapter2;
    private Client mClient;
    private CheckBox pret;
    private int i=1;
    private boolean ok=false;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayList = new ArrayList<>();
        arrayList2 = new ArrayList<String>();
        arrayList3 = new ArrayList<Integer>();
        arrayListInformations = new ArrayList<String>();

        final EditText editText = (EditText) findViewById(R.id.editText);
        Button send = (Button)findViewById(R.id.send_button);

        //relate the listView from java to the one created in xml
        mList = (ListView)findViewById(R.id.list);
        pret = (CheckBox)findViewById(R.id.checkBox);
        mAdapter = new MyCustomAdapter(this, arrayList);
        mList.setAdapter((android.widget.ListAdapter) mAdapter);


        // connect to the server
        new connectTask().execute("");

        pret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pret.isChecked()){
                    String mes = "pret";
                    if (mClient != null) {
                        mClient.sendMessage(mes);
                    }
                    //refresh the list
                    mAdapter.notifyDataSetChanged();
                }else{

                }
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String message = editText.getText().toString();

                //add the text in the arrayList
                //arrayList.add("c: " + message);

                //sends the message to the server
                if (mClient != null) {
                    mClient.sendMessage(message);
                }

                //refresh the list
                mAdapter.notifyDataSetChanged();

                editText.setText("");
            }
        });

    }

    public class connectTask extends AsyncTask<String,String,Client> {

        @Override
        protected Client doInBackground(String... message) {

            //we create a Client object and
            mClient = new Client(new Client.OnMessageReceived() {
                @Override
                //here the messageReceived method is implemented
                public void messageReceived(String message) {
                    //this method calls the onProgressUpdate
                    publishProgress(message);
                }
            });
            mClient.run();
            Client.setInstance(mClient);
            return null;

        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);

            //in the arrayList we add the messaged received from server
            if(!ok){
                arrayList.add(values[0]);
            }
            else if (arrayList2.size()!=9){
                arrayList2.add(values[0]);
            }
            else if (arrayList3.size()!=9){
                arrayList3.add(Integer.parseInt(values[0]));
            }
            else if(arrayListInformations.size()!=4){
                arrayListInformations.add(values[0]);
            }

            int size = arrayList.size();
            if(arrayList.get(size-1).equals("La partie va commencer...")) {
                ok = true;

            }
                if ( arrayList3.size() == 9 && arrayList2.size()==9 && arrayListInformations.size() ==4){
                    Intent intent = new Intent(getApplicationContext(), roborally.class);
                    intent.putStringArrayListExtra("dataActionDeck",arrayList2);
                    intent.putIntegerArrayListExtra("dataPrioriteDeck",arrayList3);
                    intent.putStringArrayListExtra("information", arrayListInformations);
                    //intent.putExtra("client", (Parcelable) mClient);
                    startActivity(intent);
                }




            // notify the adapter that the data set has changed. This means that new message received
            // from server was added to the list
            mAdapter.notifyDataSetChanged();

        }
    }
}
