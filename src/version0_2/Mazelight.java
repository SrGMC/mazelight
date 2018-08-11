package version0_2;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Mazelight {
    private Graphics2D imageToGraphics(String path) throws IOException{
        //Gets image file
        File file = new File(path);
        BufferedImage buffer = ImageIO.read(file);

        //Converts to B&W image
        BufferedImage img = new BufferedImage(buffer.getWidth(), buffer.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
        Graphics2D graphics = img.createGraphics();
        graphics.drawImage(buffer, 0, 0, null);

        return graphics;
    }

    private Graph graphicsToGraph(Graphics2D graphics){
        Graph graph = new Graph();

        return graph;
    }

    public static void main(String[] args) {

    }
}
