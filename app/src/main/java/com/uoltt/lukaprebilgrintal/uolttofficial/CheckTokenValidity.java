package com.uoltt.lukaprebilgrintal.uolttofficial;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Luka on 14/09/2016.
 */
public class CheckTokenValidity extends AsyncTask <String, Void, Boolean> {

    protected Boolean doInBackground(String... params){
        String token = params[0];
        String userlink = "http://api.uoltt.org/api/v4/register/" + token;
        try{

            String jsonstring = new Scanner(new URL(userlink).openStream()).useDelimiter("\\A").next();
            JSONObject json = new JSONObject(jsonstring);
            System.err.println(jsonstring);
            if (json.has("error")){ //if the token is invalid, will return error object
                UserData.tokenValidity = false;
                System.err.println("JSON FOR TOKEN HAS ERROR");
                return false;
            } else {
                UserData.username = json.getString("username");

                if(!json.isNull("organization_id") && !json.isNull("organization")){
                    JSONObject org = json.getJSONObject("organization");
                    UserData.fillOrgData(org);
                }
                if(!json.isNull("fleet_id") && !json.isNull("fleet")){
                    JSONObject fleet = json.getJSONObject("fleet");
                    UserData.fillFleetData(fleet);
                }
                if(!json.isNull("squad_id") && !json.isNull("squad")){
                    JSONObject squad = json.getJSONObject("squad");
                    UserData.fillSquadData(squad);
                }
                if (json.isNull("organization_id") || json.isNull("fleet_id") || json.isNull("squad_id")){
                    UserData.userNotInSquad = true;
                    System.err.println("USER NOT IN SQUAD");
                }

                UserData.token = token;
                UserData.tokenValidity = true;
                return true;
            }
        } catch (Exception e){
            System.err.println(e.getMessage());
            if (e instanceof JSONException){
                System.err.println("Org json invalid");
            }
            UserData.tokenValidity = false;
            return false;
        }
    }
}
