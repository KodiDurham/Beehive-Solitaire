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

    public void displayBoard(){
        ImageView fg11=findViewById(R.id.iv_fg_11);
        ImageView fg12=findViewById(R.id.iv_fg_12);
        ImageView fg13=findViewById(R.id.iv_fg_13);
        ImageView fg21=findViewById(R.id.iv_fg_21);
        ImageView fg22=findViewById(R.id.iv_fg_22);
        ImageView fg23=findViewById(R.id.iv_fg_23);

        fg11.setImageResource(flowerG11.getCardFace(flowerG11.getTopCard()));
        fg12.setImageResource(flowerG11.getCardFace(flowerG12.getTopCard()));
        fg13.setImageResource(flowerG11.getCardFace(flowerG13.getTopCard()));
        fg21.setImageResource(flowerG11.getCardFace(flowerG21.getTopCard()));
        fg22.setImageResource(flowerG11.getCardFace(flowerG22.getTopCard()));
        fg23.setImageResource(flowerG11.getCardFace(flowerG23.getTopCard()));
    }

    private void moveCard(CardPile to, CardPile from){
        to.addCard(from.removeCard());
    }
}
