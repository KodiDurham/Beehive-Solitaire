/*
    Author: Kodi Durham

    Course: CSC 309

    Date: Oct. 22, 2019

    Class: Main Activity

    Purpose: For the user to play beehive solitaire needing 6 garden piles, working pile that goes
        through deck, deck, and a beehive. the goal is to match cards based on face once you hae one
        of each suit you can remove the pile and file with another face value. Once you there are no
        cards left he user wins. if the user gets stuck and can't play anything they lose.

    Class Purpose: This class is the main activity and sets up the board is where all the actions in
        the game are taken it check win conditions and most of the program.
*/

package com.example.beehivesolitaire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //set up variables for suits and counting suits
    private int numOfSuits=4;
    private int numInSuits=13;

    //create all card piles
    //create deck
    CardPile deck= new CardPile();

    //create beehive
    CardPile beehive = new CardPile();

    //create working pile
    CardPile working = new CardPile();

    //create card piles
    CardPile flowerG11=new CardPile();
    CardPile flowerG12=new CardPile();
    CardPile flowerG13=new CardPile();
    CardPile flowerG21=new CardPile();
    CardPile flowerG22=new CardPile();
    CardPile flowerG23=new CardPile();


    //set up the image views that need to be changed
    ImageView fg11;
    ImageView fg12;
    ImageView fg13;
    ImageView fg21;
    ImageView fg22;
    ImageView fg23;
    ImageView deckIv;
    ImageView beehiveIv;
    ImageView workingIv;

    //variable to test lose condition
    int deckThrough=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get the image views from the activity
        fg11 = findViewById(R.id.iv_fg_11);
        fg12=findViewById(R.id.iv_fg_12);
        fg13=findViewById(R.id.iv_fg_13);
        fg21=findViewById(R.id.iv_fg_21);
        fg22=findViewById(R.id.iv_fg_22);
        fg23=findViewById(R.id.iv_fg_23);
        deckIv=findViewById(R.id.iv_deck);
        beehiveIv=findViewById(R.id.iv_beehive);
        workingIv=findViewById(R.id.iv_working);

        //set up the new game button as a variable
        Button newGameBnt=findViewById(R.id.bnt_newGame);

        //sets up the listeners for the piles
        flowerG11.setImageViewClick(fg11,this);
        flowerG12.setImageViewClick(fg12,this);
        flowerG13.setImageViewClick(fg13,this);
        flowerG21.setImageViewClick(fg21,this);
        flowerG22.setImageViewClick(fg22,this);
        flowerG23.setImageViewClick(fg23,this);
        beehive.setImageViewClick(beehiveIv,this);
        working.setImageViewClick(workingIv,this);

        //set up the listener for the new game button
        newGameBnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newGame();
            }
        });

        //populate deck
        int numOfCards= numInSuits*numOfSuits;
        for (int i = 0; i<numOfCards;i++)
            deck.addCard(i);

        //shuffle deck
        deck.shuffle();

        //cut top 10 from deck to beehive
        for (int i =0;i<10;i++)
            moveCard(beehive, deck);


        //deal each pile of the flower garden a card from hive
        moveCard(flowerG11, deck);
        moveCard(flowerG12, deck);
        moveCard(flowerG13, deck);
        moveCard(flowerG21, deck);
        moveCard(flowerG22, deck);
        moveCard(flowerG23, deck);

        //check if there is going to need a working pile and populate it ifthere is
        if(!checkBoard()){
            if(working.getSize()<1 && deck.getSize()>0) {
                for (int i = 0; i < 3; i++)
                    if (deck.getSize() > 0)
                        moveCard(working, deck);
            }

        }

        //update the board
        displayBoard();
    }

    //take all the actions needed to move cards from piles
    public void progressBoard(CardPile to, CardPile from){
        //check if its from beehive or working pile and moves cards based on that
        if (from==beehive||from==working)
            moveCard(to,from);
        else
            while(from.getSize()>0)
                moveCard(to,from);

        //check if to is full
        if(to.getSize()==numOfSuits)
            to.clear();
        //check if there is open gardens spots and move from beehive to and from
        if (to.getSize()==0){
            fillGarden(to);
        }
        //makes sure the only the garden piles are filled
        if (from.getSize()==0&&!(from==beehive||from==working)){
            fillGarden(from);
        }

        //turn gone through to false
        deckThrough=0;
    }

    //update board
    private void displayBoard(){
        //set the faces for all the piles except deck
        setFace(flowerG11,fg11);
        setFace(flowerG12,fg12);
        setFace(flowerG13,fg13);
        setFace(flowerG21,fg21);
        setFace(flowerG22,fg22);
        setFace(flowerG23,fg23);
        setFace(beehive,beehiveIv);
        setFace(working,workingIv);

        //make the card back show or the background
        if (deck.getSize()==0)
            deckIv.setImageResource(R.drawable.c_background2);
        else
            deckIv.setImageResource(R.drawable.c_b);
    }

    //moves a card from one pile to another
    private void moveCard(CardPile to, CardPile from){
        to.addCard(from.removeCard());
    }

    //sets up a new game resetting everything
    private void newGame(){
        //clear all piles
        deck.clear();
        beehive.clear();
        working.clear();
        flowerG11.clear();
        flowerG12.clear();
        flowerG13.clear();
        flowerG21.clear();
        flowerG22.clear();
        flowerG23.clear();

        //populate deck
        int numOfCards= numInSuits*numOfSuits;
        for (int i = 0; i<numOfCards;i++)
            deck.addCard(i);

        //shuffle deck
        deck.shuffle();

        //cut top 10 from deck to beehive
        for (int i =0;i<10;i++)
            moveCard(beehive, deck);

        deckThrough=0;

        //deal each pile of the flower garden a card from hive
        moveCard(flowerG11, deck);
        moveCard(flowerG12, deck);
        moveCard(flowerG13, deck);
        moveCard(flowerG21, deck);
        moveCard(flowerG22, deck);
        moveCard(flowerG23, deck);

        //cehck if there are moves and to start working if needed
        if(!checkBoard()){
            if(working.getSize()<1 && deck.getSize()>0) {
                for (int i = 0; i < 3; i++)
                    if (deck.getSize() > 0)
                        moveCard(working, deck);
            }

        }
        //update board
        displayBoard();
    }

    //sets up the card faces or background if there is none
    private void setFace(CardPile pile, ImageView view){
        if(pile.getSize()==0)
            view.setImageResource(R.drawable.c_background2);
        else
            view.setImageResource(pile.getCardFace(pile.getTopCard()));
    }

    //defines what happens when the pile is clicked
    public void pileClicked(CardPile pile){
        //check if there is a match to the from pile
        if (pileMatchCheck(flowerG11,pile)){
            progressBoard(flowerG11,pile);
        }else
        if (pileMatchCheck(flowerG12,pile)){
            progressBoard(flowerG12,pile);
        }else
        if (pileMatchCheck(flowerG13,pile)){
            progressBoard(flowerG13,pile);
        }else
        if (pileMatchCheck(flowerG21,pile)){
            progressBoard(flowerG21,pile);
        }else
        if (pileMatchCheck(flowerG22,pile)){
            progressBoard(flowerG22,pile);
        }else
        if (pileMatchCheck(flowerG23,pile)){
            progressBoard(flowerG23,pile);
        }else
        if(!checkBoard() && pile==working){ //to checks if working is needed and does it if is needed
            if(deck.getSize()==0) {
                while (working.getSize() > 0)
                    moveCard(deck, working);
                for (int i =0;i<3;i++)
                    if(deck.getSize()>0)
                        moveCard(working,deck);
                if(working.getSize()>0)
                    deckThrough++;
            }
            else if(pile==working){
                for (int i = 0; i < 3; i++)
                    if (deck.getSize() > 0) {
                        moveCard(working, deck);
                    }
            }
        }
        //update board
        displayBoard();

        //check win/lose
        if (deckThrough>1) {
            Context context = getApplicationContext();
            CharSequence text = "you lost";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        if(flowerG11.getSize()==0 && flowerG12.getSize()==0 && flowerG13.getSize()==0)
            if(flowerG21.getSize()==0 && flowerG22.getSize()==0 && flowerG23.getSize()==0){
                Context context = getApplicationContext();
                CharSequence text = "you win";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
    }

    //fill the garden piles
    public void fillGarden(CardPile pile){
        //check which pile to take from and fill pile if needed to fill garden
        if(beehive.getSize()<1){
            if(working.getSize()<1){
                for (int i= 0; i<3;i++)
                    if (deck.getSize()>0)
                        moveCard(working,deck);
            }
            if (working.getSize()>0)
                moveCard(pile,working);

        }else
            moveCard(pile,beehive);
    }

    //checks if the files have a matching card on top
    public boolean pileMatchCheck(CardPile to, CardPile from){
        if(from.getSize() > 0)
            if(!(from==to)&& to.getSize()>0)
                return from.getTopCard() % numInSuits == to.getTopCard() % numInSuits;

        return false;
    }

    //checks if there is  a card move possible
    public boolean checkBoard(){
        if(hasSolution(beehive)|| hasSolution(flowerG11)|| hasSolution(flowerG12)|| hasSolution(flowerG13))
            return true;

        return hasSolution(flowerG21) || hasSolution(flowerG22) || hasSolution(flowerG23);
    }

    //checks if there is a match from the pile sent in any of the garden slots
    public boolean hasSolution(CardPile pile){
        if (pileMatchCheck(flowerG11,pile)||pileMatchCheck(flowerG12,pile)||pileMatchCheck(flowerG13,pile))
            return true;
        return pileMatchCheck(flowerG21, pile) || pileMatchCheck(flowerG22, pile) || pileMatchCheck(flowerG23, pile);
    }

}
