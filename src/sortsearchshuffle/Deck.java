/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sortsearchshuffle;

import java.util.Random;

/**
 *
 * @author alexb
 */
public class Deck {

    private Card[] cards;
    private int current;
    private int numSets;

    public Deck(int numSets) {
        this.numSets = numSets;
        this.current = 0;
        this.cards = new Card[this.numSets * 52];
        int index = 0;
        for (int set = 0; set < this.numSets; set++) {
            for (Card.Suit suit : Card.Suit.values()) {
                for (Card.Rank rank : Card.Rank.values()) {
                    this.cards[index] = new Card(rank, suit, index);
                    index++;
                }
            }
        }
    }

    public Deck() {
        this(1);
    }

    public Deck shuffle() {
        //TODO:Shuffles the order of the cards in this.cards
        //TODO:Also re-introduces all "drawn" cards back into the deck
        Random r = new Random();
        Card dummy;
        for (int i = this.cards.length - 1; i > 0; i--) {
            int next = r.nextInt(i + 1);
            dummy = this.cards[i];
            this.cards[i] = this.cards[next];
            this.cards[next] = dummy;
        }
        return this;
    }

    public Card drawCard() {
        //TODO:Draws the "top" card off the deck
        if (hasNext()) {
            Card result = this.cards[current];
            current++;
            return result;
        } else {
            //In general don't do this!!
            return null;
        }
    }

    public boolean hasNext() {
        //TODO:Checks if there are any more cards to draw
        return this.current < 52;
    }

    public Deck printDeck() {
        for (Card card : this.cards) {
            System.out.println(card);
        }
        return this;
    }

    public int bruteForceSearch(Card card) {
        int i = 0;
        for (Card next : cards) {
            if (card.equals(next)) {
                return i;
            } else {
                i++;
            }
        }
        return -1;
    }

    public int binarySearch(Card card) {
        int left = 0;
        int right = cards.length - 1;
        int pivot = cards.length/2;
        
        while (left != pivot && right!= pivot) { 
            if(cards[pivot].equals(card)){
                return pivot;
            }
            
            if(card.compareTo(cards[pivot]) < 0){
                right = pivot;
                pivot = pivot -(pivot-left)/2;
            }else{
                left = pivot;
                pivot = (right-pivot)/2 + pivot;
            }
        }
        if(cards[left].equals(card))return left;
        else if(cards[right].equals(card))return right;
        else return -1;
    }

    private void swap(int ndx1, int ndx2) {
        Card temp = this.cards[ndx1];
        this.cards[ndx1] = this.cards[ndx2];
        this.cards[ndx2] = temp;
    }

    public void selectionSort() {
        int smallest = 0;
        for (int i = 0; i < cards.length; i++) {
            smallest = i;
            for (int j = i + 1; j < cards.length; j++) {
                if (cards[smallest].compareTo(cards[j]) > 0) {
                    smallest = j;
                }
            }
            swap(i, smallest);
        }
    }

    public void insertionSort() {
        int current = 0;
        for (int i = 1; i < cards.length; i++) {
            current = i;
            while (current >= 1 && cards[current].compareTo(cards[current - 1]) < 0) {
                swap(current - 1, current);
                current--;
            }
        }
    }

    public Deck mergeSort() {

        this.cards = mergeSortR(this.cards);
        return this;
    }

    private Card[] mergeSortR(Card[] cards) {
        //Stops infinite loops
        //based on idea that list of 1 or less things is already sorted
        if (cards.length <= 1) {
            return cards;
        }

        //Splitting lists
        Card[] left = new Card[cards.length / 2];
        for (int i = 0; i < cards.length / 2; i++) {
            left[i] = cards[i];
        }
        Card[] right = new Card[cards.length - left.length];
        for (int i = left.length; i < cards.length; i++) {
            right[i - left.length] = cards[i];
        }

        //Sorting lists
        left = mergeSortR(left);
        right = mergeSortR(right);

        //Combining lists
        Card[] result = new Card[left.length + right.length];

        int lmark = 0, rmark = 0, newmark = 0;

        //combine until at least one list all gone
        while (lmark < left.length && rmark < right.length) {
            if (left[lmark].compareTo(right[rmark]) < 0) {
                result[newmark] = left[lmark];
                lmark++;
            } else {
                result[newmark] = right[rmark];
                rmark++;
            }
            newmark++;
        }
        //select remaining list and add it to back of result
        Card[] remaining;
        int endx;
        if (lmark < left.length) {
            remaining = left;
            endx = lmark;
        } else {
            remaining = right;
            endx = rmark;
        }

        while (newmark < result.length) {
            result[newmark] = remaining[endx];
            newmark++;
            endx++;
        }
        return result;
    }
}
