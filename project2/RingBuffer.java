public class RingBuffer {
   private static class Node<E> {
      private E element;

   }
   private int capacity;
   private int size;
   public RingBuffer() {
      this.capacity = 100;
      this.size = 0;
   }
   public RingBuffer(int capacity) {
      this.capacity = capacity;
      this.size = 0;
   }
   public int size() {
      return this.size;
   }
   public boolean isEmpty() {
      if (size > 0) return false;
      else return true;
   }
   public boolean isFull() {
      if (size == capacity) return true;
      else return false;
   }
   public void enqueue(double x) {

   }
   public double dequeue() {}
   public double peek() {}
}
