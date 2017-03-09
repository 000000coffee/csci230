import java.util.Random;
public class blah {
   public static final String ANSI_RESET = "\u001B[0m";
   public static final String ANSI_RED = "\u001B[31m";
   public static void main (String[] args) {
      System.out.print(ANSI_RED);
      CircularDoublyLinkedList<Integer> a = new CircularDoublyLinkedList<Integer>();
      System.out.println("Created CircularDoublyLinkedList a");
      CircularDoublyLinkedList<Integer> b = new CircularDoublyLinkedList<Integer>();
      System.out.println("Created CircularDoublyLinkedList b");
      CircularDoublyLinkedList<Integer> c = new CircularDoublyLinkedList<Integer>();
      System.out.println("Created CircularDoublyLinkedList c");

      for (Integer i = 0; i < 10; ++i) {
         a.addFirst(i);
         b.addLast(Math.abs(i-9));
      }
      System.out.println("Filled a with addFirst() and b with addLast()");
      System.out.print(ANSI_RESET);
      System.out.println("c.size: " + c.size());
      System.out.println("c.isEmpty: " + c.isEmpty());
      System.out.println("a.isEmpty: " + a.isEmpty());
      System.out.println("a.first: " + a.first());
      System.out.println("b.last: " + b.last());
      System.out.println("a: " + a.toString());
      System.out.println("b: " + b.toString());
      System.out.println("a.equals(b): " + a.equals(b));
      a.rotate();
      System.out.print(ANSI_RED);
      System.out.println("Rotated a");
      System.out.print(ANSI_RESET);
      System.out.println("a: " + a.toString());
      System.out.println("b: " + b.toString());
      System.out.println("a.equals(b): " + a.equals(b));
      Integer tmp = a.removeFirst();
      System.out.print(ANSI_RED);
      System.out.println("a.removeFirst: " + tmp);
      System.out.print(ANSI_RESET);
      System.out.println("a: " + a.toString());
      System.out.println("b: " + b.toString());
      System.out.println("a.equals(b): " + a.equals(b));
      a.addFirst(tmp);
      System.out.print(ANSI_RED);
      System.out.println("added the one removed");
      a.rotateBackward();
      System.out.println("Rotated a backwards");
      CircularDoublyLinkedList<Integer> d = a.clone();
      System.out.println("list a cloned set to list d");
      System.out.print(ANSI_RESET);
      System.out.println("a: " + a.toString());
      System.out.println("d: " + d.toString());
      System.out.println("a.equals(d): " + a.equals(d));
      System.out.print(ANSI_RED);
      System.out.println("filling c with random values: ");
      Random r = new Random();
      for (int i = 0; i < a.size(); ++i) {
         c.addFirst(r.nextInt(10));
      }
      System.out.print(ANSI_RESET);
      System.out.println("a: " + a.toString());
      System.out.println("c: " + c.toString());
      System.out.println("a.equals(c): " + a.equals(c));
      System.out.print(ANSI_RED);
      System.out.println("Concatenate a with c: ");
      System.out.print(ANSI_RESET);
      a.concatenate(c);
      System.out.println("a: " + a.toString());
   }
}
