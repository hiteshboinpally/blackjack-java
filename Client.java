public class Client extends Player {
   private int money;
   private String name;
   
   public Client(String name) {
      this(100, name);
   }
   
   public Client(int money, String name) {
      super();
      this.name = name;
      this.money = money;
   }
   
   public String getName() {
      return name;
   }

   public int getMoney() {
      return money;
   }
   
   public void add(int money) {
      this.money += money;
   }
   
   public void subtract(int money) {
      this.money -= money;
   }
   
   public String toString() {
      return "Player " + name + ": $" + money + ".00";
   }
}