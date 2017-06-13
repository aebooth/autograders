package anagrams;

import LetterInventory.LetterInventory;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import tools.OutputCapturer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;

/**
 * Created by Alex on 6/12/17.
 */
public class AnagramTester {
    private static List<String> dictionary;
    private static Anagrams anagrams;
    private static OutputCapturer oc;

    @Rule
    public ExpectedException e = ExpectedException.none();

    @BeforeClass
    public static void initialize() {
        dictionary = new ArrayList<>();
        dictionary.addAll(Arrays.asList("a", "aa", "aaa", "aaaa"));
        anagrams = new Anagrams(dictionary);
        oc = new OutputCapturer();
    }

    @Test
    public void testInvalidMaxValueException() {
        e.expect(IllegalArgumentException.class);
        anagrams.print("a", -1);
    }

    @Test
    public void testMaxIsZero() {
        oc.start();
        anagrams.print("aaaa", 999);
        oc.stop();
        String hack = oc.toString();
        oc.reset();
        oc.start();
        anagrams.print("aaaa", 0);
        oc.stop();
        String correct = oc.toString();
        assertEquals("an unnecessarily large max number of words should have the same output as max = 0", correct, hack);
    }

    @Test
    public void testWhetherPrintedValuesAreAnagrams() {
        oc.start();
        anagrams.print("aaaa", 999);
        oc.stop();
        String[] res = oc.toString().split("\n");
        Pattern p;
        p = Pattern.compile("\\[(.*)\\]");
        try {
            for (String line : res) {
                Matcher m = p.matcher(line);
                m.find();
                String[] words = m.group(1).split(",");
                StringBuilder target = new StringBuilder();
                for (String word : words) {
                    target.append(word);
                }
                assertEquals("Every line should have the same LetterInventory as the text it was an anagram of",
                        new LetterInventory("aaaa").toString(), new LetterInventory(target.toString()).toString());
            }
            oc.reset();
        } catch (Exception e) {
            fail("your words are not printing, or are not printing correctly.");
        }
    }

    @Test
    public void testWhetherMaxLimitsResults() {
        for (int n = 1; n <= 4; n++) {
            oc.start();
            anagrams.print("aaaa", n);
            oc.stop();
            String[] res = oc.toString().split("\n");
            Pattern p = Pattern.compile("\\[(.*)\\]");
            try {
                for (String line : res) {
                    Matcher m = p.matcher(line);
                    m.find();
                    String[] words = m.group(1).split(",");
                    assertTrue("The word list should be less than or equal to max words long", words.length <= n);
                }
                oc.reset();
            }catch(Exception e){
                fail("your words are not printing, or are not printing correctly.");
            }
        }
    }
}
