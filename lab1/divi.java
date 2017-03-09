public class divi {
	public static void main(String[] args) {
		int firstArg = Integer.parseInt(args[0]);
		double tmp = (double)firstArg;
		int numTimes = 0;
		while (tmp > 2) {
			tmp = tmp/2;
			++numTimes;
		}
		System.out.println(firstArg + " must be divided " + numTimes + " times to be <= 2");
	}
}
