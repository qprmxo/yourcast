package com.yourcast.app.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yourcast.app.vo.BoardReplyReportVO;

@Repository
public class BoardReplyReportDAO {

	@Autowired
	private SqlSession sqlSession;
	private static final String NAMESPACE = "com.jhta.mybatis.BoardReplyReport";

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public int insert(BoardReplyReportVO vo) {
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	public int getCount(int br_num) {
		return sqlSession.selectOne(NAMESPACE + ".getcount", br_num);
	}
}
