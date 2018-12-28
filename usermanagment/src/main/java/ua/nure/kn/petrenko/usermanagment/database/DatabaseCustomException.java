package main.java.ua.nure.kn.petrenko.usermanagment.database;

public class DatabaseCustomException extends Exception {
	public DatabaseCustomException() {
	}
	public DatabaseCustomException(String message) {
		super(message);
	}
	public DatabaseCustomException(Throwable cause) {
		super(cause);
	}
	public DatabaseCustomException(String message, Throwable cause) {
		super(message, cause);
	}
	public DatabaseCustomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}