package test.java.ua.nure.kn.vitalii.petrenko.usermanagment.web;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.junit.Test;

import main.java.ua.nure.kn.vitalii.petrenko.usermanagment.User;
import main.java.ua.nure.kn.vitalii.petrenko.usermanagment.web.BrowseServlet;

public class BrowseServletTest extends MockServletTestCase {
    protected void setUp() throws Exception {
        super.setUp();
        createServlet(BrowseServlet.class);
    }
    
    @Test
    public void testBrowse() {
        User user = new User(new Long(1000), "NameTest1", "NameTest2", new Date());
        List list = Collections.singletonList(user);
        getMockUserDao().expectAndReturn("findAll", list);
        doGet();
        Collection collection = (Collection) getWebMockObjectFactory().getMockSession().getAttribute("users");
        assertNotNull("Could not find list of users in session", collection);
        assertSame(list, collection);
    }
    
    @Test
    public void testEdit() {
        User user = new User(new Long(1000), "NameTest1", "NameTest2", new Date());
        getMockUserDao().expectAndReturn("find", new Long(1000), user);
        addRequestParameter("editButton", "Edit");
        addRequestParameter("id", "1000");
        doPost();
        User userInSession = (User) getWebMockObjectFactory().getMockSession().getAttribute("user");
        assertNotNull("Could not find user in session", user);
        assertSame(user, userInSession);
    }
    
    @Test
    public void testEditWithoutId() {
        User user = new User(new Long(1000), "NameTest1", "NameTest2", new Date());
        addRequestParameter("editButton", "Edit");
        doPost();
        assertNotNull("Could not find error message", getWebMockObjectFactory().getMockRequest().getAttribute("error"));
    }
} 