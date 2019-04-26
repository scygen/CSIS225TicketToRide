package CSIS225TicketToRide;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Write a description of class DisplayImagePanel here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BoardPanel extends JPanel implements MouseListener,ActionListener
{
    // instance variables - replace the example below with your own
    protected int width;
    protected int height;
    protected Toolkit toolkit;
    protected Image background; 
    protected Image board;
    protected Image deck;
    protected Image cards[] = new Image[7];
    protected Color colors[] = {Color.red, Color.orange, Color.blue, Color.green, Color.black, Color.pink, Color.cyan};
    int index = 0;
    ArrayList<JButton> buttons = new ArrayList<JButton>();
    JButton transport = new JButton("Draw Transportation Card");
    JButton objective = new JButton("Draw Objective Card");
    JButton claim = new JButton("Claim a route");
    JButton viewTrans = new JButton("View Transportation Cards");
    JButton viewObject = new JButton("View Objective Cards");
    protected String choice = "";
    
    /**
     * Constructor for objects of class DisplayImagePanel
     */
    public BoardPanel()
    {
        setPreferredSize(new Dimension(1200, 1200));
        toolkit = Toolkit.getDefaultToolkit();
        addMouseListener(this);  
        buttons.add(transport);
        buttons.add(objective);
        buttons.add(claim);
        buttons.add(viewTrans);
        buttons.add(viewObject);
        int offset = 50;
        System.out.println(buttons.size());
        for(int i = 0; i < buttons.size(); i++){
            buttons.get(i).setSize(buttons.get(i).getPreferredSize());
            buttons.get(i).setLocation(950, 600 + i*offset);
            buttons.get(i).addActionListener(this);
        }
        
        // provide any initialisation necessary for your JApplet
        background = toolkit.getImage("Z:\\CS225\\CSIS225TicketToRide\\fwdboardandtransport\\background.jpg");
        board = toolkit.getImage("Z:\\CS225\\CSIS225TicketToRide\\fwdboardandtransport\\board.jpg"); 
        deck = toolkit.getImage("Z:\\CS225\\CSIS225TicketToRide\\fwdboardandtransport\\Deck.jpg");
        cards[0] = toolkit.getImage("Z:\\CS225\\CSIS225TicketToRide\\fwdpieces\\redCab.jpg");
        cards[1] = toolkit.getImage("Z:\\CS225\\CSIS225TicketToRide\\fwdpieces\\orangeCab.jpg");
        cards[2] = toolkit.getImage("Z:\\CS225\\CSIS225TicketToRide\\fwdpieces\\blueCab.jpg");
        cards[3] = toolkit.getImage("Z:\\CS225\\CSIS225TicketToRide\\fwdpieces\\greenCab.jpg");
        cards[4] = toolkit.getImage("Z:\\CS225\\CSIS225TicketToRide\\fwdpieces\\blackCab.jpg");
        cards[5] = toolkit.getImage("Z:\\CS225\\CSIS225TicketToRide\\fwdpieces\\pinkCab.jpg");
        cards[6] = toolkit.getImage("Z:\\CS225\\CSIS225TicketToRide\\fwdpieces\\rainbowCab.jpg");
        //transport.addActionListener(this);
        //objective.addActionListener(this);
        width = getPreferredSize().width;
        height = getPreferredSize().height;
    }

    /**
     * PaintComponent method for JPanel.
     * 
     * @param  g the Graphics object for this applet
     */
    @Override
    public void paintComponent(Graphics g)
    {
        int offset = 0;
        super.paintComponent(g);
        // display images
        g.drawImage(background,0,0,width,height,this);
        g.drawImage(board, 0, 0,width-300,height-300, this);
        g.drawImage(deck, 950, offset,200,75, this);
        for(int i = 0; i < cards.length-2; i++){
            offset += 100;
            g.drawImage(cards[i], 950, offset,200,75, this); 
        }
        //System.out.println(index);
    }

    public void actionPerformed( ActionEvent e ) {
        Object src = e.getSource();
        if(src == transport){
            choice = "3";
        }
        else if(src == objective){
            choice = "2";
        }
        else if(src == claim){
            choice = "3";
        }
    }

    public void mousePressed( MouseEvent e ) { }

    public void mouseReleased( MouseEvent e ) { }

    public void mouseEntered( MouseEvent e ) { }

    public void mouseExited( MouseEvent e ) {}

    public void mouseClicked( MouseEvent e ) {

    }
}
