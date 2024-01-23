package it_academy.$18_01_24Homework.dao.personDaoImpl;

import it_academy.$18_01_24Homework.dao.PersonDao;
import it_academy.$18_01_24Homework.dbConnection.Connector;
import it_academy.$18_01_24Homework.dto.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDaoImpl implements PersonDao {
	@Override
	public List<Person> getAllPersonsWithCustomQuery(String query) throws SQLException {
		List<Person> list = new ArrayList<>();

		try (Connection conn = Connector.getConnection();
			 Statement statement = conn.createStatement()) {

			try (ResultSet resultSet = statement.executeQuery(query)) {

				while (resultSet.next()) {
					list.add(Person.builder()
							.id(resultSet.getLong(1))
							.age(resultSet.getInt(2))
							.salary(resultSet.getDouble(3))
							.passport(resultSet.getString(4))
							.address(resultSet.getString(5))
							.dateOfBirth(resultSet.getDate(6))
							.dateTimeCreate(resultSet.getTimestamp(7))
							.timeToLunch(resultSet.getTime(8))
							.letter(resultSet.getString(9))
							.build());
				}
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public List<Person> getAllWithAgeGreaterThen(int age) throws SQLException {
		List<Person> list = new ArrayList<>();

		try (Connection conn = Connector.getConnection();
			 PreparedStatement ps = conn.prepareStatement(Queries.GET_WITH_AGE_GREATER_THEN_QUERY)) {

			ps.setInt(1, age);

			try (ResultSet resultSet = ps.executeQuery()) {

				while (resultSet.next()) {
					list.add(Person.builder()
							.id(resultSet.getLong(1))
							.age(resultSet.getInt(2))
							.salary(resultSet.getDouble(3))
							.passport(resultSet.getString(4))
							.address(resultSet.getString(5))
							.dateOfBirth(resultSet.getDate(6))
							.dateTimeCreate(resultSet.getTimestamp(7))
							.timeToLunch(resultSet.getTime(8))
							.letter(resultSet.getString(9))
							.build());
				}
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public List<Person> getAll() {
		List<Person> list = new ArrayList<>();

		try (Connection conn = Connector.getConnection();
			 Statement statement = conn.createStatement();
			 ResultSet resultSet = statement.executeQuery(Queries.GET_ALL_QUERY)) {

			while (resultSet.next()) {
				list.add(Person.builder()
						.id(resultSet.getLong(1))
						.age(resultSet.getInt(2))
						.salary(resultSet.getDouble(3))
						.passport(resultSet.getString(4))
						.address(resultSet.getString(5))
						.dateOfBirth(resultSet.getDate(6))
						.dateTimeCreate(resultSet.getTimestamp(7))
						.timeToLunch(resultSet.getTime(8))
						.letter(resultSet.getString(9))
						.build());
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

		return list;
	}

	@Override
	public Person save(Person person) {

		try (Connection conn = Connector.getConnection();
			 PreparedStatement ps = conn.prepareStatement(
					 Queries.ADD_QUERY, PreparedStatement.RETURN_GENERATED_KEYS)) {

			ps.setString(1, String.valueOf(person.getAge()));
			ps.setString(2, String.valueOf(person.getSalary()));
			ps.setString(3, person.getPassport());
			ps.setString(4, person.getAddress());
			ps.setString(5, String.valueOf(person.getDateOfBirth()));
			ps.setString(6, String.valueOf(person.getTimeToLunch()));
			ps.setString(7, person.getLetter());

			ps.executeUpdate();

			try (ResultSet resultSet = ps.getGeneratedKeys()) {
				resultSet.next();
				long id = resultSet.getLong(1);
				person = get(id);
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}

		} catch (SQLException throwables) {
			throwables.printStackTrace();
			person = null;
		}
		return person;
	}

	@Override
	public Person get(long id) {

		Person person;

		try (Connection conn = Connector.getConnection();
			 PreparedStatement ps = conn.prepareStatement(Queries.GET_BY_ID_QUERY)) {

			ps.setLong(1, id);

			try (ResultSet resultSet = ps.executeQuery()) {
				resultSet.next();
				person = Person.builder()
						.id(resultSet.getLong(1))
						.age(resultSet.getInt(2))
						.salary(resultSet.getDouble(3))
						.passport(resultSet.getString(4))
						.address(resultSet.getString(5))
						.dateOfBirth(resultSet.getDate(6))
						.dateTimeCreate(resultSet.getTimestamp(7))
						.timeToLunch(resultSet.getTime(8))
						.letter(resultSet.getString(9))
						.build();
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
			person = null;
		}
		return person;
	}

	@Override
	public void update(Person person) {

		try (Connection conn = Connector.getConnection();
			 PreparedStatement ps = conn.prepareStatement(Queries.UPDATE_QUERY)) {

			ps.setInt(1, person.getAge());
			ps.setDouble(2, person.getSalary());
			ps.setString(3, person.getPassport());
			ps.setString(4, person.getAddress());
			ps.setDate(5, person.getDateOfBirth());
			ps.setTime(6, person.getTimeToLunch());
			ps.setString(7, person.getLetter());
			ps.setLong(8, person.getId());

			ps.executeUpdate();

		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	@Override
	public int delete(long id) {
		int count = 0;
		try (Connection conn = Connector.getConnection();
			 PreparedStatement ps = conn.prepareStatement(Queries.DELETE_BY_ID_QUERY)) {

			ps.setLong(1, id);
			count = ps.executeUpdate();
			if (count == 1) {
				System.out.println("Deleted successfully.");
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return count;
	}
}
