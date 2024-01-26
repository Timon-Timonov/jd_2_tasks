package it_academy.$23_01_24Homework.dao.dao_T.door;

import it_academy.$23_01_24Homework.dao.DAO;
import it_academy.$23_01_24Homework.dto.Door;

import java.sql.SQLException;
import java.util.List;

public interface DoorDAO extends DAO <Door>{
	List<Door> getAll() throws SQLException;
}
