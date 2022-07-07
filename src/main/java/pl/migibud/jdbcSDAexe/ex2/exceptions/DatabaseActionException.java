package pl.migibud.jdbcSDAexe.ex2.exceptions;

public class DatabaseActionException extends RuntimeException{
	public DatabaseActionException(final Throwable cause) {
		super(cause);
	}
}
