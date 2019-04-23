/**
 * Creates a graph to represent the board
 *
 * @author Steven Gibson
 * @version 4/20/19
 */
public class Board {

    // List of destination references on the gameboard
    DestinationReferences locRefs;

    /**
     * Constructor
     */
    public Board() {

        // Adding all destination nodes to the board
        DestinationNode wallStreetNode = new DestinationNode(true, 5, Destination.WALL_STREET);
        DestinationNode brooklynNode   = new DestinationNode(true, 5, Destination.BROOKLYN);
        DestinationNode chinatownNode  = new DestinationNode(true, 7, Destination.CHINATOWN);
        DestinationNode greenwichNode  = new DestinationNode(true, 9, Destination.GREENWICH_VILLAGE);
        DestinationNode chelseaNode    = new DestinationNode(true, 7, Destination.CHELSEA);
        DestinationNode eSBNode        = new DestinationNode(true, 8, Destination.EMPIRE_STATE_BUILDING);
        DestinationNode timeSqrNode    = new DestinationNode(true, 8, Destination.TIMES_SQUARE);
        DestinationNode uNNode         = new DestinationNode(true, 4, Destination.UNITED_NATIONS);
        DestinationNode centralPrkNode = new DestinationNode(true, 4, Destination.CENTRAL_PARK);
        DestinationNode sohoNode       = new DestinationNode(false, 3, Destination.SOHO);
        DestinationNode lESNode        = new DestinationNode(false, 4, Destination.LOWER_EAST_SIDE);
        DestinationNode eastVilNode    = new DestinationNode(false, 3, Destination.EAST_VILLAGE);
        DestinationNode gramercyNode   = new DestinationNode(false, 7, Destination.GRAMERCY_PARK);
        DestinationNode midtownNode    = new DestinationNode(false, 4, Destination.MIDTOWN_WEST);
        DestinationNode lincolnCtrNode = new DestinationNode(false, 4, Destination.LINCOLN_CENTER);

        // Connecting all nodes together
        wallStreetNode.linkLocation(brooklynNode,3,RouteColor.BLACK);
        wallStreetNode.linkLocation(brooklynNode,3,RouteColor.BLUE);
        wallStreetNode.linkLocation(chinatownNode,1,RouteColor.GREEN);
        wallStreetNode.linkLocation(chinatownNode,1,RouteColor.PINK);
        wallStreetNode.linkLocation(sohoNode,2,RouteColor.GREY);
        brooklynNode.linkLocation(chinatownNode, 3,RouteColor.RED);
        brooklynNode.linkLocation(chinatownNode, 3,RouteColor.ORANGE);
        brooklynNode.linkLocation(lESNode, 3,RouteColor.GREY);
        lESNode.linkLocation(chinatownNode,1, RouteColor.BLUE);
        lESNode.linkLocation(greenwichNode,2,RouteColor.GREY);
        lESNode.linkLocation(eastVilNode,1,RouteColor.BLACK);
        sohoNode.linkLocation(greenwichNode,2,RouteColor.ORANGE);
        sohoNode.linkLocation(chelseaNode,4,RouteColor.PINK);
        chinatownNode.linkLocation(greenwichNode,2,RouteColor.GREY);
        chinatownNode.linkLocation(greenwichNode,2,RouteColor.GREY);
        eastVilNode.linkLocation(greenwichNode,2, RouteColor.BLUE);
        eastVilNode.linkLocation(gramercyNode,2,RouteColor.GREY);
        greenwichNode.linkLocation(chelseaNode,3,RouteColor.GREEN);
        greenwichNode.linkLocation(chelseaNode,3,RouteColor.RED);
        greenwichNode.linkLocation(gramercyNode,2,RouteColor.BLACK);
        greenwichNode.linkLocation(gramercyNode,2,RouteColor.PINK);
        gramercyNode.linkLocation(chelseaNode,2,RouteColor.ORANGE);
        gramercyNode.linkLocation(eSBNode,1,RouteColor.RED);
        gramercyNode.linkLocation(eSBNode,1,RouteColor.BLUE);
        gramercyNode.linkLocation(uNNode,3,RouteColor.GREEN);
        chelseaNode.linkLocation(eSBNode,2,RouteColor.GREY);
        chelseaNode.linkLocation(eSBNode,2,RouteColor.GREY);
        chelseaNode.linkLocation(midtownNode,2,RouteColor.BLUE);
        eSBNode.linkLocation(uNNode,2,RouteColor.BLACK);
        eSBNode.linkLocation(timeSqrNode,1,RouteColor.ORANGE);
        eSBNode.linkLocation(timeSqrNode,1,RouteColor.PINK);
        eSBNode.linkLocation(midtownNode,2,RouteColor.GREEN);
        midtownNode.linkLocation(timeSqrNode,1,RouteColor.GREY);
        midtownNode.linkLocation(lincolnCtrNode,2,RouteColor.RED);
        timeSqrNode.linkLocation(lincolnCtrNode,2,RouteColor.GREEN);
        timeSqrNode.linkLocation(lincolnCtrNode,2,RouteColor.BLUE);
        timeSqrNode.linkLocation(centralPrkNode,2,RouteColor.BLACK);
        timeSqrNode.linkLocation(centralPrkNode,2,RouteColor.RED);
        uNNode.linkLocation(centralPrkNode,3,RouteColor.PINK);
        uNNode.linkLocation(timeSqrNode,2,RouteColor.GREY);
        lincolnCtrNode.linkLocation(centralPrkNode,2,RouteColor.ORANGE);


        // Storing all references to the destination nodes
        locRefs = new DestinationReferences();
        locRefs.addLocation(wallStreetNode);
        locRefs.addLocation(brooklynNode);
        locRefs.addLocation(chinatownNode);
        locRefs.addLocation(greenwichNode);
        locRefs.addLocation(chelseaNode);
        locRefs.addLocation(eSBNode);
        locRefs.addLocation(timeSqrNode);
        locRefs.addLocation(uNNode);
        locRefs.addLocation(centralPrkNode);
        locRefs.addLocation(sohoNode);
        locRefs.addLocation(lESNode);
        locRefs.addLocation(eastVilNode);
        locRefs.addLocation(gramercyNode);
        locRefs.addLocation(midtownNode);
        locRefs.addLocation(lincolnCtrNode);


    }

    /**
     * Returns the list of destination references
     * @return locRefs
     */
    DestinationReferences getDestinationReferences() {
        return this.locRefs;
    }


}
