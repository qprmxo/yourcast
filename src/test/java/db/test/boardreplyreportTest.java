package db.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.yourcast.app.dao.BoardReplyReportDAO;
import com.yourcast.app.vo.BoardReplyReportVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
@WebAppConfiguration
public class boardreplyreportTest {
	@Autowired
	private BoardReplyReportDAO dao;

	@Test
	public void getcount() {
		int n = dao.getCount(2);
		System.out.println("���:" + n);
	}

	@Test
	public void insert() {
		int n = dao.insert(new BoardReplyReportVO(1, 2));
		boolean b = false;
		if (n > 0) {
			b = true;
		}
		assertTrue(b);
	}
}
