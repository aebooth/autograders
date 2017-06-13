/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assassin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import tools.OutputCapturer;

/**
 *
 * @author Alex
 */
public class AssassinTester {
    private OutputCapturer oc;
    private AssassinManager am;
    private static List<String> names;
    
    public AssassinTester(){
        this.names = new ArrayList<>();
        this.names.addAll(Arrays.asList("Alex","Kristine","Robert","Anna","William","Graham","Elizabeth"));
        this.am = new AssassinManager(names);
        this.oc = new OutputCapturer();
    }
 
    public AssassinManager getAssassinManager(){
        return am;
    }
    
    public void testCapture(){
       oc.start();
       System.out.println("Testing, testing.....");
       System.out.println("Do multiple print statements work?");
       oc.stop();
       System.out.println(oc);
       oc.reset();
    }
    
    public boolean testPrint(String target, Runnable printingMethod){
        if(target == null) return oc.noOutputProduced();
        oc.start();
        printingMethod.run();
        oc.stop();
        
        String result = oc.toString();
        oc.reset();
        return result.equals(target);
    }
    
    public static void main(String[] args){
        AssassinTester test = new AssassinTester();
        AssassinManager am = test.getAssassinManager();
        boolean fail;
        double points = 0;
        double total = 0;
        double score = 0;
        
        
        System.out.println("Testing Beginning of Game:");
        System.out.println();
        
        StringBuilder kills = new StringBuilder();
        kills.append("    Alex is stalking Kristine\n");
        kills.append("    Kristine is stalking Robert\n");
        kills.append("    Robert is stalking Anna\n");
        kills.append("    Anna is stalking William\n");
        kills.append("    William is stalking Graham\n");
        kills.append("    Graham is stalking Elizabeth\n");
        kills.append("    Elizabeth is stalking Alex\n");
        
        System.out.print("printKillRing() should print all people in the assassin game.... ");
        fail = !test.testPrint(kills.toString(),am::printKillRing);
        System.out.println(fail?"FAIL":"PASS");
        if(!fail)points++;
        total++;
        
        System.out.print("printGraveyard() should produce no output.... ");
        fail = !test.testPrint(null,am::printGraveyard);
        System.out.println(fail?"FAIL":"PASS");
        if(!fail)points++;
        total++;
        
        fail = false;
        System.out.print("killRingContains() should be true for all names in test.... ");
        for(String s:names){
            if(!am.killRingContains(s)){
                fail = true;
                break;
            }
        }
        System.out.println(fail?"FAIL":"PASS");
        if(!fail)points++;
        total++;
        
        fail = false;
        System.out.print("graveyardContains() should be false for all names in test.... ");
        for(String s:names){
            if(am.graveyardContains(s)){
                fail = true;
                break;
            }
        }
        System.out.println(fail?"FAIL":"PASS");
        if(!fail)points++;
        total++;
        
        System.out.print("isGameOver() should be false.... ");
        fail = am.isGameOver();
        System.out.println(fail?"FAIL":"PASS");
        if(!fail)points++;
        total++;
        
        System.out.print("winner() should return null.... ");
        fail = am.winner() != null;
        System.out.println(fail?"FAIL":"PASS");
        if(!fail)points++;
        total++;
        
        
        
        kills = new StringBuilder();
        kills.append("    Kristine is stalking Robert\n");
        kills.append("    Robert is stalking William\n");
        kills.append("    William is stalking Elizabeth\n");
        kills.append("    Elizabeth is stalking Kristine\n");
        
        StringBuilder graves = new StringBuilder();
        graves.append("    Graham was killed by William\n");
        graves.append("    Anna was killed by Robert\n");
        graves.append("    Alex was killed by Elizabeth\n");
        
        System.out.println();
        System.out.println("Testing Middle of Game:");
        System.out.println();
        
        fail = false;
        System.out.print("kill() should work once for few valid names in test.... ");
        for(String s:Arrays.asList("Alex","Anna","Graham")){
          try{
              am.kill(s);
          }catch(Exception e){
              fail = true;
          }
        }
        System.out.println(fail?"FAIL":"PASS");
        if(!fail)points++;
        total++;
        
        System.out.print("kill() should throw an exception for invalid names or repeated names in test.... ");
        fail = false;
        for(String s:Arrays.asList("Bobby","Anna")){
          try{
              am.kill(s);
          }catch(Exception e){
              continue;
          }
          fail = true;
          break;
        }
        System.out.println(fail?"FAIL":"PASS");
        if(!fail)points++;
        total++;
        
        System.out.print("printKillRing() should print all but those killed with modified kill assignments.... ");
        fail = !test.testPrint(kills.toString(),am::printKillRing);
        System.out.println(fail?"FAIL":"PASS");
        if(!fail)points++;
        total++;
        
        System.out.print("printGraveyard() should print all those who were killed.... ");
        fail = !test.testPrint(graves.toString(),am::printGraveyard);
        System.out.println(fail?"FAIL":"PASS");
        if(!fail)points++;
        total++;
        
        fail = false;
        System.out.print("killRingContains() should be true for all remaining names in test.... ");
        for(String s:Arrays.asList("Kristine","Robert","William","Elizabeth")){
            if(!am.killRingContains(s)){
                fail = true;
                break;
            }
        }
        System.out.println(fail?"FAIL":"PASS");
        if(!fail)points++;
        total++;
        
        fail = false;
        System.out.print("killRingContains() should be false for all dead names in test.... ");
        for(String s:Arrays.asList("Alex","Anna","Graham")){
            if(am.killRingContains(s)){
                fail = true;
                break;
            }
        }
        System.out.println(fail?"FAIL":"PASS");
        if(!fail)points++;
        total++;
        
        fail = false;
        System.out.print("graveyardContains() should be true for all dead names in test.... ");
        for(String s:Arrays.asList("Alex","Anna","Graham")){
            if(!am.graveyardContains(s)){
                fail = true;
                break;
            }
        }
        System.out.println(fail?"FAIL":"PASS");
        if(!fail)points++;
        total++;
        
        fail = false;
        System.out.print("graveyardContains() should be false for all remaining in test.... ");
        for(String s:Arrays.asList("Kristine","Robert","William","Elizabeth")){
            if(am.graveyardContains(s)){
                fail = true;
                break;
            }
        }
        System.out.println(fail?"FAIL":"PASS");
        if(!fail)points++;
        total++;
        
        System.out.print("isGameOver() should be false.... ");
        fail = am.isGameOver();
        System.out.println(fail?"FAIL":"PASS");
        if(!fail)points++;
        total++;
        
        System.out.print("winner() should return null.... ");
        fail = am.winner() != null;
        System.out.println(fail?"FAIL":"PASS");
        if(!fail)points++;
        total++;
        
        System.out.println();
        System.out.println("Testing End of Game:");
        System.out.println();
        
        for(String s:Arrays.asList("Kristine","Robert","William")){
            am.kill(s);
        }
        
        fail = false;
        System.out.print("kill() should not work anymore for any name.... ");
        for(String s:names){
          try{
              am.kill(s);
          }catch(Exception e){
              continue;
          }
          fail = true;
        }
        System.out.println(fail?"FAIL":"PASS");
        if(!fail)points++;
        total++;
        
        graves = new StringBuilder();
        graves.append("    William was killed by Elizabeth\n");
        graves.append("    Robert was killed by Elizabeth\n");
        graves.append("    Kristine was killed by Elizabeth\n");
        graves.append("    Graham was killed by William\n");
        graves.append("    Anna was killed by Robert\n");
        graves.append("    Alex was killed by Elizabeth\n");
        
        System.out.print("printKillRing() should produce no output.... ");
        fail = !test.testPrint(null,am::printKillRing);
        System.out.println(fail?"FAIL":"PASS");
        if(!fail)points++;
        total++;
        
        System.out.print("printGraveyard() should print all people in the assassin game except winner.... ");
        fail = !test.testPrint(graves.toString(),am::printGraveyard);
        System.out.println(fail?"FAIL":"PASS");
        if(!fail)points++;
        total++;
        
        names.remove(6);
        
        fail = false;
        System.out.print("killRingContains() should be false for all names in test except winner.... ");
        for(String s:names){
            if(am.killRingContains(s)){
                fail = true;
                break;
            }
        }
        System.out.println(fail?"FAIL":"PASS");
        if(!fail)points++;
        total++;
        
        fail = false;
        System.out.print("graveyardContains() should be true for all names in test.... ");
        for(String s:names){
            if(!am.graveyardContains(s)){
                fail = true;
                break;
            }
        }
        System.out.println(fail?"FAIL":"PASS");
        if(!fail)points++;
        total++;
        
        System.out.print("isGameOver() should be true.... ");
        fail = !am.isGameOver();
        System.out.println(fail?"FAIL":"PASS");
        if(!fail)points++;
        total++;
        
        System.out.print("winner() should return last remaining player.... ");
        fail = !am.winner().equals("Elizabeth");
        System.out.println(fail?"FAIL":"PASS");
        if(!fail)points++;
        total++;
        
        System.out.println();
        System.out.println(points + " out of " + total + " tests passed");
        score = Math.round(points/total * 10 + 10);
        System.out.println("Final Score: " + (int)score);
    }
    
    
}
