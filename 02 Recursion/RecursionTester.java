public class RecursionTester
{

public static void main(String[] args)
   {
   int[] a = new int[] {1, 12, 3, 4, 5};
   System.out.println(f1(5,3));
   }


public static int f1( int a, int b )
   {
      if( a == b )
        return b;  
     else
        return a + f2(a-1, b);
   }

   public static int f2( int p, int q )
   {
      if( p < q )
        return p + q;   
     else
        return p + f1(p-2, q);

}}