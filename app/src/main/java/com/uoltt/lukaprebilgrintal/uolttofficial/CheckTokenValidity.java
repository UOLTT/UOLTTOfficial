package com.uoltt.lukaprebilgrintal.uolttofficial;

import android.os.AsyncTask;
import org.json.JSONObject;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Luka on 14/09/2016.
 */
public class CheckTokenValidity extends AsyncTask <String, Void, Boolean> {

    protected Boolean doInBackground(String... params){
        String token = params[0];
        String userlink = "http://developers.uoltt.org/api/v3/register/" + token;
        try{

            String jsonstring = new Scanner(new URL(userlink).openStream()).useDelimiter("\\A").next();
            JSONObject json = new JSONObject(jsonstring);

            if (json.has("error")){
                UserData.tokenValidity = false;
                return false;
            } else {
                UserData.username = json.getString("username");
                if (json.get("squad") != null) {
                    JSONObject squad = json.getJSONObject("squad");
                    UserData.squadronName = squad.getString("name");
                    UserData.squadronID   = squad.getInt("id");
                } else {
                    UserData.squadronName = null;
                    UserData.squadronID = -1;
                    System.err.println("SQID is wrong, squad was null");
                }
                UserData.token = token;
                UserData.tokenValidity = true;
                return true;
            }
        } catch (Exception e){
            System.err.println(e.getMessage());
            UserData.tokenValidity = false;
            return false;
        }
    }
}
