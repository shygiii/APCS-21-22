// Name: J1-024-21
// Date: 9/23/2021
  
import java.util.*;
public class Permutations {
   public static int count = 0;
    
   public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      System.out.print("\nHow many digits? ");
      int n = sc.nextInt();
      
      leftRight("", n);  
              
      oddDigits("", n);
      
      superprime(n);
      
      if (count==0)
         System.out.println("no superprimes");
      else
         System.out.println("Count is "+count);
   }
   
    /**
     * Builds all the permutations of a string of length n containing Ls and Rs
     * @param s A string 
     * @param n An postive int representing the length of the string
     */
   public static void leftRight(String s, int n) {
      if (s.length() == n) System.out.println(s);
      else if (s.length() < n) {
         String leftS = s + "L";
         String rightS = s + "R";
         leftRight(leftS, n);
         leftRight(rightS, n);
      }
   }
   
    /**
     * Builds all the permutations of a string of length n containing odd digits
     * @param s A string 
     * @param n A postive int representing the length of the string
     */
   public static void oddDigits(String s, int n) {
      if (s.length() == n) System.out.println(s);
      else if (s.length() < n) {
         for (int x = 1; x <= 9; x++) 
            if (x%2 == 1) oddDigits(s + Integer.toString(x), n);
      }
   }
      
    /**
     * Builds all combinations of a n-digit number whose value is a superprime
     * @param n A positive int representing the desired length of superprimes  
     */
   public static void superprime(int n) {
      recur(2, n); //try leading 2, 3, 5, 7, i.e. all the single-digit primes
      recur(3, n); 
      recur(5, n);
      recur(7, n);
   }

    /**
     * Recursive helper method for superprime
     * @param k The possible superprime
     * @param n A positive int representing the desired length of superprimes
     */
   private static void recur(int k, int n) {
      if (isPrime(k) == true) {
         if (n == 1) {
            System.out.println(k);
            count++;
         }
         else if (n > 1) {
            for (int x = 0; x<=9; x++) {
               int recurK = (k*10) + x;
               recur(recurK, n-1);
            }
         }
      }
   }
   
    /**
     * Determines if the parameter is a prime number.
     * @param n An int.
     * @return true if prime, false otherwise.
     */
   public static boolean isPrime(int n) {
      if (n <= 1) 
         return false;
      else if (n == 2 || n == 3 || n == 5 || n == 7) 
         return true;
      else {
         boolean isPrimeNum = true;
         for (int x = 2; x <= n/2; x++) 
            if (n%x == 0) isPrimeNum = false;
         return isPrimeNum;
      }
   }
}