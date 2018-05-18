import java.io.IOException;

public class NativeMethods {
	
	public static native void greet();
	public native long sum(int number1, long number2);
	public native String reverse(String text);
	public native int[] getPrimeNumbers(int total);
	public native int sumAndReverse(int[] array);
	public native void setInstanceValue(Result result, int value); // private variable
	public native void setClassValue(Class<?> clazz, double value); // static variable
	public native void compute(SomeMethods callback);
	public native void compute(Class<?> callback);
	
	private static final String FILENAME = "SampleJNI.dll";
	
	static {
		String dataModel = System.getProperty("sun.arch.data.model");
		String osName = System.getProperty("os.name").toLowerCase();;

		StringBuilder lib = new StringBuilder("/jni/");

		if (osName.contains("win"))
			lib.append("win/");
//		else if (osName.contains("linux"))
//			lib.append("linux/");
//		else if (osName.contains("mac"))
//			lib.append("mac/");
		else
			throw new UnsatisfiedLinkError(
				String.format("Loading %s: Unsupported operating system (%s)",
				FILENAME, osName));

		if (dataModel.equals("32"))
			lib.append("x86_32/");
		else if (dataModel.equals("64"))
			lib.append("x86_64/");
		else
			throw new UnsatisfiedLinkError(
				String.format("Loading %s: Unknown runtime data model (%s)",
				FILENAME, dataModel));

		if (osName.contains("win"))
			lib.append(FILENAME);

		try {
			NativeUtils.loadLibraryFromJar(lib.toString());
		} catch (IOException e) {
			throw new UnsatisfiedLinkError(e.getMessage());
		}
	}
}