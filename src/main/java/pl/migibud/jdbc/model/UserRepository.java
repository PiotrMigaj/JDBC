package pl.migibud.jdbc.model;

import java.sql.SQLException;
import java.util.List;

public interface UserRepository {
	User getUser(Long id) throws SQLException;
	List<User> getAllUsers() throws SQLException;
}
