package it_academy.lesson01_02_24Homework.dao.addressDAO;

import it_academy.lesson01_02_24Homework.dao.DAO;
import it_academy.lesson01_02_24Homework.dto.Address;

import java.util.List;

public interface AddressDAO extends DAO<Address> {
	List<Address> getAll();
}
