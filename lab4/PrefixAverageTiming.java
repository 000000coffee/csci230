public class PrefixAverageTiming {

   /** Returns an array a such that, for all j, a[j] equals the average of x[0], ..., x[j]. */
   public static double[] prefixAverage1(double[] x) {
      int n = x.length;
      double[] a = new double[n];    // filled with zeros by default
      for (int j=0; j < n; j++) {
         double total = 0;            // begin computing x[0] + ... + x[j]
         for (int i=0; i <= j; i++)
            total += x[i];
         a[j] = total / (j+1);        // record the average
      }
      return a;
   }
   /** Returns an array a such that, for all j, a[j] equals the average of x[0], ..., x[j]. */
   public static double[] prefixAverage2(double[] x) {
      int n = x.length;
      double[] a = new double[n];    // filled with zeros by default
      double total = 0;              // compute prefix sum as x[0] + x[1] + ...
      for (int j=0; j < n; j++) {
         total += x[j];               // update prefix sum to include x[j]
         a[j] = total / (j+1);        // compute average based on current sum
      }
      return a;
   }

	  /**
	   * Tests the two versions of the 'repeat' algorithm, doubling the
	   * size of n each trial, beginning with the given start value. The
	   * first command line argument can be used to change the number of
	   * trials, and the second to adjust the start value.
	   */
   public static void main(String[] args) {
      int n = 50000;                           // starting value
      int trials = 10;
      try {
         if (args.length > 0)
            trials = Integer.parseInt(args[0]);
         if (args.length > 1)
            n = Integer.parseInt(args[1]);
      } catch (NumberFormatException e) { }
      for (int t=0; t < trials; t++) {
         double testArg[] = new double [n];

         for (int i = 0; i < n; ++i) {
            testArg[i] = i;
         }
         // let's run version 2 (the quicker one) first
         System.out.println("Testing repeat2...");
         long startTime = System.currentTimeMillis();
         prefixAverage2(testArg);
         long endTime = System.currentTimeMillis();
         long elapsed = endTime - startTime;
         System.out.println(String.format("n: %9d took %12d milliseconds", n, elapsed));

         System.out.println("Testing repeat1...");
         startTime = System.currentTimeMillis();
         prefixAverage1(testArg);
         endTime = System.currentTimeMillis();
         elapsed = endTime - startTime;
         System.out.println(String.format("n: %9d took %12d milliseconds", n, elapsed));
         n *= 2;                                // double the problem size
      }
   }
}
