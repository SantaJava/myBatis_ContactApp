package edu.iot.lib.view;
import java.util.ArrayList;
import java.util.List;

public class MenuGroup extends Menu {
	List<Menu> menuList;
	
	public MenuGroup() {
		this(null);
	}

	public MenuGroup(String title) {
		super(title, null);
		menuList = new ArrayList<>();
	}

	public void add(Menu menu) {
		menuList.add(menu);
	}
	
	@Override
	public void execute() {
		View view = View.getInstance();
		while(true) {
			printMenu();
			int ix = view.getInt("선택 : ");
			if(ix >= 0 && ix <menuList.size()) {
				menuList.get(ix).execute();
			} else if(ix == menuList.size()) {	// 서브메뉴 나가기
				break;
			} else {
				System.out.println("잘못된 메뉴 선택입니다.");
			}
		}
	}
	
	public void printMenu() {
		for(int i=0; i<menuList.size(); i++) {
			Menu menu = menuList.get(i);
			System.out.printf("%d) %s ", i, menu.getTitle());
		}
		// 서브메뉴 나가기 메뉴
		System.out.printf("%d) 나가기", menuList.size());
		System.out.println();
	
	}
}
