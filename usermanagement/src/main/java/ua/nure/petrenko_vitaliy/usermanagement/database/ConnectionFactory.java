package main.java.ua.nure.kn.petrenko_vitaliy.usermanagment.database;

import java.sql.Connection;

public interface ConnectionFactory {
	Connection createConnection() throws DatabaseCustomException;
}
