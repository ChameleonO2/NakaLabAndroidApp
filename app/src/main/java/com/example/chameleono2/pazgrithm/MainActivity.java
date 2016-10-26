package com.example.chameleono2.pazgrithm;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.support.v4.app.ShareCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.view.InputDeviceCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class MainActivity extends AppCompatActivity {
    public static final int fieldlength = 7,fscales=110,lmrg=0,tmrg=200;

    //Spinner's selectlist and player's location
    protected static final String[] list_data = {"なにもしない","↑まえにすすむ", "→みぎをむく", "←ひだりをむく", "Aにうつる","Bにうつる"};
    protected static final String[] lista_data = {"なにもしない","↑まえにすすむ", "→みぎをむく", "←ひだりをむく", "Bにうつる"};
    protected static final String[] listb_data = {"なにもしない","↑まえにすすむ", "→みぎをむく", "←ひだりをむく"};
    protected static final String[] times_data ={"1","2","3","4"};
    protected static final int[] spinnerid = {R.id.command1,R.id.command2,R.id.command3,R.id.command4,R.id.command5,R.id.command6,R.id.command7,R.id.command8,R.id.command9,R.id.command10,R.id.command11,R.id.command12};
    protected static final int [] A_spinnerid = {R.id.A_times,R.id.Acommand1,R.id.Acommand2,R.id.Acommand3,R.id.Acommand4,R.id.Acommand5,R.id.Acommand6,R.id.Acommand7,R.id.Acommand8,R.id.Acommand9};
    protected static final int [] B_spinnerid = {R.id.B_times,R.id.Bcommand1,R.id.Bcommand2,R.id.Bcommand3,R.id.Bcommand4,R.id.Bcommand5,R.id.Bcommand6,R.id.Bcommand7,R.id.Bcommand8,R.id.Bcommand9};
    protected Spinner[] spinners = new Spinner[spinnerid.length];
    protected Spinner[] A_spinners = new Spinner[A_spinnerid.length];
    protected Spinner[] B_spinners = new Spinner[B_spinnerid.length];

    public Playerlotate playerlotate=new Playerlotate();




    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        RelativeLayout GameField =(RelativeLayout)this.findViewById(R.id.Gamelayout);
        RelativeLayout.LayoutParams GameFieldParamg;
        final ImageView playerimg = new ImageView(this);
        ImageView[][] fields;

        TextView testext = (TextView)this.findViewById(R.id.test_text);
        fields=new ImageView[fieldlength][fieldlength];
        int[][] fielddatas=new int[fieldlength+2][fieldlength+2];
        int[] testf =new int[82];
        Button startbutton =(Button)findViewById(R.id.button_s);
        Resources res =this.getResources();
        InputStream is =null;
        BufferedReader br =null;
        StringBuilder sb = new StringBuilder();
        String testhoge;
        try{
            try {
                is = res.openRawResource(R.raw.stage);
                br = new BufferedReader(new InputStreamReader(is));
                int str;
                int i=0,j=0;
                while((str = br.read()) != -1){
                    sb.append(str +"\n");
                    if(str>=48){
                        fielddatas[i++][j]=str-48;
                        if(i>8){
                            j++;i=0;
                        }
                    }

                }
            } finally {
                if (br !=null) br.close();
            }
        } catch (IOException e) {
            Toast.makeText(this, "読み込み失敗", Toast.LENGTH_SHORT).show();
        }
        testhoge= String.valueOf(fielddatas[1][1]);
        testext.setText(testhoge);


        for(int i=0;i<7;i++){
            for(int j=0;j<7;j++){
                fields[i][j]=new ImageView(this);
            }
        }
        //spiner_settings
        for(int i=0;i<spinnerid.length ;i++){
            spinners[i] = (Spinner) findViewById(spinnerid[i]);
        }
        for(int i=0;i<A_spinnerid.length ;i++){
            A_spinners[i] = (Spinner) findViewById(A_spinnerid[i]);
        }
        for(int i=0;i<B_spinnerid.length ;i++){
            B_spinners[i] = (Spinner) findViewById(B_spinnerid[i]);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list_data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> A_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lista_data);
        A_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> B_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listb_data);
        B_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> time_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, times_data);
        time_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        for(int j = 0; j < spinnerid.length; j++) {
            spinners[j].setAdapter(adapter);
            spinners[j].setMinimumHeight(100);
        }

        A_spinners[0].setAdapter(time_adapter);
        A_spinners[0].setMinimumHeight(102);
        for(int j = 1; j < A_spinnerid.length; j++) {
            A_spinners[j].setAdapter(A_adapter);
            A_spinners[j].setMinimumHeight(102);
        }

        B_spinners[0].setAdapter(time_adapter);
        B_spinners[0].setMinimumHeight(120);
        for(int j = 1; j < B_spinnerid.length; j++) {
            B_spinners[j].setAdapter(B_adapter);
            B_spinners[j].setMinimumHeight(120);
        }

        //imagsettings
        for(int i=0;i<7;i++) {
            for (int j = 0; j < 7; j++) {

                if(fielddatas[j+1][i+1]==1){
                    fields[i][j].setImageResource(R.drawable.falsemap);
                }else {
                    fields[i][j].setImageResource(R.drawable.truemap);//
                }
                GameFieldParamg = new RelativeLayout.LayoutParams(fscales, fscales);
                GameFieldParamg.leftMargin = lmrg + fscales * j;
                GameFieldParamg.topMargin = tmrg + fscales * i;
                fields[i][j].setLayoutParams(GameFieldParamg);
                GameField.addView(fields[i][j]);
            }
        }

        playerimg.setImageResource(R.drawable.character);
        GameFieldParamg = new RelativeLayout.LayoutParams(fscales, fscales);
        playerlotate.setxy(lmrg+fscales*3,tmrg+fscales*6);
        playerimg.setLayoutParams(GameFieldParamg);
        GameFieldParamg.leftMargin=playerlotate.player_x;
        GameFieldParamg.topMargin=playerlotate.player_y;
        playerimg.setLayoutParams(GameFieldParamg);
        GameField.addView(playerimg);

        //buttonsettings
        startbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String tmp;
                tmp=(String)A_spinners[0].getSelectedItem();

                for(int i=0;i<spinnerid.length;i++) {
                    if(spinners[i].getSelectedItem() ==list_data[1]){
                        if((playerlotate.player_Rotate/90)%4==0) {
                            playerimg.layout(playerlotate.player_x, playerlotate.player_y - fscales, playerlotate.player_x + playerimg.getWidth(), playerlotate.player_y - fscales + playerimg.getHeight());
                            playerlotate.addxy(0, -fscales);
                            playerimg.invalidate();
                        }else if((playerlotate.player_Rotate/90)%4==1){

                            playerimg.layout(playerlotate.player_x+fscales, playerlotate.player_y , fscales+playerlotate.player_x + playerimg.getWidth(), playerlotate.player_y + playerimg.getHeight());
                            playerlotate.addxy(0, -fscales);
                            playerimg.invalidate();
                            playerlotate.setr(90);
                            playerimg.setRotation(playerlotate.player_Rotate);
                        }

                    }else if(spinners[i].getSelectedItem() ==list_data[2]){
                        playerlotate.addr(90);
                        playerimg.setRotation(playerlotate.player_Rotate);

                    }else if(spinners[i].getSelectedItem() ==list_data[3]){

                    }else if(spinners[i].getSelectedItem() ==list_data[4]){
                        //
                        for(int l= 1;l<=Integer.parseInt(tmp);l++) {

                        }
                    }else if(spinners[i].getSelectedItem() ==list_data[5]){

                    }else {

                    }
                }

            }
        });


    }
}
class Playerlotate{
    public int player_x,player_y,player_Rotate=0;
    Playerlotate(){
        player_x=0;player_y=0;player_Rotate=0;
    }
    Playerlotate(int x,int y){
        player_x=x;player_y=y;player_Rotate=0;
    }
    Playerlotate(int x,int y,int r){
        this(x,y);player_Rotate=r;
    }
    public void addxy(int x,int y){
        player_x+=x;player_y+=y;
    }
    public void setxy(int x,int y){
        player_x=x;player_y=y;
    }
    public void setr(int r){
        player_Rotate=r;
    }
    public  void addr(int r) {
        player_Rotate+=r;
    }
    public void addxyr(int x,int y,int r){
        this.addxy(x,y);
        this.addr(r);
    }
    public void setxyr(int x,int y,int r){
        this.setxy(x,y);
        this.setr(r);
    }

}

