import java.util.*;

public class Card {
   private String value;
   private String suit;
   
   public Card(String value, String suit) {
      this.value = value.toLowerCase();
      this.suit = suit.toLowerCase();
   }
   
   public int getValue() {
      try {
         int numValue = Integer.parseInt(value);
         return numValue;
      } catch (NumberFormatException nfe) {
         if (value.equals("ace")) {
            return 11;
         } else {
            return 10;
         }
      }
   }
   
   public String toString() {
      return value + " of " + suit + "s";
   }
}