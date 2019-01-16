package team.boolbee.poc.spring.remoting.model;

public interface CitationService {
  public Citation[] getCitationsForVehicle(
      String state, String plateNumber);
}
