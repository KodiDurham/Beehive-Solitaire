package com.example.beehivesolitaire;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

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
        moveCard(flowerG11, beehive);
        moveCard(flowerG12, beehive);
        moveCard(flowerG13, beehive);
        moveCard(flowerG21, beehive);
        moveCard(flowerG22, beehive);
        moveCard(flowerG23, beehive);

        displayBoard();

        flowerG11.setImageViewClick(fg11,this);
        flowerG12.setImageViewClick(fg12,this);
        flowerG13.setImageViewClick(fg13,this);
        flowerG21.setImageViewClick(fg21,this);
        flowerG22.setImageViewClick(fg22,this);
        flowerG23.setImageViewClick(fg23,this);
        beehive.setImageViewClick(beehiveIv,this);
        working.setImageViewClick(workingIv,this);
    }

    public void pileClicked(CardPile pile){
        pileMatchCheck(flowerG11,pile);
        pileMatchCheck(flowerG12,pile);
        pileMatchCheck(flowerG13,pile);
        pileMatchCheck(flowerG21,pile);
        pileMatchCheck(flowerG22,pile);
        pileMatchCheck(flowerG23,pile);

    }

    public void pileMatchCheck(CardPile to, CardPile from){
        if(from.getSize() > 0){
            if(!(from==to)&& to.getSize()>0){
                if(from.getTopCard()%13==to.getTopCard()%13){
                    moveCard(to,from);
                    //check if to is full
                    //check if there is open gardens spots and move from beehive to and from
                    //update piles
                    displayBoard();
                    //more stuff
                }
            }
        }
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
}
