
import java.util.Random;

public class GuitarString {
   private static final int SAMPLE_RATE = 44100;
   private static final double ENERGY_DECAY_FACTOR = 0.994;
   private double frequency;  // sampling rate per second
   private int tics;         // times tic() is called
   private RingBuffer string; // ring buffer imitating a guitarString
   /*    compiler ~ frequency to fill a ring buffer of size with all 0's   */
   public GuitarString(double frequency) {
      tics = 0;
      int n = ((int)(Math.ceil(((double)SAMPLE_RATE) / frequency)));
      string = new RingBuffer(n);
      for (int i = 0; i < n; ++i) {
         string.enqueue(0.00);
      }
   }
   /*    compiler ~ known samples to initialize the string with   */
   public GuitarString(double[] init) {
      tics = 0;
      for (int i = 0; i < init.length; ++i) {
         string.enqueue(init[i]);
      }
   }
   /*    pluck the string ~ input random values to ring buffer    */
   public void pluck() {
      Random rand = new Random();
      for (int i = string.getFirst(); i < string.size(); ++i) {
         double r = rand.nextDouble() - 0.5; // rand between -0.5 and +0.5
         string.dequeue();                   // first dequeue to make space
         string.enqueue(r);                  // then enqueue our rand
      }
   }
   /*    advance the string one tic by applying the Karplus-Strong update  */
   public void tic() {
      double tmp = string.dequeue();            // remove front
      double avg = 0.5 * (string.peek() + tmp); // average of first two items
      double next = ENERGY_DECAY_FACTOR * avg;  // compute next
      string.enqueue(next);                     // enqueue next
      ++tics;                                   // increment tics
   }
   /*    return front sample of string    */
   public double sample() {
      return string.peek();
   }
   /*    number of times tic was called   */
   public int time() {
      return tics;
   }
}
