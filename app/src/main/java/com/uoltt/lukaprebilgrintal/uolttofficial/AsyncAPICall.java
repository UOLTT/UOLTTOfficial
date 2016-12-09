package com.uoltt.lukaprebilgrintal.uolttofficial;


import android.os.AsyncTask;
import org.json.JSONObject;

/**
 * Created by Luka on 07/12/2016.
 */


/***
 *  Async task that executes a HTTP method on the API
 *
 *  String[] is of form [name, status_id, org_id, manifesto,
 *                       fleet_id, squad_id, email, pwd, user_id,
 *                       mode]
 *
 *  Pass APIaccess.buildString(parameters) as the task's argument.
 */
public class AsyncAPICall extends AsyncTask <String[], Void, JSONObject> {
    @Override
    protected JSONObject doInBackground(String[]... params) {
        String[] data = params[0];

        int fleetID = Integer.parseInt(data[4]);
        int squadID = Integer.parseInt(data[5]);
        int userID  = Integer.parseInt(data[8]);

        int mode = Integer.parseInt(data[data.length - 1]);

        JSONObject returnJSON = new JSONObject();
        switch(mode){
            case 0:
                returnJSON = APIaccess.createNewFleet(data); break;
            case 1:
                returnJSON = APIaccess.editFleet(data); break;
            case 2:
                returnJSON = APIaccess.deleteFleet(fleetID); break;
            case 3:
                returnJSON = APIaccess.createNewSquad(data); break;
            case 4:
                returnJSON = APIaccess.editSquad(data); break;
            case 5:
                returnJSON = APIaccess.deleteSquad(squadID); break;
            case 6:
                returnJSON = APIaccess.createNewUser(data); break;
            case 7:
                returnJSON = APIaccess.editUser(data); break;
            case 8:
                returnJSON = APIaccess.deleteUser(userID); break;
        }

        return returnJSON;
    }

}
