//name: J1-024-21    date: 8/26/2021

import java.text.DecimalFormat;
public class SmartCard 
{
   public final static DecimalFormat df = new DecimalFormat("$0.00");
   public final static double MIN_FARE = 0.5;
   /* enter the private fields */
   private double balance;
   private boolean isBoarded;
   private Station station;
   
   /* the one-arg constructor  */
   public SmartCard(double initBalance)
   {
      balance = 0.0;
      balance += initBalance;
      isBoarded = false;
      station = null;
   }

   //these three getter methods only return your private data
   //they do not make any changes to your data
   public boolean getIsBoarded() { 
      return isBoarded;
   }
   
   public double getBalance() {
      return balance;
   }
         
   public Station getBoardedAt() {
      return station;
   }
   
   public String getFormattedBalance() {
      int cents = (int)(100*(balance - (int)balance));
      if (cents < 10) 
         return "$" + (int)balance + ".0" + cents;
      else 
         return "$" + (int)balance + "." + cents;
   }
    
   /* write the instance methods  */
   
   public void board(Station s) {
      if (isBoarded == true) System.out.println("Error: already boarded?!");
      else if (balance < MIN_FARE) System.out.println("Insufficient funds to board. Please add more money.");
      else { 
         station = s;
         isBoarded = true; 
      }
   }  
      
   public double cost(Station s) {
      double cost = MIN_FARE;
      int diff = 0;
      if (station.getZone() == s.getZone()) cost += 0;
      else {
         diff = s.getZone()-station.getZone();
         if (diff<0) diff *= -1;
         cost += 0.75 * diff;
      }
      return cost;
   }    

   public void exit(Station s) {
      if (isBoarded == false) System.out.println("Error: Did not board?!");
      else if (cost(s) > balance) System.out.println("Insufficient funds to exit. Please add more money.");
      else {
         double cost = cost(s);
         balance -= cost(s);
         isBoarded = false;
         String formattedCost = "";
         int cents = (int)(100*(cost - (int)cost));
         if (cents < 10)  formattedCost = "$" + (int)cost + ".0" + cents;
         else formattedCost = "$" + (int)cost + "." + cents;
         System.out.println("From " + station.getName() + " to " + s.getName() + " costs " + formattedCost + ". SmartCard has " + getFormattedBalance());
         station = null;
      }
   }

   public void addMoney(double d) {
      balance += d;
   }
}
   
// ***********  start a new class.  The new class does NOT have public or private.  ***/
class Station {
   private String name;
   private int zone;
   
   public Station() {
      name = null;
      zone = 0;  
   }
   
   public Station(String n, int z) {
      name = n;
      zone = z;
   }
   
   public int getZone() {
      return zone;
   }
 
   public String getName() {
      return name;
   } 
}