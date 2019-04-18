import java.util.ArrayList;
import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader; 

/**
 * Write a description of class Driver here.
 *
 * @author Laura Hendley
 * @version 4/16/2019
 */
public class Driver {

    public static void main( String args[] ) {

        //Enter data using BufferReader 
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
        int numPlayers = 0;

        Deck transportDeck = new Deck();
        Deck destinationDeck = new Deck();

        // List of Players
        ArrayList<User> Players = new ArrayList<User>(); 


        // Choose the amount of players
        print("How many players?");
        try {
            numPlayers = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        print(numPlayers + " player selected!");
        String name = "";

        for(int i = 0; i < 3; i++) {
            print("Player " + (i+1) + " Name:");
            try {
                name = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            print("Hello " + name + "!");
            //Players.add(new User(name, TeamColorPicker(i), objectiveCardCt, cabCardCt)
            
        }

    }

    // Adding the initial 44 transport cards and 18 dest cards
    public void BuildDeck() {

        
        int colorIndex = 0;

        // Adding Transport cards
        for( int i = 0; i < 44; i++ ) {

            // Increment colorIndex every 6 iterations;
            if(i % 6 == 0) {
                colorIndex++;
            }
            TransportCard t = new TransportCard(TransportCardColorPicker(colorIndex));
            //transportDeck
        }
    }

    public static RouteColor TransportCardColorPicker(int cardNum) {
        switch(cardNum) {
            case 0: return RouteColor.ORANGE;
            case 1: return RouteColor.RED;
            case 2: return RouteColor.PINK;
            case 3: return RouteColor.BLACK;
            case 4: return RouteColor.BLUE;
            default: return RouteColor.GREEN;
        }
    }

    public static CabColor TeamColorPicker(int playerNum) {
        switch(playerNum) {
            case 0: return CabColor.YELLOW;
            case 1: return CabColor.WHITE;
            case 2: return CabColor.CYAN;
            default: return CabColor.PURPLE;
        }
    }
// public enum CabColor{
//   YELLOW, WHITE, CYAN, PURPLE
// }
    public static void print(String msg) {
        System.out.println(msg);
    }


}

