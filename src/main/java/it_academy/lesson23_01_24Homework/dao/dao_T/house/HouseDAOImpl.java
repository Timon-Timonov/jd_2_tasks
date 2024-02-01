package it_academy.lesson23_01_24Homework.dao.dao_T.house;


import it_academy.lesson23_01_24Homework.dao.DAOImpl_T;
import it_academy.lesson23_01_24Homework.dto.House;

import java.sql.SQLException;
import java.util.List;

public class HouseDAOImpl extends DAOImpl_T implements HouseDAO {

	Class clazz = House.class;

	@Override
	public House save(House house) throws SQLException {
		return (House) super.save(house, clazz);
	}

	@Override
	public House get(int id) throws SQLException {
		return (House) super.get(id, clazz);
	}

	@Override
	public House update(House house) throws SQLException {
		return (House) super.update(house, clazz);
	}

	@Override
	public House delete(int id) throws SQLException {
		return (House) super.delete(id, clazz);
	}

	@Override
	public List<House> getAll() throws SQLException {
		return (List<House>) (super.getAll(clazz));
	}
}
