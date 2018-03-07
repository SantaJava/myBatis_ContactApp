package edu.iot.lib.view;

import edu.iot.lib.command.Command;

public class MenuItem extends Menu {
	
	public MenuItem() { }

	public MenuItem(String title, Command command) {
		super(title, command);
	}

	@Override
	public void execute() {
		if(command != null) {
			command.execute();
		}
	}
	
}
