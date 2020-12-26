// Dealer class that represents the dealer in the game.
// The dealer class deals the cards to players or to itself.

import java.util.ArrayList;
import java.util.*;

public class Dealer{
    public Deck theDeck;
    public ArrayList<Card> dealersHand;

    Dealer( ) { theDeck = new Deck( );}
    
    void set_dealersHand(ArrayList<Card> Hand) { dealersHand = Hand;}
    int get_DeckSize( )                        { return theDeck.size( );}

    public void getNewDeck( ){
        theDeck.newDeck( );
    }

    public ArrayList<Card> dealHand( ){
        dealersHand = new ArrayList<Card>();
        for(int i = 0; i < 3; ++i)
            dealersHand.add(theDeck.remove(0));
        return dealersHand;
    }
    
    void print_DealersHand( ){
        sort_hand(dealersHand);
        if(dealersHand.size() == 0){
            System.out.println("Dealer's hand is empty.");
            return;
        }
        dealersHand.forEach(e->System.out.println("Suit: " + e.get_suit() + "; Value: " + e.get_value()));
    }

    private void sort_hand(ArrayList<Card> hand){
        Collections.sort(hand, new Comparator<Card>(){
            public int compare(Card c1, Card c2){
                return Integer.valueOf(c1.get_value()).compareTo(c2.get_value());
            }
        });
    }

    public String get_card(int index){
        return String.valueOf(dealersHand.get(index).get_value()) + dealersHand.get(index).get_suit()   + ".jpg";
    }

    Boolean has_Queen_or_higher( ){
        System.out.println("     >> has_Queen_or_higher() . . . ");
        for(int i = 0; i < dealersHand.size(); ++i){
            if(dealersHand.get(i).get_value() > 11){
                System.out.println("Value: " + theDeck.get(i).get_value());
                return true;
            }
        }
        return false;
    }

}