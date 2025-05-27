// Name: J1-024-21
// Date: 3/10/22

import java.io.*;
import java.util.*;

public class Dictionary {
   public static void main(String[] args) {
      Scanner infile = null;
      try {
         infile = new Scanner(new File("spanglish.txt"));
         System.setOut(new PrintStream(new FileOutputStream("dictionaryOutput.txt")));
      }
      catch(Exception e) {
      }
      
      Map<String, Set<String>> eng2spn = makeDictionary( infile );
      System.out.println("ENGLISH TO SPANISH");
      display(eng2spn);
   
      Map<String, Set<String>> spn2eng = reverse(eng2spn);
      System.out.println("SPANISH TO ENGLISH");
      display(spn2eng);
   }
   
   public static Map<String, Set<String>> makeDictionary(Scanner infile) {
      Map<String, Set<String>> dict = new TreeMap<String, Set<String>>();
      while (infile.hasNext()) {
         String eng = infile.nextLine();
         String span = "";
         if (infile.hasNext()) span = infile.nextLine();
         if (!dict.containsKey(eng)) add(dict, eng, span);
         else dict.get(eng).add(span);
      }
      return dict;
   }
   
   public static void add(Map<String, Set<String>> dictionary, String word, String translation) {
      Set<String> settrans = new TreeSet<String>();
      settrans.add(translation);
      dictionary.put(word, settrans);
   }
   
   public static void display(Map<String, Set<String>> m) {
      if (m != null) {
         for (String s:m.keySet()) {
            System.out.print(s + " [");
            String str = "";
            for (String vals:m.get(s)) str += (vals + ", ");
            str = str.substring(0, str.length()-2);
            System.out.print(str + "]\n");
         }
      }
      System.out.println();
   }
   
   public static Map<String, Set<String>> reverse(Map<String, Set<String>> dictionary) {
      Map<String, Set<String>> dict = new TreeMap<String, Set<String>>();
      for (String s:dictionary.keySet()) {
         for (String vals:dictionary.get(s)) {
            if (!dict.containsKey(vals)) add(dict, vals, s);
            else dict.get(vals).add(s);
         }  
      } 
      return dict;
   }
}

   /********************
	INPUT:
   	holiday
		fiesta
		holiday
		vacaciones
		party
		fiesta
		celebration
		fiesta
     <etc.>
  *********************************** 
	OUTPUT:
		ENGLISH TO SPANISH
			banana [banana]
			celebration [fiesta]
			computer [computadora, ordenador]
			double [doblar, doble, duplicar]
			father [padre]
			feast [fiesta]
			good [bueno]
			hand [mano]
			hello [hola]
			holiday [fiesta, vacaciones]
			party [fiesta]
			plaza [plaza]
			priest [padre]
			program [programa, programar]
			sleep [dormir]
			son [hijo]
			sun [sol]
			vacation [vacaciones]

		SPANISH TO ENGLISH
			banana [banana]
			bueno [good]
			computadora [computer]
			doblar [double]
			doble [double]
			dormir [sleep]
			duplicar [double]
			fiesta [celebration, feast, holiday, party]
			hijo [son]
			hola [hello]
			mano [hand]
			ordenador [computer]
			padre [father, priest]
			plaza [plaza]
			programa [program]
			programar [program]
			sol [sun]
			vacaciones [holiday, vacation]

**********************/