import java.util.Random;
public class testStackDeque {
   public static void main (String[] args) {
      StackDeque a = new StackDeque();
      Random rand = new Random();
      for (int i = 0; i < 10; ++i) {
         a.addFirst(rand.nextInt(33));
         a.addLast(rand.nextInt(33));
      }
      long startTime = 0;
      long endTime = 0;
      String[] t = {"isEmpty", "first", "last", "addFirst", "addLast", "moveEnd"};
      System.out.println(System.currentTimeMillis());
      System.out.println(String.format("%12s%12s%12s%12s%12s%12s", t[0], t[1], t[2], t[3], t[4], t[5]));
      for (int i = 0; i < 25; ++i) {
         // test isEmpty
         startTime = System.currentTimeMillis();
         a.isEmpty();
         endTime = System.currentTimeMillis();
         System.out.print(String.format("%12d", startTime - endTime));
         // test first
         startTime = System.currentTimeMillis();
         a.first();
         endTime = System.currentTimeMillis();
         System.out.print(String.format("%12d", startTime - endTime));
         // test last
         startTime = System.currentTimeMillis();
         a.last();
         endTime = System.currentTimeMillis();
         System.out.print(String.format("%12d", startTime - endTime));
         // test addFirst
         startTime = System.currentTimeMillis();
         a.addFirst(99);
         endTime = System.currentTimeMillis();
         System.out.print(String.format("%12d", startTime - endTime));
         // test addLast
         startTime = System.currentTimeMillis();
         a.addLast(99);
         endTime = System.currentTimeMillis();
         System.out.print(String.format("%12d", startTime - endTime));
         // test moveEnd
         startTime = System.currentTimeMillis();
         a.moveEnd();
         endTime = System.currentTimeMillis();
         System.out.print(String.format("%12d", startTime - endTime));
         // insert new lines
         System.out.println("");
      }
      System.out.println(System.currentTimeMillis());
      a.print();
   }
}
