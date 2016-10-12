package com.uoltt.lukaprebilgrintal.uolttofficial;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageCanvas extends AppCompatActivity {

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
                System.err.println(UserData.formationID);
                    switch (UserData.formationID) {
                        case 1:
                            formimage.setImageResource(R.drawable.f1); break;
                        case 2:
                            formimage.setImageResource(R.drawable.f2); break;
                        case 3:
                            formimage.setImageResource(R.drawable.f3); break;
                        case 4:
                            formimage.setImageResource(R.drawable.f4); break;
                        default: formimage.setImageResource(R.drawable.badjson); //FIXME it always displays badjson
                    }
                    handler.postDelayed(this, UserData.POLLING_RATE);
            }
        };
        formimage.post(imgUpdateTask);
    }
}
