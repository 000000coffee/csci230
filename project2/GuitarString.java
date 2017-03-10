import java.util.Random;

public class GuitarString {
   private static final int SAMPLE_RATE = 44100;
   private static final double ENERGY_DECAY_FACTOR = 0.994;
   private double frequency; // sampling rate per second
   private int times;
   private RingBuffer string;
   public GuitarString(double frequency) {
      times = 0;
      int n = ((int)(Math.ceil(((double)SAMPLE_RATE) / frequency)));
      string = new RingBuffer(n);
      for (int i = 0; i < n; ++i) {
         string.enqueue(0.00);
      }
   }
   public GuitarString(double[] init) {
      times = 0;
      for (int i = 0; i < init.length; ++i) {
         string.enqueue(init[i]);
      }
   }
   public void pluck() {
      Random rand = new Random();
      for (int i = string.getFirst(); i < string.size(); ++i) {
         string.enqueue(rand.nextDouble() - 0.5);
      }
   }
   public void tic() {
      double tmp = string.dequeue();
      double next = ENERGY_DECAY_FACTOR * 0.5 * (string.peek() + tmp);
      ++times;
      string.enqueue(next);
   }
   public double sample() {
      return string.peek();
   }
   public int time() {
      return times;
   }
}
