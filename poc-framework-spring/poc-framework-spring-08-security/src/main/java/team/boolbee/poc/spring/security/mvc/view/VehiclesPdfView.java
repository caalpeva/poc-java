package team.boolbee.poc.spring.security.mvc.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;

import team.boolbee.poc.spring.hibernate.model.Vehicle;

public class VehiclesPdfView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map model, Document document, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<Vehicle> vehicles = (List<Vehicle>) model.get("vehicles");
		//Person person = (Person) model.get("person");
		
		Table table = new Table(3);
		table.setWidth(90);
		table.setBorderWidth(1);
		
		table.addCell("Type");
		table.addCell("Plate number");
		table.addCell("Registration date");
		
		for(Vehicle vehicle: vehicles) {
			table.addCell(vehicle.getType().name());
			table.addCell(vehicle.getPlateNumber());
			table.addCell(vehicle.getRegistrationDate().toString());
		}
		
		document.add(table);
	}
}