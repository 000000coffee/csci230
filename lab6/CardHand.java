import java.util.Iterator;
import java.util.Random;
import java.util.NoSuchElementException;

public class CardHand implements Iterable<Card>{

   PositionalList<Card> hand = new LinkedPositionalList<>();
   Position<Card> fingers[] = (Position<Card>[]) new Position[4];
   int numSuit[] = new int[4];

   void addCard(Card.Rank rank, Card.Suit suit) {
      Card newCard = new Card(rank, suit);
      if (fingers[suit.ordinal()] == null) {
         hand.addLast(newCard);
         fingers[suit.ordinal()] = hand.last();
         numSuit[suit.ordinal()] += 1;
      }
      else {
         hand.addAfter(fingers[suit.ordinal()], newCard);
         numSuit[suit.ordinal()] += 1;
      }
   }
   Card play(Card.Suit suit) {
      Position<Card> tmp = fingers[suit.ordinal()]; 
      // if has no suit of type passed, and hand isn't empty, return(play) any card
      if ((numSuit[suit.ordinal()] == 0) && (!hand.isEmpty())) {
         suit = hand.last().getElement().getSuit();
         numSuit[suit.ordinal()] -= 1;
         if (numSuit[suit.ordinal()] > 1)
            fingers[suit.ordinal()] = hand.after(tmp);
         return hand.remove(hand.last());
      }
      // if has no suit of type passed, and hand is empty, return null
      else if ((numSuit[suit.ordinal()] == 0) && (hand.isEmpty())) {
         return null;
      }
      else if (numSuit[suit.ordinal()] > 0) {
         if (numSuit[suit.ordinal()] > 1)
            fingers[suit.ordinal()] = hand.after(tmp);
         numSuit[suit.ordinal()] -= 1;
         return hand.remove(fingers[suit.ordinal()]);
      }
      else { return null; }
   }

   public Iterator<Card> iterator() {
      return hand.iterator();
   }

   public Iterator<Card> suitIterator(Card.Suit s) {
      return new SuitIterator(s);
   }
   private class SuitIterator implements Iterator<Card> {
      private Position<Card> cursor = null;
      private Position<Card> recent = null;
      private int numIterated = 0;
      private Card.Suit suit;
      public SuitIterator(Card.Suit s) {
         suit = s;
         cursor = fingers[suit.ordinal()];
         recent = cursor;
      }
      public Card next() throws NoSuchElementException {
         recent = cursor;
         cursor = hand.after(cursor);
			numIterated+=1;
			return recent.getElement();
      }
      public boolean hasNext() { 
         if(cursor==null || numIterated >= numSuit[suit.ordinal()])
            return false;
         else
            return true;
      }
      public void remove() throws IllegalStateException {
         recent = fingers[suit.ordinal()];
         fingers[suit.ordinal()] = hand.after(fingers[suit.ordinal()]);
         cursor = fingers[suit.ordinal()];
         hand.remove(recent);
      }
   }

   // main test program
   public static void main(String[] args) {
      CardHand hand = new CardHand();

      hand.addCard(Card.Rank.A, Card.Suit.HEART);
      hand.addCard(Card.Rank.TWO, Card.Suit.CLUB);
      hand.addCard(Card.Rank.J, Card.Suit.SPADE);
      hand.addCard(Card.Rank.NINE, Card.Suit.DIAMOND);
      hand.addCard(Card.Rank.Q, Card.Suit.HEART);
      hand.addCard(Card.Rank.K, Card.Suit.CLUB);
      hand.addCard(Card.Rank.EIGHT, Card.Suit.CLUB);

      System.out.println("testing iterator...");
      Iterator<Card> it = hand.iterator();
      while (it.hasNext()) {
         Card c = it.next();

         System.out.println("Suit = " + c.getSuit());
         System.out.println("Rank = " + c.getRank());
      }

      System.out.println("testing suit iterator for heart");
      Iterator<Card> itHeart = hand.suitIterator(Card.Suit.HEART);
      while (itHeart.hasNext()) {
         Card c = itHeart.next();

         System.out.println("Suit = " + c.getSuit());
         System.out.println("Rank = " + c.getRank());
      }

      System.out.println("testing suit iterator for club");
      Iterator<Card> itClub = hand.suitIterator(Card.Suit.CLUB);
      while (itClub.hasNext()) {
         Card c = itClub.next();

         System.out.println("Suit = " + c.getSuit());
         System.out.println("Rank = " + c.getRank());
      }

      System.out.println("testing suit iterator for spade");
      Iterator<Card> itSpade = hand.suitIterator(Card.Suit.SPADE);
      while (itSpade.hasNext()) {
         Card c = itSpade.next();

         System.out.println("Suit = " + c.getSuit());
         System.out.println("Rank = " + c.getRank());
      }

      System.out.println("testing suit iterator for diamond");
      Iterator<Card> itDiamond = hand.suitIterator(Card.Suit.DIAMOND);
      while (itDiamond.hasNext()) {
         Card c = itDiamond.next();

         System.out.println("Suit = " + c.getSuit());
         System.out.println("Rank = " + c.getRank());
      }

      Card c = hand.play(Card.Suit.HEART);
      System.out.println("played " + c);

      c = hand.play(Card.Suit.CLUB);
      System.out.println("played " + c);

      c = hand.play(Card.Suit.SPADE);
      System.out.println("played " + c);

      c = hand.play(Card.Suit.DIAMOND);
      System.out.println("played " + c);

      c = hand.play(Card.Suit.DIAMOND);
      System.out.println("played " + c);

      System.out.println("remaining cards...");
      it = hand.iterator();
      while (it.hasNext()) {
         c = it.next();

         System.out.println("Suit = " + c.getSuit());
         System.out.println("Rank = " + c.getRank());
      }
   }
}
