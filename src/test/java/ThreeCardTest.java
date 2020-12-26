import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ThreeCardTest {
    public static ArrayList<Card> Player_Hand;
    public static ArrayList<Card> Dealer_Hand;

    public static Deck Deck01;
    public static Dealer Dealer_01;

    // ThreeCardLogic class, at least 20 test cases
    // Deck and Dealer class, at least 10 test cases
    @BeforeEach
    void setup( ){
        Deck01 = new Deck( );
        Dealer_01 = new Dealer( );
        Player_Hand = new ArrayList<Card>( );
        Dealer_Hand = new ArrayList<Card>( );
    }

    // Prints out the info on the console for each test cases
//* DECK AND DEALER TEST CASES
    @Test
    void DeckSize_Test( ){
        assertEquals(52, Deck01.size(), "Deck size is not 52");
    }

    @Test
    void newDeck_refertosameObject_Test( ){
        Deck temp = Deck01;
        temp.newDeck();
        assertSame(Deck01, temp);
    }

    @Test
    void Dealer_Constructor_Test( ){
        assertEquals(52, Dealer_01.get_DeckSize( ), "Incorrect deck size");
    }

    @Test
    void Dealer_getNewDeck_Test( ){
        Dealer_01.dealHand( );
        Dealer_01.getNewDeck( );
        assertEquals(52, Dealer_01.get_DeckSize( ), "Incorrect deck size");
    }

    @Test
    void Dealer_dealHand_Test( ){
        Dealer_01.dealHand( );
        assertEquals(49, Dealer_01.get_DeckSize( ), "Incorrect deck size");
    }

    @Test
    void Dealer_dealHand_Test2( ){
        Dealer_01.dealHand( );
        Dealer_01.dealHand( );
        assertEquals(46, Dealer_01.get_DeckSize( ), "Incorrect deck size");
    }

    @Test
    void Dealer_has_Queen_or_higher_Test( ){
        Deck01.add(new Card('H', 12));
        Deck01.add(new Card('S', 2));
        Deck01.add(new Card('C', 6));
        Dealer_01.set_dealersHand(Deck01);
        assertTrue(Dealer_01.has_Queen_or_higher( ), "Should've been true since dealer does have at least a queen ranked card");
    }

    @Test
    void Dealer_has_Queen_or_higher_Test2( ){
        Deck01.add(new Card('D', 1));
        Deck01.add(new Card('C', 14));
        Deck01.add(new Card('C', 6));
        Dealer_01.set_dealersHand(Deck01);
        assertTrue(Dealer_01.has_Queen_or_higher( ), "Should've been true since dealer does have at least a queen ranked card");
    }

    
    @Test
    void Deck_two_separateDecks_notReffer_to_same_object( ){
        assertNotSame(Deck01, new Deck(), "Should've not reffered to same object");
    }

    @Test
    void Dealer_two_separateDealers_notReffer_to_same_object( ){
        assertNotSame(Dealer_01, new Dealer(), "Should've not reffered to same object");
    }


//* EVALHAND() TEST CASES ---------------------------------------------------
	@Test
	void Pair_01_Test( ) {
        Player_Hand.add(new Card('H', 2));
        Player_Hand.add(new Card('S', 2));
        Player_Hand.add(new Card('C', 6));
        assertEquals(5, ThreeCardLogic.evalHand(Player_Hand), "Pair not found.");
    }

    @Test
	void Flush_01_Test( ) {
        Player_Hand.add(new Card('D', 9));
        Player_Hand.add(new Card('D', 13));
        Player_Hand.add(new Card('D', 6));
        assertEquals(4, ThreeCardLogic.evalHand(Player_Hand), "Flush not found.");
    }

    @Test
	void Straight_01_Test( ) {
        Player_Hand.add(new Card('D', 8));
        Player_Hand.add(new Card('C', 7));
        Player_Hand.add(new Card('S', 6));
        assertEquals(3, ThreeCardLogic.evalHand(Player_Hand), "Straight not found.");
    }

    @Test
	void ThreeOfKind_01_Test( ) {
        Player_Hand.add(new Card('D', 12));
        Player_Hand.add(new Card('C', 12));
        Player_Hand.add(new Card('S', 12));
        assertEquals(2, ThreeCardLogic.evalHand(Player_Hand), "Three-of-a-kind not found.");
    }

    @Test
	void StraightFlush_01_Test( ) {
        Player_Hand.add(new Card('C', 10));
        Player_Hand.add(new Card('C', 9));
        Player_Hand.add(new Card('C', 8));
        assertEquals(1, ThreeCardLogic.evalHand(Player_Hand), "Straight-Flush not found.");
    }

//* EVALPPWINNINGS() TEST CASES ---------------------------------------------------
@Test
void Pair_01_Test_EvalPP( ) {
    Player_Hand.add(new Card('H', 2));
    Player_Hand.add(new Card('S', 2));
    Player_Hand.add(new Card('C', 6));
    assertEquals(1, ThreeCardLogic.evalPPWinnings(Player_Hand, 1), "Pair evalPPWinning not correct.");
}

@Test
void Flush_01_Test_EvalPP( ) {
    Player_Hand.add(new Card('D', 9));
    Player_Hand.add(new Card('D', 13));
    Player_Hand.add(new Card('D', 6));
    assertEquals(3, ThreeCardLogic.evalPPWinnings(Player_Hand, 1), "Flush evalPPWinning not correct.");
}

@Test
void Straight_01_Test_EvalPP( ) {
    Player_Hand.add(new Card('D', 8));
    Player_Hand.add(new Card('C', 7));
    Player_Hand.add(new Card('S', 6));
    assertEquals(6, ThreeCardLogic.evalPPWinnings(Player_Hand, 1), "Straight evalPPWinning not correct.");
}

@Test
void ThreeOfKind_01_Test_EvalPP( ) {
    Player_Hand.add(new Card('D', 12));
    Player_Hand.add(new Card('C', 12));
    Player_Hand.add(new Card('S', 12));
    assertEquals(30, ThreeCardLogic.evalPPWinnings(Player_Hand, 1), "Three-of-a-kind evalPPWinning not correct.");
}

@Test
void StraightFlush_01_Test_EvalPP( ) {
    Player_Hand.add(new Card('C', 10));
    Player_Hand.add(new Card('C', 9));
    Player_Hand.add(new Card('C', 8));
    assertEquals(40, ThreeCardLogic.evalPPWinnings(Player_Hand,1 ), "Straight-Flush evalPPWinning not correct.");
}

//* COMPAREHANDS() TEST CASES ---------------------------------------------------
@Test
void Pair_01_Test_CompHands( ) {
    // Pairs
    Player_Hand.add(new Card('H', 2));
    Player_Hand.add(new Card('S', 2));
    Player_Hand.add(new Card('C', 6));

    // Flush
    Dealer_Hand.add(new Card('D', 9));
    Dealer_Hand.add(new Card('D', 13));
    Dealer_Hand.add(new Card('D', 6));
    assertEquals(1, ThreeCardLogic.compareHands(Dealer_Hand, Player_Hand), "Dealer should've won.");
}

@Test
void Flush_01_Test_CompHands( ) {
    // Flush
    Player_Hand.add(new Card('D', 9));
    Player_Hand.add(new Card('D', 13));
    Player_Hand.add(new Card('D', 6));

    // Straight
    Dealer_Hand.add(new Card('D', 8));
    Dealer_Hand.add(new Card('C', 7));
    Dealer_Hand.add(new Card('S', 6));
    assertEquals(1, ThreeCardLogic.compareHands(Dealer_Hand, Player_Hand), "Dealer should've won.");
}

@Test
void Straight_01_Test_CompHands( ) {
    // 3-of-kind
    Player_Hand.add(new Card('D', 12));
    Player_Hand.add(new Card('C', 12));
    Player_Hand.add(new Card('S', 12));

    // Straight
    Dealer_Hand.add(new Card('D', 8));
    Dealer_Hand.add(new Card('C', 7));
    Dealer_Hand.add(new Card('S', 6));

    assertEquals(2, ThreeCardLogic.compareHands(Dealer_Hand, Player_Hand), "Player should've won.");
}

@Test
void ThreeOfKind_01_Test_CompHands( ) {
    // 3-of-kind
    Player_Hand.add(new Card('D', 12));
    Player_Hand.add(new Card('C', 12));
    Player_Hand.add(new Card('S', 12));

    // Straight Flush
    Dealer_Hand.add(new Card('C', 10));
    Dealer_Hand.add(new Card('C', 9));
    Dealer_Hand.add(new Card('C', 8));
    assertEquals(1, ThreeCardLogic.compareHands(Dealer_Hand, Player_Hand), "Dealer should've won.");
}

@Test
void StraightFlush_01_Test_CompHands( ) {
    // StraightFlush
    Player_Hand.add(new Card('C', 10));
    Player_Hand.add(new Card('C', 9));
    Player_Hand.add(new Card('C', 8));

    // Flush
    Dealer_Hand.add(new Card('D', 9));
    Dealer_Hand.add(new Card('D', 13));
    Dealer_Hand.add(new Card('D', 6));
    assertEquals(2, ThreeCardLogic.compareHands(Dealer_Hand, Player_Hand), "Player should've won.");
}

@Test
void Pair_Tie_Test_CompHands( ) {
    // Pairs
    Player_Hand.add(new Card('H', 2));
    Player_Hand.add(new Card('S', 2));
    Player_Hand.add(new Card('C', 6));

    // Pairs
    Dealer_Hand.add(new Card('C', 2));
    Dealer_Hand.add(new Card('S', 6));
    Dealer_Hand.add(new Card('D', 6));
    assertEquals(0, ThreeCardLogic.compareHands(Dealer_Hand, Player_Hand), "Should've been a tie.");
}

@Test
void Flush_Tie_Test_CompHands( ) {
    // Flush
    Player_Hand.add(new Card('D', 9));
    Player_Hand.add(new Card('D', 13));
    Player_Hand.add(new Card('D', 6));

    // Straight
    Dealer_Hand.add(new Card('S', 9));
    Dealer_Hand.add(new Card('S', 13));
    Dealer_Hand.add(new Card('S', 6));
    assertEquals(0, ThreeCardLogic.compareHands(Dealer_Hand, Player_Hand), "Should've been a tie.");
}

@Test
void Straight_Tie_Test_CompHands( ) {
    // Straight
    Player_Hand.add(new Card('C', 9));
    Player_Hand.add(new Card('S', 10));
    Player_Hand.add(new Card('H', 11));

    // Straight
    Dealer_Hand.add(new Card('H', 2));
    Dealer_Hand.add(new Card('S', 3));
    Dealer_Hand.add(new Card('H', 4));

    assertEquals(0, ThreeCardLogic.compareHands(Dealer_Hand, Player_Hand), "Should've been a tie.");
}

@Test
void ThreeOfKind_Tie_Test_CompHands( ) {
    // 3-of-kind
    Player_Hand.add(new Card('D', 12));
    Player_Hand.add(new Card('C', 12));
    Player_Hand.add(new Card('S', 12));

     // 3-of-kind
    Dealer_Hand.add(new Card('C', 11));
    Dealer_Hand.add(new Card('S', 11));
    Dealer_Hand.add(new Card('H', 11));
    assertEquals(0, ThreeCardLogic.compareHands(Dealer_Hand, Player_Hand), "Should've been a tie.");
}

@Test
void StraightFlush_Tie_Test_CompHands( ) {
    // StraightFlush
    Player_Hand.add(new Card('C', 10));
    Player_Hand.add(new Card('C', 9));
    Player_Hand.add(new Card('C', 8));

    // Flush
    Dealer_Hand.add(new Card('D', 2));
    Dealer_Hand.add(new Card('D', 3));
    Dealer_Hand.add(new Card('D', 4));
    assertEquals(0, ThreeCardLogic.compareHands(Dealer_Hand, Player_Hand), "Should've been a tie.");
}

    




}
