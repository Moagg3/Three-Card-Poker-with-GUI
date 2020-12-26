// ThreeCardPokerGame Class implements a 3 Card Poker game using
// JavaFX Tools.
// The game is a two player version of the popular casino game 3 
// Card Poker.

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType; 


public class ThreeCardPokerGame extends Application {
    // Players One and Two and the Dealer Class Objects.
    Player playerOne;
    Player playerTwo;
    Dealer theDealer;

    BorderPane Game_BorderPane;

    // Player One related Data Fields
    private VBox P1_PaneCenter;
    private TextField P1_Winnings;
    private TextField P1_PairPlus_TextField;
    private TextField P1_AnteBet_TextField;
    private TextField P1_PlayBet_TextField;

    // Player Two related Data Fields
    private VBox P2_PaneCenter;
    private TextField P2_Winnings;
    private TextField P2_PairPlus_TextField;
    private TextField P2_AnteBet_TextField;
    private TextField P2_PlayBet_TextField;

    // Button Data Fields defined with unique handlers
    private Button P1_EnterBet;
    private Button P2_EnterBet;
    private Button NextRound;
    private ButtonType Play;
    private ButtonType Fold;

    // Data field used to display player 1 and 2 hand card images on screen
    HBox P1_HandImage;
    HBox P2_HandImage;
    
    // Data field used to display Dealer's hand card images on screen
    HBox Dealer_HandImage;
    VBox Dealer_HandImage_VBox;

    // Menubar and options data fields
    private MenuBar game_menuBar;
    private Menu Options;
    private MenuItem FreshStart_menu;
    private MenuItem Exit_menu;
    private MenuItem NewLook_menu;

    // Displays Message on screen
    private TextField Message;
    VBox Message_VBox;

    // Data field initialization
    public void Init( ){
        Game_BorderPane = new BorderPane( );
        playerOne       = new Player( );
        playerTwo       = new Player( );
        playerOne.PlayerNumber = "Player 1";
        playerOne.PlayerNumber = "Player 2";

        theDealer       = new Dealer( );
        P1_EnterBet     = new Button("P1 Enter Bet");
        P2_EnterBet     = new Button("P2 Enter Bet");
        NextRound       = new Button("Next Round");
        Play            = new ButtonType("Play");
        Fold            = new ButtonType("Fold");

        game_menuBar    = new MenuBar( );
        Options         = new Menu("Options");
        FreshStart_menu = new MenuItem("Fresh Start");
        Exit_menu       = new MenuItem("Exit");
        NewLook_menu    = new MenuItem("New Look");

        Message         = new TextField( );
        Message.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        
        Message_VBox = new VBox(new Label("Game Message"), Message);
        Message_VBox.setAlignment(Pos.BOTTOM_CENTER);

        game_menuBar.getMenus( ).addAll(Options);
        Options.getItems( ).addAll(FreshStart_menu, NewLook_menu, Exit_menu);
    }

    public static void main(String[] args) {launch(args);}
    

	// Start Method
	@Override
	public void start(Stage primaryStage) throws Exception {
        Init( ); // Initialize Class Objects
        primaryStage.setTitle("Let's Play Three Card Poker!!!");
        Game_BorderPane.setTop(Message_VBox);

        
        // Menu Bar
        Show_Menu_Option( );

        Deal_cards( ); // Deal Cards to both players and the dealer

        // Places Player 1 and Player 2 UI at the bottom center of the screen.
        Create_2Player_UI( );

        // Shows the dealer's cards faced down
        Dealer_FaceDownCards( );
        
        // When player clicks Enter Bet, handler sets the bets to playerOne and playerTwo class objects
        // Players cards are revealed
        Handler_EnterBetBtn_Set_PlayersAntePairPlus( );
        Handler_NextRound( );

        // Create Scene
        Scene _scene = new Scene(Game_BorderPane);
        
        // Sets the scene to primary stage and displays the stage on screen
        ShowStage(primaryStage, _scene);
    }

