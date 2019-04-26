package CSIS225TicketToRide;

import sun.rmi.transport.Transport;

import java.lang.reflect.Array;
import java.nio.Buffer;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 * This class is currently simulating the Ticket to Ride gameplay via a console application
 *
 * @author Laura Hendley & Steven Gibson
 * @version 4/16/2019
 */
public class Driver {

    public static final int MAX_PLAYERS = 4;
    public static final int MIN_PLAYERS = 2;
    public static final int NUM_TRANSPORT_CARDS = 44;
    public static final int TRANSP_CARDS_PER_COLOR = 6;
    protected static String turnChoice = "";
    protected static BoardPanel panel;
    
     private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("TicketToRide");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Add the ubiquitous "Hello World" label.
        //MenuPanel  m = new MenuPanel();
        panel = new BoardPanel();
        //frame.getContentPane().add(panel);
        JButton button = new JButton("Start");
        button.setSize(button.getPreferredSize());
        button.setLocation(500, 500);
        frame.add(panel.transport);
        frame.add(panel.objective);
        //m.add(button);
        frame.getContentPane().add(panel);
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    
    public static void main( String args[] ) throws IOException{
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
        
        //Enter data using BufferReader
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numPlayers = 0;

        Deck transportDeck = new Deck();
        Deck objectiveDeck = new Deck();

        // Pool of 5 transport cards
        ArrayList<TransportCard> transportCardPool = new ArrayList<TransportCard>();

        // List of Players
        ArrayList<User> players = new ArrayList<User>();

        introMessage();
        
        // Building both decks of cards
        buildTransportDeck(transportDeck);
        buildObjectiveDeck(objectiveDeck);

        // Choose amount of players and initialize them
        addPlayers(numPlayers, players,  reader);

        // Adding initial transport cards to each player
        for( User player : players ) {
            // Adding two initial transport cards to each player
            print("Player " + player.getUserName() + " received 2 transportation cards!");
            print("Player " + player.getUserName() + " received 2 objective cards!");

            player.addTransportCard((TransportCard)transportDeck.draw());
            player.addTransportCard((TransportCard)transportDeck.draw());
            // Adding two initial objective (destination) cards to each player
            drawObjectiveCards(player, objectiveDeck, reader);
            print("----------------------");
        }

        // Creating the 5 transportation card pool (isfirst flag set in case of 3+ wildcards)
        transportCardPool(transportCardPool, transportDeck, true);

        // Creating a new Ticket To Ride Board with all node information
        Board board = new Board();

        // While each player has more than 2 taxis, the game will continue
        while(!gameEndTrigger(players)) {
            // Take each player's turn
            for (User player : players) {
                takeTurn(reader, player, transportDeck, objectiveDeck, transportCardPool, board);
            }


        }

    }

    public static void takeTurn(BufferedReader reader, User player, Deck transportDeck, Deck objectiveDeck, ArrayList<TransportCard> transportCardPool, Board board) {
        String option = "";
        Boolean turnExecuted = false;
        do {
            print("----------------------");
            print(player.getUserName() + "'s turn");
            print(player.getNumTaxis() + " " + player.getTeamColor().toString().toLowerCase() + " taxis remaining");
            print("(Type in the number of the option you would like)");
            print("What would you like to do?");
            print("[1] Draw two Transport cards");
            print("[2] Draw two new Objective cards");
            print("[3] Claim a Route");
            print("[4] View Transportation Cards");
            print("[5] View Objective Cards");
            print("[6] View Transport Card Pool");
            print("[7] View Claimed Routes");
            print("[Q] Quit Game!");
            try {
                option = panel.choice;
                panel.choice = "";
            } catch (Exception e) {
                e.printStackTrace();
            }

            switch(option) {
                case "1": turnExecuted = drawTransportCards(reader, transportDeck, transportCardPool, player); break;
                case "2": turnExecuted = drawObjectiveCards(player, objectiveDeck, reader); break;
                case "3": turnExecuted = claimRoute(board, reader, player); break;
                case "4": viewTransportationCards(player); break;
                case "5": viewObjectiveCards(player); break;
                case "6": viewPool(transportCardPool); break;
                case "7": viewClaimedRoutes(player); break;
                case "Q": System.exit(0); break;
            }


        } while(!option.equals("1") &&
                !option.equals("2") &&
                !option.equals("3") &&
                !option.equals("4") &&
                !option.equals("5") &&
                !option.equals("6") &&
                !option.equals("7") ||
                !turnExecuted);

    }

