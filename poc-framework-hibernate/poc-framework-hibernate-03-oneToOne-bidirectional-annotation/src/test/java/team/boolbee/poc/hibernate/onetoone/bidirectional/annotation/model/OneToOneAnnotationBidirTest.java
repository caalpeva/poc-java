package team.boolbee.poc.hibernate.onetoone.bidirectional.annotation.model;

import org.testng.annotations.Test;

import team.boolbee.poc.hibernate.onetoone.bidirectional.annotation.dao.PortDAO;
import team.boolbee.poc.hibernate.onetoone.bidirectional.annotation.dao.ServiceDAO;

public class OneToOneAnnotationBidirTest {
	
    @Test
    public void testOneToOneBidirMappedByAnnotation() {
    	Port port1 = new Port();
		port1.setNumber(80);
		port1.setType(PortType.TCP);

		Service service1 = new Service();
		service1.setName("Apache Web Server");
		service1.setPath("/usr/bin/apache2");
		port1.setService(service1);
		service1.setPort(port1);
		
		Port port2 = new Port();
		port2.setNumber(3306);
		port2.setType(PortType.TCP);
		
		Service service2 = new Service();
		service2.setName("MySQL");
		service2.setPath("/usr/bin/mysql");
		port2.setService(service2);
		service2.setPort(port2);
		
		Port port3 = new Port();
		port3.setNumber(8080);
		port3.setType(PortType.TCP);
		
		Service service3 = new Service();
		service3.setName("Apache Server Tomcat");
		service3.setPath("/usr/bin/tomcat");
		port3.setService(service3);
		service3.setPort(port3);

		PortDAO portDAO = new PortDAO();
		ServiceDAO serviceDAO = new ServiceDAO();

		Service service4 = new Service();
		service4.setName("SQLServer");
		service4.setPath("c:\\Program Files\\SQLServer\\sqls.exe");
		serviceDAO.insert(service4);
		
		serviceDAO.insert(service1);
		//serviceDAO.insert(service2);
		portDAO.insert(port2);
		portDAO.insert(port3);

		// Finalmente se elimina el puerto 1, luego el servicio será 
		// borrado en cascada.
		
		serviceDAO.delete(service1);
		portDAO.delete(port3);
    }
}