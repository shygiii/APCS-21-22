public class test {
   public static void main(String[] args) {
      m(5);   }
      
      public static void m(int n) {
      if (n>0) 
      m(n-1);
      else System.out.println(n);
      }
      }
      