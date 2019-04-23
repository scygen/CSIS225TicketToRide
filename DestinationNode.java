/**
 * Object for tracking of destinations of the gameboard
 *
 * @author Steven Gibson
 * @version 1.0
 */
public class DestinationNode {

    // Destination label
    private Destination destination;

    // List of routes that this node connects to
    private RouteNode[] routeList;

    // Number of occupied routes
    private int routeIndex;

    // Flag to determine if node is a destination spot
    private Boolean isTouristAttraction;

    /**
     * Node Default Constructor
     * @param isTouristAttraction boolean flag to mark destination as tourist attraction (for scoring)
     * @param numRoutes number of routes that this node is connected to
     * @param destination destination value of node
     */
    public DestinationNode( boolean isTouristAttraction, int numRoutes, Destination destination) {
        this.isTouristAttraction = isTouristAttraction;
        this.routeList = new RouteNode[numRoutes];
        this.routeIndex = 0;
        this.destination = destination;
    }

    /**
     * Used to link two location nodes together with a route node between
     * @param n node to be linked to
     * @param numTaxis number of taxies the route between costs to occupy
     * @param rtColor color of the route between
     */
    public void linkLocation(DestinationNode n, int numTaxis, RouteColor rtColor) {
        if(this.routeIndex < this.routeList.length && n.routeIndex < n.routeList.length) {
            RouteNode rt = new RouteNode(rtColor, numTaxis, this, n);
            this.routeList[this.routeIndex++] = rt;
            n.routeList[n.routeIndex++] = rt;
        }
    }

    public Destination getDestination() {
        return this.destination;
    }

    /**
     * Returns whether this destination is a tourist attraction
     * @return isTouristAttraction
     */
    public Boolean getIsTouristAttraction() {
        return this.isTouristAttraction;
    }

    /**
     * Returns the number of routes this node is connected to
     * @return routeIndex
     */
    public int getRouteIndex() {
        return this.routeIndex;
    }

    /**
     * Returns the list of routes this node is connected to
     * @return routeIndex
     */
    public RouteNode[] getRoutes() {
        return this.routeList;
    }

}
