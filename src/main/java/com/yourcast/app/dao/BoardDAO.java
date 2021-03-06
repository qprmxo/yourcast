package com.yourcast.app.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yourcast.app.vo.BoardVO;

@Repository
public class BoardDAO {
	@Autowired
	private SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	private final String NAMESPACE = "com.jhta.mybatis.BoardMapper";

	public int insert(BoardVO vo) {
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	public int update(BoardVO vo) {
		return sqlSession.update(NAMESPACE + ".update", vo);
	}

	public BoardVO getInfo(int num) {
		BoardVO vo = sqlSession.selectOne(NAMESPACE + ".getInfo", num);
		return vo;
	}

	public List<BoardVO> getList(HashMap<String, Object> map) {
		List<BoardVO> blist = sqlSession.selectList(NAMESPACE + ".getList", map);
		return blist;
	}

	public int delete(int num) {
		return sqlSession.delete(NAMESPACE + ".delete", num);
	}

	public int hitUpdate(int  b_num) {
		return sqlSession.update(NAMESPACE + ".hitUpdate", b_num);
	}

	public int getCount(HashMap<String, Object>map) {
		return sqlSession.selectOne(NAMESPACE + ".getCount",map);
	}
	public List<BoardVO> mainNoticeList(int bj_num){
		return sqlSession.selectList(NAMESPACE+".mainNoticeList",bj_num);
	}
	public List<BoardVO> mainBoardList(int bj_num){
		return sqlSession.selectList(NAMESPACE+".mainBoardList",bj_num);
	}
}
