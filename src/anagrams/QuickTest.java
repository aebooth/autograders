package anagrams;

import java.util.Arrays;

/**
 * Created by Alex on 6/12/17.
 */
public class QuickTest {
    public static void main(String[] args){
        new Anagrams(Arrays.asList("a","aa","aaa","aaaa"))
                .print("aaaa",4);
    }
}
