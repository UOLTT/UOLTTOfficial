package com.uoltt.lukaprebilgrintal.uolttofficial;

import android.app.IntentService;
import android.content.Intent;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import android.os.Handler;
import java.util.logging.LogRecord;


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
                    new URL(String.format(Locale.UK, "http://developers.uoltt.org/api/v3/squads/%d", 1)) //TODO make dependent on squad
                            .openStream(), "UTF-8").useDelimiter("\\A").next();

            JSONObject json = new JSONObject(jsonstring);

            UserData.formationID = json.getInt("formation_id");

            JSONObject formation = json.getJSONObject("formation");

            UserData.formationName = formation.getString("name");

            UserData.formDesc = formation.getString("description");

            UserData.bounds[0] = formation.getInt("minimum_members");
            UserData.bounds[1] = formation.getInt("maximum_members");

        } catch (Exception e) {
            System.err.println(e.getMessage());
            if (e instanceof JSONException){
                UserData.jsonErr = true;
            } else if (e instanceof MalformedURLException){
                UserData.linkErr = true;
            }
        }
    }
}

