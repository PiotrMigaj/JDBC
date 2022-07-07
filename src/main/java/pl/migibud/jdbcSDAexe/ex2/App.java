package pl.migibud.jdbcSDAexe.ex2;

import pl.migibud.jdbcSDAexe.dbconnection.MySQLDBConnection;
import pl.migibud.jdbcSDAexe.ex2.model.Movie;
import pl.migibud.jdbcSDAexe.ex2.model.MovieDAO;
import pl.migibud.jdbcSDAexe.ex2.model.MovieDAOImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class App {
	public static void main(String[] args) throws SQLException {
		MovieDAO movieDAO = new MovieDAOImpl(MySQLDBConnection.getConnection());
//		movieDAO.deleteTable();
//		movieDAO.createTable();
//		movieDAO.createMovie(new Movie("Harry Potter","family",2002));
//		Optional<Movie> movieById = movieDAO.findMovieById(1);
//		System.out.println(movieById.get());
		List<Movie> movies = movieDAO.findAll();
		movies.forEach(System.out::println);
		MySQLDBConnection.closeConnection();
	}
}
