package com.uoltt.lukaprebilgrintal.uolttofficial;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Luka Prebil Grintal on 31/08/2016.
 */
public class UserData {

    /*** TODO
     * Should really work on moving all of this data back to one JSON
     * and reading from it in other classes.
     *
     * If any of the data is null, assume the user isnt a member of the data group
     * (Org, Fleet, Squad), or that the squad doesnt have a set formation.
     */

    static String squadronName;
    static String token;
    static boolean tokenValidity;
    static String username;



    //Formation Data
    static int formationID;
    static String formationName;
    static String formDesc;
    static int[]  bounds = {0, 0}; //min and max number of members

    //Organisation data
    static int organisationID;
    static String organisationName;
    static String organisationDomain;
    static int adminUserID;
    static int orgStatusID;
    static String orgManifesto;
    static String[] orgDates; //date created and last edited

    //Fleet Data
    static int fleetID;
    static String fleetName;
    static int admiralID;
    static int parentOrgID;
    static int fleetStatusID;
    static String fleetManifesto;
    static String[] fleetDates; //date created and last edited

    //Squadron Data
    static int squadronID;
    static int parentFleetID;
    static int squadLeaderID;
    static int squadStatusID;
    static int squadFormationID;
    static String squadName;
    static String[] squadDates; //date created and last edited


    //Errors
    static boolean jsonErr = false;
    static boolean linkErr = false;

    //CONSTANTS
    static final int POLLING_RATE = 5000;
    static final String API_ROOT  = "https://api.uoltt.org/api/v4/";

    //Methods
    static void fillOrgData(JSONObject org) throws JSONException {
            organisationID = org.getInt("id");
            organisationName = org.getString("name");
            organisationDomain = org.getString("domain");
            adminUserID = org.getInt("admin_user_id");
            orgStatusID = org.getInt("status_id");
            orgManifesto = org.getString("manifesto");
            orgDates[0] = org.getString("created_at");
            orgDates[1] = org.getString("updated_at");

    }

    static void fillFleetData(JSONObject fleet) throws JSONException {
        fleetID = fleet.getInt("id");
        fleetName = fleet.getString("name");
        admiralID = fleet.getInt("admiral_id");
        parentOrgID = fleet.getInt("organization_id");
        fleetStatusID = fleet.getInt("status_id");
        fleetManifesto = fleet.getString("manifesto");
        fleetDates[0] = fleet.getString("created_at");
        fleetDates[1] = fleet.getString("updated_at");
    }

    static void fillSquadData(JSONObject squad) throws JSONException {
        squadronID = squad.getInt("id");
        parentFleetID = squad.getInt("fleet_id");
        squadLeaderID = squad.getInt("squad_leader_id");
        squadStatusID = squad.getInt("status_id");
        squadFormationID = squad.getInt("formation_id");
        squadName = squad.getString("name");
        squadronName = squadName; //TODO replace instances of squadronName with squadName, deprecate former
        squadDates[0] = squad.getString("created_at");
        squadDates[1] = squad.getString("updated_at");

    }


}
