package com.yourcast.app.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yourcast.app.vo.VideoReplyUpVO;

@Repository
public class VideoReplyUpDAO {

	@Autowired
	private SqlSession sqlSession;
	private final String NAMESPACE = "com.jhta.mybatis.VideoReplyUpMapper";

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public int insert(VideoReplyUpVO vo) {
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	public int getCount(int vr_num) {
		return sqlSession.selectOne(NAMESPACE + ".getcount", vr_num);
	}
	public VideoReplyUpVO check(VideoReplyUpVO vo) {
		return sqlSession.selectOne(NAMESPACE+".check",vo);
	}
}
