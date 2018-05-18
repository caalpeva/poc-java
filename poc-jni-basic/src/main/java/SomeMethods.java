
public class SomeMethods {
	
	public double multiply(int number1, double number2) {
		System.out.println(String.format("In Java, compute %d * %.2f", number1, number2));
		return number1 * number2;
	}
	
	public static void onMessageReceived(String text) {
		System.out.println("In Java, the message received from C: " + text);
	}
}
