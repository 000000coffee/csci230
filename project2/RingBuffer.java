public class RingBuffer {
   private double[] ringBuffer;
   private int capacity;
   private int first;
   private int last;
   private int size;
   public RingBuffer() {
      this.capacity = 100;
      this.size = 0;
      this.first = 0;
      this.last = 0;
   }
   public RingBuffer(int capacity) {
      this.capacity = capacity;
      this.size = 0;
      this.first = 0;
      this.last = 0;
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
   public int getFirst() {
      return this.first;
   }
   public void enqueue(double x) {
      // TODO: add throws RingBufferException if isFull()
      // add item x to the end
      ringBuffer[last] = x;
      if (last == capacity)
         last = 0;
      else
         ++last;
      ++size;
   }
   public double dequeue() {
      // TODO: add throws RingBufferException if isEmpty()
      // delete and return item from the front
      int tmp = first;
      if (first == capacity)
         first = 0;
      else
         ++first;
      --size;
      return ringBuffer[tmp];
   }
   public double peek() {
      return ringBuffer[first];
   }
}
