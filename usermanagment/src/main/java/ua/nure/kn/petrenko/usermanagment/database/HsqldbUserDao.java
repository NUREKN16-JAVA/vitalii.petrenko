package main.java.ua.nure.kn.petrenko.usermanagment.database;

import java.util.Collection;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import main.java.ua.nure.kn.petrenko.usermanagment.agent.User;

import java.sql.Date;



public class HsqldbUserDao implements UserDao {

	private static final String SELECT_ALL_QUERY = "SELECT id, firstname, lastname, dateofbirth FROM users";
	private static final String INSERT_QUERY = "INSERT INTO users(firstname, lastname, dateofbirth) VALUES (?, ?, ?)";
	private ConnectionFactory connectionFactory;
	private static final String FIND_QUERY = "SELECT id, firstname, lastname, dateofbirth FROM users WHERE id=?";
	private static final String UPDATE_QUERY = "UPDATE users SET firstname=?, lastname=?, dateofbirth=? WHERE id=?";
	private static final String FIND_BY_NAMES_QUERY = "SELECT id, firstname, lastname, dateofbirth FROM users WHERE firstname=? AND lastname=?";
	private static final String DELETE_QUERY = "DELETE FROM users WHERE id=?";
	
	public HsqldbUserDao() {
		//super();
	}
	
	public HsqldbUserDao(ConnectionFactory connectionFactory) {
		//super();
		this.connectionFactory = connectionFactory;
	}
	
	public ConnectionFactory getConnectionFactory() {
		return connectionFactory;
	}
	
	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}
	
		
	public User create(User user) throws DatabaseCustomException {
		try {
			
			Connection connection = connectionFactory.createConnection();
			PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setDate(3, new Date(user.getDateOfBirthd().getTime()) );
			int count = statement.executeUpdate();
			
			if(count != 1){
				throw new DatabaseCustomException("Number of the inserted rows: "+ count);	
			}
			
			CallableStatement callableStatement = connection.prepareCall("call IDENTITY()");
			ResultSet keys = callableStatement.executeQuery();
			
			if(keys.next()){
				user.setId(new Long(keys.getLong(1)));
			}
			
			keys.close();
			callableStatement.close();
			statement.close();
			connection.close();
			return user;
			
		} catch(DatabaseCustomException e){
			throw e;
		}catch (SQLException e) {
			throw new DatabaseCustomException(e);
		}
	
	}
	
	public User update(User user) throws DatabaseCustomException {
	       try {
	            Connection connection = connectionFactory.createConnection();
	            PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);
	            
	            statement.setString(1, user.getFirstName());
	            statement.setString(2, user.getLastName());
	            statement.setDate(3, new Date(user.getDateOfBirthd().getTime()));
	            statement.setLong(4, user.getId().longValue());
	            
	            int n = statement.executeUpdate();
	            if (n != 1) {
	                throw new DatabaseCustomException("Number of the updated rows: " + n);
	            }
	            statement.close();
	            connection.close();
	            
	            return user;
	            
	        } catch (DatabaseCustomException e) {
	            throw e;
	        } catch (SQLException e) {
	            throw new DatabaseCustomException(e);
	        }
	}
	
	public User delete(User user) throws DatabaseCustomException {
		try {
            Connection connection = connectionFactory.createConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);
            
            statement.setLong(1, user.getId().longValue());
            
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new DatabaseCustomException("Number of the deleted rows: " + count);
            }
            statement.close();
            connection.close();
            
            return user;
        } catch (DatabaseCustomException e) {
            throw e;
        } catch (SQLException e) {
            throw new DatabaseCustomException(e);
        }
	}
	
	public User find(Long id) throws DatabaseCustomException {
		   	User result = null;
		   	
	        try {
	            Connection connection = connectionFactory.createConnection();
	            PreparedStatement statement = connection.prepareStatement(FIND_QUERY);
	            statement.setLong(1, id.longValue());
	            ResultSet resultSet = statement.executeQuery();
	            if (!resultSet.next()) {
	                throw new DatabaseCustomException("Could not find the user with id=" + id);
	            }
	            result = new User();
	            result.setId(new Long(resultSet.getLong(1)));
	            result.setFirstName(resultSet.getString(2));
	            result.setLastName(resultSet.getString(3));
	            result.setDateOfBirthd(resultSet.getDate(4));
	            resultSet.close();
	            statement.close();
	            connection.close();
	        } catch (DatabaseCustomException e) {
	            throw e;
	        } catch (SQLException e) {
	            throw new DatabaseCustomException(e);
	        }
	        return result;
	}
	
	public Collection find(String firstName, String lastName) throws DatabaseCustomException {
		Collection<User> result = new LinkedList<User>();
		try {
			Connection connection = connectionFactory.createConnection();
			PreparedStatement statement = connection.prepareStatement(FIND_BY_NAMES_QUERY);
			statement.setString(1, firstName);
			statement.setString(2, lastName);
			ResultSet allUsers = statement.executeQuery();
			while (allUsers.next()) {
				User user = new User();
				user.setId(allUsers.getLong(1));
				user.setFirstName(allUsers.getString(2));
				user.setLastName(allUsers.getString(3));
				user.setDateOfBirthd(allUsers.getDate(4).toLocalDate());
				result.add(user);
			}
			allUsers.close();
			statement.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	
	public Collection findAll() throws DatabaseCustomException {
	    Collection result = new  LinkedList();
	    try {
	    Connection connection = connectionFactory.createConnection();
	   
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY);
			while(resultSet.next()){
				User user = new User();
				user.setId(new Long(resultSet.getLong(1)));
				user.setFirstName(resultSet.getString(2));
				user.setLastName(resultSet.getString(3));
				user.setDateOfBirthd(resultSet.getDate(4));
				result.add(user);
			}
			resultSet.close();
	        statement.close();
	        connection.close();
		}
	     catch(DatabaseCustomException e){
			throw e;
		}
	    catch (SQLException e) {
	    	 throw new DatabaseCustomException(e);
		}
		return result;
	}
		 
}
