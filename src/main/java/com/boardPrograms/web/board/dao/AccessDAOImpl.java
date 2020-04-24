package com.boardPrograms.web.board.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.io.ResolverUtil.Test;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.boardPrograms.web.board.model.Params;

@Repository("accessDAOImpl")
public class AccessDAOImpl implements AccessDAO {

	@Autowired
	SqlSession sqlSession;
	
	private static final String namespace = "com.boardPrograms.web.board.boardsMapper";
		
	public AccessDAOImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public void setAutoCommit(boolean autoCommit) { 
		try {
			sqlSession.getConnection().setAutoCommit(autoCommit);
		} catch (SQLException e) { 
			e.printStackTrace(); 
		} 
	}
	 
	public void commit() {
		sqlSession.commit();
	}
	
	public void rollback() {
		sqlSession.rollback();
	}

	@Override
	public Map<String, Object> getAccessList(Map<String, Object> param) {
		return sqlSession.selectOne(namespace + ".getAccessList", param);
	}
	
	@Override
	public Cursor<Object> getAccessListCursor(Map<String, Object> param) {
		return sqlSession.selectCursor(namespace + ".getAccessList", param);
	}
	 
	public SqlSession getSqlSession() {
		return sqlSession;
	}
	
	@Autowired
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public <T> List<T> executeProcPostgreSQL(String queryId, Map<String, Object> param) {
		List<T> resultList = sqlSession.selectList(queryId, param);
		return resultList;
	}
	
}