    // Displays the menu option on screen
    private void Show_Menu_Option( ){
        Game_BorderPane.setLeft(game_menuBar);

        NewLook_menu.setOnAction(e->Game_BorderPane.setStyle("-fx-background-color: #ebcb91;"));
        Exit_menu.setOnAction(e->System.exit(1));
        FreshStart_menu.setOnAction(e->{
            theDealer.getNewDeck( );
            System.out.println("    >> FreshStart, Deck Size: " + theDealer.get_DeckSize( ));

            PlayersRemoveCards( ); 
            Dealer_RemoveCards( );
            Dealer_FaceDownCards();
            playerOne.reset_bets( );
            playerTwo.reset_bets( );

            P1_PairPlus_TextField.clear( );
            P1_AnteBet_TextField.clear( );
            P1_PlayBet_TextField.clear( );

            P2_PairPlus_TextField.clear( );
            P2_AnteBet_TextField.clear( );
            P2_PlayBet_TextField.clear( );

            playerOne.finishedEnteringBets = false;
            playerTwo.finishedEnteringBets = false;

            P1_EnterBet.setDisable(false);
            P2_EnterBet.setDisable(false);

            P1_AnteBet_TextField.setDisable(false);
            P2_AnteBet_TextField.setDisable(false);

            Deal_cards( );
        });
    }

    // Reveals Dealer hand cards on screen
    public void Dealer_RevealCards( ){
        Image pic1 = new Image(theDealer.get_card(0));
        ImageView imagePic1 = new ImageView(pic1);
        Image pic2 = new Image(theDealer.get_card(1));
        ImageView imagePic2 = new ImageView(pic2);
        Image pic3 = new Image(theDealer.get_card(2));
        ImageView imagePic3 = new ImageView(pic3);

        imagePic1.setFitHeight(150);
        imagePic1.setFitWidth(150);
        imagePic1.setPreserveRatio(true);

        imagePic2.setFitHeight(150);
        imagePic2.setFitWidth(150);
        imagePic2.setPreserveRatio(true);

        imagePic3.setFitHeight(150);
        imagePic3.setFitWidth(150);

        Dealer_HandImage = new HBox(imagePic1,imagePic2, imagePic3);
        Dealer_HandImage.setAlignment(Pos.CENTER); 
        Game_BorderPane.setCenter(Dealer_HandImage);

    }

    // Removes dealer's hand card off screen
    public void Dealer_RemoveCards( ){
        Dealer_HandImage.getChildren().clear();
    }

    // Shows the dealer's hand cards facing down on screen
    public void Dealer_FaceDownCards( ){
        System.out.println("Dealing FaceDownCards . . . ");

        Image pic1 = new Image("blue_back.jpg");
        ImageView imagePic1 = new ImageView(pic1);
        Image pic2 = new Image("blue_back.jpg");
        ImageView imagePic2 = new ImageView(pic2);
        Image pic3 = new Image("blue_back.jpg");
        ImageView imagePic3 = new ImageView(pic3);

        imagePic1.setFitHeight(150);
        imagePic1.setFitWidth(150);
        imagePic1.setPreserveRatio(true);

        imagePic2.setFitHeight(150);
        imagePic2.setFitWidth(150);
        imagePic2.setPreserveRatio(true);

        imagePic3.setFitHeight(150);
        imagePic3.setFitWidth(150);
        imagePic3.setPreserveRatio(true);
        
        Dealer_HandImage = new HBox(imagePic1,imagePic2, imagePic3);
        Dealer_HandImage.setAlignment(Pos.CENTER); 

        Game_BorderPane.setCenter(Dealer_HandImage);

    }

