import java.util.ArrayList;

/**
 * Object for storing references for all destinations of board for easy access
 *
 * @author Steven Gibson
 * @version 1.0
 */
public class DestinationReferences {

    private ArrayList<DestinationNode> nodes;

    /**
     * Constructor
     */
    public DestinationReferences() {
        this.nodes = new ArrayList<DestinationNode>();
    }

    /**
     * Adds a location (destination) to the reference list
     * @param loc location to be added to list
     */
    public void addLocation(DestinationNode loc) {
        this.nodes.add(loc);
    }

    /**
     * Finds a location in the reference list based on destination value
     * @return destination
     */
    public DestinationNode findLocation(Destination dest) {
        return this.nodes.stream().filter(n -> n.getDestination().equals(dest)).findFirst().orElse(null);
    }

    /**
     * Returns the list of destination references
     * @return nodes
     */
    public ArrayList<DestinationNode>getLocations() {
        return this.nodes;
    }

    /**
     * Returns a single location based on index into reference list
     * @return destination
     */
    public DestinationNode getLocation(int ind) {
        return this.nodes.get(ind);
    }

}
