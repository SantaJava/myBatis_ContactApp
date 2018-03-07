package edu.iot.contact.view;

import java.util.List;

import edu.iot.contact.model.Contact;
import edu.iot.lib.view.AbstractView;

public class ContactView extends AbstractView {
	// Singletone 패턴
	private ContactView() {
	}
	
	private static ContactView view = new ContactView();
	
	public static ContactView getInstance() {
		return view;
	}

	// 추후 구현
	public void printPage(List<Contact> list, 
						int page, int totalPage, int total) {
		
		printList(list);
		System.out.printf("%d/%d (총 %d건)\n", page, totalPage, total);
	}	
	
	public void printList(List<Contact> list) {
		System.out.println("No 이름		전화번호		이메일");
		System.out.println("-----------------------------------------");
		for(Contact c : list) {
			System.out.printf("%3d) %8s %14s %s%n",
					c.getContactId(), c.getName(), 
					c.getCellPhone(), c.getEmail());
		}
		System.out.println("-----------------------------------------");
		
	}
	public Contact getNewContact(String owner) {
		Contact contact = new Contact();
		contact.setOwner(owner);
		
		String name = getString("이름 : ");
		contact.setName(name);
		
		String cellPhone = getString("전화번호 : ");
		contact.setCellPhone(cellPhone);

		String email = getString("email : ");
		contact.setEmail(email);

		String address = getString("주소 : ");
		contact.setAddress(address);
		
		return contact;
	}
	
	public Contact getUpdatedContact(Contact contact) {
		String name = getString(
				String.format("이름 (%s): ", contact.getName()));
		if(!name.isEmpty()) { 
			contact.setName(name);
		}
		String cellPhone = getString(
				String.format("전화번호 : (%s)", contact.getCellPhone()));
		if(!cellPhone.isEmpty()) {
			contact.setCellPhone(cellPhone);
		}

		String email = getString(
				String.format("email (%s): ", contact.getEmail()));
		if(!email.isEmpty()) {
			contact.setEmail(email);
		}

		String address = getString(
				String.format("주소 (%s) :", contact.getAddress()));
		if(!address.isEmpty()) {
			contact.setAddress(address);
		}
		
		return contact;
	}


}
