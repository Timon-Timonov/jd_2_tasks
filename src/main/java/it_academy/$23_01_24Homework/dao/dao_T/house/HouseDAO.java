package it_academy.$23_01_24Homework.dao.dao_T.house;

import it_academy.$23_01_24Homework.dao.DAO;
import it_academy.$23_01_24Homework.dto.House;

import java.sql.SQLException;
import java.util.List;

public interface HouseDAO extends DAO <House> {
	List<House> getAll() throws SQLException;
}
