public class RingBuffer {
   private double[] ringBuffer;
   private int capacity;
   int first;
   int last;
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
      // TODO: add throws RingBufferException if isFull()
      // add item x to the end
      ringBuffer[last] = x;
      if (last == capacity)
         last = 0;
      else
         ++last;
   }
   public double dequeue() {
      // TODO: add throws RingBufferException if isEmpty()
      // delete and return item from the front
      int tmp = first;
      if (first == capacity)
         first = 0;
      else
         ++first;
      return ringBuffer[tmp];
   }
   public double peek() {
      // TODO: returns (but do not delete) item from the front
      return ringBuffer[first];
   }
}
