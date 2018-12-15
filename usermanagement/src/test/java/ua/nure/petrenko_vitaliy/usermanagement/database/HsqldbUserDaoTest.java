package test.java.ua.nure.kn.petrenko_vitaliy.usermanagment.database;

import static org.junit.Assert.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.junit.Before;
import org.junit.Test;
import junit.framework.TestCase;
import main.java.ua.nure.kn.petrenko_vitaliy.usermanagment.*;
import main.java.ua.nure.kn.petrenko_vitaliy.usermanagment.database.ConnectionFactory;
import main.java.ua.nure.kn.petrenko_vitaliy.usermanagment.database.ConnectionFactoryImplementation;
import main.java.ua.nure.kn.petrenko_vitaliy.usermanagment.database.DatabaseCustomException;
import main.java.ua.nure.kn.petrenko_vitaliy.usermanagment.database.HsqldbUserDao;

public class HsqldbUserDaoTest extends DatabaseTestCase {
	
	 private HsqldbUserDao dao;
	 private ConnectionFactory connectionFactory;
	 
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		dao = new HsqldbUserDao(connectionFactory);
	}
		
	@Test
    public void testUpdate() {
        try {
            User user = new User();
            user.setId(1L);
           
            String newFirstName = "Dima";
            String newLastName = "Dimitriy";
            Date newDate = new Date(2025,10,121);
            user.setFirstName(newFirstName);
            user.setLastName(newLastName);
            user.setDateOfBirthd(newDate);
            
            dao.update(user);
            
            user = dao.find(user.getId());
            assertEquals("Firstname fail", newFirstName, user.getFirstName());
            assertEquals("Lastname fail", newLastName, user.getLastName());
            assertEquals("Date fail", newDate, user.getDateOfBirthd());
            
        } catch (DatabaseCustomException e) {
            e.printStackTrace();
            fail(e.toString());
        }
    }
	
	@Test
	public void testDelete() {
	 try {
		User user = new User();
		long id = 1L;
		user.setId(id);
		
			dao.delete(user);
			
		}catch(DatabaseCustomException e){
			e.printStackTrace();
			fail(e.toString());
		}
	}
	 
	@Test
    public void testFind() {
        try {
        	
        	long id=1L;
        	String newFirstName = "Olga";
            String newLastName = "Prokopenko";
            
            
            DateFormat format = DateFormat.getInstance();
            
        	Date date = format.parse("2000-12-12");
        	
            User user = dao.find(id);
            
            assertEquals("Firstname fail", newFirstName, user.getFirstName());
            assertEquals("Lastname fail", newLastName, user.getLastName());
            assertEquals("Date fail", date, user.getDateOfBirthd().toString());
            
        } catch (DatabaseCustomException e) {
            e.printStackTrace();
            fail(e.toString());
        } catch (ParseException e) {
			e.printStackTrace();
		}
        long id = 3L;
        try {
            dao.find(id);   
        } catch (DatabaseCustomException e) {
            assertEquals(e.getMessage().toString(), "Could not find the user with id="+id);
            
        }
    }
	
	@Test
	public void testFindAll(){
		try {
			Collection collection = dao.findAll();
			  assertNotNull("Collection is null", collection);
	          assertEquals("Collection size.", 2, collection.size());
		} catch (DatabaseCustomException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}
	
	@Test
	public void testCreate() {
		 try {
			 User user = new User();
			 user.setFirstName("John");
			 user.setLastName("Doe");
			 
			 user.setDateOfBirthd(new Date(1907,5,3));
			 assertNull(user.getId());
			 
			 user = dao.create(user);
			 
			 assertNotNull(user);
			 
			 assertNotNull(user.getId());
		} catch (DatabaseCustomException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	
	}
	
	@Override
	protected IDatabaseConnection getConnection() throws Exception {
		connectionFactory=new ConnectionFactoryImplementation( "org.hsqldb.jdbcDriver", "jdbc:hsqldb:file:db/usermanagement", "sa", "");
		return new DatabaseConnection(connectionFactory.createConnection()) ;
	}
	
	@Override
	protected IDataSet getDataSet() throws Exception {
		IDataSet dataSet = new XmlDataSet(getClass().getClassLoader().getResourceAsStream("usersDataSet.xml"));
		return dataSet;
	}
	
}