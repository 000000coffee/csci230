public class GuitarString {
   private static final double SAMPLE_RATE = 44100;
   private double frequency; // sampling rate per second
   private RingBuffer string;
   public GuitarString(double frequency) {
      int n = ((int)(Math.ceil(SAMPLE_RATE / (double)frequency)));
      string = new RingBuffer(n);
      for (int i = 0; i < n; ++i) {
         
      }
   }
   public GuitarString(double[] init) {
   
   }
   public void pluck() {
   
   }
   public void tic() {
   
   }
   public double sample() {
   
   }
   public int time() {
   
   }
}
