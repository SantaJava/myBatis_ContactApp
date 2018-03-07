package edu.iot.lib.view;

import java.util.Scanner;

public class View extends AbstractView {
	// Singletone 패턴
	private View() {
	}
	
	private static View view = new View();
	
	public static View getInstance() {
		return view;
	}
	
}


