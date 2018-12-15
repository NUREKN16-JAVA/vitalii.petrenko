package test.java.ua.nure.kn.petrenko_vitaliy.usermanagment.gui;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import main.java.ua.nure.kn.petrenko_vitaliy.usermanagment.User;
import main.java.ua.nure.kn.petrenko_vitaliy.usermanagment.database.ConnectionFactory;
import main.java.ua.nure.kn.petrenko_vitaliy.usermanagment.database.DatabaseCustomException;
import main.java.ua.nure.kn.petrenko_vitaliy.usermanagment.database.UserDao;

public class MockUserDao implements UserDao {
	private long id = 0;
	private Map users = new HashMap();

	@Override
	public User create(User user) throws DatabaseCustomException {
		Long currentId = new Long(++id);

		user.setId(currentId);
		users.put(currentId, user);
		return user;
	}

	@Override
	public User update(User user) throws DatabaseCustomException {
		Long currentId = user.getId();
		users.remove(currentId);
		users.put(currentId, user);
		return user;
	}

	@Override
	public User delete(User user) throws DatabaseCustomException {
		Long currentId = user.getId();
		users.remove(currentId);
		return user;
	}

	@Override
	public User find(Long id) throws DatabaseCustomException {
		return (User) users.get(id);
	}

	@Override
	public Collection findAll() throws DatabaseCustomException {
		return users.values();
	}

	
}