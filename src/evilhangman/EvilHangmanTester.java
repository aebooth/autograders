package evilhangman;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.runner.JUnitCore.runClasses;

/**
 * Created by Alex on 6/5/17.
 */
public class EvilHangmanTester {
    private HangmanManager hm;
    private static List<String> words;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @BeforeClass public static void dude(){
        try (Scanner scan = new Scanner(new File("res/dictionary-tiny.txt"))) {
            words = new ArrayList<>();
            while(scan.hasNext()) {
                words.add(scan.next().toLowerCase());
            }
        } catch (FileNotFoundException e){
            fail("Test suite dictionary is missing");
        }
    }

    @Test
    public void testLengthInSetup() throws IllegalArgumentException {
        thrown.expect(IllegalArgumentException.class);
        hm = new HangmanManager(words, 0, 0);
    }

    @Test
    public void testMaxInSetup() throws IllegalArgumentException {
        thrown.expect(IllegalArgumentException.class);
        hm = new HangmanManager(words, 1, -1);
    }

    @Test
    public void testEmptyListExceptions() throws IllegalStateException {
        //Empty List
        thrown.expect(IllegalStateException.class);
        hm = new HangmanManager(new ArrayList<String>(), 2, 1);
        hm.record('a');
    }

    @Test
    public void testNoGuessesLeftException() throws IllegalStateException {
        //No Guesses Left
        hm = new HangmanManager(words,4,0);
        thrown.expect(IllegalStateException.class);
        hm.record('a');
    }

    @Test
    public void testSameGuessTwiceException() throws IllegalArgumentException{
        //Same guess twice
        hm = new HangmanManager(words,4,4);
        thrown.expect(IllegalArgumentException.class);
        hm.record('a');
        hm.record('a');
    }

    @Test
    public void testInitialization(){
        List<String> words = Arrays.asList("fox","fax","box","and","but","hey");
        Set<String> wordsSet = new HashSet<String>(words);
        HangmanManager hm = new HangmanManager(words,3,3);
        assertEquals("All words should still be considered...",wordsSet,hm.words());
        assertEquals("pattern should just be a number of dashes equivalent to length...","---",hm.pattern());
        assertEquals("the number of remaining guesses should be what you set....",3,hm.guessesLeft());
        assertTrue("your guesses set should be empty....",hm.guesses().isEmpty());
    }

    @Test
    public void testRecordOnWrongGuess(){
        hm = new HangmanManager(words, 4, 3);
        hm.record('a');
        String pattern = hm.pattern();
        int size = hm.words().size();
        int numGuesses = hm.guessesLeft();
        hm.record('z');
        assertTrue("the guesses set should contain the most recent guess",hm.guesses().contains('z'));
        assertEquals("the pattern should not change with a letter not in any word", pattern, hm.pattern());
        assertEquals("the possible words set should stay the same size as before", size, hm.words().size());
        assertEquals("the number of guesses should be one less than before",numGuesses - 1,hm.guessesLeft());
    }

    @Test
    public void testWordSetShrinkProcess(){
        List<String> testWords = new ArrayList<>();
        testWords.addAll(Arrays.asList("a","ab","abc","abd","abe","acb","adb","aeb","azb","abcd","abcde"));
        List<String> filteredTestWords = new ArrayList<>();
        filteredTestWords.addAll(Arrays.asList("abc","abd","abe","acb","adb","aeb","azb"));
        hm = new HangmanManager(testWords,3,100);
        boolean passingWordSet = hm.words().containsAll(testWords) || hm.words().containsAll(filteredTestWords);
        assertTrue("words set should contain all words in original list",passingWordSet);
        hm.record('a');
        testWords.removeAll(Arrays.asList("a","ab","abcd","abcde"));
        assertTrue("word set should only contain words of the right length and pattern",hm.words().containsAll(testWords));
        hm.record('b');
        assertTrue("word set should be largest possible given the current guesses", hm.words().containsAll(Arrays.asList("acb","adb","aeb","azb")));
        hm.record('c');
        hm.record('d');
        hm.record('e');
        assertTrue("words set should be able to trim down to one possibility",
                hm.words().contains("azb") && hm.words().size() == 1);
    }

    @Test
    public void testRecordOnRightGuess(){
        hm = new HangmanManager(Arrays.asList("abcd","acde","adef","aefg"), 4, 3);
        String pattern = hm.pattern();
        hm.record('a');
        int size = hm.words().size();
        int numGuesses = hm.guessesLeft();
        assertTrue("the new pattern should contain the letter guessed",hm.pattern().contains("a"));
        assertTrue("the guesses set should contain the most recent guess",hm.guesses().contains('a'));
        assertTrue("the possible words set should have the same number or less words in it", hm.words().size() <= size);
        assertEquals("the number of guesses should be the same as before",numGuesses,hm.guessesLeft());
    }

    public static void main(String[] args){
        runClasses(EvilHangmanTester.class);
    }


}

