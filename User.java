import java.awt.*;
import java.util.ArrayList;

public class User {


  private String userName;
  private Color teamColor;
  private ArrayList<CabCard> cabCards;
  private ArrayList<ObjectiveCard> objectiveCards;
  private int objectiveCardCt;
  private int cabCardCt;

  {
    objectiveCardCt = 2;
    cabCardCt = 15;
  }

  public User(String userName, Color teamColor, int objectiveCardCt, int cabCardCt) {
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
   */
  public String getUserName() {
    return userName;
  }

  /**
   * Returns the team color
   */
  public Color getTeamColor() {
    return teamColor;
  }

  /**
   * Returns the list of objective cards
   */
  public ArrayList<ObjectiveCard> getObjectiveCards() {
    return objectiveCards;
  }

  /**
   * Returns the list of cab cards
   */
  public ArrayList<CabCard> getCabCards() {
    return cabCards;
  }

  /**
   * Returns the objective card count
   */
  public int getObjectiveCardCt() {
    return objectiveCardCt;
  }


  public int getCabCardCt() {
    return cabCardCt;
  }

  public void setCabCardCt(int cabCardCt) {
    this.cabCardCt = cabCardCt;
  }

  public void setObjectiveCardCt(int objectiveCardCt) {
    this.objectiveCardCt = objectiveCardCt;
  }
}
