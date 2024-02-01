package it_academy.lesson23_01_24Homework.dao.dao_T.door;

import it_academy.lesson23_01_24Homework.dao.DAOImpl_T;
import it_academy.lesson23_01_24Homework.dto.Door;

import java.sql.SQLException;
import java.util.List;

public class DoorDAOImpl extends DAOImpl_T implements DoorDAO {

	Class clazz = Door.class;

	@Override
	public Door save(Door door) throws SQLException {
		return (Door) super.save(door, clazz);
	}

	@Override
	public Door get(int id) throws SQLException {
		return (Door) super.get(id, clazz);
	}

	@Override
	public Door update(Door door) throws SQLException {
		return (Door) super.update(door, clazz);
	}

	@Override
	public Door delete(int id) throws SQLException {
		return (Door) super.delete(id, clazz);
	}

	@Override
	public List<Door> getAll() throws SQLException {
		return (List<Door>) (super.getAll(clazz));
	}
}
