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
   public CircularDoublyLinkedList<E> clone() {
      CircularDoublyLinkedList<E> other = new CircularDoublyLinkedList<E>();
      if (this.size > 0) {
         other.tail = new Node<E>(this.tail.getElement(), null, null);
         ++other.size;
         Node<E> holder = other.tail;
         Node<E> walk = this.tail.getNext();
         while (walk != this.tail) {
            Node<E> newest = new Node<E>(walk.getElement(), other.tail, null);
            other.tail.setNext(newest);
            other.tail = newest;
            walk = walk.getNext();
            ++other.size;
         }
         other.tail.setNext(holder);
         other.tail.getNext().setPrev(other.tail);
         other.tail = other.tail.getNext();
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
      if (other == null) { return false; }
      if (getClass() != other.getClass()) { return false; }
      if (size != other.size) { return false; }
      Node<E> walkA = tail;
      Node<E> walkB = other.tail;
      int numMatching = 0;
      for (int i = 0; i < this.size + 1; ++i) {
         do {
            if (walkA.getElement() != walkB.getElement()) {
               numMatching = 0;
               break;
            }
            else { ++numMatching; }
            walkA = walkA.getNext();
            walkB = walkB.getNext();
         }  while (walkA != tail.getPrev());
         walkA = walkA.getNext();
         if (numMatching == this.size) { return true; }
      }
      return false;
   }
   public CircularDoublyLinkedList<E> concatenate(CircularDoublyLinkedList<E> b) {
      Node<E> walkB = b.tail.getNext();
      for (int i = 0; i < b.size; ++i) {
         this.addLast(walkB.getElement());
         walkB = walkB.getNext();
      }
      return this;
   }
   public String toString() {
      if (tail == null) return "()";
      StringBuilder sb = new StringBuilder("size = " + size + " (");
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
