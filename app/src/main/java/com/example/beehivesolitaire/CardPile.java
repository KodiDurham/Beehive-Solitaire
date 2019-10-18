package com.example.beehivesolitaire;

import java.util.List;

public class CardPile {
    protected List<Integer> pile;

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

    public void addCard(int cardNum){
        pile.add(cardNum);
    }

    public int removeCard(){
        return pile.remove(pile.size());
    }

    public void shuffle(){
        
    }
}
