package picturelab;


import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alexb
 */
public class PictureUtility {
    
    public static Color[][] imageTo2DArray(Image image){
        Color[][] pixels = new Color[(int)image.getHeight()][(int)image.getWidth()];
        for(int row = 0; row < pixels.length; row++){
            for(int col = 0; col < pixels[0].length; col++){
                pixels[row][col] = image.getPixelReader().getColor(col, row);
            }
        }
        return pixels;
    }
    
    public static Image array2DToImage(Color[][] pixels){
        WritableImage image = new WritableImage(pixels[0].length,pixels.length);
        for(int row = 0; row < pixels.length; row++){
            for(int col = 0; col < pixels[0].length; col++){
                image.getPixelWriter().setColor(col, row, pixels[row][col]);
            }
        }
        return image;
    }
    
    public static Image setPixelRange(int startX, int startY, Color[][] newPixels, Image image){
        WritableImage wPic = new WritableImage(image.getPixelReader(),(int) image.getWidth(), (int)image.getHeight());
        for(int row = 0; row < newPixels.length; row++){
            for(int col = 0; col < newPixels[0].length; col++){
                wPic.getPixelWriter().setColor(col + startX, row + startY, newPixels[row][col]);
            }
        } 
        return wPic;
    }
    
    public static Image setPixelRange(int startX, int startY, int endX, int endY, Color color, Image image){
        WritableImage wPic = new WritableImage(image.getPixelReader(),(int) image.getWidth(), (int)image.getHeight());
        for(int row = startY; row < endY; row++){
            for(int col = startX; col < endX; col++){
                wPic.getPixelWriter().setColor(col, row, color);
            }
        } 
        return wPic;
    }
}
