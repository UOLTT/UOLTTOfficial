package com.uoltt.lukaprebilgrintal.uolttofficial;

import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;


/**
 * Created by Luka on 07/11/2016.
 */

class APIaccess {

    static String buildString(int mode, int fleetID, String name, String status){
        return String.format("%d %d %s %s", mode, fleetID, name, status);
    } //TODO v4 compat

    private static void sendGet(String URL) throws Exception {
        /***
         * Sends GET request to the URL in the argument.
         */

        String url = URL;

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", "UOLTT App/v3");

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

    }

    private static String sendPost(String URL, String params) throws Exception {
        /***
         * Sends a POST request to the URL in the argument, with parameters in the second argument,
         * will sooner or later create a method that builds the params string...
         */
        String url = URL;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add request header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", "UOLTT App/v3");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        String urlParameters = params;

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();

    }



    // Fleet Management
    static JSONObject createNewFleet(String[] data){
        /***
         * Creates a new FLEET object, to fill it with members,
         * add its fleetID to a squadron.
         */

        try{

            String name = data[0];
            String status_id = data[1];
            String org_id = data[2];
            String manifesto = data[3];

            String frame = "name=%s&status_id=%s&organization_id=%s&manifesto=%s";

            String newFleet = sendPost(UserData.API_ROOT + "fleets",
                              String.format(frame, name, status_id, org_id, manifesto));

            return new JSONObject(newFleet);

        } catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }

    }

    static JSONObject editFleet(String[] data) {
        /***
         * Updates the selected fleet (fleetID), and returns the updated fleet JSON object
         */
        try {

            String frame = "id=%s&name=%s&organization_id=%s&status_id=%s&manifesto=%s&_method=patch";

            String name = data[0];
            String status_id = data[1];
            String org_id = data[2];
            String manifesto = data[3];
            String fleetID = data[4];


            String params = String.format(frame, fleetID, name, org_id, status_id, manifesto);
            String URL = String.format(Locale.UK, UserData.API_ROOT + "fleets/%s", fleetID);

            String newFleet = sendPost(URL, params);

            return new JSONObject(newFleet);

        } catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    static JSONObject deleteFleet(int fleetID){
        /***
         * Deletes the specified fleet
         */
        try {
            String URL = String.format(Locale.UK, UserData.API_ROOT + "fleets/%d", fleetID);

            String newFleet = sendPost(URL, "_method=delete");

            return new JSONObject(newFleet);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }

    }


    // Squadron Management
    static JSONObject createNewSquad(String name, String status){
        /***
         * Creates a new SQUADRON object, to fill it with members,
         * add its fleetID to a squadron.
         */

        try{
            String newFleet = sendPost("http://developers.uoltt.org/api/v3/fleets",
                    String.format("name=%s&status=%s", name, status));

            JSONObject newFleetJSON = new JSONObject(newFleet);

            return newFleetJSON;

        } catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }

    }

    static JSONObject editSquad(int fleetID, String name, String status) {
        /***
         * Updates the selected fleet (fleetID), and returns the updated fleet JSON object
         */
        try {
            String params = String.format("name=%s&status=%s", name, status);
            String URL = String.format("http://developers.uoltt.org/api/v3/fleets/%d", fleetID);

            String newFleet = sendPost(URL, params);

            JSONObject newFleetJSON = new JSONObject(newFleet);

            return newFleetJSON;

        } catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    static JSONObject deleteSquad(int fleetID){
        /***
         * Deletes the specified fleet
         */
        try {
            String URL = String.format("http://developers.uoltt.org/api/v3/fleets/%d", fleetID);

            String newFleet = sendPost(URL, "_method=delete");
            JSONObject returnMsg = new JSONObject(newFleet);
            return returnMsg;

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new JSONObject();
        }

    }
}
