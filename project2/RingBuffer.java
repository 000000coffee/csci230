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
   public void enqueue(double x) {
      try {
         this.validateEnqueue(x);
      }
      catch (RingBufferException ex) { ex.printMessage(); }
   }
   public double dequeue() {
      int tmp = first;
      try {
         this.validateDequeue();
      }
      catch (RingBufferException ex) { ex.printMessage(); }
      return ringBuffer[tmp];
   }
   private void validateEnqueue(double x) throws RingBufferException {
      if (this.isFull())
         throw new RingBufferException("Full");
      ringBuffer[last] = x;
      if (last == (capacity - 1))
         last = 0;
      else
         ++last;
      ++size;
   }
   private void validateDequeue() throws RingBufferException {
      if (this.isEmpty())
         throw new RingBufferException("Empty");
      if (first == (capacity - 1))
         first = 0;
      else
         ++first;
      --size;
   }
   @SuppressWarnings("serial")
   public class RingBufferException extends Exception {
      private String message = null;
      public RingBufferException() {
         super();
      }
      public RingBufferException(String message, Throwable cause) {
         super(message, cause);
         this.message = message;
      }
      public RingBufferException(String message) {
         super(message);
         this.message = message;
      }
      public RingBufferException(Throwable cause) {
         super(cause);
      }
      public void printMessage() { System.out.println(message); }
      @Override
      public String toString() { return message; }
      @Override
      public String getMessage() { return message; }
   }
}
