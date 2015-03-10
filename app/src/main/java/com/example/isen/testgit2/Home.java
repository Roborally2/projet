package com.example.isen.testgit2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Home extends ActionBarActivity {

    private Button connect;
    private EditText ipAdress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ipAdress=(EditText) findViewById(R.id.editText1);
        ipAdress.setText("192.168.1.33");
        connect=(Button)findViewById(R.id.button1);
        connect.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String ip=ipAdress.getText().toString();
                Client.SERVERIP=ip;
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                //Log.e("ServerIP", Client.SERVERIP);
                startActivity(intent);
            }
        });
    }
}
