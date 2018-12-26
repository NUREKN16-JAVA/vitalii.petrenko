package main.java.ua.nure.kn.vitalii.petrenko.usermanagment.database;

import java.util.Collection;
import main.java.ua.nure.kn.vitalii.petrenko.usermanagment.*;

public interface UserDao {
	User create(User user) throws DatabaseCustomException;
	
	User update(User user) throws DatabaseCustomException;
	
	User delete(User user) throws DatabaseCustomException;
	
	User find(Long id) throws DatabaseCustomException;
	
	Collection findAll() throws DatabaseCustomException;
	
	void setConnectionFactory(ConnectionFactory connectionFactory);
}
