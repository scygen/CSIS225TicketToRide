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
        string name = "";

        for(int i = 0; i < 3; i++) {
            print("Player " + (i+1) + " Name:");
            name = reader.readLine();
            print("Hello " + name + "!");
            Players.add(new User(name, TeamColorPicker(i), objectiveCardCt, cabCardCt)
            
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

