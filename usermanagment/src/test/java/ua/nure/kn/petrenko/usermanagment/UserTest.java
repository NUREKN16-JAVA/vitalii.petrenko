package test.java.ua.nure.kn.vitalii.petrenko.usermanagment;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import main.java.ua.nure.kn.vitalii.petrenko.usermanagment.*;
import org.junit.Before;
import org.junit.Test;

public class UserTest {

	private User user;
	private Date dateOfBirth;
	
	@Before
	public void setUp() throws Exception {
		user = new User();
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(1984, Calendar.MAY, 26);
		dateOfBirth = calendar.getTime();
	}
	
	@Test
	public void testGetFullName(){
		user.setFirstName("usr1");
		user.setLastName("Doe");
		assertEquals("Doe, usr1", user.getFullName());
	}
	
	@Test
	public void testGetAge(){
		user.setDateOfBirthd(dateOfBirth);
		assertEquals(2016-1982, user.getAge());
	}
	

}