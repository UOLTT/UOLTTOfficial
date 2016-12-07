package com.uoltt.lukaprebilgrintal.uolttofficial;

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
        /*
        try{
            JSONObject returnJSON = new AsyncAPICall().execute(APIaccess.buildString(0,
                                                                                     4,
                                                                                     "programmatically",
                                                                                     "created")).get();
            System.out.println(returnJSON.toString());
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
        */
    }
}
