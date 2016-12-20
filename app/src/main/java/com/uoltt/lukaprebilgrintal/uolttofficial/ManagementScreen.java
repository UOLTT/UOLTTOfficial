package com.uoltt.lukaprebilgrintal.uolttofficial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class ManagementScreen extends MenuTemplate {

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
