package com.yourcast.app;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.util.page.PageUtil;
import com.yourcast.app.service.BoardReplyService;
import com.yourcast.app.service.BoardReportService;
import com.yourcast.app.service.BoardService;
import com.yourcast.app.service.BoardUpService;
import com.yourcast.app.service.CategoryService;
import com.yourcast.app.service.MemberProfileService;
import com.yourcast.app.service.MemberService;
import com.yourcast.app.service.StarUseService;
import com.yourcast.app.vo.BoardReplyVO;
import com.yourcast.app.vo.BoardReportVO;
import com.yourcast.app.vo.BoardUpVO;
import com.yourcast.app.vo.BoardVO;
import com.yourcast.app.vo.CategoryVO;
import com.yourcast.app.vo.MemberProfileVO;
import com.yourcast.app.vo.MemberVO;
import com.yourcast.app.vo.StarUseVO;

@Controller
public class BoardController {

	@Autowired private BoardService b_service;
	@Autowired private CategoryService c_service;
	@Autowired private BoardReportService r_service;
	@Autowired private MemberService m_service;
	@Autowired private MemberProfileService mp_service;
	@Autowired private BoardReplyService br_service;
	@Autowired private BoardUpService bu_service;
	@Autowired private StarUseService u_service;

	@RequestMapping(value = "/{id}/board/insert", method = RequestMethod.GET)
	public String insertForm(@PathVariable(value = "id") String id, Model model) {
		MemberVO vo = m_service.getInfo(id);
		List<CategoryVO> clist = c_service.getList(vo.getM_num());
		model.addAttribute("clist", clist);
		model.addAttribute("id", id);
		MemberProfileVO voMP = mp_service.getInfo(vo.getM_num());
		model.addAttribute("voMP", voMP);
		List<StarUseVO> flist = u_service.getHotfList(vo.getM_num());
		model.addAttribute("flist", flist);
		return ".personnel.board.insert";
	}
	// insert : post
	@RequestMapping(value = "/{id}/board/insert", method = RequestMethod.POST)
	public String insert(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
			@PathVariable(value = "id") String id, HttpServletRequest request, HttpSession session, Model model) {
		String sid = request.getParameter("sid");
		MemberVO vo1 = m_service.getInfo(sid);
		int m_num = vo1.getM_num();
		MemberVO mvo = m_service.getInfo(id);
		int bj_num = mvo.getM_num();// bj페이지 번호
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String notice = request.getParameter("notice");
		String category = request.getParameter("cate_list");
		// 공지사항 여부
		int category_num = Integer.parseInt(category);
		int a = 0;
		if (notice == null) {
			a = 0;
		} else {
			a = 1;
		}
		// 추가 작업
		BoardVO bvo = new BoardVO(0, title, content, null, 0, a, 0, m_num, bj_num, category_num);
		b_service.insert(bvo);

		// 카테고리 리스트 지정
		CategoryVO vo = c_service.getInfo(category_num);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("m_num", vo.getM_num());
		map.put("category_num", category_num);

		// 페이징 처리
		int totalRowCount = b_service.getCount(map);
		PageUtil pu = new PageUtil(pageNum, 15, 5, totalRowCount);
		map.put("startRow", pu.getStartRow());
		map.put("endRow", pu.getEndRow());

		// getList처리
		List<CategoryVO> clist = c_service.getList(vo.getM_num());
		List<BoardVO> blist = b_service.getList(map);

		MemberProfileVO voMP = mp_service.getInfo(vo.getM_num());
		model.addAttribute("voMP", voMP);

		model.addAttribute("id", id);
		model.addAttribute("pu", pu);
		model.addAttribute("category_num", category_num);
		model.addAttribute("clist", clist);
		model.addAttribute("blist", blist);
		List<StarUseVO> flist = u_service.getHotfList(vo.getM_num());
		model.addAttribute("flist", flist);

		return ".personnel.board.list";
	}

