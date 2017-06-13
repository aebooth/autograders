package diff;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 6/4/17.
 */
public class DiffTester {
    public static String diff(String str1, String str2) {
        StringBuilder result = new StringBuilder();
        String lcs = lCS(str1,str2);
        int ndx1 = 0;
        int ndx2 = 0;
        for(char c:lcs.toCharArray()){
            while(c != str1.charAt(ndx1)){
                result.append("-");
                ndx1++;
            }
            while(c != str2.charAt(ndx2)){
                result.append("+");
                ndx2++;
            }
            result.append("0");
            ndx1++;
            ndx2++;
        }
        while(ndx1 < str1.length()){
            result.append("-");
            ndx1++;
        }
        while(ndx2 < str2.length()){
            result.append("+");
            ndx2++;
        }
        return result.toString();
    }

    public static String lCS(String a, String b){
        for(int i = 0; i < b.length(); i++){
            if(!a.contains(b.substring(i,i+1))) {
                b = b.substring(0, i) + b.substring(i + 1);
                i--;
            }
        }
        for(int i = 0; i < a.length(); i++){
            if(!b.contains(a.substring(i,i+1))) {
                a = a.substring(0, i) + a.substring(i + 1);
                i--;
            }
        }
        if(a.length() <= 0 || b.length() <= 0){
            return "";
        }else {
            if (a.charAt(0) == b.charAt(0)) {
                return a.substring(0, 1) + lCS(a.substring(1), b.substring(1));
            } else {
                String s1p = lCS(a.substring(1), b);
                String s2p = lCS(b.substring(1), a);
                if (s1p.length() > s2p.length()) {
                    return s1p;
                } else {
                    return s2p;
                }
            }
        }
    }

    public static void main(String[] args){
        List<String> diffs = new ArrayList<>();
        //setup diff strings
        diffs.add("abcdefg");
        diffs.add("zbydxfw");

        diffs.add("abcdefg");
        diffs.add("zyxwvut");

        diffs.add("aaabbbccc");
        diffs.add("ihgfedcba");

        diffs.add("aaabbbccc");
        diffs.add("abcdefghi");

        int size = diffs.size();

        //get reference diffs
        while(size > 0){
            try {
                String mine = diff(diffs.get(0), diffs.get(1));
                String yours = DiffGenerator.diff(diffs.get(0), diffs.get(1));
                if (mine.equals(yours)) {
                    System.out.println("PASS");
                } else {
                    System.out.println("FAIL:");
                    System.out.println(" mine: " + mine);
                    System.out.println("yours: " + yours);
                }
            }catch(Exception e){
                System.out.println("ERROR: " + e.toString());
            }
            diffs.remove(0);
            diffs.remove(0);
            size -= 2;
        }

    }

}
