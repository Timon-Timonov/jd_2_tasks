package it_academy.$18_01_24Homework.dao.personDaoImpl;

public class QueryCOnstructor {
	public static String constructQuery(int columnNumber, String operator, String value,
										int columnNumberToOrderBy, boolean isInvertOrder) {
		StringBuilder str = new StringBuilder();
		str.append(Queries.GET_ALL_WITH_WHERE_QUERY)
				.append(Queries.columns[columnNumber - 1])
				.append(operator)
				.append(value)
				.append(Queries.ORDER_BY)
				.append(Queries.columns[columnNumberToOrderBy - 1])
				.append(isInvertOrder ?
						Queries.INVERT_ORDER
						: "");

		return str.toString();
	}
}
