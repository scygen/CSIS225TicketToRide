import javax.swing.*;
import java.awt.Image;
import java.awt.Image;
import java.io.File;
import java.awt.*;
import java.util.Scanner;
import java.io.FileReader;
import  java.nio.file.Path;
import  java.nio.file.Paths;
/**
 * This class creates the destination cards for the game
 *
 * @author (Amar Jagrup)
 * @version (4/4/19)
 */
public class ObjectiveCard extends Card 
{
    protected int points;
    protected Image pic; 
    Destination start;
    Destination end; 
  protected ImageIcon img; 
    /**
     * This method gets the path of the image.
     *
     * @return the Image
     */
    public Image getImage()
    {
        String path = "images\\destCards\\"; 
        Image pic = null;
        String location = path + start + "-"+ end+  ".jpg";
         img = new ImageIcon(location);
        try  {           
            Toolkit toolkit = Toolkit.getDefaultToolkit(); 
            //resize the image
            img.setImage(img.getImage().getScaledInstance(150,200,Image.SCALE_DEFAULT));       
            return img.getImage();
        } catch (Exception e) {
            System.err.println(" Cannot find file "  +location );
        }
        return pic;
    }

    /**
     * Construtures a objectivecard object
     *
     * @param start the starting location on the card
     * @param dest the destination location on the card
     * @param pts the point value of the card
     */
    public ObjectiveCard(Destination a, Destination dest, int pts)
    {
        start =a ;
        end = dest;
        points = pts;

    }

    /**
     * this method gets the destination
     *
     * @return The destination
     */
    public Destination getObj()
    {
        return this.end;
    }

    /**
     * this method gets the starting point
     *
     * @return the starting location
     */
    public Destination getStart()
    {
        return this.start;
    }

    /**
     * this method gets the points value
     *
     * @return the points
     */
    public int getPoints()
    {
        return points;
    }

}
