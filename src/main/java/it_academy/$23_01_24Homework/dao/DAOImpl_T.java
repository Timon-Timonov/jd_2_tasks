package it_academy.$23_01_24Homework.dao;

import it_academy.$23_01_24Homework.annotations.Column;
import it_academy.$23_01_24Homework.annotations.Identifier;
import it_academy.$23_01_24Homework.annotations.Table;
import it_academy.$23_01_24Homework.dao.utils.Queries;
import it_academy.$23_01_24Homework.dbConnection.ConnectorToDB;
import it_academy.$23_01_24Homework.dbConnection.mySQLlocalDB.ConnectorToMysqlDB;
import it_academy.$23_01_24Homework.exceptions.ClassIsNotDTOException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAOImpl_T<T> {

	private final ConnectorToDB connector = ConnectorToMysqlDB.getInstance();

	private String table_name;
	private int column_count;
	private int identifier_index;
	private String[] column_names;
	private Field[] fields;
	private T thiz;


	public T get(int id, Class<?> clazz) throws SQLException {

		fillFields(clazz);
		try (Statement st = connector.getConnection().createStatement()) {
			String query = Queries.createGetByIdQuery(id, table_name, column_names[identifier_index]);
			ResultSet res = st.executeQuery(query);
			while (res.next()) {
				fillDTO(clazz, res);
			}
			res.close();
		}
		return thiz;
	}

	public List<T> getAll(Class<?> clazz) throws SQLException {

		List<T> list = new ArrayList<>();
		fillFields(clazz);
		String query = Queries.createGetAllQuery(table_name);
		try (Statement st = connector.getConnection().createStatement()) {
			ResultSet res = st.executeQuery(query);
			while (res.next()) {
				fillDTO(clazz, res);
				list.add(thiz);
			}
			res.close();
		}
		return list;
	}

	public T delete(int id, Class<?> clazz) throws SQLException {

		if (get(id, clazz) == null) {
			return null;
		}
		String query = Queries.getDeleteByIdQuery(id, table_name, column_names[identifier_index]);
		try (Statement st = connector.getConnection().createStatement()) {
			if (1 != st.executeUpdate(query)) {
				return null;
			}
		}
		return thiz;
	}

	public T update(T t, Class<?> clazz) throws SQLException {

		fillFields(clazz);
		String query = Queries.createUpdateQuery(table_name, identifier_index, fields, t);
		try (Statement st = connector.getConnection().createStatement()) {
			if (1 == st.executeUpdate(query)) {
				return get((Integer) fields[identifier_index].get(t), clazz);
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public T save(T t, Class<?> clazz) throws SQLException {

		fillFields(clazz);
		String query = Queries.createSaveQuery(table_name, identifier_index, fields, t);
		try (Statement st = connector.getConnection().createStatement()) {
			if (1 == st.executeUpdate(query, Statement.RETURN_GENERATED_KEYS)) {
				ResultSet res = st.getGeneratedKeys();
				int id = 0;
				while (res.next()) {
					id = res.getInt(1);
				}
				thiz = id != 0 ? get(id, clazz) : null;
				res.close();
			} else {
				return null;
			}
		}
		return thiz;
	}


	private void fillFields(Class<?> clazz) {

		thiz = null;
		if (clazz.isAnnotationPresent(Table.class)) {
			table_name = clazz.getAnnotation(Table.class).name();
		} else {
			throw new ClassIsNotDTOException();
		}
		fields = clazz.getDeclaredFields();
		column_count = fields.length;
		column_names = new String[column_count];

		for (int i = 0; i < column_count; i++) {
			fields[i].setAccessible(true);
			if (fields[i].isAnnotationPresent(Column.class)) {
				column_names[i] = fields[i].getAnnotation(Column.class).name();
			} else if (fields[i].isAnnotationPresent(Identifier.class)) {
				identifier_index = i;
				column_names[i] = fields[i].getAnnotation(Identifier.class).name();
			} else {
				throw new ClassIsNotDTOException();
			}
		}
	}

	private void fillDTO(Class<?> clazz, ResultSet res) throws SQLException {
		try {
			Constructor constructor = clazz.getDeclaredConstructor();
			constructor.setAccessible(true);
			thiz = (T) constructor.newInstance();

			for (int i = 0; i < column_count; i++) {
				Field f = fields[i];
				f.set(thiz,
						res.getObject(f.getName(), f.getType()));
			}
		} catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
