public class Hailstone {
   public static void main(String[] args) {
      System.out.println(hailstone(12, 1));
   }
   
   
   
   
   public static int hailstone(int n, int count) {
      if (n==1) {
         count = 1;
         System.out.println("1 takes " + count + " steps");
         return count;
      }
      else {
         count++;
         if (n%2==0) {
            System.out.print(n + "-");
            return hailstone(n/2, count);
         }
         else {
            System.out.print(n + "-");
            return hailstone(n*3 + 1, count);
         }
      }
   }
   
   public static int hailstone(int n, int count) {
      if (n==1) {
         System.out.println("1 takes " + count + " steps");
         
      }
      else {
         count++;
         if (n%2==0) {
            System.out.print(n + "-");
            return hailstone(n/2, count);
         }
         else {
            System.out.print(n + "-");
            return hailstone(n*3 + 1, count);
         }
      }
   }
}