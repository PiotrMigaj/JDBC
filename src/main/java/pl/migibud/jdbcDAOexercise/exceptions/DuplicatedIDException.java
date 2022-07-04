package pl.migibud.jdbcDAOexercise.exceptions;

public class DuplicatedIDException extends RuntimeException {

	public DuplicatedIDException(Long id) {
		super("Cant add task with id: "+id+" to DB, because it already exists.");
	}
}
