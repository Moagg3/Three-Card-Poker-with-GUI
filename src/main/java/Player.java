import java.util.ArrayList;
import java.util.*;

public class Player{
    ArrayList<Card> hand;
    int anteBet;
    int playBet;
    int pairPlusBet;
    int totalWinnings;
    
    Boolean finishedEnteringBets;
    Boolean playBetEntered;
    String PlayerNumber;

    Player( ){
        hand = new ArrayList<Card>();
        anteBet = playBet = pairPlusBet = totalWinnings = 0;
        playBetEntered = finishedEnteringBets = false;
        PlayerNumber = "";
    }

    void print_PlayersHand( ){
        sort_hand(hand);
        if(hand.size() == 0){
            System.out.println("Player's hand is empty.");
            return;
        }
        hand.forEach(e->System.out.println("Suit: " + e.get_suit() + "; Value: " + e.get_value()));
    }

    private void sort_hand(ArrayList<Card> hand){
        Collections.sort(hand, new Comparator<Card>(){
            public int compare(Card c1, Card c2){
                return Integer.valueOf(c1.get_value()).compareTo(c2.get_value());
            }
        });
    }

    // Returns Value + Suit
    public String get_card(int index){
        return String.valueOf(hand.get(index).get_value()) + hand.get(index).get_suit()   + ".jpg";
    }

    public void set_pairPlusBet(int bet){
        if(bet > 0)
            pairPlusBet = bet;
    }

    public void set_anteBet(int bet){
        anteBet = bet;
    }

    public Boolean hasPlaced_PairPlusBet( ){
        return pairPlusBet > 0;
    }

    public void reset_bets( ){
        anteBet = playBet = pairPlusBet = 0;
    }

}