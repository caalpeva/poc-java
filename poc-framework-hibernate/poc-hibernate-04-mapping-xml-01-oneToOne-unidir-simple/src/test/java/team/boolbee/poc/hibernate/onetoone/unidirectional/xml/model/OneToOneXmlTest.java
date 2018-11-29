package team.boolbee.poc.hibernate.onetoone.unidirectional.xml.model;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.testng.annotations.Test;

import team.boolbee.poc.hibernate.onetoone.unidirectional.xml.dao.PortDAO;
import team.boolbee.poc.hibernate.onetoone.unidirectional.xml.dao.ServiceDAO;
import team.boolbee.poc.hibernate.utils.HibernateSession;

public class OneToOneXmlTest {
	
    @Test
    public void testOneToOneMappedByXml() {
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

        // Por defecto, el mecanismo lazy está habilitado en el mapeo xml.
        // Se produce LazyInitializationException al intentar acceder a los datos
        // de propiedades complejas fuera de la sessión
//        Port port = portDAO.selectById(apachePort.getId());
//        System.out.println("PortNumber: " + port.getNumber());
//        
//        Service service = port.getService();
//        System.out.println("ServiceName: " + service.getName());
        
        // A continuacion se comprueba que se realiza de la petición SQL
        // en el momento de consultar los datos
        
        Session session = HibernateSession.getSession();
        Port port = (Port) session.get(Port.class, apachePort.getId());
        System.out.println("PortNumber: " + port.getNumber());
        Service service = port.getService();
        System.out.println("ServiceName: " + service.getName());
        session.close();
    }
    
    @Test(dependsOnMethods = "testOneToOneMappedByXml", expectedExceptions = ConstraintViolationException.class)
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