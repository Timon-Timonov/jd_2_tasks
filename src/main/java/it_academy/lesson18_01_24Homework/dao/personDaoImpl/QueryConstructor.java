package it_academy.lesson18_01_24Homework.dao.personDaoImpl;

public class QueryConstructor {

	public static String constructSelectQueryWithConditionsAndOrder
			(int[] columnNumber,
			 String[] operator,
			 String[] value,
			 int[] columnNumberToOrderBy,
			 boolean[] isInvertOrder) {

		StringBuilder str = new StringBuilder();
		str.append(Queries.GET_ALL_WITH_WHERE_QUERY);

		for (int i = 0; i < columnNumber.length; i++) {

			str.append(Queries.columns[columnNumber[i] - 1])
					.append(operator[i])
					.append(value[i]);
			if (i != columnNumber.length - 1) {
				str.append(Queries.POINT);
			}
		}

		str.append(Queries.ORDER_BY);

		for (int i = 0; i < columnNumberToOrderBy.length; i++) {

			str.append(Queries.columns[columnNumberToOrderBy[i] - 1])
					.append(isInvertOrder[i] ?
							Queries.INVERT_ORDER
							: Queries.SPACE);
			if (i != columnNumberToOrderBy.length - 1) {
				str.append(Queries.POINT);
			}
		}
		return str.toString();
	}
}
