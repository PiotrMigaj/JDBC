package pl.migibud.jdbcDAOexercise.task;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface AbstractDAOInterface<T extends Serializable> {

	void create(T t) throws SQLException;
	Optional<T> read(Long id) throws SQLException;
	List<T> readAll() throws SQLException;
	void update(T t) throws SQLException;
	void delete(Long id) throws SQLException;
	List<T> readAllForUser(String username) throws SQLException;

}
