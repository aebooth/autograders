package sortsearchshuffle;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

public class SearchSortShuffleTester {

    @Test
    public void testShuffle() {
        Deck inOrder = new Deck();
        Deck shuffled = new Deck();
        shuffled.shuffle();
        assertNotEquals(deckToList(inOrder), deckToList(shuffled));
    }

    @Test
    public void testSelectionSort() {
        testSorts("selection");
    }

    @Test
    public void testInsertionSort() {
        testSorts("insertion");
    }

    @Test
    public void testMergeSort() {
        testSorts("merge");
    }

    public void testSorts(String sort) {
        Deck d = new Deck();
        d.shuffle();

        switch (sort) {
            case "selection":
                d.selectionSort();
            case "insertion":
                d.insertionSort();
            case "merge":
                d.mergeSort();
        }
        ArrayList<Card> cards = deckToList(d);

        int nth = 0;
        Card ref = cards.get(0);
        for (Card card : cards) {
            if (nth > 3) {
                nth = 0;
                assertTrue(ref.compareTo(card) < 1);
                ref = card;
                if (card.getRank().value != ref.getRank().value) {
                    d.printDeck();
                    fail("Cards not in ascending order!");
                }
                nth++;
            }
        }
    }

    public static ArrayList<Card> deckToList(Deck deck) {
        ArrayList<Card> cards = new ArrayList<>();
        while (deck.hasNext()) {
            cards.add(deck.drawCard());
        }
        return cards;
    }
}
