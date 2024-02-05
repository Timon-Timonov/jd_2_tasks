package it_academy.lesson23_01_24Homework.dao.utils;

import it_academy.lesson23_01_24Homework.dao.Const;

import java.lang.reflect.Field;

public class Queries {

	private Queries() {
	}

	public static String createGetByIdQuery(int id, String table_name, String identifier_name) {
		return Const.SELECT_ALL_FROM +
				//.append(Const.DATABASE_NAME)
				table_name +
				Const.WHERE +
				identifier_name +
				Const.EQUALS +
				id +
				Const.END_LINE;
	}

	public static String createGetAllQuery(String table_name) {
		return Const.SELECT_ALL_FROM +
				//.append(Const.DATABASE_NAME)
				table_name +
				Const.END_LINE;

	}

	public static String getDeleteByIdQuery(int id, String table_name, String identifier_name) {
		return Const.DELETE_FROM +
				//.append(Const.DATABASE_NAME)
				table_name +
				Const.WHERE +
				identifier_name +
				Const.EQUALS +
				id +
				Const.END_LINE;
	}

	public static String createUpdateQuery(String table_name, int identifier_index, Field[] fields, Object t) {

		int column_count = fields.length;
		StringBuilder str = new StringBuilder();

		str.append(Const.UPDATE)
				//.append(Const.DATABASE_NAME)
				.append(table_name)
				.append(Const.SET);

		boolean isIdentifierAlreadyUse = false;
		for (int i = 0; i < column_count; i++) {
			fields[i].setAccessible(true);
			if (i != identifier_index) {
				try {
					str.append(fields[i].getName())
							.append(Const.EQUALS)
							.append(Const.QUOTE)
							.append(fields[i].get(t).toString())
							.append(Const.QUOTE);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				if (useDelimiter(isIdentifierAlreadyUse, i, column_count)) {
					str.append(Const.BETWEEN_COLUMNS);
				}
			} else {
				isIdentifierAlreadyUse = true;
			}
		}

		try {
			str.append(Const.WHERE)
					.append(fields[identifier_index].getName())
					.append(Const.EQUALS)
					.append(Const.QUOTE)
					.append(fields[identifier_index].get(t).toString())
					.append(Const.QUOTE)
					.append(Const.END_LINE);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return str.toString();
	}

	public static String createSaveQuery(String table_name, int identifier_index, Field[] fields,Object t) {

		int column_count = fields.length;
		StringBuilder str = new StringBuilder();
		str.append(Const.INSERT_INTO)
				//.append(Const.DATABASE_NAME)
				.append(table_name)
				.append(Const.BRACKET_OP);

		boolean isIdentifierAlreadyUse = false;
		for (int i = 0; i < column_count; i++) {
			if (i != identifier_index) {
				str.append(fields[i].getName());
				if (useDelimiter(isIdentifierAlreadyUse, i, column_count)) {
					str.append(Const.BETWEEN_COLUMNS);
				}
			} else {
				isIdentifierAlreadyUse = true;
			}
		}
		str.append(Const.VALUE);

		isIdentifierAlreadyUse = false;
		for (int i = 0; i < column_count; i++) {
			fields[i].setAccessible(true);
			try {
				if (i != identifier_index) {
					str.append(fields[i].get(t).toString());
					if (useDelimiter(isIdentifierAlreadyUse, i, column_count)) {
						str.append(Const.BETWEEN_VALUES);
					}
				} else {
					isIdentifierAlreadyUse = true;
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		str.append(Const.BRACKET_CL)
				.append(Const.END_LINE);
		return str.toString();
	}


	private static boolean useDelimiter(boolean isIdentifierAlreadyUse, int i, int column_count) {
		return ((i != (column_count - 1)) || (!isIdentifierAlreadyUse))
				&& ((i != (column_count - 2)) || (isIdentifierAlreadyUse));
	}
}