    public static void viewClaimedRoutes(User player) {
        print("----------------------");
        if(player.getClaimedRoutes().size() > 0) {
            for(RouteNode route : player.getClaimedRoutes()) {
                print(getRouteInformation(route));
            }
        } else {
            print(player.getUserName() + " does not have any routes!");
        }


    }

    public static void viewPool( ArrayList<TransportCard> transportCardPool) {
        print("Cards currently in the Transport Card pool...");
        for( TransportCard card : transportCardPool) {
            print(card.getTransColor().toString().toLowerCase());
        }
    }


    public static void viewTransportationCards(User player) {
        print("----------------------");
        print(player.getUserName() + "'s Transportation Cards:");
        for(TransportCard card : player.getTransportCards()) {
            print(card.getTransColor().toString().toLowerCase());
        }
    }

    public static void viewObjectiveCards(User player) {
        print("----------------------");
        print(player.getUserName() + "'s Objective Cards:");
        for(ObjectiveCard card : player.getObjectiveCards()) {
            print(card.getStart().toString().toLowerCase() + " to " + card.getEnd().toString().toLowerCase());
        }
    }

    public static boolean claimRoute(Board board, BufferedReader reader, User player) {
        DestinationReferences locationReferences = board.getDestinationReferences();
        int option = 0;
        int i;
        do {
            print("----------------------");
            print("View a Location...");
            i = 1;
            String[] options = new String[locationReferences.getLocations().size()];
            // Display potential routes to claim
            for( DestinationNode location : locationReferences.getLocations()) {
                print("[" + i + "] " + location.getDestination().toString().toLowerCase());
                options[i-1] = "[" + i + "] " + location.getDestination().toString();
                i++;
            }
            //displays the options for start city
            String input = (String) JOptionPane.showInputDialog(null,"Choose your start city:"
            ,"Claim",JOptionPane.QUESTION_MESSAGE, null,options,options[0]);
            
            try {
                option = Integer.parseInt(input.substring(1,2));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }while(option > 15 || option < 1);

        DestinationNode selectedLocation = locationReferences.getLocation(option-1);
        return printRoutes(selectedLocation, reader, player);
    }

    public static boolean printRoutes(DestinationNode loc, BufferedReader reader, User player) {
        int option = 0;
        int i;
        ArrayList<RouteNode> tempRoutes = new ArrayList<RouteNode>();
        do {
            i = 1;
            print("----------------------");
            print("Select a route from " + loc.getDestination().toString().toLowerCase() + ":");
            String[] options = new String[loc.getRoutes().length];
            System.out.println(options.length);
            // Display the full information between each route
            for( RouteNode route : loc.getRoutes()) {
                // Don't display own route back
                if(!route.getLink1().getDestination().toString().toLowerCase().equals(loc.getDestination().toString().toLowerCase())) {
                    print("[" + i++ + "] (" + loc.getDestination().toString().toLowerCase() + ") <-->" + getRouteInformation(route)  + 
                    "<--> (" + route.getLink1().getDestination().toString().toLowerCase() + ")");
                    options[i-2] += "[" + i + "] (" + loc.getDestination().toString().toLowerCase() + ") <-->" + getRouteInformation(route) 
                    + "<--> (" + route.getLink1().getDestination().toString().toLowerCase() + ")";
                    tempRoutes.add(route);
                }
                if(!route.getLink2().getDestination().toString().toLowerCase().equals(loc.getDestination().toString().toLowerCase())) {
                    print("[" + i++ + "] (" + loc.getDestination().toString().toLowerCase() + ") <-->" + getRouteInformation(route)  +
                    "<--> (" + route.getLink2().getDestination().toString().toLowerCase() + ")");
                    options[i-2] += "[" + i + "] (" + loc.getDestination().toString().toLowerCase() + ") <-->" + getRouteInformation(route)
                    + "<--> (" + route.getLink2().getDestination().toString().toLowerCase() + ")";
                    tempRoutes.add(route);
                }
            }
            for(int r = 0; r < options.length; r++){
                options[r] = options[r].substring(4);
            }
            String input = (String) JOptionPane.showInputDialog(null,
            "Select a route from " + loc.getDestination().toString().toLowerCase() + ":"
            ,"Claim",JOptionPane.QUESTION_MESSAGE, null,options,options[0]);
            try {
                option = Integer.parseInt(input.substring(1,2));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while(option > i-1 || option < 1);

        return payTaxiCost(player, tempRoutes.get(option-1));

    }

    public static boolean payTaxiCost(User player, RouteNode route) {
        // If the player can afford the route
        if(player.getNumTaxis() < route.getNumTaxis()) {
            print("Not enough taxis!");
            return false;
        }else if(route.getOccupyingPlayer() == CabColor.NONE) { // if the route is unoccupied
            player.addClaimedRoute(route);
            player.setNumTaxis(player.getNumTaxis()-route.getNumTaxis());
            route.setOccupyingPlayer(player.getTeamColor());
            print("Selected: " + getRouteInformation(route));
            print(player.getUserName() + " has " + player.getNumTaxis() + " remaining.");
            return true;
        } else { // if the route is already occupied
            print("This location is already occupied by a player!");
            return false;
        }

    }

    public static String getRouteInformation(RouteNode route) {
        // Displaying verbose information about the route
        return "[Taxi Cost: " + route.getNumTaxis() + " | Route Color: " + route.getRouteColor().toString().toLowerCase() + " Occupied: " + route.getOccupyingPlayer().toString().toLowerCase() + "]";
    }

    public static boolean drawObjectiveCards(User player, Deck objectiveDeck, BufferedReader reader) {
        print("----------------------");
        print(player.getUserName() + " drew two Objective cards...");
        ObjectiveCard card1 = (ObjectiveCard)objectiveDeck.draw();
        ObjectiveCard card2 = (ObjectiveCard)objectiveDeck.draw();
        String option = "";

        // Give player option to discard 1 objective card
        do {
            print("----------------------");
            print("Discard one?");
            String input = JOptionPane.showInputDialog(player.getUserName() + " drew two Objective cards... \n [1] " + card1.getStart().toString().toLowerCase() + " to " + card1.getEnd().toString().toLowerCase() + "\n" + 
            "[2] " + card2.getStart().toString().toLowerCase() + " to " + card2.getEnd().toString().toLowerCase() + "\n[N] No");
            print("[1] " + card1.getStart().toString().toLowerCase() + " to " + card1.getEnd().toString().toLowerCase());
            print("[2] " + card2.getStart().toString().toLowerCase() + " to " + card2.getEnd().toString().toLowerCase());
            print("[N] No");

            try {
                option = input;
            } catch (Exception e) {
                e.printStackTrace();
            }

            switch(option) {
                case "N":
                    player.addObjectiveCard(card1);
                    print(player.getUserName() + " obtained card " + card1.getStart().toString().toLowerCase() + " to " + card1.getEnd().toString().toLowerCase());
                case "1":
                    player.addObjectiveCard(card2);
                    print(player.getUserName() + " obtained card " + card2.getStart().toString().toLowerCase() + " to " + card2.getEnd().toString().toLowerCase());
                    break;
                case "2":
                    player.addObjectiveCard(card1);
                    print(player.getUserName() + " obtained card " + card1.getStart().toString().toLowerCase() + " to " + card1.getEnd().toString().toLowerCase());
            }
        }while(!option.equals("N") && !option.equals("1") && !option.equals("2"));

        return true;
    }

    public static boolean drawTransportCards(BufferedReader reader, Deck transportDeck, ArrayList<TransportCard> transportCardPool, User player) {
        String option = "";
        int numCardsLeft = 2;
        boolean isWildcard = false;
        do {
            print("----------------------");
            print(player.getUserName() + " is drawing " + numCardsLeft + " Transport Cards");
            print("[1] Draw a card from pool");
            print("[2] Draw a card from top of deck");
            
            String input = JOptionPane.showInputDialog(player.getUserName() + " is drawing " + numCardsLeft + " Transport Cards\n"
            + "[1] Draw a card from pool \n [2] Draw a card from top of deck");
            // Don't display the option for the player to go back if they already pulled a card (that would be cheating!)
            if(numCardsLeft == 2) {
                print("[B] Go Back!");
            }

            try {
                option = input;
            } catch (Exception e) {
                e.printStackTrace();
            }

            switch(option) {
                case "1": isWildcard = drawFromPool(reader, transportCardPool, player, transportDeck, numCardsLeft); break;
                case "2": drawFromTopDeck(reader, transportDeck, player); break;
                case "B":
                    // Don't allow player to go back if they already pulled a card (that would be cheating!)
                    if(numCardsLeft == 2) {
                        return false;
                    };
            }

            // If a wildcard was pulled from pool, don't allow second card to be chosen (that would be cheating!)
            if(isWildcard) {
                numCardsLeft = 0;
            } else if(option.equals("1") || option.equals("2")) {
                numCardsLeft--;
            }
        } while(!option.equals("1") && !option.equals("2") && !option.equals("B") || numCardsLeft == 1);

        return true;
    }

    public static boolean drawFromPool(BufferedReader reader,ArrayList<TransportCard> transportCardPool, User player, Deck transportDeck, int numCardsLeft) {
        int option = 0;
        int i;
        String[] options = new String[transportCardPool.size()];
        do {
            print("----------------------");
            print("Drawing from Pool...\n");
            print("Which card do you want?");
            i = 1;
            // Show all transport cards in pool
            for( TransportCard card : transportCardPool ) {
                print("[" + i + "] " +card.getTransColor().toString().toLowerCase());
                options[i-1] += "[" + i + "] " + card.getTransColor().toString();
                i++;
            }
            //removes the null in front of the string (I don't know why it appears in the string)
            for(int r = 0; r < options.length; r++){
                options[r] = options[r].substring(4);
            }
            String input = (String) JOptionPane.showInputDialog(null,"Drawing from Pool...\n Which card do you want?"
            ,"Draw",JOptionPane.QUESTION_MESSAGE, null,options,options[0]);
            try {
                option = Integer.parseInt(input.substring(1,2));
            } catch (Exception e) {
                e.printStackTrace();
            }

            if(transportCardPool.get(option-1).getTransColor() == RouteColor.GREY && numCardsLeft == 1) {
                print("You cannot select a wildcard from the pool at this time...");
                option = -1;
            }
        }while(option > i || option < 1);
        TransportCard selectedCard = transportCardPool.get(option-1);
        print("----------------------");
        print("Player " + player.getUserName() + " obtained a " + selectedCard.getTransColor().toString().toLowerCase() + " Transport Card!");
        takeFromPool(player, transportCardPool, transportDeck, option-1);

        if(selectedCard.getTransColor() == RouteColor.GREY) {
            // If pulling a wildcard from the pile, return true
            return true;
        }
        return false;
    }

    public static void takeFromPool(User player, ArrayList<TransportCard> transportCardPool, Deck transportDeck, int index) {
        // Taking a single card from the 5 card pool
        player.addTransportCard(transportCardPool.get(index));
        transportCardPool.remove(index);
        // Replacing the card from the pool off the top of the deck
        transportCardPool(transportCardPool, transportDeck, false);
    }

    public static void drawFromTopDeck(BufferedReader reader, Deck transportDeck, User player) {
        print("----------------------");
        TransportCard card = (TransportCard)transportDeck.draw();
        player.addTransportCard(card);
        print("Player " + player.getUserName() + " obtained a " + card.getTransColor().toString().toLowerCase() + " Transport Card!");
        JOptionPane.showMessageDialog(null, "Player " + player.getUserName() + " obtained a " + card.getTransColor().toString().toLowerCase() + " Transport Card!");
    }

    public static boolean gameEndTrigger(ArrayList<User> players) {
        // Check that each player doesn't have 2 or less taxis (which triggers endgame)
        for(User p : players) {
            if(p.getNumTaxis() <= 2) {
                return true;
            }
        }
        return false;
    }

    public static void transportCardPool(ArrayList<TransportCard> tc, Deck transportDeck, boolean isFirst) {

        // If the pool is less than 5, replenish it
        while(tc.size() < 5) {
            tc.add((TransportCard)transportDeck.draw());
        }

        // If this is the first time replenishing pool, check for 3 wildcards
        if(isFirst) {
            int wildcardCt;
            do {
                wildcardCt = 0;
                for(TransportCard card : tc) {
                    // If the card is wild, increase ct
                    if(card.getTransColor() == RouteColor.GREY) {
                        wildcardCt++;
                    }
                }

                if(wildcardCt >= 3) {
                    tc.clear();
                    while(tc.size() < 5) {
                        tc.add((TransportCard)transportDeck.draw());
                    }
                }
            } while(wildcardCt >= 3);
        }
    }

    public static void introMessage() {
        print("~~~ WELCOME TO TICKET TO RIDE NEW YORK ~~~\n");
    }

    public static void addPlayers(int numPlayers, ArrayList<User> players, BufferedReader reader) {
        // Choose the amount of players
        while(numPlayers > MAX_PLAYERS || numPlayers < MIN_PLAYERS) {
            String input = JOptionPane.showInputDialog("Please enter the number of Player");
            print("How many players?");
            try {
                numPlayers = Integer.parseInt(input);
            } catch (Exception e) {
                e.printStackTrace();
            }
            print("----------------------");
        }
        print("You've selected " + numPlayers + " players!");
        print("----------------------");
        // Adding players
        String name = "";
        for(int i = 0; i < numPlayers; i++) {
            print("Player " + (i+1) + " Name:");
            try {
                name = JOptionPane.showInputDialog("Please enter Player " + (i+1) + " Name:" );
            } catch (Exception e) {
                e.printStackTrace();
            }
            print("Hello " + name + "!");
            print("----------------------");
            players.add(new User(name,TeamColorPicker(i)));
        }
    }


    public static void buildObjectiveDeck(Deck objDeck) {
        // These should probably be changed since I just made them up!
        objDeck.addCard(new ObjectiveCard(Destination.CHELSEA,Destination.BROOKLYN,2));
        objDeck.addCard(new ObjectiveCard(Destination.CHELSEA,Destination.TIMES_SQUARE,2));
        objDeck.addCard(new ObjectiveCard(Destination.CHELSEA,Destination.EMPIRE_STATE_BUILDING,2));
        objDeck.addCard(new ObjectiveCard(Destination.CHELSEA,Destination.UNITED_NATIONS,2));
        objDeck.addCard(new ObjectiveCard(Destination.CHELSEA,Destination.WALL_STREET,2));
        objDeck.addCard(new ObjectiveCard(Destination.CHELSEA,Destination.CHINATOWN,2));
        objDeck.addCard(new ObjectiveCard(Destination.CHINATOWN,Destination.EMPIRE_STATE_BUILDING,2));
        objDeck.addCard(new ObjectiveCard(Destination.CHINATOWN,Destination.BROOKLYN,2));
        objDeck.addCard(new ObjectiveCard(Destination.CHINATOWN,Destination.TIMES_SQUARE,2));
        objDeck.addCard(new ObjectiveCard(Destination.CHINATOWN,Destination.CENTRAL_PARK,2));
        objDeck.addCard(new ObjectiveCard(Destination.CENTRAL_PARK,Destination.EMPIRE_STATE_BUILDING,2));
        objDeck.addCard(new ObjectiveCard(Destination.CENTRAL_PARK,Destination.TIMES_SQUARE,2));
        objDeck.addCard(new ObjectiveCard(Destination.CENTRAL_PARK,Destination.BROOKLYN,2));
        objDeck.addCard(new ObjectiveCard(Destination.CENTRAL_PARK,Destination.WALL_STREET,2));
        objDeck.addCard(new ObjectiveCard(Destination.UNITED_NATIONS,Destination.WALL_STREET,2));
        objDeck.addCard(new ObjectiveCard(Destination.UNITED_NATIONS,Destination.CENTRAL_PARK,2));
        objDeck.addCard(new ObjectiveCard(Destination.UNITED_NATIONS,Destination.GREENWICH_VILLAGE,2));
        objDeck.addCard(new ObjectiveCard(Destination.GRAMERCY_PARK,Destination.EMPIRE_STATE_BUILDING,2));

        // Shuffling new deck
        objDeck.shuffleDeck();
    }

    // Adding the initial 44 transport cards and 18 dest cards
    public static void buildTransportDeck(Deck transDeck) {
        int colorIndex = -1;

        // Adding Transport cards
        for( int i = 0; i < NUM_TRANSPORT_CARDS; i++ ) {

            // Increment colorIndex every 6 iterations;
            if(i % TRANSP_CARDS_PER_COLOR == 0 && i <= 36) {
                colorIndex++;
            }

            transDeck.addCard(new TransportCard(TransportCardColorPicker(colorIndex)));
        }

        // Shuffling new deck
        transDeck.shuffleDeck();
    }

    public static RouteColor TransportCardColorPicker(int cardNum) {
        switch(cardNum) {
            case 0: return RouteColor.ORANGE;
            case 1: return RouteColor.RED;
            case 2: return RouteColor.PINK;
            case 3: return RouteColor.BLACK;
            case 4: return RouteColor.BLUE;
            case 5: return RouteColor.GREEN;
            default: return RouteColor.GREY;
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

    public static void print(String msg) {
        System.out.println(msg);
    }


}

