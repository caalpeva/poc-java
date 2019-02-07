package team.boolbee.poc.spring.security.mvc.tiles;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.tiles.ComponentContext;
import org.springframework.web.servlet.view.tiles.ComponentControllerSupport;

import team.boolbee.poc.spring.security.services.VehicleRegistrationService;

public class HeaderTileController extends ComponentControllerSupport {

	@Override
	protected void doPerform(ComponentContext componentContext, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		int vehiclesToday = getVehicleRegistrationService().getVehiclesForDay(new Date()).size();
		componentContext.putAttribute("vehiclesToday", vehiclesToday);
	}
	
	private VehicleRegistrationService getVehicleRegistrationService() {
		return (VehicleRegistrationService) getApplicationContext().getBean("vehicleRegistrationService");
	}
}