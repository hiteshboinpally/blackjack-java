import java.util.*; 

public class Player {
   protected ArrayList<Card> cards;
   protected int currValue;
   protected boolean isBust;
   
   public Player() {
      cards = new ArrayList<>();
      currValue = 0;
      isBust = false;   
   }
   
   public void hit(Deck d) {
      Card c = d.draw();
      cards.add(c);
      currValue += c.getValue();
      isBust = currValue > 21;
   }
   
   public boolean isBust() {
      return isBust;
   }
   
   public int currValue() {
      return currValue;
   }
   
   public void printCards() {
      System.out.println(cards);
   }
   
   public void getDealt(Deck d) {
      hit(d);
      hit(d);
   }
   
   public void clear() {
      cards.clear();
      currValue = 0;
      isBust = false;
   }
 
}