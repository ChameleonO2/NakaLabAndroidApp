package com.example.chameleono2.pazgrithm;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
/**
 * Created by ChameleonO2 on 2016/11/01.
 */

public class TitleActivity extends AppCompatActivity{
    MediaPlayer bgm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);

        bgm = MediaPlayer.create(getApplicationContext(), R.raw.iti);
        bgm.setLooping(true);
        bgm.start();
        setTitle("Puzgorithm");

        ImageButton imageButton  = (ImageButton) findViewById(R.id.imageButton);
        ImageButton imageButton2 = (ImageButton) findViewById(R.id.imageButton2);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bgm.release();
               Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
            }
        });

        imageButton2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
              Intent intent = new Intent(getApplication(), HelpActivity.class);
              startActivity(intent);
            }
        });
    }
    //onResumeとonPause入れるとアプリ実行できんwhy!
//画面表示したとき
    /*
    @Override
   protected void onResume() {
        super.onResume();
        bgm.start();
    }*/
//画面非表示にしたとき
//    @Override
//    protected void onPause() {
//        super.onPause();
//        bgm.pause();
//    }
//
    @Override
    protected void onDestroy() {
        super.onDestroy();
        bgm.release();
        //bgm = null;
    }
}
