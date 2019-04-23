
/**
 * Object for storing information about routes between destinations
 *
 * @author Steven Gibson
 * @version 1.0
 */
public class RouteNode {
    private RouteColor routeColor;
    private int numTaxis;
    private CabColor occupyingPlayer;
    private DestinationNode link1;
    private DestinationNode link2;

    /**
     * Constructor
     * @param routeColor color of the route
     * @param numTaxis number of taxies the route costs
     * @param link1 first destination that route is connected to
     * @param link2 second destination that route is connected to
     */
    public RouteNode(RouteColor routeColor, int numTaxis, DestinationNode link1, DestinationNode link2) {
        this.routeColor = routeColor;
        this.numTaxis = numTaxis;
        this.occupyingPlayer = CabColor.NONE;
        this.link1 = link1;
        this.link2 = link2;
    }

    /**
     * Returns the first destination that route is connected to
     * @return link1
     */
    public DestinationNode getLink1() {
        return this.link1;
    }

    /**
     * Returns the second destination that route is connected to
     * @return link2
     */
    public DestinationNode getLink2() {
        return this.link2;
    }

    /**
     * Returns team color of the player that is occupying this route
     * @return occupyingPlayer
     */
    public CabColor getOccupyingPlayer() {
        return this.occupyingPlayer;
    }

    /**
     * Returns route color of this route
     * @return routeColor
     */
    public RouteColor getRouteColor() {
        return this.routeColor;
    }

    /**
     * Returns cost in taxis of this route
     * @return numTaxis
     */
    public int getNumTaxis() {
        return this.numTaxis;
    }

    /**
     * Sets the team color of the player that is occupying this route
     * @param color color of the player occupying this route
     */
    public void setOccupyingPlayer(CabColor color) {
        this.occupyingPlayer = color;
    }
}
