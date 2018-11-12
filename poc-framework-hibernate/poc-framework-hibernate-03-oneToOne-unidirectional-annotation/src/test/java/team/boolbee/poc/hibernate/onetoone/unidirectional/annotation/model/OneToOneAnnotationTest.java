package team.boolbee.poc.hibernate.onetoone.unidirectional.annotation.model;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.testng.annotations.Test;

import team.boolbee.poc.hibernate.onetoone.unidirectional.annotation.dao.PortDAO;
import team.boolbee.poc.hibernate.onetoone.unidirectional.annotation.dao.ServiceDAO;
import team.boolbee.poc.hibernate.onetoone.unidirectional.annotation.model.Port;
import team.boolbee.poc.hibernate.onetoone.unidirectional.annotation.model.PortType;
import team.boolbee.poc.hibernate.onetoone.unidirectional.annotation.model.Service;

public class OneToOneAnnotationTest {
	
    @Test
    public void testOneToOneMappedByAnnotation() {
        Service sqlServerService = new Service();
        sqlServerService.setName("SQLServer");
        sqlServerService.setPath("C:\\Program Files\\SQLServer\\sqls.exe");

        // Se almacena el servicio sqlServer sin puerto asociado
        ServiceDAO serviceDAO = new ServiceDAO();
        serviceDAO.insert(sqlServerService);

        List<Service> services = serviceDAO.selectAll();
        assertEquals(services.size(), 1);
        
        Service mySQLService = new Service();
        mySQLService.setName("MySQL");
        mySQLService.setPath("/usr/bin/mysql");

        Port mySQLPort = new Port();
        mySQLPort.setNumber(3306);
        mySQLPort.setType(PortType.TCP);
        mySQLPort.setService(mySQLService);
       
        Service apacheService = new Service();
        apacheService.setName("Apache Web Server");
        apacheService.setPath("/usr/bin/apache2");
        
        Port apachePort = new Port();
        apachePort.setNumber(80);
        apachePort.setType(PortType.TCP);
        apachePort.setService(apacheService);
        
        PortDAO portDAO = new PortDAO();
        portDAO.insert(mySQLPort);
        portDAO.insert(apachePort);
        
        List<Port> ports = portDAO.selectAll();
        assertEquals(ports.size(), 2);
        
        services = serviceDAO.selectAll();
        assertEquals(services.size(), 3);
        
        portDAO.delete(mySQLPort);

        ports = portDAO.selectAll();
        assertEquals(ports.size(), 1);
        
        services = serviceDAO.selectAll();
        assertEquals(services.size(), 2);
    }
    
    @Test(dependsOnMethods = "testOneToOneMappedByAnnotation", expectedExceptions = ConstraintViolationException.class)
    public void testUniqueKeyConstraintViolation() {
    	Service tomcatService = new Service();
    	tomcatService.setName("Apache Tomcat");
    	tomcatService.setPath("C:\\Program Files\\Apache Software Foundation\\Tomcat 7.0");
    	
    	Port tomcatPort = new Port();
    	tomcatPort.setNumber(80);
    	tomcatPort.setType(PortType.TCP);
    	tomcatPort.setService(tomcatService);
    	
    	PortDAO portDAO = new PortDAO();
    	portDAO.insert(tomcatPort);
    }
}