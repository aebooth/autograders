package evilhangman;

import java.util.*;

/**
 * Created by Alex on 6/5/17.
 */
public class HangmanManager {
    private Set<String> words;
    private int numGuesses;
    private int length;
    private String pattern;
    private Set<Character> guesses;

    public HangmanManager(List<String> dictionary, int length, int max) {
        if (length < 1) {
            throw new IllegalArgumentException("Word must be at least 1 letter long.");
        } else if (max < 0) {
            throw new IllegalArgumentException("Can't have a negatvie number of guesses.");
        }
        words = new TreeSet<>(dictionary);
        guesses = new TreeSet<>();
        numGuesses = max;
        this.length = length;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < length; i++){
            sb.append("-");
        }
        pattern = sb.toString();
    }

    public Set<String> words() {
        return this.words;
    }

    public int guessesLeft() {
        return this.numGuesses;
    }

    public Set<Character> guesses(){
        return this.guesses;
    }

    public String pattern() {
        if (this.words.isEmpty()) {
            throw new IllegalStateException("No words left that match that pattern.");
        }
        return pattern;
    }

    public int record(char guess) {
        if (numGuesses < 1 || words.isEmpty()) {
            throw new IllegalStateException("No guesses or guessable words left in game.");
        } else if (!words.isEmpty() && guesses.contains(guess)) {
            throw new IllegalArgumentException("This letter has already been guessed.");
        }
        guesses.add(guess);
        Map<String, Set<String>> possibles = new TreeMap<>();
        for (String word : words) {
            String currPattern = determinePattern(word, this.guesses);
            if (possibles.get(currPattern) != null) {
                possibles.get(currPattern).add(word);
            } else {
                String pattern = determinePattern(word, this.guesses);
                possibles.put(pattern, new TreeSet<String>());
                possibles.get(pattern).add(word);
            }
        }
        int maxWords = 0;
        String maxPattern = pattern;
        for (String key : possibles.keySet()) {
            if (maxWords < possibles.get(key).size()) {
                maxPattern = key;
                maxWords = possibles.get(key).size();
            }
        }
        this.pattern = maxPattern;
        this.words = possibles.get(this.pattern);
        int numChars = countCharsInPattern(this.pattern,guess);
        if(numChars == 0){
            numGuesses--;
        }
        return numChars;
    }

    private static int countCharsInPattern(String word, char guess) {
        int count = 0;
        int start = 0;
        String dummy = word;
        while (dummy.indexOf(guess) != -1) {
            int letterNdx = dummy.indexOf(guess);
            start += letterNdx;
            count++;
            dummy = dummy.substring(letterNdx + 1);
        }
        return count;
    }

    private static String determinePattern(String word, Set<Character> guesses) {
        char[] res = new char[word.length()];
        for (int i = 0; i < res.length; i++) {
            res[i] = '-';
        }
        for (char letter : guesses) {
            int start = 0;
            while (start < word.length() && word.indexOf(letter,start) != -1) {
                start = word.indexOf(letter,start);
                res[start] = letter;
                start++;
            }
        }
        StringBuilder resString = new StringBuilder();
        for (char c : res) {
            resString.append(c);
        }
        return resString.toString();
    }


}
