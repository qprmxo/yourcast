package com.yourcast.app;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yourcast.app.service.BroadcastService;
import com.yourcast.app.service.CategoryService;
import com.yourcast.app.service.GenreService;
import com.yourcast.app.service.MemberProfileService;
import com.yourcast.app.service.MemberService;
import com.yourcast.app.service.StarUseService;
import com.yourcast.app.vo.BroadcastVO;
import com.yourcast.app.vo.CategoryVO;
import com.yourcast.app.vo.GenreVO;
import com.yourcast.app.vo.MemberProfileVO;
import com.yourcast.app.vo.MemberVO;
import com.yourcast.app.vo.StarUseVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@Autowired
	private CategoryService service;

	@Autowired
	private MemberService m_sevice;

	@Autowired
	private MemberProfileService mp_service;

	@Autowired
	private BroadcastService b_service;

	@Autowired
	private StarUseService u_service;

	@Autowired
	private GenreService g_service;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(@RequestParam(value = "genre_num", defaultValue = "0") int genre_num, Model model) {
		List<BroadcastVO> blist = null;
		if (genre_num == 0) {
			blist = b_service.getList();
		} else {
			blist = b_service.getList(genre_num);
		}

		int cnt = (int) Math.ceil(blist.size() / 4.0);

		List<GenreVO> glist = g_service.getList();
		model.addAttribute("glist", glist);
		model.addAttribute("blist", blist);
		model.addAttribute("genre_num", genre_num);
		model.addAttribute("cnt", cnt);
		model.addAttribute("end", blist.size() - 1);

		return ".main";
	}


	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String personnel(@PathVariable(value = "id") String id, Model model) {
		MemberVO vo = m_sevice.getInfo(id);
		if (vo != null) {
			List<CategoryVO> clist = service.getList(vo.getM_num());
			MemberProfileVO voMP = mp_service.getInfo(vo.getM_num());
			List<StarUseVO> flist = u_service.getHotfList(vo.getM_num());
			model.addAttribute("flist", flist);
			model.addAttribute("clist", clist);
			model.addAttribute("id", id);
			model.addAttribute("voMP", voMP);
			return ".personnel";
		} else {
			return ".personnel.board.nomember";
		}
	}
}
