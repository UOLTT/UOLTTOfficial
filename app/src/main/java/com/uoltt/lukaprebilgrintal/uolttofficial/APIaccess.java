package com.uoltt.lukaprebilgrintal.uolttofficial;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

/**
 * Created by Luka on 07/11/2016.
 */
public class APIaccess {

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
    public static JSONObject createNewFleet(String name, String status){
        /***
         * Creates a new FLEET object, to fill it with members,
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

    public static JSONObject editFleet(int fleetID, String name, String status) {
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

    public static void deleteFleet(int fleetID){
        /***
         * Deletes the specified fleet
         */
        try {
            String URL = String.format("http://developers.uoltt.org/api/v3/fleets/%d", fleetID);

            String newFleet = sendPost(URL, "_method=delete");

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    // Squadron Management

}
