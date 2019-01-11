package team.boolbee.poc.spring.security.mvc.view;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import team.boolbee.poc.spring.hibernate.model.Person;
import team.boolbee.poc.spring.hibernate.model.Vehicle;

public class VehiclesExcelView extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map model, HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<Vehicle> vehicles = (List<Vehicle>) model.get("vehicles");
		Person person = (Person) model.get("person");
		
		HSSFSheet sheet = createSheet(workbook, person.getName() + " " + person.getSurname());
		
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
		
		int rowNum = 1;
		for (Vehicle vehicle: vehicles) {
			rowNum = addVehicleRow(sheet, cellStyle, rowNum, vehicle);
		}
	}
	
	private HSSFSheet createSheet(HSSFWorkbook workbook, String fullNameOfPerson) {
		HSSFSheet sheet = workbook.createSheet("Vehicles for " + fullNameOfPerson);
		HSSFRow header = sheet.createRow(0);
		header.createCell((short) 0).setCellValue("Date");
		header.createCell((short) 1).setCellValue("Type");
		header.createCell((short) 2).setCellValue("Plate number");
		return sheet;
	}
	
	private int addVehicleRow(HSSFSheet sheet, HSSFCellStyle cellStyle, int rowNum, Vehicle vehicle) {
		HSSFRow row = sheet.createRow(rowNum++);
		row.createCell((short) 0).setCellValue(vehicle.getRegistrationDate());
		row.createCell((short) 1).setCellValue(vehicle.getType().name());
		row.createCell((short) 2).setCellValue(vehicle.getPlateNumber());
		row.getCell((short) 0).setCellStyle(cellStyle);
		return rowNum;
	}
}