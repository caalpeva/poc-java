package team.boolbee.poc.spring.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class Utils {
	
	/**
	 * Metodo que regresa una Lista de Strings con las fechas siguientes, segun el parametro count
	 * @param count
	 * @return
	 */
	public static List<String> getNextDays(int count) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		// Today's Date
		Date start = new Date();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, count); // Next N days from now
		Date endDate = cal.getTime();

		GregorianCalendar gcal = new GregorianCalendar();
		gcal.setTime(start);
		List<String> nextDays = new ArrayList<String>();
		while (!gcal.getTime().after(endDate)) {
			nextDays.add(sdf.format(gcal.getTime()));
			gcal.add(Calendar.DATE, 1);
		}
		return nextDays;
	}
	
	/**
	 * Metodo para guardar una imagen en el directorio /resources/images/ de nuestro despligue
	 * @param multiPart
	 * @param request
	 * @return
	 */
	public static String saveImage(MultipartFile multipartFile, HttpServletRequest request) {
		// Se obtiene el nombre original del archivo
		String srcName = multipartFile.getOriginalFilename();
		// Se reemplazan en el nombre de archivo los espacios por guiones
		srcName = srcName.replaceAll(" ", "-");
		// Se agrega al archivo 8 caracteres aleatorios para evitar duplicados
		String dstFinal = randomAlphaNumeric(8) + srcName;
		// Se obtiene la ruta absoluta del directorio imágenes
		String dstPath = request.getServletContext().getRealPath("/resources/images/");
		try {
			File imageFile = new File(dstPath + dstFinal);
			// Se guarda el archivo en el disco duro
			multipartFile.transferTo(imageFile);
			return dstFinal;
		} catch (IllegalStateException | IOException e) {
			System.err.println(e);
			return null;
		}
	}
	
	// Metodo para generar una cadena de longitud N de caracteres aleatorios.
	public static String randomAlphaNumeric(int count) {
		String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * CARACTERES.length() - 1);
			builder.append(CARACTERES.charAt(character));
		}
		return builder.toString();
	}
}
