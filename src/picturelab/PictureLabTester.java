/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package picturelab;

import javafx.scene.paint.Color;

/**
 *
 * @author Alex
 */
public class PictureLabTester {
    public static final int HEIGHT = 50;
    public static final int WIDTH = 100;
    private Color[][] stripes;
    
    public PictureLabTester(){
        stripes = new Color[HEIGHT][WIDTH];
        
        for(int row = 0; row < HEIGHT/2; row++){
            for(int col = 0; col < WIDTH/2; col++){
                stripes[row][col] = Color.BLACK;
            }
        }
    }
    
}
