package com.uoltt.lukaprebilgrintal.uolttofficial;

import android.app.IntentService;
import android.content.Intent;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import android.os.Handler;
import java.util.logging.LogRecord;


/**
 * A background service for updating formation info in real time,
 * while not bogging down the UI thread.
 *
 */
public class BackgroundOps extends IntentService {

    public BackgroundOps() {
        super("BackgroundOps");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        try{

            String URL = UserData.API_ROOT + "formations/%d";
            String jsonstring = new Scanner(new URL(String.format(Locale.UK, URL, UserData.squadFormationID))
                                .openStream(), "UTF-8").useDelimiter("\\A").next();

            JSONObject json = new JSONObject(jsonstring);


            UserData.formationID = json.getInt("id");

            UserData.formationName = json.getString("name");

            UserData.formDesc = json.getString("description");

            UserData.formMinMem = json.getInt("minimum_users");


        } catch (JSONException e) {
            System.err.println("There was a json exception in the bckops");
            System.err.println(e.getMessage());
            System.err.println(e.toString());
            UserData.jsonErr = true;
        } catch (MalformedURLException e) {
            System.err.println("There was a url exception in the bckops");
            System.err.println(e.getMessage());
            System.err.println(e.toString());
            UserData.linkErr = true;
        } catch (IOException e){
            System.err.println("IOException");
            for(StackTraceElement m : e.getStackTrace()){
                System.err.println(m.toString());
            }
        }
    }
}

