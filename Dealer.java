public class Dealer extends Player {  
   public void printCards(boolean showCards) {
      if (showCards) {
         super.printCards();
      } else {
         System.out.println("[" + cards.get(0) +", facedown card]");
      }
   }
}