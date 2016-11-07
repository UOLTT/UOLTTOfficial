package com.uoltt.lukaprebilgrintal.uolttofficial;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MenuTemplate extends AppCompatActivity {


    // Menu Stuff
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        if (this instanceof ImageCanvas){
            menu.getItem(0).setEnabled(false);
        }
//        menu.getItem(1).setEnabled(false);
//        menu.getItem(2).setEnabled(false);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.logout:
                logout();
                return true;
            case R.id.settings:
                openSettingsActivity();
                return true;
            case R.id.credits:
                openCreditsActivity();
                return true;
            case R.id.canvas:
                openCanvasActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void logout(){
        SharedPreferences prefs = this.getSharedPreferences(getString(R.string.shared_preferences_file), Context.MODE_PRIVATE);
        prefs.edit().putBoolean("LoggedIn", false).commit();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void openSettingsActivity(){
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
        finish();
    }

    public void openCreditsActivity(){
        Intent intent = new Intent(this, Credits.class);
        startActivity(intent);
        finish();
    }

    public void openCanvasActivity(){
        if (!(this instanceof ImageCanvas)){
            Intent intent = new Intent(this, ImageCanvas.class);
            startActivity(intent);
            finish();
        }
    }
}
