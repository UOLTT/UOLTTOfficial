package com.uoltt.lukaprebilgrintal.uolttofficial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import org.json.JSONObject;

public class Credits extends MenuTemplate {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        //test create, edit, delete fleet
        APIaccess.createNewFleet("NAMETEST", "TEEEST"); //TODO make it not run in UI thread, its a network op
    }
}
