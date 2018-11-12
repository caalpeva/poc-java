package chapter04.general;

import chapter04.model.SimpleObject;
import team.boolbee.poc.hibernate.utils.HibernateSession;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PersistingEntitiesTest {
	
    @Test
    public void testSaveLoad() {
        Long id = null;

        Session session = HibernateSession.getSession();
        Transaction tx = session.beginTransaction();

        SimpleObject obj = new SimpleObject();
        obj.setText("SAVE AND LOAD");
        obj.setValue(10L);

        session.save(obj);
        assertNotNull(obj.getId());
        // we should have an id now, set by Session.save()
        id = obj.getId();

        tx.commit();
        session.close();

        session = HibernateSession.getSession();
        tx = session.beginTransaction();

        // we're loading the object by id
        SimpleObject o2 = (SimpleObject) session.load(SimpleObject.class, id);
        assertEquals(o2.getText(), "SAVE AND LOAD");
        assertNotNull(o2.getValue());
        assertEquals(o2.getValue().longValue(), 10L);

        SimpleObject o3 = (SimpleObject) session.load(SimpleObject.class, id);

        // since o3 and o2 were loaded in the same session, they're not only
        // equivalent - as shown by equals() - but equal, as shown by ==.
        // since obj was NOT loaded in this session, it's equivalent but not ==.
        assertEquals(o2, o3);
        assertEquals(obj, o2);

        assertTrue(o2 == o3);
        assertFalse(o2 == obj);

        tx.commit();
        session.close();
    }

    @Test
    public void testSavingEntitiesTwice() {
        Long id;
        Session session = HibernateSession.getSession();
        Transaction tx = session.beginTransaction();

        SimpleObject obj = new SimpleObject();

        obj.setText("SAVING ONCE");
        obj.setValue(10L);

        session.save(obj);
        assertNotNull(obj.getId());

        id = obj.getId();

        tx.commit();
        session.close();

        // El objeto pasa del estado transitorio (cuando se crea) al estado persistente (cuando se guarda por primera vez)
        // y luego pasa al estado disociado (cuando se cierra la sesión).
        
        session = HibernateSession.getSession();
        tx = session.beginTransaction();

        obj.setText("SAVING TWICE");
        obj.setValue(12L);

        // No es apropiado guardar de nuevo un objeto (en estado disociado) que ha sido previamente persistido.
        // Al invocar al método Session.save() con un objeto disociado lo considerará como transitorio, por lo tanto,
        // dicho objeto se actualizará y en realidad terminará creando un duplicado persistente con un nuevo identificador.
        session.save(obj);
        
        tx.commit();
        session.close();

        // note that save() creates a new row in the database!
        // this is wrong behavior. Don't do this!
        assertNotEquals(id, obj.getId());
    }

    @Test
    public void testSaveOrUpdateEntity() {
        Long id;
        Session session = HibernateSession.getSession();
        Transaction tx = session.beginTransaction();

        SimpleObject obj = new SimpleObject();

        obj.setText("SAVE OR UPDATE");
        obj.setValue(14L);

        session.save(obj);
        assertNotNull(obj.getId());

        id = obj.getId();

        tx.commit();
        session.close();

        // El objeto pasa del estado transitorio (cuando se crea) al estado persistente (cuando se guarda por primera vez)
        // y luego pasa al estado disociado (cuando se cierra la sesión).
        
        session = HibernateSession.getSession();
        tx = session.beginTransaction();

        obj.setValue(12L);
        
        // Se actualiza el objeto mientras está en estado disociado
        // y se devuelve al estado persistente invocando a Session.saveOrUpdate().
        session.saveOrUpdate(obj);
        
        // Idealmente para actualizar un objeto, lo adecuado es obtener dicho objeto de la sesión en primer lugar.
        // Una vez que se disponga del objeto en estado persistente, Hibernate administra las actualizaciones
        // de la base de datos a medida que se modifican los campos y las propiedades de dicho objeto, sin necesidad
        // de invocar al método Session.saveOrUpdate().

        tx.commit();
        session.close();

        // saveOrUpdate() will update a row in the database
        // if one matches. This is what one usually expects.
        assertEquals(id, obj.getId());
    }
}
