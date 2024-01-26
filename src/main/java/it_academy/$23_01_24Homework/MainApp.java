package it_academy.$23_01_24Homework;

import it_academy.$23_01_24Homework.dao.dao_T.door.DoorDAO;
import it_academy.$23_01_24Homework.dao.dao_T.door.DoorDAOImpl;
import it_academy.$23_01_24Homework.dao.dao_T.house.HouseDAO;
import it_academy.$23_01_24Homework.dao.dao_T.house.HouseDAOImpl;
import it_academy.$23_01_24Homework.dbConnection.ConnectorToDB;
import it_academy.$23_01_24Homework.dbConnection.mySQLlocalDB.ConnectorToMysqlDB;
import it_academy.$23_01_24Homework.dto.Door;
import it_academy.$23_01_24Homework.dto.House;

import java.sql.SQLException;
import java.util.List;

public class MainApp {

	public static final int ID_THREE = 3;
	public static final String IT_IS_PROBLEM_WITH_ACCESS_TO_DB = "It is problem with access to DB.";
	public static final String THERE_IS_NO_ROW_IN_DB_WITH_SUCH_ID = "There is no row in DB with such id.";
	public static final String UPDATED_SUCCESSFULLY = " updated successfully.";
	public static final String DELETED_SUCCESSFULLY = " Deleted successfully.";
	public static final String SAVED_SUCCESSFULLY = " saved successfully.";

