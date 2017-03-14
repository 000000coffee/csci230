public class GuitarHelo {

   public static void main(String[] args) {
      
      final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
      
      // Create guitar strings
      GuitarString[] strings = new GuitarString[keyboard.length()];
      for (int i = 0; i < strings.length; ++i) {
         strings[i] = new GuitarString(440.0 * Math.pow(1.05956, (i - 24)));
      }

      double time = 0.00;
      double sampleHolder = 0.00;

      // the main input loop
      while (true) {
         while (StdDraw.hasNextKeyTyped()) {
            char key = StdDraw.nextKeyTyped();
            if (keyboard.indexOf(key) == -1)
               break;
            System.out.println(key);
            strings[keyboard.indexOf(key)].pluck();
         }

         // compute the superposition of the samples
         double sample = 0.00;
         for (int i = 0; i < keyboard.length(); ++i) {
            sample += strings[i].sample();
         }

         // send the result to standard audio
         StdAudio.play(sample);
         
         // draw
         //StdDraw.enableDoubleBuffering();
         //StdDraw.line(time - 1.00, sampleHolder, time, sample);
         //StdDraw.show();
         //sampleHolder = sample;
         //++time;

         // advance the simulation of each guitar string by one step
         for (int i = 0; i < keyboard.length(); ++i) {
            strings[i].tic();
         }
      }
   }
}
