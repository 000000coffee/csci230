public class CircularDoublyLinkedList<E> implements Cloneable {
   private static class Node<E> {
      private E element;
      private Node<E> prev;
      private Node<E> next;
      public Node(E e, Node<E> p, Node<E> n) {
         element = e;
         prev = p;
         next = n;
      }
      public E getElement() { return element; }
      public Node<E> getPrev() { return prev; }
      public Node<E> getNext() { return next; }
      public void setPrev(Node<E> p) { prev = p; }
      public void setNext(Node<E> n) { next = n; }
   }
   private Node<E> tail = null;
   private int size = 0;
   public CircularDoublyLinkedList() { }
   // clones object
   public CircularDoublyLinkedList<E> clone() {
      // makes other
      CircularDoublyLinkedList<E> other = new CircularDoublyLinkedList<E>();
      if (this.size > 0) {
         // make first node
         other.tail = new Node<E>(this.tail.getElement(), null, null);
         Node<E> holder = other.tail;           // have a tmp node to hold the tail
         Node<E> walk = this.tail.getNext();    // make node to walk around the clonee
         while (walk != this.tail) {            // while walk doesn't reach the tail
            // make new node with prev as other tail
            Node<E> newest = new Node<E>(walk.getElement(), other.tail, null);
            other.tail.setNext(newest);   // set next to our recently created node
            other.tail = newest;          // set the other tail to recently created node
            walk = walk.getNext();        // increment walk
            ++size;                       // increment size
         }
         other.tail.setNext(holder);               // reset the tail to the holder
         other.tail.getNext().setPrev(other.tail); // make it link itself
         other.tail = other.tail.getNext();        // finnish linking
      }
      return other;
   }
   public int size() { return size; }
   public boolean isEmpty() { return size == 0; }
   public E first() {
      if (isEmpty()) return null;
      return tail.getNext().getElement();
   }
   public E last() {
      if (isEmpty()) return null;
      return tail.getElement();
   }
   public void rotate() {
      if (tail != null)
         tail = tail.getNext();
   }
   public void rotateBackward() {
      if (tail != null)
         tail = tail.getPrev();
   }
   public void addFirst(E e) {
      if (size == 0) {
         tail = new Node<>(e, null, null);
         tail.setNext(tail);
         tail.setPrev(tail);
      }
      else {
         Node<E> newest = new Node<E>(e, tail, tail.getNext());
         tail.getNext().setPrev(newest);
         tail.setNext(newest);
      }
      size++;
   }
   public void addLast(E e) {
      addFirst(e);
      tail = tail.getNext();
   }
   public E removeFirst() {
      if (isEmpty()) return null;
      Node<E> head = tail.getNext();
      if (head == tail) tail = null;
      else tail.setNext(head.getNext());
      size--;
      return head.getElement();
   } 
   public boolean equals (CircularDoublyLinkedList<E> other) {
      if (other == null) return false;
      if (getClass() != other.getClass()) return false;
      if (size != other.size) return false;
      Node<E> walkA = tail;
      Node<E> walkB = other.tail;
      int correct = 0;
      for (int i = 0; i < this.size; ++i) {
         do {
            if (!walkA.getElement().equals(walkB.getElement())) { break; }
            if (walkA.getElement().equals(walkB.getElement())) { ++correct; }
            walkA = walkA.getNext();
            walkB = walkB.getNext();
         }
         while (walkA != tail.getPrev());
         walkA = walkA.getNext();
      }
      if (correct == size) { return true; }
      else { return true; }
   }
   public String toString() {
      if (tail == null) return "()";
      StringBuilder sb = new StringBuilder("(");
      Node<E> walk = tail;
      do {
         walk = walk.getNext();
         sb.append(walk.getElement());
         if (walk != tail)
            sb.append(", ");
      }
	  while (walk != tail);
      sb.append(")");
      return sb.toString();
   }
   public void print() {
      System.out.println(toString());
   }
}
