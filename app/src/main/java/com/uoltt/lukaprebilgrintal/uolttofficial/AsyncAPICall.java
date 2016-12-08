package com.uoltt.lukaprebilgrintal.uolttofficial;

import android.os.AsyncTask;

import org.json.JSONObject;

/**
 * Created by Luka on 07/12/2016.
 */

public class AsyncAPICall extends AsyncTask <String, Void, JSONObject> {
    @Override
    protected JSONObject doInBackground(String... params) {
        String[] data = params[0].split(" ");
        int mode = Integer.parseInt(data[0]);
        int fleetID = Integer.parseInt(data[1]);
        String name = data[2];
        String status = data[3];

        JSONObject returnJSON = new JSONObject();
        switch(mode){
            case 0:
                returnJSON = APIaccess.createNewFleet(data); break;
            case 1:
                returnJSON = APIaccess.editFleet(data); break;
            case 2:
                returnJSON = APIaccess.deleteFleet(fleetID); break;
        }

        return returnJSON;
    }

}
