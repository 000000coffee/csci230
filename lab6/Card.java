
public class Card {
	public static enum Suit {HEART, CLUB, SPADE, DIAMOND};
	public static enum Rank {A, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN,
		J, Q, K};
		
	private Suit suit;
	private Rank rank;
	
	public Card(Rank rank, Suit suit) { 
		this.suit = suit;
		this.rank = rank;
	}
	public Suit getSuit() { return suit; }
	public Rank getRank() { return rank; }

	public String toString() {
		String s = "";
		s += ("Suit = " + getSuit());
		s += "\n";
		s += ("Rank = " + getRank());
		
		return s;
	}
} 
