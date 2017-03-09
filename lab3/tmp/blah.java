public class blah {
   public static void main (String[] args) {
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

      System.out.println("List c size: " + c.size());
      System.out.println("List c is empty? " + c.isEmpty());
      System.out.println("List a is empty? " + a.isEmpty());
      System.out.println("First e of a: " + a.first());
      System.out.println("Last e of b: " + b.last());

      System.out.println("~~~~~~~~~~~~~~BEFORE~~~~~~~~~~~~~");
      System.out.println("a: " + a.toString());
      System.out.println("b: " + b.toString());
      System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
      System.out.println("Is a == b? " + a.equals(b));
      a.removeFirst();
      a.rotate();
      System.out.println("a.removeFirst and Rotated a:");
      System.out.println("~~~~~~~~~~~~~~AFTER~~~~~~~~~~~~~~");
      System.out.println("a: " + a.toString());
      System.out.println("b: " + b.toString());
      System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
      System.out.println("Is a == b? " + a.equals(b));
      a.rotateBackward();
      System.out.println("rorated backward");
      // a.addFirst(9);
      System.out.println("added first");
      // System.out.println("a: " + a.toString());
      System.out.println("printed");
      CircularDoublyLinkedList<Integer> d = a.clone();
      System.out.println("Rotated a backwards, then cloned");
      System.out.println("_________________________________");
      System.out.println("This is list d, cloned from a: ");
      System.out.println("a: " + a.toString());
      System.out.println("d: " + d.toString());
      System.out.println("_________________________________");
      System.out.println("a.removeFirst() returns: " + a.removeFirst());
      System.out.print("a: ");
      a.print();
   }
}
