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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



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
    }

    private void displayBoard(){
        ImageView fg11=findViewById(R.id.iv_fg_11);
        ImageView fg12=findViewById(R.id.iv_fg_12);
        ImageView fg13=findViewById(R.id.iv_fg_13);
        ImageView fg21=findViewById(R.id.iv_fg_21);
        ImageView fg22=findViewById(R.id.iv_fg_22);
        ImageView fg23=findViewById(R.id.iv_fg_23);
        ImageView deckIv=findViewById(R.id.iv_deck);
        ImageView beehiveIv=findViewById(R.id.iv_beehive);
        ImageView workingIv=findViewById(R.id.iv_working);

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
