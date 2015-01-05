package com.example.isen.testgit2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class CreerPartie extends Activity implements Button.OnClickListener {

    private TextView titre;
    private TextView message;
    private TextView espace1;
    private TextView espace2;
    private ImageView roborally;
    private Button rejoindre;
    private Button regles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer_partie);
        titre=(TextView)findViewById(R.id.textViewTitre);
        message=(TextView)findViewById(R.id.textViewMessage);
        message.setText("Bienvenue !");
        rejoindre=(Button)findViewById(R.id.btnRejoindre);
        rejoindre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent choix_port = new Intent(CreerPartie.this,ChoixPort.class);
                startActivity(choix_port);
            }
        });
        regles=(Button)findViewById(R.id.btnRegles);
        espace1=(TextView)findViewById(R.id.space1);
        espace2=(TextView)findViewById(R.id.space2);
        roborally=(ImageView)findViewById(R.id.imageView);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_creer_partie, menu);
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

    @Override
    public void onClick(View view) {
        Intent choix_port = new Intent(CreerPartie.this,ChoixPort.class);
        startActivity(choix_port);
    }
}
