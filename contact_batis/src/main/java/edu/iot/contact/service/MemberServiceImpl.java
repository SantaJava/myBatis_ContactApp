package edu.iot.contact.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.iot.contact.dao.ContactDao;
import edu.iot.contact.dao.ContactDaoImpl;
import edu.iot.contact.dao.MemberDao;
import edu.iot.contact.dao.MemberDaoImpl;
import edu.iot.contact.db.ConnectionFactory;
import edu.iot.contact.model.Member;
import edu.iot.contact.view.MemberView;

public class MemberServiceImpl implements MemberService {
	MemberDao dao;
	ContactDao contactDao;
	MemberView view;
	
	public MemberServiceImpl() {
		dao = new MemberDaoImpl();
		contactDao = new ContactDaoImpl();
		
		view = MemberView.getInstance();
	}

	@Override
	public Member login() {
		String userId = view.getString("사용자 ID : ");
		String password = view.getString("비밀번호 : ");
		
		Member member = null;
		try {
			member = dao.selectOne(userId);
			if(member == null) {
				System.out.println("존재하지 않는 ID 입니다.");
			} else {
				if(password.equals(member.getPassword())) {
					System.out.println("로그인 성공");
				} else {
					System.out.println("비밀번호가 틀렸습니다.");
					member = null;
				}				
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return member;
	}

	@Override
	public Member getMember(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Member> getMemberList(int page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void join() {
		String userId = null;
		try {
			while(true) {
				userId = view.getString("사용자 ID : ");
				Member member = dao.selectOne(userId);
				if(member == null) {
					break;
				} else {
					System.out.println("사용중인 ID 입니다.");
				}
			}
			// 나머지 정보 입력 후 insert
			Member member = view.getNewMember(userId);
			int result = dao.insert(member);
			ConnectionFactory.getSqlSession().commit();
			if(result == 1) {
				System.out.println("사용자 추가 성공");
			}	
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update() {
		String userId = view.getString("수정할 사용자 ID : ");
		try {
			Member member = dao.selectOne(userId);
			if(member!=null) {
				member = view.getUpdatedMember(member);
				dao.update(member);
				ConnectionFactory.getSqlSession().commit();
				System.out.println("업데이트 완료");
			} else {
				System.out.println("존재하지 않는 사용자입니다.");
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}

	@Override
	public void delete() {
		String userId = view.getString("삭제할 사용자 ID : ");
		try {
			Member member = dao.selectOne(userId);
			if(member==null) {
				System.out.println("존재하지 않는 사용자입니다.");
				return;
			}
			
			// 외래키 처리 ?	
			Map<String, String> map = new HashMap<>();
			map.put("owner", userId);
			contactDao.delete(map);
			dao.delete(userId);		
			ConnectionFactory.getSqlSession().commit();
			
		} catch (Exception e) {
			ConnectionFactory.getSqlSession().rollback();
			e.printStackTrace();
			
		}
		
	}

	@Override
	public void changePassword() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	final static int PER_PAGE = 5;
	
	@Override
	public void printList() {
		try {
			int total = dao.getCount();
			int totalPage = (int)Math.ceil( (double)total/PER_PAGE );
			int page = 1;
			while(true) {
				if(page >= 1 && page <= totalPage) {
					List<Member> list;
					int start = (page-1)*PER_PAGE +1;
					int end = start + PER_PAGE -1;
					Map<String, Integer> map = new HashMap<>();
					map.put("start", start);
					map.put("end", end);
					list = dao.selectList(map);
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

}
