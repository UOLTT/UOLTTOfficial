package com.uoltt.lukaprebilgrintal.uolttofficial;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
                        formimage.setImageResource(R.drawable.f1); break;
                    case 2:
                        formimage.setImageResource(R.drawable.f2); break;
                    case 3:
                        formimage.setImageResource(R.drawable.f3); break;
                    case 4:
                        formimage.setImageResource(R.drawable.f4); break;
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
