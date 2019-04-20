import java.awt.*;
import javax.swing.*;
import javax.swing.*;
import java.awt.event.*;
import java.nio.file.*;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import javax.imageio.ImageIO;
/**
 * This method displays the player's hand.
 * 
 *
 * @author (Amar)
 * @version (1.0)
 */
public class Hand extends JPanel implements MouseListener
{
    // instance variables - replace the example below with your own

    private Toolkit toolkit;
    //stores the deck for transportation cards
    protected Deck transDeck;    
    int transCardCt = 44;
    int destinationCardCt = 18;
    
    protected TransportCard c;
    //stores the deck for destination cards
    protected Deck dest;
    int count =0;
    protected boolean notStarted;
    /**
     * This Constructor intiaizes the deck for both group of cards
     */
    public Hand()
    {
        dest = new Deck();
        transDeck = new Deck();
        setPreferredSize(new Dimension(500, 500));
 
        //add all destinations to the deck
        dest.addDeck( new ObjectiveCard(Destination.BROOKLYN,Destination.CHELSEA,8));
        dest.addDeck( new ObjectiveCard(Destination.BROOKLYN,Destination.EMPIRE,7));
        dest.addDeck( new ObjectiveCard(Destination.BROOKLYN,Destination.TIMESSQUARE,8));
        dest.addDeck( new ObjectiveCard(Destination.CHELSEA,Destination.CENTRAL,5));
        dest.addDeck( new ObjectiveCard(Destination.CHINA,Destination.CENTRAL,8));
        dest.addDeck( new ObjectiveCard(Destination.CHINA,Destination.GRAMERCY,4));
        dest.addDeck( new ObjectiveCard(Destination.EAST,Destination.TIMESSQUARE,4));
        dest.addDeck( new ObjectiveCard(Destination.EMPIRE,Destination.LINCOLN,3));
        dest.addDeck( new ObjectiveCard(Destination.GRAMERCY,Destination.CENTRAL,4));
        dest.addDeck( new ObjectiveCard(Destination.GREENWICH,Destination.LINCOLN,3));
        dest.addDeck( new ObjectiveCard(Destination.MIDTOWN,Destination.CENTRAL,3));
        dest.addDeck( new ObjectiveCard(Destination.MIDTOWN,Destination.UN,3));
        dest.addDeck( new ObjectiveCard(Destination.SOHO,Destination.EAST,4));
        dest.addDeck( new ObjectiveCard(Destination.SOHO,Destination.TIMESSQUARE,6));
        dest.addDeck( new ObjectiveCard(Destination.WALLSTREET,Destination.CHELSEA,6));
        dest.addDeck( new ObjectiveCard(Destination.WALLSTREET,Destination.LOWEREASTSIDE,2));
        dest.addDeck( new ObjectiveCard(Destination.WALLSTREET,Destination.UN,8));
  

        // these several for loops adds to the transporation deck
        // 44 total cards 
        for(int i=0; i< 6;i++)
        {
            c = new TransportCard(RouteColor.BLUE);
            transDeck.addDeck(c);
        }

        for(int i=0; i< 6;i++)
        {
            c = new TransportCard(RouteColor.PINK);
            transDeck.addDeck(c);
        }

        for(int i=0; i< 6;i++)
        {
            c = new TransportCard(RouteColor.GREEN);
            transDeck.addDeck(c);
        }

        for(int i=0; i< 6;i++)
        {
            c= new TransportCard(RouteColor.BLACK);
            transDeck.addDeck(c);

        }

        for(int i=0; i< 6;i++)
        {
            c = new TransportCard(RouteColor.ORANGE);
            transDeck.addDeck(c);
        }

        for(int i=0; i< 6;i++)
        {
            c= new TransportCard(RouteColor.RED);
            transDeck.addDeck(c);
        }

        for(int i=0; i< 8;i++)
        {
            c = new TransportCard(RouteColor.RAINBOW);
            transDeck.addDeck(c);
        }

        //this next two loops adds a mouselistener to each card 
        for(Card c:dest.deck)
        {
            addMouseListener(this);
        }

        for(Card c:transDeck.deck)
        {
            addMouseListener(this);

        }
        Collections.shuffle(transDeck.deck);
        Collections.shuffle(dest.deck);
        addMouseListener(this);
    }