	public static void main(String[] args) {
		ConnectorToDB connector = ConnectorToMysqlDB.getInstance();

		HouseDAO houseDAO = new HouseDAOImpl();
		DoorDAO doorDAO = new DoorDAOImpl();

		System.out.println();
		System.out.println("TESTING OF METHODS WITH HOUSES!");
		System.out.println("!!!!!!!!!!!!!!!!!!!");
		System.out.println();


		System.out.println("Using of method getAll() with Doors:");
		try {
			List<Door> list = doorDAO.getAll();
			if (list != null) {
				list.forEach(System.out::println);
			} else {
				throw new SQLException();
			}
		} catch (SQLException throwables) {
			System.out.println(IT_IS_PROBLEM_WITH_ACCESS_TO_DB);
		}
		System.out.println("___________________________________________");
		System.out.println();


		System.out.println("Using of method get(id=" + ID_THREE + ") with Doors:");
		getDoorWithId(doorDAO,ID_THREE);
		System.out.println("___________________________________________");
		System.out.println();


		System.out.println("Using of method get(id=" + (ID_THREE + 1) + ") with Doors:");
		getDoorWithId(doorDAO,(ID_THREE+1));
		System.out.println("___________________________________________");
		System.out.println();


		Door door = Door.builder()
				.id(ID_THREE+1)
				.size(999.0)
				.type("Very_big")
				.build();

		System.out.println("Using of method update(door) with Doors:");
		try {
			Door door1 = doorDAO.update(door);
			if (door1!= null) {
				System.out.println(door1 + UPDATED_SUCCESSFULLY);
			} else {
				System.out.println(THERE_IS_NO_ROW_IN_DB_WITH_SUCH_ID + door);
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		System.out.println("___________________________________________");
		System.out.println();


		System.out.println("Using of method delete(id=" + (ID_THREE + 1) + ") with Doors:");
		try {
			Door door1 = doorDAO.delete((ID_THREE + 1));
			if (door1 != null) {
				System.out.println(door1 + DELETED_SUCCESSFULLY);
			} else {
				System.out.println(THERE_IS_NO_ROW_IN_DB_WITH_SUCH_ID);
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		System.out.println("___________________________________________");
		System.out.println();


		System.out.println("Using of method save(door) with Doors:");
		Door door2 = Door.builder()
				.size(555d)
				.type("wood")
				.build();
		try {
			Door door3 = doorDAO.save(door2);
			if (door3 != null) {
				System.out.println(door3 + SAVED_SUCCESSFULLY);
			} else {
				throw new SQLException();
			}
		} catch (SQLException throwables) {
			System.out.println(IT_IS_PROBLEM_WITH_ACCESS_TO_DB);
		}
		System.out.println("___________________________________________");
		System.out.println();


		door = Door.builder()
				.id(ID_THREE+1)
				.size(999.0)
				.type("Very_big")
				.build();

		System.out.println("Using of method update(door) with Doors:");
		try {
			Door door1 = doorDAO.update(door);
			if (door1!= null) {
				System.out.println(door1 + UPDATED_SUCCESSFULLY);
			} else {
				System.out.println(THERE_IS_NO_ROW_IN_DB_WITH_SUCH_ID + door);
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		System.out.println("___________________________________________");
		System.out.println();


		System.out.println("Using of method delete(id=" + (ID_THREE + 1) + ") with Doors:");
		try {
			Door door1 = doorDAO.delete((ID_THREE + 1));
			if (door1 != null) {
				System.out.println(door1 + DELETED_SUCCESSFULLY);
			} else {
				System.out.println(THERE_IS_NO_ROW_IN_DB_WITH_SUCH_ID);
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		System.out.println("___________________________________________");
		System.out.println();



		System.out.println();
		System.out.println("!!!!!!!!!!!!!!!!!!!");
		System.out.println("TESTING OF METHODS WITH HOUSES!");
		System.out.println("!!!!!!!!!!!!!!!!!!!");
		System.out.println();

		System.out.println("Using of method getAll() with Houses:");
		try {
			List<House> list = houseDAO.getAll();
			if (list != null) {
				list.forEach(System.out::println);
			} else {
				throw new SQLException();
			}
		} catch (SQLException throwables) {
			System.out.println(IT_IS_PROBLEM_WITH_ACCESS_TO_DB);
		}
		System.out.println("___________________________________________");
		System.out.println();


		System.out.println("Using of method get(id=" + ID_THREE + ") with Houses:");
		getHouseWithId(houseDAO,ID_THREE);
		System.out.println("___________________________________________");
		System.out.println();


		System.out.println("Using of method get(id=" + (ID_THREE + 1) + ") with Houses:");
		getHouseWithId(houseDAO,(ID_THREE+1));
		System.out.println("___________________________________________");
		System.out.println();


		House house = House.builder()
				.id(ID_THREE)
				.size(999.0)
				.color("Yellow")
				.build();

		System.out.println("Using of method update(house) with Houses:");
		try {
			House house1 = houseDAO.update(house);
			if (house1!= null) {
				System.out.println(house1 + UPDATED_SUCCESSFULLY);
			} else {
				System.out.println(THERE_IS_NO_ROW_IN_DB_WITH_SUCH_ID + door);
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		System.out.println("___________________________________________");
		System.out.println();


		System.out.println("Using of method delete(id=" + (ID_THREE + 1) + ") with Houses:");
		try {
			House house1 = houseDAO.delete((ID_THREE + 1));
			if (house1 != null) {
				System.out.println(house1 + DELETED_SUCCESSFULLY);
			} else {
				System.out.println(THERE_IS_NO_ROW_IN_DB_WITH_SUCH_ID);
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		System.out.println("___________________________________________");
		System.out.println();


		System.out.println("Using of method save(door) with Houses:");
		House house2 = House.builder()
				.size(555d)
				.color("Pink")
				.build();
		try {
			House house3 = houseDAO.save(house2);
			if (house3 != null) {
				System.out.println(house3 + SAVED_SUCCESSFULLY);
			} else {
				throw new SQLException();
			}
		} catch (SQLException throwables) {
			System.out.println(IT_IS_PROBLEM_WITH_ACCESS_TO_DB);
		}
		System.out.println("___________________________________________");
		System.out.println();


		try {
			connector.getConnection().close();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}


	private static void getHouseWithId(HouseDAO houseDAO, int id) {
		try {
			House house = houseDAO.get(id);
			if (house != null) {
				System.out.println(house);
			} else {
				System.out.println(THERE_IS_NO_ROW_IN_DB_WITH_SUCH_ID);
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	private static void getDoorWithId(DoorDAO doorDAO,int id) {
		try {
			Door door = doorDAO.get(id);
			if (door != null) {
				System.out.println(door);
			} else {
				System.out.println(THERE_IS_NO_ROW_IN_DB_WITH_SUCH_ID);
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}
}
