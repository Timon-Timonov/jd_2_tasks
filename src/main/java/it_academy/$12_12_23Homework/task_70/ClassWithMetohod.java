package it_academy.$12_12_23Homework.task_70;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassWithMetohod {

	private static void printManInformation(Man man) {
		Class manClass = man.getClass();
		String className = manClass.getSimpleName();
		Method[] mansMethods = manClass.getDeclaredMethods();
		Field[] mansFilds = manClass.getDeclaredFields();


		System.out.println("ClassName: " + className);
		System.out.println("Methods: ");
		int position = 1;
		for (Method m : mansMethods) {
			System.out.println(position++ + ") " + m.getReturnType().getSimpleName() + " " + m.getName() + "();");
		}

		System.out.println("Fields: ");
		position = 1;
		for (Field f : mansFilds) {
			System.out.println(position++ + ") " + f.getType().getSimpleName() + " " + f.getName() + ";");
		}
	}
}
