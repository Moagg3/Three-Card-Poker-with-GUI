// Card class that contains two private data fields
// for suit and value

public class Card{
    private char suit;
    private int  value;

    // Param. Constructor that assigns suit and value to the data fields
    Card(char _suit, int _value) {

        // Validates suit and value prior to initializing data fields
        if(!valid_suit(_suit) || !valid_value(_value)){
            System.out.println("    >> Invalid suit entry.");
            return;
        }

        this.suit = _suit; 
        this.value = _value;
    }

    // Accessor functions for suit and value of the card
    public char get_suit( )  {return this.suit;}
    public int  get_value( ) {return this.value;}

    // Prints the card value and suit on console
    public void print_card( ){
        System.out.println("Suit: " + this.suit + "; Value: " + this.value);
    }

    // Verifies if suit is valid
    private Boolean valid_suit(char _suit){
        char[] suit_array = {'C', 'D', 'S', 'H'};

        for(int i = 0; i < 4; ++i){
            if(_suit == suit_array[i])
                return true;
        }
        return false;
    }

    // Verifies if card value is valid (within 2 to 14 range)
    private Boolean valid_value(int _value){
        if(_value < 2 || _value > 14)
            return false;
        return true;
    }
}