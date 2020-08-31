import java.util.*;

public class BlackjackClient {
   public static void main(String[] args) {
      Scanner console = new Scanner(System.in);
      
      System.out.println("Welcome to Blackjack!");
      System.out.println();
      
      List<String> names = getNames(console);
      
      System.out.print("How many decks would you like to play with? ");
      int deckCt = console.nextInt();
      
      BlackjackManager bj = new BlackjackManager(names, deckCt, console);
      runGame(bj, console);
      
   }
   
   public static List<String> getNames(Scanner console) {
      List<String> names = new ArrayList<>();
      boolean canContinue = true;
      while (canContinue) {
         System.out.print("Please enter player's name: ");
         names.add(console.nextLine());
         canContinue = yesTo("Are there any other players", console);
      }
      System.out.println();
      return names;
   }
   
   public static void runGame(BlackjackManager bj, Scanner console) {
      boolean canContinue = true;
      while (canContinue) {
         bj.currStandings();
         System.out.println();
         
         bj.askBets();
         System.out.println();
         
         bj.dealCards();
         System.out.println();
         
         bj.playRound();
         System.out.println();
         bj.updateBets();
         System.out.println();
         
         bj.currStandings();
         bj.reset();
         for (int i = 0; i < 4; i++) {
            System.out.println();
         }
         
         canContinue = yesTo("Would you like to continue playing?", console);
      }
      
      System.out.println();
      bj.finalStandings();
   }
   
   // post: asks the user a question, forcing an answer of "y" or "n";
   //       returns true if the answer was yes, returns false otherwise
   public static boolean yesTo(String prompt, Scanner console) {
      System.out.print(prompt + " (y/n)? ");
      String response = console.nextLine().trim().toLowerCase();
      while (!response.equals("y") && !response.equals("n")) {
         System.out.println("Please answer y or n.");
         System.out.print(prompt + " (y/n)? ");
         response = console.nextLine().trim().toLowerCase();
      }
      return response.equals("y");
   }

}