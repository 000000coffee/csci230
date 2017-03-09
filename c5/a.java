public class a {
   public static void mystery (int[] R, int[] A, int[] B) { 
      int n = A.length();                             // +2 
      if ( n != R.length || n != B.length) return;    // +5
      for (int i = 0; i < n ; ++i) {                  // n + 1
         R[i] = 0;
         for (int j = 0; j <= i; ++j) {               // n^2
            R[i] += A[j] * B[i - j];
         }
      } 
   }
}
