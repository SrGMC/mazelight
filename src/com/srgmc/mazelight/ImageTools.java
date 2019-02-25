package com.srgmc.mazelight;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageTools {
    public static int black = new Color(0,0,0).getRGB();
    public static int white = new Color(255, 255, 255).getRGB();
    public static int red = new Color(255, 0, 0).getRGB();
    public static int green = new Color(0, 255, 0).getRGB();
    
    /**
     * Converts an image into a BufferedImage object
     * @param path Absolute or relative path of the image
     * @return {@link BufferedImage}
     * @throws IOException
     */
    public static BufferedImage imageToBuffer(String path) throws IOException{
        //Gets image file
        File file = new File(path);
        BufferedImage img = ImageIO.read(file);

        return img;
    }
    
    public static BufferedImage draw(BufferedImage image, Position start, Position end, int rgb) {
    	if(start.getX() == end.getX()) {
    		int min = Math.min(start.getY(), end.getY());
    		int max = Math.max(start.getY(), end.getY());
    		for (int i = min; i <= max; i++) {
				image.setRGB(start.getX(), i, rgb);
			}
    	} else {
    		int min = Math.min(start.getX(), end.getX());
    		int max = Math.max(start.getX(), end.getX());
    		for (int i = min; i <= max; i++) {
				image.setRGB(i, start.getY(), rgb);
			}
    	}
    	
    	return image;
    }
}
