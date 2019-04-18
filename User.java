import java.util.ArrayList;

/**
 * User object for tracking information about a user
 *
 * @author Steven Gibson
 * @version 1.0
 */
public class User {


  private String userName;
  private CabColor teamColor;
  //private ArrayList<CabCard> cabCards;
  private ArrayList<ObjectiveCard> objectiveCards;
  private ArrayList<TransportCard> transportCards;
  private int objectiveCardCt;
  private int transportCardCt;
  private int score;
  private int numTaxies;

  // Default values for each player
  {
    objectiveCardCt = 2;
    transportCardCt = 15;
    numTaxies = 15;
  }

  /**
   * Constructor
   * @param userName user name
   * @param teamColor team color
   */
  public User(String userName, CabColor teamColor) {
    this.userName = userName;
    this.teamColor = teamColor;

    // The logic for these still need to be implemented
    //cabCards = null;
    objectiveCards = null;
    transportCards = null;
  }

  /**
   * Returns the user name
   * @return username
   */
  public String getUserName() {
    return userName;
  }

  /**
   * Returns the team color
   * @return teamColor
   */
  public CabColor getTeamColor() {
    return teamColor;
  }

  /**
   * Returns the list of objective cards
   * @return objectiveCards
   */
  public ArrayList<ObjectiveCard> getObjectiveCards() {
    return objectiveCards;
  }

  /**
   * Returns the list of cab cards
   * @return cabCards
   */
  //public ArrayList<CabCard> getCabCards() {
  //  return cabCards;
  //}

  /**
   * Returns the objective card count
   * @return objectiveCardCt
   */
  public int getObjectiveCardCt() {
    return objectiveCardCt;
  }


  /**
   * Sets the objective card count
   * @param objectiveCardCt the new objective card count
   */
  public void setObjectiveCardCt(int objectiveCardCt) {
    this.objectiveCardCt = objectiveCardCt;
  }
}
