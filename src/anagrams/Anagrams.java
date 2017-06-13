package anagrams;

import LetterInventory.LetterInventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Alex on 6/11/17.
 */
public class Anagrams {
    private HashMap<String, LetterInventory> inventories;

    public Anagrams(List<String> dictionary){
        inventories = new HashMap<String, LetterInventory>();
        for(String word:dictionary){
            inventories.put( word, new LetterInventory(word));
        }
    }

    public void print(String text, int max){
        if(max < 0){
            throw new IllegalArgumentException("You need at least one word");
        }
        LetterInventory original = new LetterInventory(text);
        List<String> dictionary = new ArrayList<>();
        for(String key:inventories.keySet()){
            if(original.subtract(new LetterInventory(key)) != null){
                dictionary.add(key);
            }
        }
        max = (max == 0)? -1 : max;
        getAnagrams(original,dictionary,new ArrayList<String>(),max);
    }

    private void getAnagrams(LetterInventory current, List<String> dictionary, List<String> words, int max){
        if(current == null || !current.isEmpty() && max == 0) return;
        if(current.isEmpty()){
            System.out.println(words);
        }else {
            for (String word : dictionary) {
                if (current.subtract(inventories.get(word)) != null) {
                    words.add(word);
                    getAnagrams(current.subtract(inventories.get(word)), dictionary, words, max-1);
                    words.remove(words.size() - 1);
                }
            }
        }
    }
}
