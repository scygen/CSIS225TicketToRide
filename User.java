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
  private ArrayList<CabCard> cabCards;
  private ArrayList<ObjectiveCard> objectiveCards;
  private int objectiveCardCt;
  private int cabCardCt;

  {
    objectiveCardCt = 2;
    cabCardCt = 15;
  }

  /**
   * Constructor
   * @param userName user name
   * @param teamColor team color
   * @param objectiveCardCt number of remaining objective cards
   * @param cabCardCt number of remaining cab cards
   */
  public User(String userName, CabColor teamColor, int objectiveCardCt, int cabCardCt) {
    this.userName = userName;
    this.teamColor = teamColor;
    this.objectiveCardCt = objectiveCardCt;
    this.cabCardCt = cabCardCt;

    // The logic for these still need to be implemented
    cabCards = null;
    objectiveCards = null;
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
  public ArrayList<CabCard> getCabCards() {
    return cabCards;
  }

  /**
   * Returns the objective card count
   * @return objectiveCardCt
   */
  public int getObjectiveCardCt() {
    return objectiveCardCt;
  }

  /**
   * Returns the cab card count
   * @return cabCardCt
   */
  public int getCabCardCt() {
    return cabCardCt;
  }

  /**
   * Sets the cab card count
   * @param cabCardCt the new cab card count
   */
  public void setCabCardCt(int cabCardCt) {
    this.cabCardCt = cabCardCt;
  }

  /**
   * Sets the objective card count
   * @param objectiveCardCt the new objective card count
   */
  public void setObjectiveCardCt(int objectiveCardCt) {
    this.objectiveCardCt = objectiveCardCt;
  }
}
