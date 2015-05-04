package edu.scut.wusir.client;

import java.util.Scanner;

public class consoleInput {

	public static String input(String mo){
		Scanner scanner = new Scanner(System.in);
		String msg = scanner.nextLine();
		return msg;
	}
}
