package edu.iot.contact.dao;

import java.util.List;
import java.util.Map;

import edu.iot.contact.model.Contact;

public interface ContactDao {

	int getCount(Map map) throws Exception;
	
	List<Contact> selectList(Map map) throws Exception;

	Contact selectOne(Map map) throws Exception;

	int insert(Contact contact) throws Exception;

	int update(Contact contact) throws Exception;

	int delete(Map map) throws Exception;
	
	List<Contact> search(Map map) throws Exception;
	
	
}
