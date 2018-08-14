package master;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.util.LinkedList;

public class Mazelight {

    private static Color colorBlack = new Color(0, 0, 0);
    private static int black = colorBlack.getRGB();
    private static Color colorWhite = new Color(255, 255, 255);
    private static int white = colorWhite.getRGB();
    private static Color colorRed = new Color(255, 0, 0);
    private static int red = colorRed.getRGB();
    private static Color colorGreen = new Color(0, 255, 0);
    private static int green = colorGreen.getRGB();

    private static Position start = null;
    private static Position end = null;

    private static boolean isNode(LinkedList<Position> list, Position pos){
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).equals(pos)){
                return true;
            }
        }
        return false;
    }

    private static BufferedImage imageToBuffer(String path) throws IOException{
        //Gets image file
        File file = new File(path);
        BufferedImage img = ImageIO.read(file);

        return img;
    }

    private static Graph bufferToGraph(BufferedImage img) throws IOException, Exception{
        Graph graph = new Graph();
        LinkedList<Position> nodes = new LinkedList<Position>();

        //Node finder
        for(int i = 1; i < img.getWidth()-1; i++){
            for(int j = 1; j < img.getHeight()-1; j++){
                if(img.getRGB(i, j) != black){
                    if(img.getRGB(i+1, j) == black && img.getRGB(i-1, j) == black && img.getRGB(i, j-1) != black && img.getRGB(i, j+1) != black){
                        continue;
                    } else if(img.getRGB(i+1, j) != black && img.getRGB(i-1, j) != black && img.getRGB(i, j-1) == black && img.getRGB(i, j+1) == black){
                        continue;
                    } else {
                        nodes.addLast(new Position(i, j));
                   }
                }
            }
        }

        //Start and end points finder
        for(int i = 0; i < img.getWidth(); i++){
            for(int j = 0; j < img.getHeight(); j++){
                if((i == 0 || i == img.getWidth()-1 || j == 0 || j == img.getHeight()-1) && img.getRGB(i, j) == white){
                    img.setRGB(i,j, red);
                    if(start == null){
                        start = new Position(i,j);
                        nodes.addFirst(new Position(i, j));
                    } else if (end == null){
                        end = new Position(i,j);
                        nodes.addLast(new Position(i, j));
                    }
                }
            }
        }

        //Path finding algorithm
        for(int i = 0; i < nodes.size(); i++){
            Position current = nodes.get(i);
            int x, y, c;

            System.out.println("Finding edges from: [" + current.getX() + ", " + current.getY() + "]");
            //Search nodes on the top and bottom
            System.out.println("Above: ");
            x = current.getX(); y = current.getY() - 1; c = 1;
            while(y >= 0 && img.getRGB(x, y) != black && !isNode(nodes, new Position(x,y))) { System.out.println("c=" + c + "[" + x + ", " + y + "] is not a node"); y--; c++; }
            if(isNode(nodes, new Position(x,y)) && !graph.isEdge(current, new Position(x,y))) { System.out.println("c=" + c + "[" + x + ", " + y + "] is node"); graph.addEdge(current, new Position(x,y), c); System.out.println("c=" + c ); }

            //Search nodes on the left and right
            System.out.println("Sides: ");
            x = current.getX() - 1; y = current.getY(); c = 1;
            while(x >= 0 && img.getRGB(x, y) != black && !isNode(nodes, new Position(x,y))) { System.out.println("c=" + c + "[" + x + ", " + y + "] is not a node"); x--; c++; }
            if(isNode(nodes, new Position(x,y)) && !graph.isEdge(current, new Position(x,y))) { System.out.println("c=" + c + "[" + x + ", " + y + "] is node"); graph.addEdge(current, new Position(x,y), c); System.out.println("c=" + c ); }

            System.out.println("");
        }

        System.out.println("Start: " + start.getX() + ", " + start.getY());
        System.out.println("End: " + end.getX() + ", " + end.getY());
        System.out.println("Total paths: " + graph.size());
        System.out.println("Total nodes: " + nodes.size());

        return graph;
    }


    private static LinkedList<Position> sortByWeight(Graph graph, Position start, LinkedList<Position> list){
        for(int i = 0; i < list.size(); i++){
            for(int j = 0; j < list.size()-i-1; j++){
                if(graph.getWeight(start, list.get(j)) > graph.getWeight(start, list.get(j+1))){
                    Position temp = list.get(j);
                    list.set(j, list.get(j+1));
                    list.set(j+1, temp);
                }
            }
        }

        return list;
    }

    public static void main(String[] args) throws IOException, Exception {
        long startT = System.currentTimeMillis();

        Graph maze = bufferToGraph(imageToBuffer("mazes/maze1.png"));

        System.out.println("\n\nTotal computation time: " + (System.currentTimeMillis() - startT) + "ms");
    }
}
