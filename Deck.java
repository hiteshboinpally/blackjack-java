import java.util.*;

public class Deck {
   public final int CARDS_IN_DECK = 52;
   
   private Card[] deckArray;
   private Set<Card> usedCards;
   
   public Deck() {
      this(1);
   }
   
   public Deck(int numDecks) {
      usedCards = new HashSet<>();
      deckArray = new Card[CARDS_IN_DECK * numDecks];
      for (int i = 0; i < numDecks; i++) {
         int startIndex = i * CARDS_IN_DECK;
         for (int j = 2; j <= 10; j++) {
            valuePerSuit("" + j, 4 * (j - 2) + startIndex);
         }
         valuePerSuit("ace", 36 + startIndex);
         valuePerSuit("jack", 40 + startIndex);
         valuePerSuit("queen", 44 + startIndex);
         valuePerSuit("king", 48 + startIndex);
      }
   }
   
   private void valuePerSuit(String value, int index) {
      deckArray[index] = new Card(value, "heart");
      deckArray[index + 1] = new Card(value, "diamond");
      deckArray[index + 2] = new Card(value, "spade");
      deckArray[index + 3] = new Card(value, "club");
   }
   
   public Card draw() {
      Random rand = new Random();
      Card drawn = deckArray[rand.nextInt(deckArray.length)];
      while (true) {
         if (!usedCards.contains(drawn)) {
            usedCards.add(drawn);
            return drawn;
         } else {
            drawn = deckArray[rand.nextInt(deckArray.length)];
         }
      }
   }
   
   public void reset() {
      usedCards.clear();
   }
}