    // Contains Handler for the next round button displayed on screen
    public void Handler_NextRound( ){
        NextRound.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent action){
                Message.clear( );

                P1_PairPlus_TextField.setDisable(false);
                P2_PairPlus_TextField.setDisable(false);

                P1_AnteBet_TextField.setDisable(false);
                P2_AnteBet_TextField.setDisable(false);
                
                PlayersRemoveCards( ); 
                Dealer_RemoveCards( );
                Dealer_FaceDownCards();
                playerOne.reset_bets( );
                playerTwo.reset_bets( );
                
                P1_PairPlus_TextField.clear( );
                P1_AnteBet_TextField.clear( );
                P1_PlayBet_TextField.clear( );
                
                P2_PairPlus_TextField.clear( );
                P2_AnteBet_TextField.clear( );
                P2_PlayBet_TextField.clear( );
                
                playerOne.finishedEnteringBets = false;
                playerTwo.finishedEnteringBets = false;
                
                P1_EnterBet.setDisable(false);
                P2_EnterBet.setDisable(false);

                if(theDealer.get_DeckSize( ) <= 34){
                    theDealer.getNewDeck( );
                }
                
                Deal_cards( );
                System.out.println("    >> NextRound, Deck Size: " + theDealer.get_DeckSize( ));
            }
        });
    }

    // Contains Handler Functions for the Enter Button displayed on screen
    public void Handler_EnterBetBtn_Set_PlayersAntePairPlus( ){
        Alert invalidRange = new Alert(AlertType.ERROR, "Placed bet must be within [5 to 25] range.");

        P1_EnterBet.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent action){
                P1_EnterBet.setDisable(true);

                if(Integer.valueOf(P1_AnteBet_TextField.getText()) < 5 || Integer.valueOf(P1_AnteBet_TextField.getText()) > 25)
                    invalidRange.showAndWait();

                if(Integer.valueOf(P1_PairPlus_TextField.getText()) < 5 || Integer.valueOf(P1_PairPlus_TextField.getText()) > 25)
                    invalidRange.showAndWait();
                
                playerOne.anteBet     = Integer.valueOf(P1_AnteBet_TextField.getText());
                playerOne.pairPlusBet = Integer.valueOf(P1_PairPlus_TextField.getText());
                playerOne.finishedEnteringBets = true;
                
                if(playerOne.finishedEnteringBets && playerTwo.finishedEnteringBets)
                    PlayersRevealCards( );

                System.out.println("Player One Ante Bet: " + playerOne.anteBet);
                System.out.println("Player One PairPlus Bet: " + playerOne.pairPlusBet);
                System.out.println("Player One entered bets: " + playerOne.finishedEnteringBets);

                AlertBox_PlayFold(playerOne, AlertType.NONE, "Player 1, play or fold?");
                AlertBox_PlayFold(playerTwo, AlertType.NONE, "Player 2, play or fold?");
            }
        });

        P2_EnterBet.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent action){
                P2_EnterBet.setDisable(true);

                if(Integer.valueOf(P2_AnteBet_TextField.getText()) < 5 || Integer.valueOf(P2_AnteBet_TextField.getText()) > 25)
                    invalidRange.showAndWait();
                
                if(Integer.valueOf(P2_PairPlus_TextField.getText()) < 5 || Integer.valueOf(P2_PairPlus_TextField.getText()) > 25)
                    invalidRange.showAndWait();

                playerTwo.anteBet     = Integer.valueOf(P2_AnteBet_TextField.getText());
                playerTwo.pairPlusBet = Integer.valueOf(P2_PairPlus_TextField.getText());
                playerTwo.finishedEnteringBets = true;

                if(playerOne.finishedEnteringBets && playerTwo.finishedEnteringBets)
                    PlayersRevealCards( );

                System.out.println("Player Two Ante Bet: " + playerTwo.anteBet);
                System.out.println("Player Two PairPlus Bet: " + playerTwo.pairPlusBet);
                System.out.println("Player Two entered bets: " + playerTwo.finishedEnteringBets);
                
                AlertBox_PlayFold(playerOne, AlertType.NONE, "Player 1, play or fold?");
                AlertBox_PlayFold(playerTwo, AlertType.NONE, "Player 2, play or fold?");
            }
        });

    }

    // Updates the winning values for assigned player
    public void Update_PlayerWinnings(Player player){
        if(theDealer.has_Queen_or_higher()){
            System.out.println("    >> DEALER HAS QUEEN OR HIGHER");
            System.out.println("    >> Dealer's Hand: ");
            theDealer.print_DealersHand();
            System.out.println("    >> Done. \n");

            Integer win_val = ThreeCardLogic.compareHands(theDealer.dealersHand, player.hand);
            
            if(win_val == 2) // Player Wins
            {   
                System.out.println(player.PlayerNumber + " win!");
                Message.setText("Player wins!");
                player.totalWinnings += ThreeCardLogic.evalPPWinnings(player.hand, player.pairPlusBet);
                player.totalWinnings += player.anteBet;
                player.totalWinnings += player.playBet;
            }
            else if(win_val == 1) // Dealer Wins
            {
                Message.setText("Dealer wins!");
                player.totalWinnings -= player.pairPlusBet;
                player.totalWinnings -= player.anteBet;
                player.totalWinnings -= player.playBet;
            }
            else{ // Tie
                Message.setText("Tie!");
                System.out.println("Tie");
                player.anteBet     = 0;
                player.pairPlusBet = 0;
                player.playBet     = 0;
            }

            P1_Winnings.setText(String.valueOf(playerOne.totalWinnings));
            P2_Winnings.setText(String.valueOf(playerTwo.totalWinnings));
        }
        else{
            Message.setText("Dealer does not have a queen or higher");
            System.out.println("    >> DEALER DOES NOT HAVE QUEEN OR HIGHER");
            System.out.println("    >> Dealer's Hand: ");
            
            player.totalWinnings += ThreeCardLogic.evalPPWinnings(player.hand, player.pairPlusBet);
            player.totalWinnings += player.anteBet;
            player.totalWinnings += player.playBet;

            P1_Winnings.setText(String.valueOf(playerOne.totalWinnings));
            P2_Winnings.setText(String.valueOf(playerTwo.totalWinnings));
        }
    }

    // AlertBox pops out on screen prompting for either play or fold for assigned player
    public void AlertBox_PlayFold(Player player, AlertType type, String message){
    
        if(playerOne.finishedEnteringBets && playerTwo.finishedEnteringBets){
            Alert PlayOrFold = new Alert(type, message);
            PlayOrFold.getButtonTypes().addAll(this.Play, this.Fold);
            PlayOrFold.showAndWait().ifPresent(response->{

                // Player Decides to PLAY
                if(response == this.Play){
                    System.out.println("    >> Player Plays.");
                    TextInputDialog dialog = new TextInputDialog();
                    dialog.setContentText("Enter Play Bet (must be the same amount as your ante bet): ");
                    while(player.playBet != player.anteBet){
                        dialog.showAndWait().ifPresent(e->{
                            System.out.println("Play Bet entered: " + dialog.getResult());

                            P1_PairPlus_TextField.setDisable(true);
                            P2_PairPlus_TextField.setDisable(true);

                            P1_AnteBet_TextField.setDisable(true);
                            P2_AnteBet_TextField.setDisable(true);

                            P1_PlayBet_TextField.setVisible(true);
                            P1_PlayBet_TextField.setText(dialog.getResult());
                            P1_PlayBet_TextField.setDisable(true);

                            P2_PlayBet_TextField.setVisible(true);
                            P2_PlayBet_TextField.setText(dialog.getResult());
                            P2_PlayBet_TextField.setDisable(true);

                            player.playBet = Integer.valueOf(dialog.getResult());
                            player.playBetEntered = true;
                            
                        });
                    }

                    if(theDealer.has_Queen_or_higher())
                        Message.setText("Dealer does not have a queen or higher!");

                    Update_PlayerWinnings(player);


                }
                // Player FOLDS, player loses ante and pairplus bet (if one is made)
                else{
                    System.out.println("    >> Player Folds.");
                    player.totalWinnings -= (player.anteBet + player.pairPlusBet);
                }

                if(playerOne.playBetEntered && playerTwo.playBetEntered)
                    Dealer_RevealCards();
            });
        }

    }

    // Shows both players UI screen
    void Create_2Player_UI( ){
        HBox Players_UI = new HBox(PlayerOne_Interface(), P1_EnterBet, NextRound, P2_EnterBet, PlayerTwo_Interface());
        Players_UI.setSpacing(60);
        Players_UI.setAlignment(Pos.CENTER);
        Game_BorderPane.setBottom(Players_UI);
    }

    // Returns a VBox exclusively for player one
    VBox PlayerOne_Interface( ){
        P1_Winnings           = new TextField();
        P1_PairPlus_TextField = new TextField();
        P1_AnteBet_TextField  = new TextField();
        P1_PlayBet_TextField  = new TextField();

        P1_Winnings.setMaxWidth(50);
        P1_PairPlus_TextField.setMaxWidth(50);
        P1_AnteBet_TextField.setMaxWidth(50);
        P1_PlayBet_TextField.setMaxWidth(50);

        P1_Winnings.setText(String.valueOf(playerOne.totalWinnings));
        P1_Winnings.setDisable(true);
        P1_PlayBet_TextField.setVisible(false);

        P1_PaneCenter = new VBox(
                                 P1_Winnings, new Label("Winnings"),
                                 P1_PairPlus_TextField,  new Label("Pair Plus"), 
                                 P1_AnteBet_TextField,   new Label("AnteBet"), 
                                 P1_PlayBet_TextField,   new Label("PlayBet"),
                                 new Label("\nPLAYER ONE"));
        P1_PaneCenter.setSpacing(5);
        return P1_PaneCenter;

    }

    // Returns a VBox exclusively for player two
    VBox PlayerTwo_Interface( ){
        P2_Winnings           = new TextField();
        P2_PairPlus_TextField = new TextField();
        P2_AnteBet_TextField  = new TextField();
        P2_PlayBet_TextField  = new TextField();

        P2_Winnings.setMaxWidth(50);
        P2_PairPlus_TextField.setMaxWidth(50);
        P2_AnteBet_TextField.setMaxWidth(50);
        P2_PlayBet_TextField.setMaxWidth(50);

        P2_Winnings.setText(String.valueOf(playerOne.totalWinnings));
        P2_Winnings.setDisable(true);
        P2_PlayBet_TextField.setVisible(false);

        P2_PaneCenter = new VBox(
                                 P2_Winnings, new Label("Winnings"),
                                 P2_PairPlus_TextField,  new Label("Pair Plus"), 
                                 P2_AnteBet_TextField,   new Label("AnteBet"), 
                                 P2_PlayBet_TextField,   new Label("PlayBet"),
                                 new Label("\nPLAYER TWO"));
        P2_PaneCenter.setSpacing(5);
        return P2_PaneCenter;

    }

    // Dealers deal cards to all parties
    public void Deal_cards( ){
        playerOne.hand = theDealer.dealHand(); 
        playerTwo.hand = theDealer.dealHand(); 
        theDealer.dealersHand = theDealer.dealHand();
    }

    // Shows the stage on screen
    public void ShowStage(Stage primaryStage, Scene scene){   
        primaryStage.setScene(scene);
        primaryStage.setWidth(800);
        primaryStage.setHeight(700);
        primaryStage.show( );
    }

    // Reveals Both players' hand cards on screen
    public void PlayersRevealCards( ){
        PlayerOneShowCards(this.playerOne);
        PlayerTwoShowCards(this.playerTwo);
    }

    // Adds Player One's card images
    public void PlayerOneShowCards(Player p1){
        P1_HandImage = new HBox();

        System.out.println("\n\n    >> Printing player's Hand: ");
        p1.print_PlayersHand();

        Image pic1 = new Image(p1.get_card(0)); // TODO: NEEDS FIXING . . .
        ImageView imagePic1 = new ImageView(pic1);
        Image pic2 = new Image(p1.get_card(1));
        ImageView imagePic2 = new ImageView(pic2);
        Image pic3 = new Image(p1.get_card(2));
        ImageView imagePic3 = new ImageView(pic3);

        imagePic1.setFitHeight(150);
        imagePic1.setFitWidth(150);
        imagePic1.setPreserveRatio(true);

        imagePic2.setFitHeight(150);
        imagePic2.setFitWidth(150);
        imagePic2.setPreserveRatio(true);

        imagePic3.setFitHeight(150);
        imagePic3.setFitWidth(150);
        imagePic3.setPreserveRatio(true);
        P1_HandImage.getChildren().addAll(imagePic1, imagePic2, imagePic3);
        P1_PaneCenter.getChildren().add(P1_HandImage);
    }

    // Adds Player One's card images
    public void PlayerTwoShowCards(Player p2){
        P2_HandImage = new HBox();

        Image pic1 = new Image(p2.get_card(0));
        ImageView imagePic1 = new ImageView(pic1);
        Image pic2 = new Image(p2.get_card(1));
        ImageView imagePic2 = new ImageView(pic2);
        Image pic3 = new Image(p2.get_card(2)); //TODO: Error occuring here
        ImageView imagePic3 = new ImageView(pic3);

        imagePic1.setFitHeight(150);
        imagePic1.setFitWidth(150);
        imagePic1.setPreserveRatio(true);

        imagePic2.setFitHeight(150);
        imagePic2.setFitWidth(150);
        imagePic2.setPreserveRatio(true);

        imagePic3.setFitHeight(150);
        imagePic3.setFitWidth(150);
        imagePic3.setPreserveRatio(true);
        P2_HandImage.getChildren().addAll(imagePic1, imagePic2, imagePic3);
        P2_PaneCenter.getChildren().add(P2_HandImage);
    }

    // Removes cards off the screen
    public void PlayersRemoveCards( ){
        P1_HandImage.getChildren().clear();
        P2_HandImage.getChildren().clear();
    }
}