	@RequestMapping(value = "/{id}/board/delete")
	public String delete(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
			@PathVariable(value = "id") String id, int b_num, int category_num, Model model) {
		b_service.delete(b_num);
		CategoryVO vo = c_service.getInfo(category_num);

		// 카테고리 리스트 지정
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("m_num", vo.getM_num());
		map.put("category_num", category_num);

		// 페이징 처리
		int totalRowCount = b_service.getCount(map);
		PageUtil pu = new PageUtil(pageNum, 15, 5, totalRowCount);
		map.put("startRow", pu.getStartRow());
		map.put("endRow", pu.getEndRow());

		// getList처리
		List<CategoryVO> clist = c_service.getList(vo.getM_num());
		List<BoardVO> blist = b_service.getList(map);

		model.addAttribute("id", id);
		model.addAttribute("pu", pu);
		model.addAttribute("category_num", category_num);
		model.addAttribute("clist", clist);
		model.addAttribute("blist", blist);

		MemberProfileVO voMP = mp_service.getInfo(vo.getM_num());
		model.addAttribute("voMP", voMP);
		List<StarUseVO> flist = u_service.getHotfList(vo.getM_num());
		model.addAttribute("flist", flist);

		return ".personnel.board.list";
	}

	// update
	@RequestMapping(value = "/{id}/board/update", method = RequestMethod.GET)
	public String updateForm(@PathVariable(value = "id") String id, int b_num, int category_num, Model model) {
		BoardVO bvo = b_service.getInfo(b_num);

		CategoryVO cvo = c_service.getInfo(category_num);
		List<CategoryVO> clist = c_service.getList(cvo.getM_num());

		model.addAttribute("id", id);
		model.addAttribute("clist", clist);
		model.addAttribute("vo", bvo);

		MemberProfileVO voMP = mp_service.getInfo(cvo.getM_num());
		model.addAttribute("voMP", voMP);
		List<StarUseVO> flist = u_service.getHotfList(cvo.getM_num());
		model.addAttribute("flist", flist);

		return ".personnel.board.update";
	}

	// updateOk
	@RequestMapping(value = "/{id}/board/update", method = RequestMethod.POST)
	public String update(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
			@PathVariable(value = "id") String id, HttpServletRequest request, HttpSession session, Model model) {
		String b_num = request.getParameter("b_num");
		int bnum = Integer.parseInt(b_num);
		String sid = request.getParameter("sid");
		MemberVO vo1 = m_service.getInfo(sid);
		int m_num = vo1.getM_num();
		MemberVO mvo = m_service.getInfo(id);
		int bj_num = mvo.getM_num();// bj페이지 번호
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String notice = request.getParameter("notice");
		String category = request.getParameter("cate_list");

		System.out.println(title);
		System.out.println(content);

		// 공지사항 여부
		int category_num = Integer.parseInt(category);
		int a = 0;
		if (notice == null) {
			a = 0;
		} else {
			a = 1;
		}
		// 수정 작업
		BoardVO bvo = new BoardVO(bnum, title, content, null, 0, a, 0, m_num, bj_num, category_num);
		b_service.update(bvo);

		// 카테고리 리스트 지정

		CategoryVO vo = c_service.getInfo(category_num);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("m_num", vo.getM_num());
		map.put("category_num", category_num);

		// 페이징 처리
		int totalRowCount = b_service.getCount(map);
		PageUtil pu = new PageUtil(pageNum, 15, 5, totalRowCount);
		map.put("startRow", pu.getStartRow());
		map.put("endRow", pu.getEndRow());

		// getList처리
		List<CategoryVO> clist = c_service.getList(vo.getM_num());
		List<BoardVO> blist = b_service.getList(map);
		model.addAttribute("id", id);
		model.addAttribute("pu", pu);
		model.addAttribute("category_num", category_num);
		model.addAttribute("clist", clist);
		model.addAttribute("blist", blist);

		MemberProfileVO voMP = mp_service.getInfo(vo.getM_num());
		model.addAttribute("voMP", voMP);
		List<StarUseVO> flist = u_service.getHotfList(vo.getM_num());
		model.addAttribute("flist", flist);

		return ".personnel.board.list";
	}

