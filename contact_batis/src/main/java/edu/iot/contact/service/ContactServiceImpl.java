package edu.iot.contact.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.iot.contact.dao.ContactDao;
import edu.iot.contact.dao.ContactDaoImpl;
import edu.iot.contact.db.ConnectionFactory;
import edu.iot.contact.model.Contact;
import edu.iot.contact.model.Member;
import edu.iot.contact.view.ContactView;
import edu.iot.lib.app.Context;
import edu.iot.lib.db.ConnectionProvider;

public class ContactServiceImpl implements ContactService {
	final static int PER_PAGE = 5;
	ContactDao dao;
	ContactView view;
	
	public ContactServiceImpl() {
		dao = new ContactDaoImpl();
		view = ContactView.getInstance();
	}

	@Override
	public void printMyList(String userId) {
		
	}
	
	@Override
	public void printPerOwner() {
		String userId = view.getString("검색 ID : ");
		printList(userId);	
	}
	
	@Override
	public void printList() {
		Member member = (Member) Context.getAttribute("USER");
		if(member.getGrade() == 0 ) {	// 관리자
			printList(null);
		} else {
			printList(member.getUserId());
		}
	}
	
	
	private void printList(String userId) {
		try {
			int total ;
			if(userId == null ) {	// 관리자
				total = dao.getCount(null);
			} else {
				Map<String, String> map = new HashMap<>();
				map.put("owner", userId);
				total = dao.getCount(map);
			}
			
			int totalPage = (int)Math.ceil( (double)total/PER_PAGE );
			int page = 1;
			while(true) {
				if(page >= 1 && page <= totalPage) {
					List<Contact> list;
					int start = (page-1)*PER_PAGE +1;
					int end = start + PER_PAGE -1;
					
					Map<String, Object> map = new HashMap<>();
					map.put("start", start);
					map.put("end", end);
					
					if(userId == null ) {	// 관리자
						list = dao.selectList(map);
					} else {	// 일반회원
						map.put("owner", userId);
						list = dao.selectList(map);
					}
					// 리스트 출력
					view.printPage(list, page, totalPage, totalPage);
				} else if(page == -1) {
					break;
				} else {
					System.out.println("잘못된 페이지 번호 입니다.");
				}				
				page = view.getInt("페이지 : ");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void add() {
		Member member = (Member) Context.getAttribute("USER");
		Contact contact = view.getNewContact(member.getUserId());
		try {
			dao.insert(contact);
			ConnectionFactory.getSqlSession().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update() {
		Member member = (Member) Context.getAttribute("USER");
		int contactId = view.getInt("수정할 연락처 ID : ");
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("owner", member.getUserId());
			map.put("contactId", contactId);
			Contact contact = dao.selectOne(map);
			if(contact!=null) {
				contact = view.getUpdatedContact(contact);
				dao.update(contact);
				ConnectionFactory.getSqlSession().commit();
				System.out.println("업데이트 완료");
			} else {
				System.out.println("존재하지 않는 연락처입니다.");
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void delete() {
		Member member = (Member) Context.getAttribute("USER");
		int contactId = view.getInt("삭제할 연락처 ID : ");
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("owner", member.getUserId());
			map.put("contactId", contactId);	
			
			int result = dao.delete(map);
			if(result == 1) {
				ConnectionFactory.getSqlSession().commit();
				System.out.println("삭제 완료");
			} else {
				System.out.println("삭제 실패");
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void search() {
		try {
			Member member = (Member) Context.getAttribute("USER");
			String keyword = view.getString("검색어 : ");			
			keyword = "%" + keyword + "%";
			
			Map<String, Object> map = new HashMap<>();
			map.put("owner", member.getUserId());
			map.put("keyword", keyword);
			
			List<Contact> list = dao.search(map);
			view.printList(list);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
