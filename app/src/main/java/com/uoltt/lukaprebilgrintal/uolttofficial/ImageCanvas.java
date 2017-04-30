package com.uoltt.lukaprebilgrintal.uolttofficial;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageCanvas extends MenuTemplate {


    private void defaultCase(ImageView formimage){
        if(UserData.jsonErr){
            formimage.setImageResource(R.drawable.badjson);
        } else if (UserData.linkErr){
            formimage.setImageResource(R.drawable.badlink);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_canvas);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        System.err.println("UserNotInSquad = ");
        System.err.println(UserData.userNotInSquad);
        if (UserData.userNotInSquad){
            //display error in a message bubble
            System.err.println("In the error block");
            AlertDialog.Builder builder = new AlertDialog.Builder(ImageCanvas.this)
                                          .setTitle(R.string.squad_error_title)
                                          .setMessage(R.string.squad_error_message)
                                          .setPositiveButton(R.string.squad_error_logout, new DialogInterface.OnClickListener() {
                                              @Override
                                              public void onClick(DialogInterface dialogInterface, int i) {
                                                  //User Clicked OK, needs to logout and speak to an admin on Discord.
                                                  Snackbar snackbar = Snackbar.make(findViewById(R.id.canvas),
                                                          "Please log out.", Snackbar.LENGTH_LONG);
                                                  snackbar.show();
                                              }
                                          });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        else {

            TextView topText = (TextView) findViewById(R.id.topText);
            topText.setText(String.format("Current Formation for %s", UserData.username));
            final ImageView formimage = (ImageView) findViewById(R.id.formationImage);
            final TextView formationName = (TextView) findViewById(R.id.formationName);
            final TextView formationDesc = (TextView) findViewById(R.id.formationDesc);

            final Runnable viewsUpdateTask = new Runnable() {
                @Override
                public void run() {
                    Handler handler = new Handler();

                    Intent mServiceIntent = new Intent(ImageCanvas.this, BackgroundOps.class);
                    startService(mServiceIntent);

                    switch (UserData.formationID) {
                        case 1:
                            formimage.setImageResource(R.drawable.f1);
                            break;
                        case 2:
                            formimage.setImageResource(R.drawable.f2);
                            break;
                        case 3:
                            formimage.setImageResource(R.drawable.f3);
                            break;
                        case 4:
                            formimage.setImageResource(R.drawable.f4);
                            break;
                        default:
                            defaultCase(formimage);
                    }

                    formationName.setText(UserData.formationName);
                    formationDesc.setText(UserData.formDesc);
                    handler.postDelayed(this, UserData.POLLING_RATE);
                }
            };
            formimage.post(viewsUpdateTask);
        }
    }

}
