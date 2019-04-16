import java.util.ArrayList;
import java.util.Collections;
/**
 * Creates a deck which holds an ArrayList of card objects
 *
 * @author Laura Hendley
 * @version Ticket to Ride 
 */
public class Deck
{
    protected ArrayList<Card> deck;
    protected ArrayList<Card> discard;
    /**
     * Constructor for Deck object
     */
    public Deck()
    {
        deck = new ArrayList<Card>();
        discard = new ArrayList<Card>();
    }
    
    /**
     * Gets the size of the playable deck
     * 
     * @return an int representing the size of the playable deck
     */
    public int getDeckSize(){
        return deck.size();
    }

    /**
     * Gets the size of the discarded deck
     * 
     * @return an int representing the size of the discarded deck
     */
    public int getDiscardSize(){
        return discard.size();
    }
    
    /**
     * Adds a card to the playable deck
     * 
     * @param a card object to be added to the playable deck
     */
    public void addDeck(Card card){
        deck.add(card);
    }
    
    /**
     * Adds a card to the deck of discarded cards
     * 
     * @param a card object to be added to the discard deck
     */
    public void addDiscard(Card card){
        discard.add(card);
    }

    /**
     *  Draw method which finds and returns the first card from the deck
     * 
     * @return  a card object that is the card on the top of the deck
     */
    public Card draw(){
        Card topCard = null;
        if(deck.get(0) != null){
            //Returns the card from the top of the deck if cards remain
            //in the playable deck
            topCard = deck.get(0);
            deck.remove(0);
        }
        else if(discard.size()!=0){
            //Case if there are no more cards in the playable deck but
            //there are cards in the discard deck.
            shuffle();
            topCard = deck.get(0);
            deck.remove(0);
        }
        //Returns the first card in the deck, unless there are no
        //more cards to be dealt, then it returns null.
        return topCard;
    }

    /**
     * This method takes the discarded cards and replaces them into the
     * playable deck.
     */
    public void shuffle(){
        //Adds cards from discard back into playable deck
        for(int i = 0; i < discard.size(); i++){
            deck.add(discard.get(i));
        }
        //Shuffles the cards in the deck
        Collections.shuffle(deck);
        //Clears the deck of discarded cards
        discard.clear();
    }
    
    public void bottomOfDeck(Card card){
        deck.add(card);
    }
}
