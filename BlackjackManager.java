import java.util.*;

public class BlackjackManager {
   private Map<Client, Integer> players;
   private Dealer bjDealer;
   private Deck bjDeck;
   private Scanner console;
   
   public BlackjackManager(List<String> names, int deckCt) {
      this(names, deckCt, new Scanner(System.in));
   }
   public BlackjackManager(List<String> names, int deckCt, Scanner console) {
      players = new HashMap<>();
      for (String s : names) {
         players.put(new Client(s), 0);
      }
      bjDealer = new Dealer();
      bjDeck = new Deck(deckCt);
      this.console = console;
   }
   
   public void dealCards() {
      bjDealer.getDealt(bjDeck);
      System.out.print("Dealer's hand: ");
      bjDealer.printCards(false);
      for (Client c : players.keySet()) {
         c.getDealt(bjDeck);
         System.out.print(c.getName() + "'s hand: ");
         c.printCards();
      }
   }
   
   public void askBets() {
      for (Client c : players.keySet()) {
         System.out.print("Bet for " + c.getName() + "? ");
         int bet = console.nextInt();
         while(bet < 0 || bet > c.getMoney()) {
            System.out.println("Please enter a valid bet.");
            System.out.print("Bet for " + c.getName() + "? ");
            bet = console.nextInt();
         }
         console.nextLine();
         players.put(c, bet);
      }
   }
   
   public void playRound() {
      for (Client c : players.keySet()) {
         boolean canContinue = true;
         System.out.println(c.getName() + "'s turn");
         System.out.print("\tCurrent Hand: ");
         c.printCards();
         while (canContinue) {
            canContinue = playerTurn(c);
         }
         System.out.println(c.getName() + "'s turn is over");
         System.out.println();
      }
      
      dealerTurn();
   }
   
   public void updateBets() {
      for (Client c : players.keySet()) {
         int bet = players.get(c);
         if (c.isBust()) {
            c.subtract(bet);
            System.out.println(c.getName() + " lost " + bet + " dollars.");
         } else if (bjDealer.isBust()) {
            c.add(bet);
            System.out.println(c.getName() + " won " + bet + " dollars!");
         } else if (c.currValue() > bjDealer.currValue()) {
            c.add(bet);
            System.out.println(c.getName() + " won " + bet + " dollars!");
         } else if (c.currValue() < bjDealer.currValue()) {
            c.subtract(bet);
            System.out.println(c.getName() + " lost " + bet + " dollars.");
         }
      }
   }
   
   public void reset() {
      for (Client c : players.keySet()) {
         c.clear();
      }
      bjDealer.clear();
      bjDeck.reset();
   
   }
   public void currStandings() {
      System.out.println("Current Standings: ");
      printStandings();
   }
   
   public void finalStandings() {
      System.out.println("Final Standings: ");
      printStandings(); 
   }
   
   private boolean playerTurn(Client c) {
      String input = hitOrStand("\tWould you like to hit or stand?");
      
      if (input.toLowerCase().equals("h")) {
         c.hit(bjDeck);
         System.out.print("\tCurrent Hand: ");
         c.printCards();
         if (c.isBust()) {
            System.out.println("\t\tYou busted!");
         }
         return !c.isBust();
      } else {
         return false;
      }
   }
   
   private void dealerTurn() {
      System.out.println("Dealer's turn");
      System.out.print("\tCurrent Hand: ");
      bjDealer.printCards(true);
      
      while (bjDealer.currValue() < 17 && !bjDealer.isBust()) {
         System.out.println("\tDealer hits");
         bjDealer.hit(bjDeck);
         System.out.print("\tCurrent Hand: ");
         bjDealer.printCards(true); 
      }
      
      if (bjDealer.isBust()) {
         System.out.println("\t\tDealer busted!");
      }
      
      System.out.println("Dealer's turn is over");
   }
   
   // post: asks the user a question, forcing an answer of "y" or "n";
   //       returns true if the answer was yes, returns false otherwise
   private String hitOrStand(String prompt) {
      System.out.print(prompt + " (h/s)? ");
      String response = console.nextLine().trim().toLowerCase();
      while (!response.equals("h") && !response.equals("s")) {
         System.out.println("Please answer h or s.");
         System.out.print(prompt + " (h/s)? ");
         response = console.nextLine().trim().toLowerCase();
      }
      return response;
   }
   
   private void printStandings() {
      for (Client c : players.keySet()) {
         System.out.println("\t" + c);
      }
   }
}