package test.java.ua.nure.kn.petrenko.usermanagment.web;

import java.util.Properties;
import com.mockobjects.dynamic.Mock;
import com.mockrunner.servlet.BasicServletTestCaseAdapter;

import main.java.ua.nure.kn.petrenko.usermanagment.database.DaoFactory;
import test.java.ua.nure.kn.petrenko.usermanagment.database.MockDaoFactory;

// эххххххххххххххх
public abstract class MockServletTestCase extends BasicServletTestCaseAdapter {
	private Mock mockUserDao;
	   
    protected void setUp() throws Exception {
        super.setUp();
        Properties properties = new Properties();
        properties.setProperty("dao.factory", MockDaoFactory.class.getName());
        DaoFactory.init(properties);
        setMockUserDao(((MockDaoFactory) DaoFactory.getInstance()).getMockUserDao());
    }
    protected void tearDown() throws Exception {
        getMockUserDao().verify();
        super.tearDown();
    }
 
    public Mock getMockUserDao() {
        return mockUserDao;
    }
  
    public void setMockUserDao(Mock mockUserDao) {
        this.mockUserDao = mockUserDao;
    }
}