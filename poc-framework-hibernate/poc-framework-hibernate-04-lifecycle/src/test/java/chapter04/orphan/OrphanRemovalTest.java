package chapter04.orphan;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.testng.annotations.Test;

import team.boolbee.poc.hibernate.utils.SessionFactoryHelper;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class OrphanRemovalTest {
    @SuppressWarnings("unchecked")
	@Test
    public void orphanRemovalTest() {
        Long id = createLibrary();

        Session session = SessionFactoryHelper.getSession();
        Transaction tx = session.beginTransaction();

        Library library = (Library) session.load(Library.class, id);
        assertEquals(library.getBooks().size(), 3);

        library.getBooks().remove(0);
        assertEquals(library.getBooks().size(), 2);

        tx.commit();
        session.close();

        session = SessionFactoryHelper.getSession();
        tx = session.beginTransaction();

        Library l2 = (Library) session.load(Library.class, id);
        assertEquals(l2.getBooks().size(), 2);

        Query query = session.createQuery("from Book b");
        List<Book> books = query.list();
        assertEquals(books.size(), 2);

        tx.commit();
        session.close();
    }

    private Long createLibrary() {
        Session session = SessionFactoryHelper.getSession();
        Transaction tx = session.beginTransaction();

        Library library = new Library();
        library.setName("orphanLib");
        session.save(library);

        Book book = new Book();
        book.setLibrary(library);
        book.setTitle("book 1");
        session.save(book);
        library.getBooks().add(book);

        book = new Book();
        book.setLibrary(library);
        book.setTitle("book 2");
        session.save(book);
        library.getBooks().add(book);

        book = new Book();
        book.setLibrary(library);
        book.setTitle("book 3");
        session.save(book);
        library.getBooks().add(book);

        tx.commit();
        session.close();

        return library.getId();
    }
}
