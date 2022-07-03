package pl.migibud.jdbc;

import pl.migibud.jdbc.model.User;
import pl.migibud.jdbc.model.UserRepository;
import pl.migibud.jdbc.model.UserRepositoryImpl;

import java.sql.SQLException;
import java.util.List;

public class Main7Users {
	public static void main(String[] args) throws SQLException {

		UserRepository userRepository = new UserRepositoryImpl();
		List<User> allUsers = userRepository.getAllUsers();
		System.out.println(allUsers);


	}
}
