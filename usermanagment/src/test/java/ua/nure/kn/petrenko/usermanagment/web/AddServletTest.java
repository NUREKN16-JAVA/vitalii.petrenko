package test.java.ua.nure.kn.petrenko.usermanagment.web;
import java.text.DateFormat;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;

import main.java.ua.nure.kn.petrenko.usermanagment.User;
import main.java.ua.nure.kn.petrenko.usermanagment.web.AddServlet;

public class AddServletTest extends MockServletTestCase {
    protected void setUp() throws Exception {
        super.setUp();
        createServlet(AddServlet.class);
    }
    
    @Test
    public void testAdd() {
        Date date = new Date();
        User newUser = new User("Oleg", "Ivanov", date);
        User user = new User(new Long(1000), "Oleg", "Ivanov", date);
        getMockUserDao().expectAndReturn("create", newUser, user);
        
        addRequestParameter("firstName", "Oleg");
        addRequestParameter("lastName", "Ivanov");
        addRequestParameter("date", DateFormat.getDateInstance().format(date));
        addRequestParameter("okButton", "Ok");
        doPost();
    }
    
    @Test
    public void testAddEmptyFirstName() {
        Date date = new Date();
        addRequestParameter("lastName", "Ivanov");
        addRequestParameter("date", DateFormat.getDateInstance().format(date));
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }
    
    @Test
    public void testAddEmptyLastName() {
        Date date = new Date();
        addRequestParameter("firstName", "Oleg");
        addRequestParameter("date", DateFormat.getDateInstance().format(date));
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }
    
    @Test
    public void testAddEmptyDate() {
        Date date = new Date();
        addRequestParameter("firstName", "Oleg");
        addRequestParameter("lastName", "Ivanov");
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }
    
    @Test
    public void testAddEmptyDateIncorrect() {
        Date date = new Date();
        addRequestParameter("firstName", "Oleg");
        addRequestParameter("lastName", "Ivanov");
        addRequestParameter("date", "asdasdasdas");
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }
}