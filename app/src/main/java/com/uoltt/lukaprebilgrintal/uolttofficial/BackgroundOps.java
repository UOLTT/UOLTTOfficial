package com.uoltt.lukaprebilgrintal.uolttofficial;

import android.app.IntentService;
import android.content.Intent;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;
import java.util.Scanner;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class BackgroundOps extends IntentService {

    public BackgroundOps() {
        super("BackgroundOps");
    }


    @Override
    protected void onHandleIntent(Intent intent) {

        try{
            String jsonstring = new Scanner(
                    new URL(String.format(Locale.UK, "http://developers.uoltt.org/api/v3/squads/%d", 1))
                            .openStream(), "UTF-8").useDelimiter("\\A").next();

            JSONObject json = new JSONObject(jsonstring);
            int formation = json.getInt("Formation");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

}
