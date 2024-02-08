package it_academy.lesson01_02_24Homework.dao.addressDAO;

import it_academy.lesson01_02_24Homework.dao.DaoImpl;
import it_academy.lesson01_02_24Homework.dto.Address;
import it_academy.lesson01_02_24Homework.dto.People;

import java.util.List;
import java.util.Set;

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

		Address addressToRemove = get(id);
		if (addressToRemove != null) {
			Set<People> people = addressToRemove.getPeople();
			if (people == null || people.isEmpty()) {
				super.delete(Address.class, id);
			} else {
				System.out.println("[WARNING!] Removing of row from table address failed!\n" +
						"[WARNING!] You have to resolve all constraints.");
			}
		} else {
			System.out.println("There is no row with such ID in table address.");
		}
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
