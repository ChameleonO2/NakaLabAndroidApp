package com.example.chameleono2.pazgrithm;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by ChameleonO2 on 2016/11/01.
 */

public class TitleActivity extends AppCompatActivity {
    MediaPlayer bgm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        if (bgm == null) {
            bgm = MediaPlayer.create(getApplicationContext(), R.raw.iti);
            bgm.setLooping(true);
            bgm.start();
        }
        setTitle("Puzgorithm");

        ImageButton imageButton = (ImageButton) findViewById(R.id.imageButton);
        ImageButton imageButton2 = (ImageButton) findViewById(R.id.imageButton2);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bgm.release();
                bgm = null;
                Intent intent = new Intent(getApplication(), StageSelectActivity.class);
                startActivity(intent);
            }
        });

        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               /* bgm.release();
               bgm=null;*/
                Intent intent = new Intent(getApplication(), HelpActivity.class);
                startActivity(intent);
            }
        });
    }
    //onResumeとonPause入れるとアプリ実行できんwhy!
//画面表示したとき

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("onResume!", "--------------------------------------");
        if (bgm == null) {
            bgm = MediaPlayer.create(getApplicationContext(), R.raw.iti);
            bgm.setLooping(true);
            bgm.start();
        }
    }

    //画面非表示にしたとき
    @Override
    protected void onPause() {
        super.onPause();
        if (bgm != null) {
            bgm.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bgm.release();
        bgm = null;
    }
}
