package com.uoltt.lukaprebilgrintal.uolttofficial;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import org.json.JSONObject;

public class ManagementScreen extends MenuTemplate {

    public void uploadData(View v){

        EditText nameObj = (EditText) findViewById(R.id.name);
        EditText maniObj = (EditText) findViewById(R.id.manifesto);
        EditText mailObj = (EditText) findViewById(R.id.email);
        EditText passObj = (EditText) findViewById(R.id.pass);

        EditText uidObj = (EditText) findViewById(R.id.user_id);
        EditText sidObj = (EditText) findViewById(R.id.squadron_id);
        EditText fidObj = (EditText) findViewById(R.id.fleet_id);
        EditText oidObj = (EditText) findViewById(R.id.org_id);
        EditText stidObj = (EditText) findViewById(R.id.status_id);

        String name = nameObj.getText().toString();
        String manifesto = maniObj.getText().toString();
        String email = mailObj.getText().toString();
        String pass = passObj.getText().toString();
        int user_id = Integer.parseInt(uidObj.getText().toString());
        int squadron_id = Integer.parseInt(sidObj.getText().toString());
        int fleet_id = Integer.parseInt(fidObj.getText().toString());
        int org_id = Integer.parseInt(oidObj.getText().toString());
        int status_id = Integer.parseInt(stidObj.getText().toString());

        Spinner spinner = (Spinner)findViewById(R.id.select_method_type);
        String type = spinner.getSelectedItem().toString();
        Spinner spinner2 = (Spinner)findViewById(R.id.select_method_group);
        String group = spinner2.getSelectedItem().toString();

        int mode = -1;

        if(type.equals("Create") && group.equals("Fleet")) mode = 0;
        else if(type.equals("Edit") && group.equals("Fleet")) mode = 1;
        else if(type.equals("Delete") && group.equals("Fleet")) mode = 2;
        else if(type.equals("Create") && group.equals("Squad")) mode = 3;
        else if(type.equals("Edit") && group.equals("Squad")) mode = 4;
        else if(type.equals("Delete") && group.equals("Squad")) mode = 5;
        else if(type.equals("Create") && group.equals("User")) mode = 6;
        else if(type.equals("Edit") && group.equals("User")) mode = 7;
        else if(type.equals("Delete") && group.equals("User")) mode = 8;


        try{
            JSONObject returnJSON = new AsyncAPICall().execute(APIaccess.buildString(
                    name, status_id, org_id, manifesto, fleet_id,
                    squadron_id, email, pass, user_id, mode)).get();
        } catch (Exception e){
            System.err.println(e.getMessage());
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management_screen);

        Spinner methodType = (Spinner) findViewById(R.id.select_method_type);
        ArrayAdapter<CharSequence> methodTypeAdapter = ArrayAdapter.createFromResource(this,
                R.array.method_types, android.R.layout.simple_spinner_item);
        methodTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        methodType.setAdapter(methodTypeAdapter);

        Spinner methodGroup = (Spinner) findViewById(R.id.select_method_group);
        ArrayAdapter<CharSequence> methodGroupAdapter = ArrayAdapter.createFromResource(this,
                R.array.method_group, android.R.layout.simple_spinner_item);
        methodGroupAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        methodGroup.setAdapter(methodGroupAdapter);
    }
}
