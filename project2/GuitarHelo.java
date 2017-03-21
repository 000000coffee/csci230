
import java.util.Random;

public class GuitarHelo {
   /*    piano keys on the keyboard    */
   private final static String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
   /*    size of the ring buffer and therefore amount of 'zoom'   */

   public static void main(String[] args) {


      /*    Create guitar strings   */
      GuitarString[] strings = new GuitarString[keyboard.length()];

      /*    initialize the guitar strings so the ith character of the string
       *    corresponds to a frequency of 440 × 1.05956 ^ (i - 24)
       */
      for (int i = 0; i < strings.length; ++i) {
         strings[i] = new GuitarString(440.0 * Math.pow(1.05956, (i - 24)));
      }

      RingBuffer samples = new RingBuffer(1000);
      for (int i = 0; !samples.isFull(); ++i) {
         samples.enqueue(0.00);
      }

      StdDraw.enableDoubleBuffering();
      double x0 = 0;
      double y0 = 0;  
      double x1 = 0;  
      double y1 = 0;  

      /*    the main input loop  */
      while (true) {
         if ((strings[0].time() % samples.size()) == 0) {
            StdDraw.clear();
         }
         /*    while has next key
          *       if key inputed isn't valid then break
          *       print the key and pluck the string of that key
          */
         while (StdDraw.hasNextKeyTyped()) {
            char key = StdDraw.nextKeyTyped();
            if (keyboard.indexOf(key) == -1)
               break;
            System.out.println(key);
            strings[keyboard.indexOf(key)].pluck();
         }

         /*    compute the superposition of the samples  */
         double sample = 0.00;
         for (int i = 0; i < keyboard.length(); ++i) {
            sample += strings[i].sample();
         }

         /*    send the result to standard audio   */
         StdAudio.play(sample);

         x0 = (strings[0].time() % samples.size()) / (double)samples.size();
         y0 = (samples.dequeue()/1.5)+0.5;
         samples.enqueue(sample);

         /*    advance the simulation of each guitar string by one step    */
         for (int i = 0; i < keyboard.length(); ++i) {
            strings[i].tic();
         }
         x1 = (strings[0].time() % samples.size()) / (double)samples.size();
         if (x0 > x1)
            x0 = 0;
         y1 = (samples.peek()/1.5)+0.5;
         StdDraw.line(x0, y0, x1, y1);
         if ((strings[0].time() % samples.size()) == 0) {
            StdDraw.show();
            StdDraw.pause(10);
         }
      }
   }
}
