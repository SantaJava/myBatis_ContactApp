package edu.iot.contact;

import java.util.List;

import edu.iot.contact.dao.ContactDao;
import edu.iot.contact.dao.ContactDaoImpl;
import edu.iot.contact.model.Contact;

public class MyBatisContactDaoTest {
	public static void main(String[] args) {
		ContactDao dao = new ContactDaoImpl();
		try {
//			int count;
//			// count = dao.getCount();
//			count = dao.getCount(null);
//			System.out.println("관리자 : " + count);
//			count = dao.getCount("go");
//			System.out.println("go : " + count);
//			System.out.println("===============================");
//			
//			List<Contact> list;
//			
//			list = dao.selectList(1, 5);
//			System.out.println("관리자");
//			for(Contact c : list) {
//				System.out.println(c);
//			}
//			
//			list = dao.selectList("go", 1, 5);
//			System.out.println("go");
//			for(Contact c : list) {
//				System.out.println(c);
//			}
//			
//			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
}
