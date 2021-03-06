package edu.iot.contact.service;

import java.util.List;

import edu.iot.contact.model.Member;

public interface MemberService {

	Member login();

	Member getMember(String userId);
	
	List<Member> getMemberList(int page);
	
	void printList();
	
	void join();
	
	void update();
	
	void delete();
	
	void changePassword() throws Exception;
}
