package com.example.beehivesolitaire;

import android.content.ClipData;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CardPile {
    private List<Integer> pile = new ArrayList<Integer>();
    private ImageView myImageView;

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


    public int getTopCard(){
        return pile.get(pile.size()-1);
    }

    public int getSecondCard(){
        return pile.get(pile.size()-2);
    }

    public int getSize(){
        return pile.size();
    }

    public void addCard(int cardNum){
        pile.add(cardNum);
    }

    public int removeCard(){
        return pile.remove(pile.size()-1);
    }

    public void shuffle(){
        Random ranGen = new Random();
        for(int i=0;i<pile.size();i++){
            int ranNum= ranGen.nextInt(pile.size());
            int temp=pile.get(ranNum);
            pile.set( ranNum, pile.get(i));
            pile.set( i, temp);
        }
    }

    public void clear(){
        pile.clear();
    }

    public boolean hasCard(int num){
        for (int i = 0;i<pile.size();i++)
            if(pile.get(i)==num)
                return true;
         return false;
    }

    public int getCardFace(int index){
        return cardFaceImages[index];
    }

    public void setImageViewClick(ImageView view, MainActivity mAct){
        myImageView=view;
        final MainActivity mainActivity = mAct;
        /*myImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.pileClicked(CardPile.this);
            }
        });*/

        myImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    mainActivity.pileDragged(CardPile.this,view);

                    return true;
                }

//                if (motionEvent.getAction()==MotionEvent.ACTION_UP){
//                    mainActivity.pileClicked(CardPile.this);
//
//
//                    return true;
//                }

                return false;
            }
        });

        myImageView.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {

                switch (dragEvent.getAction()){
                    case DragEvent.ACTION_DROP:
                        mainActivity.pileDropped((ImageView) dragEvent.getLocalState(),CardPile.this);
                }

                return true;
            }
        });

    }
}
