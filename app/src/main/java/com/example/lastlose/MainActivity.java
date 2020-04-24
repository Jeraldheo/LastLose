package com.example.lastlose;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;
//TODO Improve project organization


public class MainActivity extends AppCompatActivity {
    Random rand = new Random();
    String namePlayer1;
    String namePlayer2;
    private Button play;
    private EditText player1;
    private EditText player2;
    private String totenPlayer1;
    private String totenPlayer2;
    private int turn = 0;
    private int markCount = 9;
    private Button boardButton[] = new Button[10];
    private int chancePlayer1 = 2;
    private int chancePlayer2 = 2;
    private Button passButton;
    private int chances = 10;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play =  findViewById(R.id.button);
        player1 = findViewById(R.id.editText);
        player2 = findViewById(R.id.editText2);
        boardButton[0] = findViewById(R.id.button2);

        for(int i = 1; i<8; i++)
        {
            boardButton[i] = findViewById(boardButton[0].getId() + i);
        }
        boardButton[8] = findViewById(R.id.button10);
        boardButton[9] = findViewById(R.id.button11);
        passButton = findViewById(R.id.button12);


        passButton.setVisibility(View.INVISIBLE);

        play.setOnClickListener(new View.OnClickListener()
                                {
                                    @Override
                                    public void onClick(View v)
                                    {
                                        namePlayer1 = player1.getText().toString() ;
                                        namePlayer2 = player2.getText().toString();
                                        try
                                        {
                                            totenPlayer1 = namePlayer1.substring(0,1);
                                            totenPlayer2 = namePlayer2.substring(0,1);
                                        } catch (Exception e) {
                                            totenPlayer1 = "P1";
                                            totenPlayer2 = "P2";
                                            namePlayer1 = "Player1";
                                            namePlayer2 = "Player2";
                                        }


                                        play.setClickable(false);
                                        player1.setFocusable(false);
                                        player2.setFocusable(false);



                                        turn =  rand.nextInt(2);
                                        if(turn == 0)
                                        {
                                            Toast.makeText(MainActivity.this, namePlayer1 + " goes first", Toast.LENGTH_LONG).show();
                                            chancePlayer1 = 2;
                                            chancePlayer2 = 0;
                                        }

                                        else
                                        {
                                            Toast.makeText(MainActivity.this, namePlayer2 + " goes first", Toast.LENGTH_LONG).show();
                                            chancePlayer1 = 0;
                                            chancePlayer2 = 2;
                                        }



                                    }

                                }
        );

        View.OnClickListener board = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int buttonId = v.getId();
                Button  clickedButton = findViewById(buttonId);
                passButton.setVisibility(View.INVISIBLE);
                if(chancePlayer1>0)
                {
                    clickedButton.setText(totenPlayer1);
                    chancePlayer1--;
                    if(chancePlayer1==1)
                    {
                        passButton.setVisibility(View.VISIBLE);
                        chancePlayer2 = 2;
                        turn = 0;
                    }
                }
                else
                {
                    clickedButton.setText(totenPlayer2);
                    chancePlayer2--;
                    if(chancePlayer2==1)
                    {
                        passButton.setVisibility(View.VISIBLE);
                        turn = 1;
                    }
                    if(chancePlayer2 == 0)
                    {
                        chancePlayer1 = 2;
                        chancePlayer2 = 2;
                    }


                }
                chances--;
                checkWinner();
            }
        };

        for(int i = 0; i<10; i++)
        {
            boardButton[i].setOnClickListener(board);
        }

        View.OnClickListener passButtonListener = new View.OnClickListener()
        {
          @Override
          public void onClick(View v)
          {

              if(turn == 0)
              {
                  chancePlayer1 = 0;
                  chancePlayer2 = 2;
              }
              else
              {
                  chancePlayer1 = 2;
                  chancePlayer2 = 0;
              }
              v.setVisibility(View.INVISIBLE);
          }
        };

        passButton.setOnClickListener(passButtonListener);

    }

    private void checkWinner() {
        if(chances<2)
        {
            if(turn == 0)
            {
                Toast.makeText(MainActivity.this, namePlayer1 + " Wins", Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(MainActivity.this, namePlayer2 + " Wins", Toast.LENGTH_LONG).show();
            }
        }
    }


}
