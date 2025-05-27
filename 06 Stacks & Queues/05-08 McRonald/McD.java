//Updated on 12.14.2020 v2

//Name: J1-024-21
//Date: 1/19/2022

import java.util.*;
import java.io.*;

public class McD {
   public static final int TIME = 1080;     //18 hrs * 60 min
   public static double CHANCE_OF_CUSTOMER = .2;
   public static int customers = 0;
   public static int totalMinutes = 0;
   public static int longestWaitTime = 0;
   public static int longestQueue = 0;
   public static int serviceWindow = 0;      // to serve the front of the queue
   //public static final int numberOfServiceWindows = 3;  //for McRonald 3
   public static int thisCustomersTime;
   public static PrintWriter outfile = null; // file to display the queue information
         
   public static int timeToOrderAndBeServed() {
      return (int)(Math.random() * 6 + 2);
   }
  
   public static void displayTimeAndQueue(Queue<Customer> q, int min) { 
      //Billington's
      outfile.println(min + ": " + q);	
      //Jurj's
      //outfile.println("Customer#" + intServiceAreas[i] + " leaves and his total wait time is " + (min - intServiceAreas[i]));                     	
   }
   
   public static int getCustomers() {
      return customers;
   }
   
   public static double calculateAverage() {
      return (int)(1.0 * totalMinutes/customers * 10)/10.0;
   }
   
   public static int getLongestWaitTime() {
      return longestWaitTime;
   }
   
   public static int getLongestQueue() {
      return longestQueue;
   }
            
   public static void main(String[] args) {     
    //set up file      
      try {
         outfile = new PrintWriter(new FileWriter("McRonald 1 Queue 1 ServiceArea.txt"));
      }
      catch(IOException e) {
         System.out.println("File not created");
         System.exit(0);
      }
      mcRonald(TIME, outfile);   //run the simulation
      outfile.close();	
   }
   
   public static void mcRonald(int TIME, PrintWriter of) {
      /***************************************
           Write your code for the simulation   
      **********************************/
      of.println("Mcronald(arrival chance is " + CHANCE_OF_CUSTOMER + ") with 1 queue / 1 service areas");
      
      //initialize var
      Queue<Customer> q = new LinkedList<Customer>();
      int waitTime = 0;
      boolean open = true;
      Customer c = new Customer(0, 0);
 
      
      for(int i = 0; i < TIME; i++)
      {
         displayTimeAndQueue(q, i); 
         if(Math.random() <= CHANCE_OF_CUSTOMER)
         {
            c = new Customer(i, timeToOrderAndBeServed());
            if(q.isEmpty() && open) 
            {
               waitTime = c.getOrderAndBeServed();
               of.println(c.getArrivedAt() + " is now being served for " + waitTime + " minutes");
               open = false;
            }
            else if(!q.isEmpty()) open = true;
            else
            {
               waitTime = waitTime - 1;
               if(waitTime == 0)
               {
                  q.remove();
                  c = q.peek();
                  waitTime = c.getOrderAndBeServed();
               }
               else
               {
                  c.setOrderAndBeServed(waitTime);
                  of.println(c.getArrivedAt() + " is now being served for " + waitTime + " minutes");
               }
               
            }

         }      
while(!q.isEmpty()) q.remove();
      }
      
      of.println("Total customers served = " + getCustomers());
      of.println("Average wait time = " + calculateAverage());
      of.println("Longest wait time = " + longestWaitTime);
      of.println("Longest queue = " + longestQueue);
                      
      /*   report the data to the screen    */  
      System.out.println("1 queue, 1 service window, probability of arrival = "+ CHANCE_OF_CUSTOMER);
      System.out.println("Total customers served = " + getCustomers());
      System.out.println("Average wait time = " + calculateAverage());
      System.out.println("Longest wait time = " + longestWaitTime);
      System.out.println("Longest queue = " + longestQueue);
   }
   
   static class Customer {
      private int arrivedAt;
      private int orderAndBeServed;
      
    /**********************************
       Complete the Customer class with  
       constructor, accessor methods, toString.
    ***********************************/
      public Customer() {
         arrivedAt = -1;
         orderAndBeServed = -1;
      }
    
      public Customer(int a, int o) {
         arrivedAt = a;
         orderAndBeServed = o;
      }
    
      public int getArrivedAt() {
         return arrivedAt;
      }
      
      public void setOrderAndBeServed(int x) {
         orderAndBeServed = x;
      }
      
      public void setArrivedAt(int x) {
         arrivedAt = x;
      }
   
      public int getOrderAndBeServed() {
         return orderAndBeServed;
      }
   
      public String toString() {
         return arrivedAt + "";
      }
   }
}