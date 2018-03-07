package edu.iot.contact;

import java.util.List;

import edu.iot.contact.dao.MemberDao;
import edu.iot.contact.dao.MemberDaoImpl;
import edu.iot.contact.db.ConnectionFactory;
import edu.iot.contact.model.Member;

public class MyBatisTest {
	public static void main(String[] args) {
		MemberDao dao = new MemberDaoImpl();
		
		try {
//			List<Member> list = dao.selectList(1, 5);
//			for(Member m : list) {
//				System.out.println(m);
//			}
			
			System.out.println("=================");
			Member m2 = dao.selectOne("hong");
			System.out.println(m2);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ConnectionFactory.getSqlSession().close();
		System.out.println("완료");
		
	}
}
