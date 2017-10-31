package com.example.hello.scarne_dice;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import static android.R.id.text1;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "TEST_TAG";
    public int uOscore = 0;
    public int uTscore = 0;
    public int cOscore = 0;
    public int cTscore = 0;
    public int value=0;
    TextView text;
    TextView test1;
    TextView text11;
    TextView text22;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test1 = (TextView)findViewById(R.id.cscore);
        text = (TextView) findViewById(R.id.pscore);
        Button rbutton =(Button)findViewById(R.id.roll);
        Button hbutton =(Button)findViewById(R.id.hold);
        Button resetbutton =(Button)findViewById(R.id.reset);
        text11 = (TextView)findViewById(R.id.ts11);
        text22 = (TextView)findViewById(R.id.ts22);
        //text.setText(value);



        rbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Todo update textview
                 roll();
                if(value!=1) {
                    uTscore+=value;
                    text11.setText("" + uTscore);


                }
                else
                {
                    uTscore=0;
                    uOscore=uTscore+uOscore;
                    compTurn();
                }

            }
        });

        hbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hold();
                compTurn();

            }
        });

        resetbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });
    }
    public void reset()
    {
        uTscore=0;
        uOscore=0;
        cOscore=0;
        cTscore=0;

        text.setText("0");

        test1.setText("0");
        text22.setText("0");
        text11.setText("0");

    }
    public void hold()
    {
        uOscore=uTscore+uOscore;
        text.setText(""+uOscore);
        text11.setText("0");
        uTscore=0;
        cOscore=cTscore+cOscore;
        test1.setText(""+cOscore);
        text22.setText("0");
        cTscore=0;
        if (cOscore>=100)
        {
            Toast myToast = Toast.makeText(this, "Copmuter win !!!",
                    Toast.LENGTH_LONG);
            myToast.show();
            Button rollButton=(Button)findViewById(R.id.roll);
            final Button holdButton=(Button) findViewById(R.id.hold);
            rollButton.setEnabled(true);
            holdButton.setEnabled(true);

            return;
        }
        if(uOscore>=100)
        {
            Toast mytoast=Toast.makeText(this, "You Win !!!",Toast.LENGTH_LONG);
            mytoast.show();
            Button rollButton=(Button)findViewById(R.id.roll);
            final Button holdButton=(Button) findViewById(R.id.hold);
            holdButton.setEnabled(false);
            rollButton.setEnabled(false);
            return;
        }


    }
    public void compTurn(){
        Button rollButton=(Button)findViewById(R.id.roll);
        final Button holdButton=(Button) findViewById(R.id.hold);
        holdButton.setEnabled(false);
        rollButton.setEnabled(false);
        Random random =   new Random();
        int numOfTurn=random.nextInt(6);
        for(int i=0;i<numOfTurn;i++) {
            roll();
            cTscore=cTscore+value;
            test1.setText(""+cTscore);

        }
        hold();
        holdButton.setEnabled(true);
        rollButton.setEnabled(true);

    }
    public int roll()
    {
        Log.d(TAG, "roll called ");
        Random random = new Random();
        value=random.nextInt(5)+1;
        Log.d("Dice Value",""+value);
        ImageView dimage=(ImageView)findViewById(R.id.image);
        if (value==1)
        {
            dimage.setImageResource(R.drawable.dice1);
        }
        if (value==2)
        {
            dimage.setImageResource(R.drawable.dice2);
        }
        if (value==3)
        {
            dimage.setImageResource(R.drawable.dice3);
        }
        if (value==4)
        {
            dimage.setImageResource(R.drawable.dice4);
        }
        if (value==5)
        {
            dimage.setImageResource(R.drawable.dice5);
        }
        if (value==6)
        {
            dimage.setImageResource(R.drawable.dice6);
        }
        return value;
    }
}

