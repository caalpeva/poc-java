
 
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
 
/**
 * Clase auxiliar para facilitar el uso de librerías nativas
 */
public class NativeUtils {
 
	private NativeUtils() {
		// Constructor privado para evitar la instanciación de la clase
	}
 
	/**
	 * Método encargado de cargar una librería desde el archivo JAR actual.
	 * La carga de la librería se debe realizar desde una ruta accesible por el sistema operativo. Por lo tanto, Previamente a la carga de la librería Antes de realizar la carga de la librería se copia del archivo librería dentro del directorio
	 * temporal del sistema antes de ser cargada. Este archivo temporal se borra despues de finalizar el programa. 
	 * @param path Ruta absoluta del fichero dentro del JAR (comienza por '/'), e.g. /package/File.ext
	 * @throws IOException Si la creación del fichero temporal o las operaciones de lectura/escritura fallan
	 * @throws IllegalArgumentException Si la ruta indicada no existe, no es absoluta o no cumple las restricciones de un fichero temporal (restriction of {@see File#createTempFile(java.lang.String, java.lang.String)}).
	 */
	public static void loadLibraryFromJar(String path) throws IOException {
		if (path == null || !path.startsWith("/")) {
			throw new IllegalArgumentException("The path must be absolute (start with '/').");
		}
 
		System.out.println(path);
		
		// Obtain filename from path
		String[] parts = path.split("/");
		String filename = (parts.length > 1) ? parts[parts.length - 1] : null;
 
		// Split filename to prefix and suffix (extension)
		String prefix = "";
		String suffix = null;
		if (filename != null) {
			parts = filename.split("\\.", 2);
			prefix = parts[0];
			suffix = (parts.length > 1) ? "." + parts[parts.length - 1] : null;
		}
 
		// Check if the filename is okay
		if (filename == null || prefix.length() < 3) {
			throw new IllegalArgumentException("The filename must be at least 3 characters long.");
		}
 
		// Prepare temporary file
		File temp = File.createTempFile(prefix, suffix);
		temp.deleteOnExit();
 
		if (!temp.exists()) {
			throw new FileNotFoundException("File " + temp.getAbsolutePath() + " does not exist.");
		}
 
		// Prepare buffer for data copying
		byte[] buffer = new byte[1024];
		int readBytes;
 
		// Open and check input stream
		InputStream is = NativeUtils.class.getResourceAsStream(path);
		if (is == null) {
			throw new FileNotFoundException("File " + path + " was not found inside JAR.");
		}
 
		// Open output stream and copy data between source file in JAR and the temporary file
		OutputStream os = new FileOutputStream(temp);
		try {
			while ((readBytes = is.read(buffer)) != -1) {
				os.write(buffer, 0, readBytes);
			}
		} finally {
			// If read/write fails, close streams safely before throwing an exception
			os.close();
			is.close();
		}
 
		// Finally, load the library
		System.out.println(temp.getAbsolutePath());
		System.load(temp.getAbsolutePath());
	}
}
