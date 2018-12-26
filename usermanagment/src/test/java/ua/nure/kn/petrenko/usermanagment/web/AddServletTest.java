package test.java.ua.nure.kn.vitalii.petrenko.usermanagment.web;
import java.text.DateFormat;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;

import main.java.ua.nure.kn.vitalii.petrenko.usermanagment.User;
import main.java.ua.nure.kn.vitalii.petrenko.usermanagment.web.AddServlet;

public class AddServletTest extends MockServletTestCase {
    protected void setUp() throws Exception {
        super.setUp();
        createServlet(AddServlet.class);
    }
    
    @Test
    public void testAdd() {
        Date date = new Date();
        User newUser = new User("NameTest1", "NameTest2", date);
        User user = new User(new Long(1000), "NameTest1", "NameTest2", date);
        getMockUserDao().expectAndReturn("create", newUser, user);
        
        addRequestParameter("firstName", "NameTest1");
        addRequestParameter("lastName", "NameTest2");
        addRequestParameter("date", DateFormat.getDateInstance().format(date));
        addRequestParameter("okButton", "Ok");
        doPost();
    }
    
    @Test
    public void testAddEmptyFirstName() {
        Date date = new Date();
        addRequestParameter("lastName", "NameTest2");
        addRequestParameter("date", DateFormat.getDateInstance().format(date));
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }
    
    @Test
    public void testAddEmptyLastName() {
        Date date = new Date();
        addRequestParameter("firstName", "NameTest1");
        addRequestParameter("date", DateFormat.getDateInstance().format(date));
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }
    
    @Test
    public void testAddEmptyDate() {
        Date date = new Date();
        addRequestParameter("firstName", "NameTest1");
        addRequestParameter("lastName", "NameTest2");
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }
    
    @Test
    public void testAddEmptyDateIncorrect() {
        Date date = new Date();
        addRequestParameter("firstName", "NameTest1");
        addRequestParameter("lastName", "NameTest2");
        addRequestParameter("date", "asdasdasdas");
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }
}