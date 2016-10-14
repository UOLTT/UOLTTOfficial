package com.uoltt.lukaprebilgrintal.uolttofficial;

import android.content.Intent;
import android.media.Image;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageCanvas extends AppCompatActivity {

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

        TextView topText = (TextView) findViewById(R.id.topText);
        topText.setText(String.format("Current Formation for %s", UserData.username));
        final ImageView formimage = (ImageView) findViewById(R.id.formationImage);

        final Runnable imgUpdateTask = new Runnable() {
            @Override
            public void run() {
                Handler handler = new Handler();
                System.err.println("runnableloop");
                System.err.println(UserData.formationID);

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
                    handler.postDelayed(this, UserData.POLLING_RATE);
            }
        };
        formimage.post(imgUpdateTask);
    }
}
