public class RingBuffer {
   private double[] ringBuffer;
   private int capacity;
   private int first;
   private int last;
   private int size;
   public RingBuffer() {
      this.capacity = 100;
      ringBuffer = new double[this.capacity];
      this.size = 0;
      this.first = 0;
      this.last = 0;
   }
   public RingBuffer(int capacity) {
      this.capacity = capacity;
      ringBuffer = new double[this.capacity];
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
   public int getLast() {
      return this.last;
   }
   public double peek() {
      return ringBuffer[first];
   }
   public void enqueue(double x) throws RingBufferException {
      try {
         if (this.isFull()) { throw new RingBufferException("full"); }
         ringBuffer[last] = x;
         if (last == (capacity - 1))
            last = 0;
         else
            ++last;
         ++size;
      }
      catch (RingBufferException ex) {
         throw ex;
      }
   }
   public double dequeue() throws RingBufferException {
      try {
         if (this.isEmpty()) { throw new RingBufferException("empty"); }
         int tmp = first;
         if (first == (capacity - 1))
            first = 0;
         else
            ++first;
         --size;
         return ringBuffer[tmp];
      }
      catch (RingBufferException ex) {
         throw ex;
      }
   }
   public class RingBufferException extends Exception {
      public RingBufferException(String message) {
         super(message);
      }
   }
}
