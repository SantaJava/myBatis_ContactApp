package edu.iot.contact.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import edu.iot.contact.db.ConnectionFactory;
import edu.iot.contact.model.Member;

public class MemberDaoImpl implements MemberDao {
	static final String namespace="edu.iot.contact.dao.MemberDao.";
	
	SqlSession sqlSession;
	public MemberDaoImpl() {
		sqlSession = ConnectionFactory.getSqlSession();
	}
	
	@Override
	public int getCount() throws Exception {
		return sqlSession.selectOne(namespace + "getCount");
	}


	@Override
	public List<Member> selectList(Map map) 
			throws Exception {
		return sqlSession.selectList(namespace + "selectList", map);
	}


	@Override
	public Member selectOne(String userId) throws Exception {
		return sqlSession.selectOne(namespace + "selectOne", userId);
	}

	@Override
	public int insert(Member member) throws Exception {
		return sqlSession.insert(namespace + "insert", member);
	}


	@Override
	public int update(Member member) throws Exception {
		return sqlSession.update(namespace + "update", member);
	}


	@Override
	public int delete(String userId) throws Exception {
		return sqlSession.delete(namespace + "delete", userId);
	}

}
