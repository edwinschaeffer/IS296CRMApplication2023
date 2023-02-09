package crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {
	@Autowired
	private JdbcTemplate jdbcT;
	
	@GetMapping("/")
	public void getData() {
		System.out.println(jdbcT.queryForList("SELECT * from POTENTIAL_LEADS"));
		// return jdbcT.queryForList("SELECT * from POTENTIAL_LEADS");
	}
	
	
}
