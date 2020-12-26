// The class contains static functions that performs three type of 
// evaluations:
//      1. evalHand(ArrayList<Card> hand): 
//              Evaluates 3-Card Hand to determines if it is either
//              straight flush, 3ofakind, straight, flush, or a pair
//
//      2. evalPPWinnings(ArrayList<Card> hand, int bet):
//              Evaluates the Pair Plus winning from a given hand
//
//      3. compareHands(ArrayList<Card> dealer, ArrayList<Card> player):
//              Evaluates the winner from the given two hands

import java.util.*;
import java.util.Comparator;

public class ThreeCardLogic{
    public static int evalHand(ArrayList<Card> hand){
        if     (is_straightFlush(hand)) return 1;
        else if(is_3_of_a_kind(hand))   return 2;
        else if(is_straight(hand))      return 3;
        else if(is_flush(hand))         return 4;
        else if(is_pair(hand))          return 5;
        else   /* high card*/           return 0; 
    }

    public static int evalPPWinnings(ArrayList<Card> hand, int bet){
        if     (is_straightFlush(hand)) return 40 * bet;
        else if(is_3_of_a_kind(hand))   return 30 * bet;
        else if(is_straight(hand))      return  6 * bet;
        else if(is_flush(hand))         return  3 * bet;
        else if(is_pair(hand))          return  1 * bet;
        else   /* high card*/           return  0; 
    }

    public static int compareHands(ArrayList<Card> dealer, ArrayList<Card> player){
        int dealerHand_rank = evalHand(dealer);
        int playerHand_rank = evalHand(player);

        if     (dealerHand_rank == 0) System.out.println("dealerHand's Hand: HighCard");
        else if(dealerHand_rank == 1) System.out.println("dealerHand's Hand: Straight Flush");
        else if(dealerHand_rank == 2) System.out.println("dealerHand's Hand: 3 of a kind");
        else if(dealerHand_rank == 3) System.out.println("dealerHand's Hand: Straight");
        else if(dealerHand_rank == 4) System.out.println("dealerHand's Hand: Flush");
        else if(dealerHand_rank == 5) System.out.println("dealerHand's Hand: Pair");

        if     (playerHand_rank == 0) System.out.println("Player's Hand: HighCard");
        else if(playerHand_rank == 1) System.out.println("Player's Hand: Straight Flush");
        else if(playerHand_rank == 2) System.out.println("Player's Hand: 3 of a kind");
        else if(playerHand_rank == 3) System.out.println("Player's Hand: Straight");
        else if(playerHand_rank == 4) System.out.println("Player's Hand: Flush");
        else if(playerHand_rank == 5) System.out.println("Player's Hand: Pair");

        // Case one party gets a HighCard hand
        if     (dealerHand_rank == 0 && playerHand_rank > 0) return 2;
        else if(playerHand_rank == 0 && dealerHand_rank > 0) return 1;

        // Case neither party gets a HighCard hand
        // if     (dealerHand_rank < playerHand_rank)  return 1; // Dealer wins
        // else if(playerHand_rank < dealerHand_rank)  return 2; // Player wins
        // else                                        return 0; // Tie

        if     (dealerHand_rank < playerHand_rank){
            System.out.println("    >> DEALER WINS! ");
            return 1;
        }
        else if(playerHand_rank < dealerHand_rank){
            System.out.println("    >> PLAYER WINS! ");
            return 2;
        }
        else{
            System.out.println("    >> TIE! ");
            return 0;
        }
    }

// PRIVATE HELP FUNCTIONS
    private static Boolean is_straight(ArrayList<Card> hand){
        sort_hand(hand);
        // hand.forEach(e->e.print_card());
        for(int i = 0; i < hand.size()-1; ++i){
            int offset_val = Math.abs(hand.get(i).get_value() - hand.get(i + 1).get_value());
            if(offset_val != 1)
                return false;
        }
        return true;
    }

    private static Boolean is_flush(ArrayList<Card> hand){
        for(int i = 0; i < hand.size()-1; ++i){
            if(hand.get(i).get_suit() != hand.get(i + 1).get_suit())
                return false;
        }
        return true; 
    }

    private static Boolean is_3_of_a_kind(ArrayList<Card> hand){
        for(int i = 0; i < hand.size()-1; ++i){
            if(hand.get(i).get_value() != hand.get(i + 1).get_value())
                return false;
        }
        return true; 
    }

    private static Boolean is_straightFlush(ArrayList<Card> hand){
        if(is_straight(hand) && is_flush(hand))
            return true;
        return false;
    }

    private static Boolean is_pair(ArrayList<Card> hand){
        sort_hand(hand);
        if(!is_3_of_a_kind(hand)){
            for(int i = 0; i < hand.size()-1; ++i){
                if(hand.get(i).get_value() - hand.get(i + 1).get_value() == 0)
                    return true;
            }
        }
        return false; 
    }

    private static void sort_hand(ArrayList<Card> hand){
        Collections.sort(hand, new Comparator<Card>(){
            public int compare(Card c1, Card c2){
                return Integer.valueOf(c1.get_value()).compareTo(c2.get_value());
            }
        });
    }
}