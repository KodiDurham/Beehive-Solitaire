/*
    Author: Kodi Durham

    Course: CSC 309

    Date: Oct. 22, 2019

    Class: Card Pile

    Purpose: For the user to play beehive solitaire needing 6 garden piles, working pile that goes
        through deck, deck, and a beehive. the goal is to match cards based on face once you hae one
        of each suit you can remove the pile and file with another face value. Once you there are no
        cards left he user wins. if the user gets stuck and can't play anything they lose.

    Class Purpose: The purpose is to organize the piles of cards in easy stacks and and to only
        allow proper operations that is needed for the pile of cards and to simply code for the main
        activity.
*/

package com.example.beehivesolitaire;

import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CardPile {
    //set up the list of cards
    private List<Integer> pile = new ArrayList<Integer>();
    //set up the imageView of the pile
    private ImageView myImageView;

    //set all the cards faces in order to reference
    protected static int[] cardFaceImages = { R.drawable.c_ac,  R.drawable.c_2c, R.drawable.c_3c,
            R.drawable.c_4c, R.drawable.c_5c, R.drawable.c_6c, R.drawable.c_7c, R.drawable.c_8c,
            R.drawable.c_9c, R.drawable.c_tc, R.drawable.c_jc, R.drawable.c_qc, R.drawable.c_kc,
            R.drawable.c_as, R.drawable.c_2s, R.drawable.c_3s, R.drawable.c_4s, R.drawable.c_5s,
            R.drawable.c_6s, R.drawable.c_7s, R.drawable.c_8s, R.drawable.c_9s, R.drawable.c_ts,
            R.drawable.c_js, R.drawable.c_qs, R.drawable.c_ks, R.drawable.c_ah, R.drawable.c_2h,
            R.drawable.c_3h, R.drawable.c_4h, R.drawable.c_5h, R.drawable.c_6h, R.drawable.c_7h,
            R.drawable.c_8h, R.drawable.c_9h, R.drawable.c_th, R.drawable.c_jh, R.drawable.c_qh,
            R.drawable.c_kh, R.drawable.c_ad, R.drawable.c_2d, R.drawable.c_3d, R.drawable.c_4d,
            R.drawable.c_5d, R.drawable.c_6d, R.drawable.c_7d, R.drawable.c_8d, R.drawable.c_9d,
            R.drawable.c_td, R.drawable.c_jd, R.drawable.c_qd, R.drawable.c_kd};

    //gets the top card of the pile (not removing)
    public int getTopCard(){
        return pile.get(pile.size()-1);
    }

    //gets the size of the pile
    public int getSize(){
        return pile.size();
    }

    //a generic functions to access the drawable
    public int getCardFace(int index){
        return cardFaceImages[index];
    }

    //sets the click options for the pile
    public void setImageViewClick(ImageView view, MainActivity mAct){
        myImageView=view;
        final MainActivity mainActivity = mAct;
        myImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.pileClicked(CardPile.this);
            }
        });

    }

    //adds a card(int value) into the pile
    public void addCard(int cardNum){
        pile.add(cardNum);
    }

    //removes top card from the pile
    public int removeCard(){
        return pile.remove(pile.size()-1);
    }

    //shuffles the pile
    public void shuffle(){
        Random ranGen = new Random();
        for(int i=0;i<pile.size();i++){
            int ranNum= ranGen.nextInt(pile.size());
            int temp=pile.get(ranNum);
            pile.set( ranNum, pile.get(i));
            pile.set( i, temp);
        }
    }

    //empties the pile of cards
    public void clear(){
        pile.clear();
    }

    //check if it has a certain card
    public boolean hasCard(int num){
        for (int i = 0;i<pile.size();i++)
            if(pile.get(i)==num)
                return true;
         return false;
    }

}
