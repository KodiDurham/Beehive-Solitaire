package com.example.beehivesolitaire;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void testAddCardToPile(){
        CardPile test = new CardPile();
        test.addCard(1);
        test.addCard(2);
        test.addCard(3);
        test.addCard(4);

        assert(test.hasCard(1));
        assert(test.hasCard(2));
        assert(test.hasCard(3));
        assert(test.hasCard(4));
    }

    @Test
    public void testShuffleHas(){
        CardPile test = new CardPile();
        test.addCard(1);
        test.addCard(2);
        test.addCard(3);
        test.addCard(4);

        test.shuffle();

        assert(test.hasCard(1));
        assert(test.hasCard(2));
        assert(test.hasCard(3));
        assert(test.hasCard(4));

        test.shuffle();

        assert(test.hasCard(1));
        assert(test.hasCard(2));
        assert(test.hasCard(3));
        assert(test.hasCard(4));

        test.shuffle();

        assert(test.hasCard(1));
        assert(test.hasCard(2));
        assert(test.hasCard(3));
        assert(test.hasCard(4));
    }

    @Test
    public void testRemoveTop(){
        CardPile test = new CardPile();
        test.addCard(1);
        test.addCard(2);
        test.addCard(3);
        test.addCard(4);

        test.removeCard();
        assert(!test.hasCard(4));
        test.removeCard();
        assert(!test.hasCard(3));
        test.removeCard();
        assert(!test.hasCard(2));
        test.removeCard();
        assert(!test.hasCard(1));

    }
}