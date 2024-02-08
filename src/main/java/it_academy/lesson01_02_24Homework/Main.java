package it_academy.lesson01_02_24Homework;

import it_academy.lesson01_02_24Homework.Utils.HibernateUtil;
import it_academy.lesson01_02_24Homework.dao.addressDAO.AddressDAO;
import it_academy.lesson01_02_24Homework.dao.addressDAO.AddressDaoImpl;
import it_academy.lesson01_02_24Homework.dao.peopleDAO.PeopleDAO;
import it_academy.lesson01_02_24Homework.dao.peopleDAO.PeopleDaoImpl;
import it_academy.lesson01_02_24Homework.dto.Address;
import it_academy.lesson01_02_24Homework.dto.People;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;

/*С основной программы:
1 при помощи DAO создать 5 адресов и 5 человек.
2 при помощи DAO увеличить в 2-ух последних адресов дом на 1 и
у двух последних людей возраст на 2.
3 при помощи DAO удалить первый адрес и первого человека*/
public class Main {

	public static final int ANY_AGE = 35;
	public static final int ANY_HOUSE_NUMBER = 5;
	public static final int NAMES_INCREMENT = 1;
	public static final int COUNT_OF_EACH_ENTITIES = 5;
	public static final int HOUSE_INCREMENT = 1;
	public static final int AGE_INCREMENT = 2;
	public static final int COUNT_OF_LAST_ROWS = 2;
	public static final int ID_TO_DELETE = 1;
	public static final int INDEX = 0;

	public static void main(String[] args) {

		List<People> people = new ArrayList<>();
		List<Address> addresses = new ArrayList<>();
		PeopleDAO peopleDAO = new PeopleDaoImpl();
		AddressDAO addressDAO = new AddressDaoImpl();

		//creating of entities(1)
		IntStream.range(0, COUNT_OF_EACH_ENTITIES).forEach(i -> {
			Address adr = Address.builder()
					.house(i + ANY_HOUSE_NUMBER)
					.street("UniqueStreet_" + (i + NAMES_INCREMENT))
					.build();
			People pep = People.builder()
					.age(i + ANY_AGE)
					.name("UniqueName_" + (i + NAMES_INCREMENT))
					.surname("UniqueSurName_" + (i + NAMES_INCREMENT))
					.addresses(new HashSet<>())
					.build();
			pep.getAddresses().add(adr);
			addresses.add(adr);
			people.add(pep);
		});

		//saving inti DB(1)
		addresses.forEach(addressDAO::save);
		people.forEach(peopleDAO::save);

		//changing any parameters(2)
		people.get(INDEX).getAddresses().addAll(addresses);
		peopleDAO.update(people.get(INDEX));

		IntStream.range(0, COUNT_OF_LAST_ROWS).forEach(i -> {
			People people1 = people.get(people.size() - i - 1);
			int age = people1.getAge() + AGE_INCREMENT;
			people1.setAge(age);
			peopleDAO.update(people1);

			Address address = addresses.get(addresses.size() - i - 1);
			int houseNumber = address.getHouse() + HOUSE_INCREMENT;
			address.setHouse(houseNumber);
			addressDAO.update(address);
		});

		//deleting any rows(3)
		addressDAO.delete(ID_TO_DELETE);
		peopleDAO.delete(ID_TO_DELETE);
		HibernateUtil.closeFactory();
	}
}
