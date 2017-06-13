/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 *
 * @author Alex
 */
public class OutputCapturer{
    private static final PrintStream STDOUT = System.out;
    private PrintStream ps;
    private ByteArrayOutputStream baos;
    
    public OutputCapturer(){
        baos = new ByteArrayOutputStream();
        ps = new PrintStream(baos);
    }
    
    public void start(){
        System.setOut(ps);
    }
    
    public void stop(){
        System.out.flush();
        System.setOut(STDOUT);
    }
    
    public void reset(){
        baos.reset();
    }
    
    public boolean noOutputProduced(){
        return baos.size() == 0;
    }
    
    public String toString(){
        return baos.toString();
    }
}
