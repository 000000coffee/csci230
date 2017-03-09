public class str {
	public static void main(String[] args) {
		char[] blah = args[0].toCharArray();
      int len = args[0].length();
      int factorial = 1;
      for (int i = 1; i <= len; ++i) {
         factorial *= i;
      }
   	for (int i = 0; i < factorial; ++i) {
         for (int j = 0; j < len; ++j) {
            System.out.print(blah[j]);
         }
         System.out.println();
         char tmp = blah[i%len];
         if ((i%len) < len-1) {
            blah[i%len] = blah[(i%len)+1];
            blah[(i%len)+1] = tmp;
         }
         else {
            blah[i%len] = blah[0];
            blah[0] = tmp;
         }
      }
      return;
	}
}
