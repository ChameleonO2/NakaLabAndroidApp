package com.example.chameleono2.pazgrithm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.StringReader;

/**
 * Created by ChameleonO2 on 2016/11/01.
 */

public class StageSelectActivity extends AppCompatActivity {
    protected static final int[] stagedata = {R.id.st1,R.id.st2,R.id.st3};
    Button[] stageselected = new Button[stagedata.length];
    int stageid;
    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stageselect);
        setTitle("Puzgorithm");
        for(int i = 0;i<stagedata.length;i++){
            stageselected[i]=(Button)findViewById(stagedata[i]);
        }
        stageselected[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getBaseContext(),"1",Toast.LENGTH_SHORT).show();
                stageid=0;
                movegames();

            }
        });
        stageselected[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getBaseContext(),"2",Toast.LENGTH_SHORT).show();
                stageid=1;
                movegames();
            }
        });
        stageselected[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getBaseContext(),"3",Toast.LENGTH_SHORT).show();
                stageid=2;
                movegames();
            }
        });

    }//onCreate

    protected void movegames(){
        Intent intent = new Intent(getApplication(),MainActivity.class);
        intent.putExtra("STAGEDATA",stageid);
        startActivity(intent);
    }

}
