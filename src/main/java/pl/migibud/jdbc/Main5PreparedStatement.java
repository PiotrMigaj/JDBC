package pl.migibud.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.IntStream;

import static pl.migibud.jdbc.Configuration.*;

public class Main5PreparedStatement {

	public static void main(String[] args) throws SQLException {

		Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user(username,password) VALUES(?,?)");
		prepareStatementExecute(preparedStatement,List.of("Jan","Ala","Mikołaj","Ola"),List.of("password1","password2","password3","password4"));
		preparedStatement.close();
		connection.close();
	}

	public static void prepareStatementExecute(PreparedStatement preparedStatement, List<String> userNames,List<String> userPasswords){

		IntStream.range(0,userNames.size()).forEach(i->{
			try {
				preparedStatement.setString(1,userNames.get(i));
				preparedStatement.setString(2,userPasswords.get(i));
				preparedStatement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		});

	}

}
