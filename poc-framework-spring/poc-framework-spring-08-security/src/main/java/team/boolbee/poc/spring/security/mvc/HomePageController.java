package team.boolbee.poc.spring.security.mvc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import team.boolbee.poc.spring.hibernate.model.Person;
import team.boolbee.poc.spring.hibernate.services.VehicleRegistrationService;

public class HomePageController extends AbstractController {
  public HomePageController() {}
  
  protected ModelAndView handleRequestInternal(
      HttpServletRequest request, HttpServletResponse response) 
      throws Exception {
    
    List<Person> persons = vehicleRegistrationService.getPersons();
    
    return new ModelAndView("home", "persons", persons);
  }
  
  // injected
  private VehicleRegistrationService vehicleRegistrationService;
  public void setVehicleRegistrationService(VehicleRegistrationService vehicleRegistrationService) {
    this.vehicleRegistrationService = vehicleRegistrationService;
  }
}
