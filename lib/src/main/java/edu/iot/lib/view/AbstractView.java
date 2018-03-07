package edu.iot.lib.view;

import java.util.Scanner;

public abstract class AbstractView {
	
	protected Scanner scanner;	

	public AbstractView() {
		scanner = new Scanner(System.in);
	}

	
	public String getString(String msg) {
		System.out.print(msg);
		String line = scanner.nextLine();
		return line;
	}
	
	public int getInt(String msg) {
		System.out.print(msg);
		int input = scanner.nextInt();
		scanner.nextLine();	// 개행 문자 제거
		return input;
	}
}
