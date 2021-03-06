import java.awt.Image;
import java.io.File;
import java.awt.*;
import java.util.Scanner;
/**
 * This class creates the Transportation cards of the game. This class will get the file location of the image.
 *
 * @author (Amar Jagrup)
 * @version (4/4/19)
 */
public class TransportCard extends Card
{
    protected RouteColor c;
    private Image image1;

    /**
     * This method gets the path of the image.
     *
     * @return the Image
     */
    public  Image getImage()
    {

        String path = "C:\\hw5\\transportCard\\";
        Image picture = null;
        String location = path + getTransColor() + ".jpg";
        try  {
            Scanner sc = new Scanner(new File(location));
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            return toolkit.getImage(location);
        } catch (Exception e) {
            System.err.println(" Cannot find file "  +location );
        }
        return picture;
    }


    /**
     * Constructs a Transportation card. Each card will have a color and
     * image associated with it.
     *
     * @param colors the route color
     */
    public TransportCard(RouteColor colors)
    {
        c = colors;
        //image1 = getImage();

    }

    /**
     * gets the color of the card
     *
     * @return the route color
     */
    public RouteColor getTransColor()
    {
        return c;
    }

}
