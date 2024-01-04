package it_academy.$12_12_23Homework.task_72;

import java.lang.reflect.Method;

public class Runner {

	public static final String NAME = "Bob";

	public static void main(String[] args) {
		Student student = new Student(NAME);

		Class clazz = student.getClass();

		Method[] methods = clazz.getDeclaredMethods();


		System.out.println("Method(s) with annotation academyinfo:");
		int position = 1;
		for (Method m : methods) {
			if (m.isAnnotationPresent(academyinfo.class)) {
				System.out.println(position++ + ") " + m.getReturnType().getSimpleName() + " " + m.getName() + "();");
				System.out.println("Parametr of annatation: " + m.getAnnotation(academyinfo.class).year());
			}
		}
		System.out.println();

		System.out.println("Method(s) without of annotation academyinfo:");
		position = 1;
		for (Method m : methods) {
			if (!m.isAnnotationPresent(academyinfo.class)) {
				System.out.println(position++ + ") " + m.getReturnType().getSimpleName() + " " + m.getName() + "();");
			}
		}
		System.out.println();


	}
}
