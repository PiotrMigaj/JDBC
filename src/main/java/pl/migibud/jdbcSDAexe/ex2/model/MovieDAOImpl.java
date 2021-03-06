package pl.migibud.jdbcSDAexe.ex2.model;

import pl.migibud.jdbcSDAexe.ex2.exceptions.DatabaseActionException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MovieDAOImpl implements MovieDAO {

	private final Connection connection;

	public MovieDAOImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void createTable() {
		try(Statement statement = connection.createStatement()){
			final String createTableQuery = "CREATE TABLE IF NOT EXISTS MOVIES (id INTEGER AUTO_INCREMENT, " +
					"title VARCHAR(255), " +
					"genre VARCHAR(255), " +
					"yearOfRelease INTEGER, " +
					"PRIMARY KEY (id))";
			statement.execute(createTableQuery);
		}catch (SQLException e){
			throw new DatabaseActionException(e);
		}
	}

	@Override
	public void deleteTable() {
		try(Statement statement = connection.createStatement()){
			final String sql  = "DROP TABLE IF EXISTS MOVIES";
			statement.execute(sql);
		}catch (SQLException e){
			throw new DatabaseActionException(e);
		}
	}

	@Override
	public void createMovie(Movie movie) {
		try(PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO MOVIES(title, genre, yearOfRelease) VALUES (?, ?, ?)")) {
			preparedStatement.setString(1, movie.getTitle());
			preparedStatement.setString(2, movie.getGenre());
			preparedStatement.setInt(3, movie.getYearOfRelease());
			preparedStatement.executeUpdate();
		}catch (SQLException e){
			throw new DatabaseActionException(e);
		}
	}

	@Override
	public void deleteMovie(Integer id) {
		try(PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM MOVIES WHERE id=?")){
			preparedStatement.setInt(1,id);
			preparedStatement.executeUpdate();
		}catch (SQLException e){
			throw new DatabaseActionException(e);
		}
	}

	@Override
	public void updateMoviesTitle(Integer id, String newTitle) {
		try(PreparedStatement preparedStatement = connection.prepareStatement("UPDATE MOVIES SET title=? WHERE id=?")){
			preparedStatement.setString(1,newTitle);
			preparedStatement.setInt(2,id);
			preparedStatement.executeUpdate();
		}catch (SQLException e){
			throw new DatabaseActionException(e);
		}
	}

	@Override
	public Optional<Movie> findMovieById(Integer id) {
		Movie movie = null;
		try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM MOVIES WHERE id=?")){
			preparedStatement.setInt(1,id);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()){
				movie = new Movie(id,rs.getString(2),rs.getString(3),rs.getInt(4));
			}
		}catch (SQLException e){
			throw new DatabaseActionException(e);
		}
		return Optional.ofNullable(movie);
	}

	@Override
	public List<Movie> findAll() {
		List<Movie> movies = new ArrayList<>();
		try(Statement statement = connection.createStatement()){
			String sql = "SELECT * FROM MOVIES";
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()){
				Movie movie = Movie.builder()
						.id(rs.getInt(1))
						.title(rs.getString(2))
						.genre(rs.getString(3))
						.yearOfRelease(rs.getInt(4))
						.build();
				movies.add(movie);
			}


		}catch (SQLException e){
			throw new DatabaseActionException(e);
		}
		return movies;
	}

}
