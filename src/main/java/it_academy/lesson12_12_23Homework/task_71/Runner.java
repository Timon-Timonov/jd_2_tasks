package it_academy.lesson12_12_23Homework.task_71;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Runner {

	public static final String PRINT_HELLO_WORLD = "printHelloWorld";

	public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

		Class clazz = HelloWorld.class;
		Method method = clazz.getDeclaredMethod(PRINT_HELLO_WORLD, null);
		method.setAccessible(true);
		method.invoke(clazz, null);
	}
}
