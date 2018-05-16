public class MainTest {

	public static void main(String[] args) { // throws ClassNotFoundException {
		NativeMethods.greet();

		NativeMethods nm = new NativeMethods();
		System.out.println("In Java, the result obtained is " + nm.sum(10, 5L));

		// System.out.println("= " + nm.reverse(null));
		System.out.println("In Java, the result obtained is " + nm.reverse("123456789"));
		System.out.println("In Java, the result obtained is " + nm.reverse("Arriba la Birra"));

		Result result = new Result();
		System.out.println("In Java, the attribute value is " + "= " + result.getValue());
		System.out.println("In Java, the static attribute value is " + "= " + Result.getStaticValue());

		nm.setInstanceValue(result, 125);
		nm.setClassValue(result.getClass(), 240.8); // Class.forName("Result")

		System.out.println("In Java, the attribute value is " + "= " + result.getValue());
		System.out.println("In Java, the static attribute value is " + "= " + Result.getStaticValue());

		nm.compute(new SomeMethods());
		nm.compute(SomeMethods.class);
		
		int[] array = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
		printArray(array);
		System.out.println("In Java, the result obtained is " + nm.sumAndReverse(array));
		printArray(array);
		
		int[] primeNumbers = nm.getPrimeNumbers(100);
		printArray(primeNumbers);
		
	}

	private static void printArray(int[] arr) {
		System.out.println("Output from Java");
		for (int k = 0; k < arr.length; k++) {
			System.out.print(arr[k] + " ");
			if (k > 0 && (k % 10 == 0)) {
				System.out.println("");
			}
		}
	}
}