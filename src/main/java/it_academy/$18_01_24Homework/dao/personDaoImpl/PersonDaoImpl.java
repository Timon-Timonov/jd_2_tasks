package it_academy.$18_01_24Homework.dao.personDaoImpl;

import it_academy.$18_01_24Homework.dao.PersonDao;
import it_academy.$18_01_24Homework.dbConnection.ConnectorToDB;
import it_academy.$18_01_24Homework.dbConnection.mySQLlocalDB.ConnectorToMysqlDB;
import it_academy.$18_01_24Homework.dto.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDaoImpl implements PersonDao {

	private ConnectorToDB connector = ConnectorToMysqlDB.getInstance();

	@Override
	public List<Person> getAllPersonsWithCustomQuery(String query) throws SQLException {

		List<Person> list = new ArrayList<>();
		Connection conn = connector.getConnection();

		try (Statement statement = conn.createStatement();
			 ResultSet resultSet = statement.executeQuery(query)) {

			while (resultSet.next()) {
				list.add(getPersonFromSetRow(resultSet));
			}
		}
		return list;
	}

	@Override
	public List<Person> getAllWithAgeGreaterThen(int ageExclude) throws SQLException {

		List<Person> list = new ArrayList<>();
		Connection conn = connector.getConnection();

		try (PreparedStatement ps = conn.prepareStatement(Queries.GET_WITH_AGE_GREATER_THEN_QUERY)) {

			ps.setInt(1, ageExclude);

			try (ResultSet resultSet = ps.executeQuery()) {

				while (resultSet.next()) {
					list.add(getPersonFromSetRow(resultSet));
				}
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public List<Person> getAll() throws SQLException {

		List<Person> list = new ArrayList<>();
		Connection conn = connector.getConnection();

		try (Statement statement = conn.createStatement();
			 ResultSet resultSet = statement.executeQuery(Queries.GET_ALL_QUERY)) {

			while (resultSet.next()) {
				list.add(getPersonFromSetRow(resultSet));
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return list;
	}


	@Override
	public Person save(Person person) throws SQLException {

		Connection conn = connector.getConnection();

		try (PreparedStatement ps = conn.prepareStatement(
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
				if (resultSet.next()) {
					long id = resultSet.getLong(1);
					person.setId(id);
				} else {
					return null;
				}
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
	public Person get(long id) throws SQLException {

		Connection conn = connector.getConnection();
		Person person = null;

		try (PreparedStatement ps = conn.prepareStatement(Queries.GET_BY_ID_QUERY)) {

			ps.setLong(1, id);

			try (ResultSet resultSet = ps.executeQuery()) {
				if (resultSet.next()) {
					person = getPersonFromSetRow(resultSet);
				}
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return person;
	}

	@Override
	public int update(Person person) throws SQLException {

		int count = 0;
		Connection conn = connector.getConnection();

		try (PreparedStatement ps = conn.prepareStatement(Queries.UPDATE_QUERY)) {

			ps.setInt(1, person.getAge());
			ps.setDouble(2, person.getSalary());
			ps.setString(3, person.getPassport());
			ps.setString(4, person.getAddress());
			ps.setDate(5, person.getDateOfBirth());
			ps.setTime(6, person.getTimeToLunch());
			ps.setString(7, person.getLetter());
			ps.setLong(8, person.getId());

			count = ps.executeUpdate();

		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return count;
	}

	@Override
	public int delete(long id) throws SQLException {

		int count = 0;
		Connection conn = connector.getConnection();

		try (PreparedStatement ps = conn.prepareStatement(Queries.DELETE_BY_ID_QUERY)) {

			ps.setLong(1, id);
			count = ps.executeUpdate();

			if (count == 1) {
				System.out.println("Person with id=" + id + " delete successfully.");
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return count;
	}

	private Person getPersonFromSetRow(ResultSet resultSet) throws SQLException {
		return Person.builder()
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
}
