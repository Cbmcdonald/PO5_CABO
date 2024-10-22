import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

/**
 * The Deck class represents a deck of playing cards for the game Cabo. It
 * manages a collection of cards, including shuffling, drawing, and adding
 * cards.
 */
public class Deck extends Object {
	protected ArrayList<BaseCard> cardList;
	protected static processing.core.PApplet processing;

	// TODO: add everything else
	public Deck(ArrayList<BaseCard> deck) {
		if (processing == null) {
			throw new IllegalStateException("Processing environment is not set before creating deck");
		}

		this.cardList = deck;
	}

	/**
	 * Sets up the deck with CABO cards, including action cards. Initializes the
	 * deck with all necessary cards and shuffles them.
	 *
	 * @return the completed ArrayList of CABO cards
	 */
	public static ArrayList<BaseCard> createDeck() {
		ArrayList<BaseCard> cardList = new ArrayList<>();
		// Define the suits
		String[] suits = { "Clubs", "Diamonds", "Hearts", "Spades" };
		// Cards from 1 (Ace) to 13 (King)
		for (int rank = 1; rank <= 13; ++rank) {
			// Loop through each suit
			for (String suit : suits) {
				if (rank >= 7 && rank <= 12) {
					// Special action cards
					String actionType = "";
					if (rank == 7 || rank == 8) {
						actionType = "peek";
					} else if (rank == 9 || rank == 10) {
						actionType = "spy";
					} else {
						actionType = "switch";
					}
					cardList.add(new ActionCard(rank, suit, actionType)); // Add ActionCard to deck
				} else {
					cardList.add(new BaseCard(rank, suit)); // Add NumberCard to deck
				}
			}
		}
		Collections.shuffle(cardList);
		return cardList;
	}

	public static void setProcessing(processing.core.PApplet processing) {
		Deck.processing = processing;

	}

	public BaseCard drawCard() {
		if (cardList == null) {
			return null;
		} else {
			int locationOfDeletion = cardList.size() - 1;
			return cardList.remove(locationOfDeletion);
		}
	}

	public void addCard(BaseCard card) {
		cardList.add(card);
	}

	public int size() {
		int sizeOfDeck = cardList.size();
		return sizeOfDeck;
	}

	public boolean isEmpty() {
		return cardList.isEmpty();
	}

	public void draw(int x, int y, boolean isDiscard) {
		if (cardList.isEmpty() == true) {
			processing.stroke(0);
			processing.fill(0);
			processing.rect(x, y, 50, 70, 7);
			processing.fill(255);
			processing.textSize(12);
			processing.textAlign(processing.CENTER, processing.CENTER);
			processing.text("Empty", x + 25, y + 35);
		} else {// TA JR helped me to step by step on what to do
			processing.stroke(0);
			processing.fill(0);
			processing.rect(x, y, 50, 70, 7);
			processing.fill(255);
			processing.loadImage(("images" + File.separator + cardList.get(0).rank + "_of_"
					+ cardList.get(0).suit.toLowerCase() + ".png"));
			//System.out.println("End: " + cardList.get(0).suit.toLowerCase());
			processing.image(processing.loadImage(("images" + File.separator + cardList.get(0).rank + "_of_"
					+ cardList.get(0).suit.toLowerCase() + ".png")), x, y, 50, 70);
		}
	}
}
