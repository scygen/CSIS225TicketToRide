
/**
 * Write a description of class Driver here.
 *
 * @author Laura Hendley
 * @version 4/16/2019
 */
public class Driver
{
    public static void main(String args[]){
        //Variables to hold the information of each player to be used in the call to the cosntructor
        User player1;
        String player1Name;
        CabColor player1Color;
        int player1ObjCardCt;
        int player1CabCardCt;

        User player2;
        String player2Name;
        CabColor player2Color;
        int player2ObjCardCt;
        int player2CabCardCt;

        User player3;
        String player3Name;
        CabColor player3Color;
        int player3ObjCardCt;
        int player3CabCardCt;

        User player4;
        String player4Name;
        CabColor player4Color;
        int player4ObjCardCt;
        int player4CabCardCt;

        //Selected number of players for this game
        int numPlayers = 0;

        boolean finalTurn = false;
        
        int playerNum = 0;
        int player1Score = 0;
        int player2Score = 0;
        int player3Score = 0;
        int player4Score = 0;
        //
        //HOW TO GET THE VALUES FOR THE CONSTRUCTORS?
        //

        //Instantiates the desired number of users for the game, between 2 and 4.
        if(numPlayers == 2){
            player1 = new User(player1Name, player1Color, player1ObjCardCt, player1CabCardCt);
            player2 = new User(player2Name, player2Color, player2ObjCardCt, player2CabCardCt);
            while(finalTurn == false){
                playerNum = 1;
                takeTurn(player1);
                playerNum = 2;
                takeTurn(player2);
            }
            //Code last turn outside of loop
            
            player1Score = score(player1);
            player2Score = score(player2);
        }
        else if(numPlayers == 3){
            player1 = new User(player1Name, player1Color, player1ObjCardCt, player1CabCardCt);
            player2 = new User(player2Name, player2Color, player2ObjCardCt, player2CabCardCt);
            player3 = new User(player3Name, player3Color, player3ObjCardCt, player3CabCardCt);
            while(finalTurn == false){
                playerNum = 1;
                takeTurn(player1);
                playerNum = 2;
                takeTurn(player2);
                playerNum = 3;
                takeTurn(player3);
            }
            //Code last turn outside of loop
            
            player1Score = score(player1);
            player2Score = score(player2);
            player3Score = score(player3);
        }
        else{
            player1 = new User(player1Name, player1Color, player1ObjCardCt, player1CabCardCt);
            player2 = new User(player2Name, player2Color, player2ObjCardCt, player2CabCardCt);
            player3 = new User(player3Name, player3Color, player3ObjCardCt, player3CabCardCt);
            player4 = new User(player4Name, player4Color, player4ObjCardCt, player4CabCardCt);
            while(finalTurn == false){
                playerNum = 1;
                takeTurn(player1);
                playerNum = 2;
                takeTurn(player2);
                playerNum = 3;
                takeTurn(player3);
                playerNum = 4;
                takeTurn(player4);
            }
            //Code last turn outside of loop
            
            player1Score = score(player1);
            player2Score = score(player2);
            player3Score = score(player3);
            player4Score = score(player4);
        }
    }

    public static void takeTurn(User player){
        String choice = "";
        int cardsDrawn = 0;
        boolean faceUpRainbow = false;
        if(choice.equals("drawTransportCard")){
            while(cardsDrawn != 2 || faceUpRainbow == false){
                if(drawCardFaceUp && getTransColor() == RAINBOW ){
                    player.add(RAINBOW);
                    faceUpRainbow = true;
                }
                else if(drawCardFaceUp && getTransColor() != RAINBOW){
                    player.add(card);
                    cardsDrawn++;
                }
                else if(drawFromDeck){
                    player.add(card);
                    cardsDrawn++;
                }
            }
        }
        else if(choice.equals("drawDestCards")){
            ObjectiveCard dest1 = drawDestCard();
            ObjectiveCard dest2 = drawDestCard();
            //Give an option to choose one or both cards
            if(choice == dest1){
                player.add(dest1);
                bottomDeck(dest2);
            }
            else if(choice == dest2){
                player.add(dest2);
                bottomDeck(dest1);
            }
            else{
                player.add(dest1);
                player.add(dest2);
            }
        }
        else{
            //Need to check for rainbows in hand
            if(numCards != routeSize || cardColor != routeColor || cardColor != RAINBOW){
                System.out.print("You do not have the correct cards to claim this route");
            }
            else if(numCabs < routeSize){
                System.out.print("You do not have enough cabs to claim this route");
            }
            else if(routeClaimed == false){
                claimRoute();
                numCabs = numCabs - routeSize;
                if(numCabs <= 2){
                    finalTurn = true;
                }
            }
        }
    }

    public static int score(User player){

    }
}
