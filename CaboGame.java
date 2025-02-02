import java.util.ArrayList;
import processing.core.PApplet;
/**
* The CaboGame class implements the main game logic for the card game CABO.
* It manages the deck, discard pile, players, game state, and user interactions.
*/
public class CaboGame extends processing.core.PApplet{
private Deck deck;
private Deck discard;
private Player[] players;
private int currentPlayer;
private boolean gameOver;
private int caboPlayer;
private Button[] buttons;
private int selectedCardFromCurrentPlayer;
private BaseCard drawnCard;
/**
 * Enum representing the different action states in the game
 * (e.g., swapping cards, peeking, spying, switching).
 *
 * This allows us to easily restrict the possible values of a variable.
 */
private enum ActionState {
  NONE, SWAPPING, PEEKING, SPYING, SWITCHING
}
private ActionState actionState = ActionState.NONE;
 // provided data fields for tracking the players' moves through the game
private ArrayList<String> gameMessages = new ArrayList<>();
 /**
 * Launch the game window; PROVIDED. Note: the argument to PApplet.main() must match the name
 * of this class, or it won't run!
 * @param args unused
 */
public static void main(String[] args) {
  PApplet.main("CaboGame");
}
 /**
 * Sets up the initial window size for the game; PROVIDED.
 */
@Override
public void settings() {
  size(1000, 800);
}
 /**
 * Sets up the game environment, including the font, game state, and game elements.
 */
@Override
public void setup() {
  textFont(createFont("Arial", 16));
  // TODO: setProcessing for the classes which require it
   BaseCard.setProcessing(this);
  Deck.setProcessing(this);
 
  deck = new Deck(Deck.createDeck());
  discard = new Deck(new ArrayList<>());
  drawnCard = null;
  deckCheck();
  // TODO: set up players array and deal their cards
  // TODO: set up buttons and update their states for the beginning of the game
  // TODO: update the gameMessages log: "Turn for "+currentPlayer.name
}
 /**
 * Console-only output for verifying the setup of the card objects and the deck containing them
 */
public void deckCheck() {
  deck = new Deck(Deck.createDeck());
  // TODO: verify that there are 52 cards in the deck
  if(deck.size()== 52) {
    System.out.println("Deck size is 52: true");
  }else {
    System.out.println("Deck size is 52: false");
  }
  // TODO: verify that there are 8 of each type of ActionCard
  int numOfPeek = 0;
  int numOfSpy = 0;
  int numOSswitch = 0;
  for(int i = 0; i < deck.size(); i++) {
    BaseCard card = deck.cardList.get(i);
    if(card instanceof ActionCard) {
      ActionCard check = (ActionCard) card;
      if(check.getActionType().equals("peek")) {
        numOfPeek++;
      }else if(check.getActionType().equals("spy")) {
        numOfSpy++;
      }else if(check.getActionType().equals("switch")) {
        numOSswitch++;
      }
    }
  }
  if(numOfPeek == 8 && numOfSpy == 8 && numOSswitch == 8) {
    System.out.println("Found correct numbers of action cards: true");
  }else {
    System.out.println("Found correct numbers of action cards: false");
  }
   // TODO: verify that there are 13 of each suit
  int numOfHearts = 0;
  int numOfDiamonds = 0;
  int numOfClubs = 0;
  int numOfSpades = 0;
  for(int i = 0; i < deck.size(); i++) {
    BaseCard card = deck.cardList.get(i);
    if(card.suit.equalsIgnoreCase("Hearts")) {
      numOfHearts++;
    }else if(card.suit.equalsIgnoreCase("Diamonds")) {
      numOfDiamonds++;
    }else if(card.suit.equalsIgnoreCase("Clubs")) {
      numOfClubs++;
    }else if(card.suit.equalsIgnoreCase("Spades")) {
      numOfSpades++;
    }
  }
  if(numOfHearts == 13 && numOfDiamonds ==13 && numOfClubs == 13 && numOfSpades == 13) {
    System.out.println("Found correct numbers of each suit: true");
  }else {
    System.out.println("Found correct numbers of each suit: false");
  }
  // TODO: verify that the king of diamonds' getRank() returns -1
  boolean locatedKing = false;
  for(int i = 0; i < deck.size(); i++) {
    BaseCard card = deck.cardList.get(i);
    if(card.getRank() == -1) {
      System.out.print("King of Diamonds found!");
      locatedKing = true;
      break;
    }
  }
  if(locatedKing == false) {
    System.out.print("King of Diamonds not found!");
  }
}
 /**
 * Updates the state of the action buttons based on the current game state.
 * Activates or deactivates buttons depending on whether it's the start of a player's turn, a card has been drawn, or the player is an AI.
 */
public void updateButtonStates() {
  // TODO: if the current player is a computer, deactivate all buttons
  // TODO: otherwise, if no card has been drawn, activate accordingly (see writeup)
  // TODO: otherwise, if a card has been drawn, activate accordingly (see writeup)
}
 /**
 * Renders the graphical user interface; also handles some game logic for the computer players.
 */
@Override
public void draw() {
  background(0, 128, 0);
  deck.draw(500, 80, false);
  discard.draw(600, 80, true);
 
  textSize(16);
  fill(255);
  text("Deck:", 520, 60);
  text("Discard Pile:", 644, 60);
  // TODO: draw the players' hands
  // TODO: draw the buttons
  // TODO: show the drawn card, if there is one
   // TODO: if the game is over, display the game over status
  // TODO: handle the computer players' turns
}
 /**
 * Handles mouse press events during the game. It manages user interactions with buttons (that is,
 * drawing a card, declaring CABO, swapping cards, using action cards) and updates the game state
 * accordingly.
 */
@Override
public void mousePressed() {
  // TODO: if game is over or it's the computer's turn, do nothing
  // TODO: handle button clicks
   // handle additional action states (TODO: complete these methods)
  switch (actionState) {
    case SWAPPING -> handleCardSwap();
    case PEEKING -> handlePeek();
    case SPYING -> handleSpy();
    case SWITCHING -> handleSwitch();
    default -> { /* No action to be taken */ }
  }
}
 ///////////////////////////////////// BUTTON CLICK HANDLERS /////////////////////////////////////
 /**
 * Handles the action of drawing a card from the deck.
 * If the deck is empty, the game ends. Otherwise, the drawn card is displayed in the middle of the table.
 * The game status and button states are updated accordingly.
 */
public void drawFromDeck() {
  // TODO: if the deck is empty, game over
  // TODO: otherwise, draw the next card from the deck
  // TODO: update the gameMessages log: player.name+" drew a card."
  // TODO: update the button states
}
 /**
 * Handles the action of declaring CABO.
 * Updates the game status to show that the player has declared CABO.
 */
public void declareCabo() {
  // TODO: update the gameMessages log: player.name+" declares CABO!"
  // TODO: set the caboPlayer to the current player's index
  // TODO: end this player's turn
}
 ///////////////////////////////////// ACTION STATE HANDLERS /////////////////////////////////////
 /**
 * This method runs when the human player has chosen to SWAP the drawn card with one from their
 * hand. Detect if the mouse is over a card from the currentPlayer's hand and, if it is, swap the
 * drawn card with that card.
 *
 * If the mouse is not currently over a card from the currentPlayer's hand, this method does
 * nothing.
 */
public void handleCardSwap() {
  // TODO: find a card from the current player's hand that the mouse is currently over
    // TODO: swap that card with the drawnCard
    // TODO: add the swapped-out card from the player's hand to the discard pile
    // TODO: update the gameMessages log: "Swapped the drawn card with card "+(index+1)+" in the hand."
    // TODO: set the drawnCard to null and the actionState to NONE
    // TODO: set all buttons except End Turn to inactive
     // TODO: uncomment this code to erase all knowledge of the card at that index from the AI
    // (you may need to adjust its indentation and/or change some variables)
     /*AIPlayer AI;
    for (int j = 1; j < players.length; ++j) {
      AI = (AIPlayer) players[j];
      AI.setCardKnowledge(0, index, false);
    }//*/
}
 /**
 * Handles the action of peeking at one of your cards. The player selects a card from their own
 * hand, which is then revealed (set face-up).
 *
 * If the mouse is not currently over a card from the currentPlayer's hand, this method does
 * nothing.
 */
public void handlePeek() {
  // TODO: find a card from the current player's hand that the mouse is currently over
    // TODO: set that card to be face-up
    // TODO: update the gameMessages log: "Revealed card "+(index+1)+" in the hand."
    // TODO: add the drawnCard to the discard, set drawnCard to null and actionState to NONE
    // TODO: set all buttons except End Turn to inactive
}
 /**
 * Handles the spy action, allowing the current player to reveal one of another player's cards.
 * The current player selects a card from another player's hand, which is temporarily revealed.
 *
 * If the mouse is not currently over a card from another player's hand, this method does nothing.
 */
public void handleSpy() {
  // TODO: find a card from any player's hand that the mouse is currently over
    // TODO: if it is not one of their own cards, set it to be face-up
    // TODO: update the gameMessages log: "Spied on "+player.name+"'s card.";
    // TODO: add the drawnCard to the discard, set drawnCard to null and actionState to NONE
    // TODO: set all buttons except End Turn to inactive
}
 /**
 * Handles the switch action, allowing the current player to switch one of their cards with a
 * card from another player's hand.
 *
 * This action is performed in 2 steps, in this order:
 *   (1) select a card from the current player's hand
 *   (2) select a card from another player's hand
 *
 * If the mouse is not currently over a card, this method does nothing.
 */
public void handleSwitch() {
  // TODO: add CaboGame instance variable to store the index of the card from the currentPlayer's hand
   // TODO: check if the player has selected a card from their own hand yet
  // TODO: if they haven't: determine which card in their own hand the mouse is over & store it
  // and do nothing else
   // TODO: if they have selected a card from their own hand already:
    // TODO: find a card from any OTHER player's hand that the mouse is currently over
    // TODO: swap the selected card with the card from the currentPlayer's hand
    // TODO: update the gameMessages log: "Switched a card with "+player.name
    // TODO: add the drawnCard to the discard, set drawnCard to null and actionState to NONE
    // TODO: set all buttons except End Turn to inactive
     // TODO: uncomment this code to update the knowledge of the swapped card for the other player
    // (you may need to adjust its indentation and variables)
     /*boolean knowledge = ((AIPlayer)players[i]).getCardKnowledge(i, otherPlayerCardIndex);
    ((AIPlayer)players[i]).setCardKnowledge(i, otherPlayerCardIndex,
        ((AIPlayer)players[i]).getCardKnowledge(currentPlayer, currentPlayerCardIndex));
    ((AIPlayer)players[i]).setCardKnowledge(currentPlayer, currentPlayerCardIndex, knowledge);//*/
    // TODO: reset the selected card instance variable to -1
}
 /////////////////////////////////////////////////////////////////////////////////////////////////
 /**
 * Advances the game to the next player's turn.
 * Hides all players' cards, updates the current player, checks for game-over conditions,
 * resets action states, and updates the UI button states for the new player's turn.
 */
public void nextTurn() {
  // TODO: hide all players' cards
  // TODO: if there is still an active drawnCard, discard it and set drawnCard to null
  // TODO: advance the current player to the next one in the list
  // TODO: check if the new player is the one who declared CABO (and end the game if so)
  // TODO: update the gameMessages log: "Turn for "+player.name
  // TODO: reset the action state to NONE
  // TODO: update the button states
}
 /**
 * Displays the game-over screen and reveals all players' cards.
 * The method calculates each player's score, identifies the winner, and displays
 * a message about the game's result, including cases where there is no winner.
 *
 * We've provided the code for the GUI parts, but the logic behind this method is still TODO
 */
public void displayGameOver() {
  // Create a dimmed background overlay
  fill(0, 0, 0, 200);
  rect(0, 0, width, height);
  fill(255);
  textSize(32);
  textAlign(CENTER, CENTER);
  text("Game Over!", (float) width / 2, (float) height / 2 - 150);
  // TODO: reveal all players' cards
  // TODO: calculate and display each player's score
  int yPosition = height / 2 - 100;
  textSize(24);
    // TODO: uncomment to Display a player's score
    //text(player.getName() + "'s score: " + score, (float) width / 2, yPosition);
    yPosition += 30;
  // TODO: check if there is a tie or a specific CABO winner (lowest score wins)
  String winner = null;
  // TODO: display this message if there is no winner
  text("No Winner. The war starts.", (float) width / 2, yPosition + 30);
  // TODO: display this message if there is a winner
  text("Winner: " + winner, (float) width / 2, yPosition + 30);
}
 /**
 * PROVIDED: Sets the current game status message and updates the message log.
 * If the message log exceeds a maximum number of messages, the oldest message is removed.
 *
 * @param message the message to set as the current game status.
 */
private void setGameStatus(String message) {
  gameMessages.add(message);
  int MAX_MESSAGES = 15;
  if (gameMessages.size() > MAX_MESSAGES) {
    gameMessages.remove(0); // Remove the oldest message
  }
}
 /////////////////////////////////////////////////////////////////////////////////////////////////
// The 2 methods below this line are PROVIDED in their entirety to run the AIPlayer interactions
// with the CABO game. Uncomment them once you are ready to add AIPlayer actions to your game!
/////////////////////////////////////////////////////////////////////////////////////////////////
 /**
 * Performs the AI player's turn by drawing a card and deciding whether to swap, discard, or use
 * an action card.
 * If the AI player draws a card that is better than their highest card, they swap it; otherwise,
 * they discard it.
 * If the drawn card is an action card, the AI player performs the corresponding action.
 * If the AI player's hand value is low enough, they may declare CABO.
 */
/*private void performAITurn() {
  AIPlayer aiPlayer = (AIPlayer) players[currentPlayer];
  String gameStatus = aiPlayer.getName() + " is taking their turn.";
  setGameStatus(gameStatus);
  // Draw a card from the deck
  drawnCard = deck.drawCard();
  if (drawnCard == null) {
    gameOver = true;
    return;
  }
  gameStatus = aiPlayer.getName() + " drew a card.";
  setGameStatus(gameStatus);
  // Determine if AI should swap or discard
  int drawnCardValue = drawnCard.getRank();
  int highestCardIndex = aiPlayer.getHighestIndex();
  if (highestCardIndex == -1) {
    highestCardIndex = 0;
  }
  int highestCardValue = aiPlayer.getHand().getRankAtIndex(highestCardIndex);
  // Swap if the drawn card has a lower value than the highest card in hand
  if (drawnCardValue < highestCardValue) {
    BaseCard cardInHand = aiPlayer.getHand().swap(drawnCard, highestCardIndex);
    aiPlayer.setCardKnowledge(aiPlayer.getLabel(), highestCardIndex, true);
    discard.addCard(cardInHand);
    gameStatus = aiPlayer.getName() + " swapped the drawn card with card " + (highestCardIndex + 1) + " in their hand.";
    setGameStatus(gameStatus);
  } else if (drawnCard instanceof ActionCard) {
    // Use the action card
    String actionType = ((ActionCard) drawnCard).getActionType();
    gameStatus = aiPlayer.getName() + " uses an action card: " + actionType;
    setGameStatus(gameStatus);
    performAIAction(aiPlayer, actionType);
    discard.addCard(drawnCard);
  } else {
    // Discard the drawn card
    discard.addCard(drawnCard);
    gameStatus = aiPlayer.getName() + " discarded the drawn card: " + drawnCard;
    setGameStatus(gameStatus);
  }
  // AI may declare Cabo if hand value is low enough
  int handValue = aiPlayer.calcHandBlind();
  if (handValue <= random(13, 21) && caboPlayer == -1) {
    declareCabo();
  }
  // Prepare for the next turn
  drawnCard = null;
  nextTurn();
}//*/
 /**
 * Performs the specified action for the AI player based on the drawn action card.
 * Actions include peeking at their own cards, spying on another player's card, or switching cards with another player.
 *
 * @param aiPlayer   the AI player performing the action.
 * @param actionType the type of action to perform ("peek", "spy", or "switch").
 */
/*private void performAIAction(AIPlayer aiPlayer, String actionType) {
  Player otherPlayer = players[0]; // Assuming Player 1 is the human player
  String gameStatus = "";
  switch (actionType) {
    case "peek" -> {
      // AI peeks at one of its own cards
      int unknownCardIndex = aiPlayer.getUnknownCardIndex();
      if (unknownCardIndex != -1) {
        aiPlayer.setCardKnowledge(aiPlayer.getLabel(), unknownCardIndex, true);
        gameStatus = aiPlayer.getName() + " peeked at their card " + (unknownCardIndex + 1);
        setGameStatus(gameStatus);
      }
    }
    case "spy" -> {
      // AI spies on one of the human player's cards
      int spyIndex = aiPlayer.getSpyIndex();
      if (spyIndex != -1) {
        aiPlayer.setCardKnowledge(0, spyIndex, true);
        gameStatus = aiPlayer.getName() + " spied on Player 1's card " + (spyIndex + 1);
        setGameStatus(gameStatus);
      }
    }
    case "switch" -> {
      // AI switches one of its cards with one of the human player's cards
      int aiCardIndex = aiPlayer.getHighestIndex();
      if (aiCardIndex == -1) {
        aiCardIndex = (int) random(aiPlayer.getHand().size());
      }
      int otherCardIndex = aiPlayer.getLowestIndex(otherPlayer);
      if (otherCardIndex == -1)
        otherCardIndex = (int) random(otherPlayer.getHand().size());
      // Swap the cards between AI and the human player
      aiPlayer.getHand().switchCards(aiCardIndex, otherPlayer.getHand(), otherCardIndex);
      boolean preCardKnowledge = aiPlayer.getCardKnowledge(aiPlayer.getLabel(), aiCardIndex);
      aiPlayer.setCardKnowledge(aiPlayer.getLabel(), aiCardIndex, aiPlayer.getCardKnowledge(0, otherCardIndex));
      aiPlayer.setCardKnowledge(0, otherCardIndex, preCardKnowledge);
      gameStatus = aiPlayer.getName() + " switched card " + (aiCardIndex + 1) + " with " + otherPlayer.getName() + "'s " + (otherCardIndex + 1) + ".";
      setGameStatus(gameStatus);
    }
  }
}//*/
}













