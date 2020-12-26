// Deck class that represents the card deck in the game
// The following class contains array list of cards, max 52
// and a newDeck function that retrieves a newly shuffled deck.

import java.util.ArrayList;
import java.util.Collections;

public class Deck extends ArrayList<Card>{
    Deck( ){
        newDeck();
    }

    void newDeck( ){
        this.clear( );
        ArrayList<Character> suit_array = new ArrayList<Character>();
        suit_array.add('C');
        suit_array.add('D');
        suit_array.add('S');
        suit_array.add('H');

        for(int current_suit = 0; current_suit < 4; ++current_suit){
            for(int value = 2; value <= 14; ++value)
                this.add(new Card(suit_array.get(current_suit), value));
        }
        
        // This randomizes the deck.
        Collections.shuffle(this);
    }    

    void print_deck( ){
        this.forEach(e->System.out.println("Suit: " + e.get_suit() + "; Value: " + e.get_value()));
    }
}