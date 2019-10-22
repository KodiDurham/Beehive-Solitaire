package com.example.beehivesolitaire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
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


    ImageView fg11;
    ImageView fg12;
    ImageView fg13;
    ImageView fg21;
    ImageView fg22;
    ImageView fg23;
    ImageView deckIv;
    ImageView beehiveIv;
    ImageView workingIv;

    int deckThrough=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fg11 = findViewById(R.id.iv_fg_11);
        fg12=findViewById(R.id.iv_fg_12);
        fg13=findViewById(R.id.iv_fg_13);
         fg21=findViewById(R.id.iv_fg_21);
        fg22=findViewById(R.id.iv_fg_22);
        fg23=findViewById(R.id.iv_fg_23);
        deckIv=findViewById(R.id.iv_deck);
        beehiveIv=findViewById(R.id.iv_beehive);
        workingIv=findViewById(R.id.iv_working);
        Button newGameBnt=findViewById(R.id.bnt_newGame);

        flowerG11.setImageViewClick(fg11,this);
        flowerG12.setImageViewClick(fg12,this);
        flowerG13.setImageViewClick(fg13,this);
        flowerG21.setImageViewClick(fg21,this);
        flowerG22.setImageViewClick(fg22,this);
        flowerG23.setImageViewClick(fg23,this);
        beehive.setImageViewClick(beehiveIv,this);
        working.setImageViewClick(workingIv,this);

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

        if(!checkBoard()){
            if(working.getSize()<1 && deck.getSize()>0) {
                for (int i = 0; i < 3; i++)
                    if (deck.getSize() > 0)
                        moveCard(working, deck);
            }

        }
        displayBoard();
    }

    public void pileClicked(CardPile pile){
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
        if(!checkBoard() && pile==working){
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

    public boolean checkBoard(){
        if(hasSolution(beehive)|| hasSolution(flowerG11)|| hasSolution(flowerG12)|| hasSolution(flowerG13))
            return true;

        if(hasSolution(flowerG21)|| hasSolution(flowerG22)|| hasSolution(flowerG23))
            return true;

        return false;
    }

    public boolean hasSolution(CardPile pile){
        if (pileMatchCheck(flowerG11,pile)||pileMatchCheck(flowerG12,pile)||pileMatchCheck(flowerG13,pile))
            return true;
        return pileMatchCheck(flowerG21, pile) || pileMatchCheck(flowerG22, pile) || pileMatchCheck(flowerG23, pile);
    }

    public void progressBoard(CardPile to, CardPile from){
        if (from==beehive||from==working)
            moveCard(to,from);
        else
            while(from.getSize()>0)
                moveCard(to,from);
        //check if to is full
        if(to.getSize()==4)
            to.clear();
        //check if there is open gardens spots and move from beehive to and from
        if (to.getSize()==0){
            fillGarden(to);
        }
        if (from.getSize()==0&&!(from==beehive||from==working)){
            fillGarden(from);
        }
        //turn gone through to false
        deckThrough=0;
    }

    public void fillGarden(CardPile pile){
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

    public boolean pileMatchCheck(CardPile to, CardPile from){
        if(from.getSize() > 0)
            if(!(from==to)&& to.getSize()>0)
                return from.getTopCard() % 13 == to.getTopCard() % 13;

        return false;
    }

    private void displayBoard(){

        setFace(flowerG11,fg11);
        setFace(flowerG12,fg12);
        setFace(flowerG13,fg13);
        setFace(flowerG21,fg21);
        setFace(flowerG22,fg22);
        setFace(flowerG23,fg23);
        setFace(beehive,beehiveIv);
        setFace(working,workingIv);

        if (deck.getSize()==0)
            deckIv.setImageResource(R.drawable.c_background2);
        else
            deckIv.setImageResource(R.drawable.c_b);
    }

    private void setFace(CardPile pile, ImageView view){
        if(pile.getSize()==0)
            view.setImageResource(R.drawable.c_background2);
        else
            view.setImageResource(pile.getCardFace(pile.getTopCard()));
    }

    private void moveCard(CardPile to, CardPile from){
        to.addCard(from.removeCard());
    }

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

        if(!checkBoard()){
            if(working.getSize()<1 && deck.getSize()>0) {
                for (int i = 0; i < 3; i++)
                    if (deck.getSize() > 0)
                        moveCard(working, deck);
            }

        }
        displayBoard();
    }
}
