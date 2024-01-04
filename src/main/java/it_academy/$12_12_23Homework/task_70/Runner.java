package it_academy.$12_12_23Homework.task_70;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Runner {

	public static final String METHODS_NAME = "printManInformation";
	public static final int ID = 987654321;
	public static final String NAME = "Peter";
	public static final String SUR_NAME = "Pen";
	public static final int AGE = 25;

	public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		Man man = new Man(ID);
		man.setName(NAME);
		man.setSurName(SUR_NAME);
		man.setAge(AGE);


		Class clazz = ClassWithMetohod.class;

		Method method = clazz.getDeclaredMethod(METHODS_NAME, man.getClass());
		method.setAccessible(true);
		method.invoke(clazz, man);


	}


}
