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


    static String[] buildString(String name, int statusID, int orgID, String manifesto, int fleetID,
                              int squadID, String email, String pwd, int userID, int mode) {

        /***
         *  Builds a String[] to use as argument for the API methods.
         *  If its String argument is an empty string, it doesnt need to be sent,
         *  and if its int argument is negative, it doesnt need to be sent.
         */
        String toBeSplit = String.format(Locale.UK,
                "%s %d %d %s %d %d %s %s %d %d",
                name, statusID, orgID, manifesto, fleetID, squadID, email, pwd, userID, mode);

        return toBeSplit.split(" ");
    }

    private static void sendGet(String URL) throws Exception {
        /***
         * Sends GET request to the URL in the argument.
         */

        URL obj = new URL(URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", "UOLTT App/v3");

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + URL);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

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

        URL obj = new URL(URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add request header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", "UOLTT App/v3");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");


        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(params);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + URL);
        System.out.println("Post parameters : " + params);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();

    }

    // TODO make sure edit methods can post any number of params

    // Fleet Management
    static JSONObject createNewFleet(String[] data){
        /***
         * Creates a new FLEET object. to fill it with members,
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

            String frame = "name=%s&organization_id=%s&status_id=%s&manifesto=%s&_method=patch";

            String name = data[0];
            String status_id = data[1];
            String org_id = data[2];
            String manifesto = data[3];
            String fleetID = data[4];

            String newFleet = sendPost(String.format(Locale.UK, UserData.API_ROOT + "fleets/%s", fleetID),
                                       String.format(frame, name, org_id, status_id, manifesto));

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
    static JSONObject createNewSquad(String[] data){
        /***
         * Creates a new SQUADRON object. to fill it with members,
         * add its squadID to a member.
         */

        String fleetID = data[4];
        String statusID = data[1];
        String name = data[0];

        String frame = "fleet_id=%s&status_id=%s&name=%s";

        try{
            String newSquad = sendPost(UserData.API_ROOT + "squads",
                                       String.format(frame, fleetID, statusID, name));


            return new JSONObject(newSquad);

        } catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }

    }

    static JSONObject editSquad(String[] data) {
        /***
         * Updates the selected squad (squadID), and returns the updated squad JSON object
         */
        try {

            String squadID = data[5];
            String fleetID = data[4];
            String statusID = data[1];
            String name = data[0];

            String frame = "fleet_id=%s&status_id=%s&name=%s&_method=patch";

            String newFleet = sendPost(String.format(UserData.API_ROOT + "squads/%s", squadID),
                                       String.format(frame, fleetID, statusID, name));

            return new JSONObject(newFleet);

        } catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    static JSONObject deleteSquad(int squadID){
        /***
         * Deletes the specified squad
         */
        try {
            String URL = String.format(Locale.UK, UserData.API_ROOT + "squads/%d", squadID);

            String newFleet = sendPost(URL, "_method=delete");

            return new JSONObject(newFleet);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new JSONObject();
        }

    }

    //User Management

    static JSONObject createNewUser(String[] data){
        /***
         * Creates a new SQUADRON object. to fill it with members,
         * add its squadID to a member.
         */

        String fleetID = data[4];
        String squadID = data[5];
        String orgID = data[2];
        String name = data[0];
        String email = data[6];
        String pwd = data[7];


        String frame = "name=%s&organization_id=%s&fleet_id=%s&squad_id=%s&email=%s&password=%s";

        try{
            String newSquad = sendPost(UserData.API_ROOT + "users",
                    String.format(frame, name, orgID, fleetID, squadID, email, pwd));


            return new JSONObject(newSquad);

        } catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }

    }

    static JSONObject editUser(String[] data) {
        /***
         * Updates the selected user (userID), and returns the updated user JSON object
         */
        try {

            String fleetID = data[4];
            String squadID = data[5];
            String orgID = data[2];
            String name = data[0];
            String email = data[6];
            String pwd = data[7];

            String frame = "name=%s&organization_id=%s&fleet_id=%s&squad_id=%s&email=%s&password=%s&_method=patch";

            String newFleet = sendPost(String.format(UserData.API_ROOT + "users/%s", squadID),
                    String.format(frame, name, orgID, fleetID, squadID, email, pwd));
            //TODO make every editX method accept any number of params to send, not just all of them
            return new JSONObject(newFleet);

        } catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    static JSONObject deleteUser(int userID){
        /***
         * Deletes the specified user
         */
        try {
            String URL = String.format(Locale.UK, UserData.API_ROOT + "users/%d", userID);


            String newFleet = sendPost(URL, "_method=delete");

            return new JSONObject(newFleet);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new JSONObject();
        }

    }

}
