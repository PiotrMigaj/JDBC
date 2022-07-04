package pl.migibud.jdbcDAOexercise.user;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class User {

	private Long id;
	private String userName;
	private String password;
}
