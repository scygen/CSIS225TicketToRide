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

    /**
     * This method gets the path of the image.
     *
     * @return the Image
     */
    public Image getImage()
    {
        String path = "C:\\hw5\\destCards\\"; 
        Image pic = null;
        String location = path + start + "-"+ end+  ".jeg";
        try  {
            Scanner sc = new Scanner(new File(location));                      
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            return toolkit.getImage(location);
        } catch (Exception e) {
            System.err.println(" Cannot find file "  + location );
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
