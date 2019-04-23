import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * User object for tracking information about a user
 *
 * @author Steven Gibson
 * @version 1.0
 */
public class User {

    public static final int STARTING_TAXI_NUMBER = 15;

    private String userName;
    private CabColor teamColor;
    private ArrayList<ObjectiveCard> objectiveCards;
    private ArrayList<TransportCard> transportCards;
    private int score;
    private int numTaxis;
    private ArrayList<RouteNode> claimedRoutes;

    /**
     * Constructor
     * @param userName user name
     * @param teamColor team color
     */
    public User(String userName, CabColor teamColor ) {
        this.numTaxis = STARTING_TAXI_NUMBER;
        this.userName = userName;
        this.teamColor = teamColor;
        this.claimedRoutes = new ArrayList<RouteNode>();

        // The logic for these still need to be implemented
        objectiveCards = new ArrayList<ObjectiveCard>();
        transportCards = new ArrayList<TransportCard>();
    }

    /**
     * Returns number of taxies a user has left
     * @return numTaxis
     */
    public int getNumTaxis() {
        return this.numTaxis;
    }

    /**
     * Sets the number of taxis a user has left
     * @param taxis number of taxies the user has left
     */
    public void setNumTaxis(int taxis) {
        this.numTaxis = taxis;
    }

    /**
     * Returns the user name
     * @return username
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * Returns the team color
     * @return teamColor
     */
    public CabColor getTeamColor() {
        return this.teamColor;
    }

    /**
     * Returns the list of objective cards
     * @return objectiveCards
     */
    public ArrayList<ObjectiveCard> getObjectiveCards() {
        return this.objectiveCards;
    }

    /**
     * Adds an objective card to user's hand
     * @param o objective card
     */
    public void addObjectiveCard(ObjectiveCard o) {
        this.objectiveCards.add(o);
    }

    /**
     * Returns the user's transport cards
     * @return transportCards
     */
    public ArrayList<TransportCard> getTransportCards() {
        return this.transportCards;
    }

    /**
     * Adds a transport card to user's hand
     * @param t transport card
     */
    public void addTransportCard(TransportCard t) {
        this.transportCards.add(t);
    }

    /**
     * Adds a route to the list of user's claimed routes
     * @param route route to be claimed
     */
    public void addClaimedRoute(RouteNode route) {
        this.claimedRoutes.add(route);
    }

    /**
     * Returns the user's list of claimed routes
     * @return claimedRoutes
     */
    public ArrayList<RouteNode> getClaimedRoutes() {
        return this.claimedRoutes;
    }
}