	// getInfo
	@RequestMapping(value = "/{id}/board/getInfo", method = RequestMethod.GET)
	public String getInfo(@PathVariable(value = "id") String id, int b_num, int category_num, Model model) {
		BoardVO bvo = b_service.getInfo(b_num);
		CategoryVO cvo = c_service.getInfo(category_num);
		List<CategoryVO> clist = c_service.getList(cvo.getM_num());// 카테고리 목록
		int brcount=br_service.getCount(b_num);
		
		b_service.hitUpdate(b_num);

		model.addAttribute("id", id);
		model.addAttribute("b_num", b_num);
		model.addAttribute("category_num", category_num);
		model.addAttribute("getCountInfo",bu_service.getCount(b_num));
		model.addAttribute("brcount",brcount);
		model.addAttribute("clist", clist);
		model.addAttribute("vo", bvo);

		MemberProfileVO voMP = mp_service.getInfo(cvo.getM_num());
		model.addAttribute("voMP", voMP);
		
		List<StarUseVO> flist = u_service.getHotfList(cvo.getM_num());
		model.addAttribute("flist", flist);
		
		MemberProfileVO voimg = mp_service.getInfo(bvo.getM_num());
		model.addAttribute("voimg",voimg);
		

		return ".personnel.board.getInfo";
	}

	// getList
	@RequestMapping(value = "/{id}/board/list", method = RequestMethod.GET)
	public String list(@RequestParam(value="pageNum",defaultValue="1") int pageNum,@PathVariable(value="id") String id, Model model,HttpServletRequest request) {
		MemberVO mvo = m_service.getInfo(id);
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("bj_num", mvo.getM_num());
		String category_num = request.getParameter("category_num");
		if(category_num != null) {
			map.put("category_num",Integer.parseInt(category_num));	
		}else {
			category_num = null;
		}
		
		int totalRowCount=b_service.getCount(map);
		
		System.out.println("totalRowCount:" +totalRowCount);
		
		PageUtil pu=new PageUtil(pageNum,15,5,totalRowCount);
		map.put("startRow", pu.getStartRow());
		map.put("endRow", pu.getEndRow());

		//getList처리
		List<CategoryVO> clist = c_service.getList(mvo.getM_num());
		List<BoardVO> blist = b_service.getList(map);
		//List<BoardReplyVO> brcountlist=new ArrayList<BoardReplyVO>();
		
		///글에 해당하는 댓글 개수 가져오기///
		int brcnt=0;
		int bucnt=0;
		for(BoardVO bvo:blist) {
			brcnt=br_service.getCount(bvo.getB_num());
			bucnt=bu_service.getCount(bvo.getB_num());
			bvo.setBrcnt(brcnt);
			bvo.setBucnt(bucnt);
		}
		
		/////////////////////////////
		model.addAttribute("id", id);
		model.addAttribute("pu", pu);
		model.addAttribute("category_num", category_num);
		model.addAttribute("clist", clist);
		model.addAttribute("blist", blist);
		
		String board_name = request.getParameter("category_name");
		model.addAttribute("board_name",board_name);
		
		MemberProfileVO voMP = mp_service.getInfo(mvo.getM_num());
		model.addAttribute("voMP", voMP);
		List<StarUseVO> flist = u_service.getHotfList(mvo.getM_num());
		model.addAttribute("flist", flist);
		
		return ".personnel.board.list";
	}
	//�Ű�
	@RequestMapping(value="/report/insert",produces="application/json;charset=utf-8")
	@ResponseBody
	public String insert(int b_num,String m_num) {
		MemberVO mvo= m_service.getInfo(m_num);
		//int mnum=Integer.parseInt(m_num);
		BoardReportVO vo=new BoardReportVO(b_num,mvo.getM_num());
		BoardReportVO br1= r_service.isCheck(vo);
		
		JSONObject json = new JSONObject();
			
		if(br1!=null) { 
			//json.put("insert",true); 
			json.put("result", "true");
		}else { 
			//json.put("insert", false);
			r_service.insert(vo);
			json.put("result", "false");
		}
		return json.toString();
	}
	@RequestMapping(value="/boardup/insert",produces="application/json;charset=utf-8")
	@ResponseBody
	public String up_insert(String m_num,int b_num) {
		MemberVO mvo= m_service.getInfo(m_num);
		BoardUpVO vo = new BoardUpVO(mvo.getM_num(),b_num);
		BoardUpVO br1 =  bu_service.isCheck(vo);
		int bucount= bu_service.getCount(b_num);
		JSONObject json = new JSONObject();
		if(br1!=null) { 
			json.put("result", "true");
		}else { 
			bu_service.insert(vo);
			json.put("result", "false");
			json.put("getCount", bu_service.getCount(b_num));
		}
		return json.toString();
	}
}
	    
