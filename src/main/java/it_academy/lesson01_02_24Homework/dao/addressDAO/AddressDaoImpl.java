package it_academy.lesson01_02_24Homework.dao.addressDAO;

import it_academy.lesson01_02_24Homework.dao.DaoImpl;
import it_academy.lesson01_02_24Homework.dto.Address;

import java.util.List;

public class AddressDaoImpl extends DaoImpl implements AddressDAO {

	@Override
	public List<Address> getAll() {

		return super.getAll(Address.class);
	}

	@Override
	public Address get(int id) {

		return (Address) super.get(Address.class, id);
	}

	@Override
	public void delete(int id) {

		super.delete(Address.class, id);
	}

	@Override
	public Address save(Address address) {

		return (Address) super.createRow(address);
	}

	@Override
	public void update(Address address) {

		super.updateRow(address);
	}
}