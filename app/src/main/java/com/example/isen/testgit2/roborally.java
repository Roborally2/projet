package com.example.isen.testgit2;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class roborally extends ActionBarActivity {


    private Button powerDown;
    private TextView numberLife;
    private TextView numberDegats;
    private TextView chrono;
    private Button selectedCards;
    private Button listCards;
    private CheckBox isReady;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roborally);

        powerDown = (Button) findViewById(R.id.buttonPowerDown);
        numberLife = (TextView) findViewById((R.id.textViewNumberLife));
        numberDegats = (TextView) findViewById(R.id.textViewNumberDegats);
        chrono = (TextView) findViewById(R.id.textViewChrono);
        selectedCards = (Button) findViewById(R.id.buttonSelectedCards);
        listCards = (Button) findViewById(R.id.buttonListCards);
        isReady = (CheckBox) findViewById(R.id.checkBoxIsReady);
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://localhost/android/reception.php");
        String msg ="coucou";
        Log.i("LEZ",msg);
        if(msg.length()>0){
            try{
                List<NameValuePair> donnees = new ArrayList<NameValuePair>(1);
                donnees.add(new BasicNameValuePair("message", msg));
                post.setEntity(new UrlEncodedFormEntity(donnees));
                client.execute(post);
                Toast.makeText(this, "Ajout du nouveau joueur effectué !", Toast.LENGTH_SHORT);
            }
            catch(ClientProtocolException e){
                e.printStackTrace();
            }
            catch(IOException ev){
                ev.printStackTrace();
            }
        }
        else{
            Toast.makeText(this,"Echec !", Toast.LENGTH_SHORT);
        }


        selectedCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        listCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
