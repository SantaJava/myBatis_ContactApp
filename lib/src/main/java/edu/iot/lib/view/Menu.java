package edu.iot.lib.view;

import edu.iot.lib.command.Command;

public abstract class Menu {
	String title;
	Command command;
	
	public Menu() {}
	
	public Menu(String title, Command command) {
		super();
		this.title = title;
		this.command = command;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setCommand(Command command) {
		this.command = command;
	}
	
	public abstract void execute();
}