    /**
     *If you click on the deck you get a card
     *
     * @param e A parameter
     */
    @Override
    public void mouseClicked( MouseEvent e ) {
        Point point = e.getPoint();
        //this rectangle should get the measuremnt of the transport card
        Rectangle rect = new Rectangle(160,150,150,200);
        System.out.println(point);
        //this rectangle gets the meausrent and location of the dest card
        Rectangle rect2 = new Rectangle(510,150,150,200);
         Point point2 = e.getPoint();
        if(rect.contains(point) )
        {
            transDeck.draw();
            repaint();
        }
       
    }

    /**
     *The "mouse pressed" event. This MouseEvent occurs when a
     *mouse button is pushed down.
     *
     * @param e MouseEvent Object
     */
    @Override
    public void mousePressed( MouseEvent e ) {
     
    }


    /**
     * PaintComponent method for JPanel.
     * 
     * @param  g   the Graphics object for this applet
     */
    @Override
    public void paintComponent(Graphics g)
    {
        int countHand=0;
        super.paintComponent(g);
      
        ImageIcon img; 
        ImageIcon imgTik;
        String path = "images\\transportCard\\back.jpg"; 
        String path2 = "images\\destCards\\Ticket.jpg"; 
        Image picture = null;
        img = new ImageIcon(path);
        imgTik = new ImageIcon(path2);
        int pos = 50;
        //gets the back of the transport card as well
        //as the destination card
        
        try  {

            Toolkit toolkit = Toolkit.getDefaultToolkit();
            picture = toolkit.getImage(path);
            img.setImage(img.getImage().getScaledInstance(150,200,Image.SCALE_SMOOTH));
            picture = toolkit.getImage(path2);
            imgTik.setImage(imgTik.getImage().getScaledInstance(150,200,Image.SCALE_SMOOTH));
        } catch (Exception e) {
            System.err.println(" Cannot find file "  +picture );
        }

        //draws deck of transportation cards
        for(int i =0; i <transDeck.deck.size();i++)
        {          
            g.drawImage(transDeck.deck.get(i).getImage(),190-pos,200-pos,null);
           
            pos++;
        }
        //draws the back of transport card
         g.drawImage(img.getImage(),190-pos,200-pos,null);
        
        //draws the deck of destinantion cards
        for(int i=0; i<16;i++)
        {
            g.drawImage(dest.deck.get(i).getImage(),590-pos,200-pos,null);
            //g.drawImage(imgTik.getImage(),590-pos,200-pos,null);
            pos++;
        } 
        //draw back of dest card
        g.drawImage(imgTik.getImage(),590-pos,200-pos,null);
       
        //transDeck.draw();
            g.drawImage(transDeck.deck.get(count).getImage(),190,500,null);
            //countHand++;
            g.drawImage(transDeck.deck.get(count+1).getImage(),300,500,null);
            //countHand++;
        

        //deals out two dest cards to each player
        for(int i=0;i<1;i++)
        {
            //dest.draw();
            g.drawImage(dest.deck.get(i).getImage(),500,500,null);
            countHand++;
            g.drawImage(dest.deck.get(i+1).getImage(),700,500,null);
            countHand++;
            //dest.draw();
        }                     
      
    }

    /**
     * The "mouse entered" event.This MouseEvent occurs when the mouse 
     * cursor enters the unobscured part of component's geometry.
     *
     * @param e MouseEvent object
     */
    @Override
    public void mouseEntered( MouseEvent e ) { }

    /**
     * The "mouse exited" event. This MouseEvent occurs when the 
     * mouse cursor exits the unobscured part of component's geometry.

     *
     * @param e MouseEvent object
     */
    @Override
    public void mouseExited( MouseEvent e ) { }

    /**
     * The "mouse released" event. This MouseEvent occurs when a mouse 
     * button is let up.
     *6
     * @param e MouseEvent object 
     */
    @Override
    public void mouseReleased( MouseEvent e ) { }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        Hand panel = new Hand();

        JFrame frame = new JFrame("Hand");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add the ubiquitous "Drawing Lines" label.

        frame.getContentPane().add(panel);
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    createAndShowGUI();
                }
            }
        );
    }
}
