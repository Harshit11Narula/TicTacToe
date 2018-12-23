package com.example.game.game3connect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.support.v7.widget.GridLayout;
//import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {

    GridLayout gridlay;
    int activeplayer =1;
    // 1 is cross 2 is zero
    boolean gameactive = true;
    int gamestate[] = {0,0,0,0,0,0,0,0,0};

    int[][] winningposition = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    public void dropin(View view) {

        ImageView counter = (ImageView)view;


        int tappedcounter = Integer.parseInt(counter.getTag().toString());

        if (gamestate[tappedcounter] == 0 && gameactive) {
            gamestate[tappedcounter] = activeplayer;

            counter.setTranslationY(-1000f);
            if (activeplayer == 1) {
                counter.setImageResource(R.drawable.cross);
                activeplayer = 2;
            } else {
                counter.setImageResource(R.drawable.zero);
                activeplayer = 1;
            }

            counter.animate().translationYBy(1000f).rotation(360).setDuration(700);


            for (int[] winning: winningposition){

                if (gamestate[winning[0]]==gamestate[winning[1]] && gamestate[winning[1]]==gamestate[winning[2]] && gamestate[winning[2]]!=0){

                    String r1 = "Cross";
                    gameactive = false;

                    if( gamestate[winning[0]] == 2)
                        r1 = "Zero";

                    TextView winnermessage = (TextView)findViewById(R.id.textView);
                    winnermessage.setText(r1 + " has won!");
                    LinearLayout layout = (LinearLayout)findViewById(R.id.playagainlayout);
                    layout.setVisibility(View.VISIBLE);
                }




            }
            boolean gameover = true;

            for (int counterState : gamestate) {
                if (counterState == 0) gameover = false;

            }
            if (gameover){
                TextView winnermessage = (TextView)findViewById(R.id.textView);
                winnermessage.setText("It's a Draw");
                LinearLayout layout = (LinearLayout)findViewById(R.id.playagainlayout);
                layout.setVisibility(View.VISIBLE);

            }

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void playagain(View view) {
        gameactive = true;
        LinearLayout layout = (LinearLayout)findViewById(R.id.playagainlayout);
        layout.setVisibility(View.INVISIBLE);
        int activeplayer =1;
        // 1 is cross 2 is zero
        for(int i=0;i<gamestate.length;i++) {
            gamestate[i] = 0;
        }
        gridlay = (GridLayout)findViewById(R.id.grid);
        for(int i=0;i<gridlay.getChildCount();i++){
            ((ImageView)gridlay.getChildAt(i)).setImageResource(0);
        }
    }
}
