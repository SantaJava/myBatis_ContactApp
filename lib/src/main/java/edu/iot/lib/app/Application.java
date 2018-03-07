package edu.iot.lib.app;

import edu.iot.lib.view.MenuBar;

public class Application {
	protected MenuBar mainMenu;
	
	public Application() {	}
	
	public void init() {
		createMenu();
	}

	public void createMenu() {	}

	public void run() {	// 메뉴 운영
		while(true) {
			try {
				mainMenu.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
	}
	
	// 기본 메뉴 핸들러
	public void exit() {
		System.out.println("종료합니다.");
		System.exit(0);
	}

}
