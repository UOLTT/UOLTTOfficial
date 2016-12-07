package com.uoltt.lukaprebilgrintal.uolttofficial;

/**
 * Created by Luka Prebil Grintal on 31/08/2016.
 */
public class UserData {

    static String squadronName;
    static String token;
    static boolean tokenValidity;
    static String username;

    //Squadron Data
    static int squadronID;
    static int fleetID;

    //Formation Data
    static int formationID;
    static String formationName;
    static String formDesc;
    static int[]  bounds = {0, 0}; //min and max number of members

    //Errors
    static boolean jsonErr = false;
    static boolean linkErr = false;

    //CONSTANTS
    static final int POLLING_RATE = 5000;
    static final String API_ROOT  = "api.uoltt.org/api/v4/";


}
