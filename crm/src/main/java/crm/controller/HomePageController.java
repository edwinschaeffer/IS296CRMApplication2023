package crm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import crm.dao.HomePageDAO;
import crm.vo.PotentialLead;

@Controller
public class HomePageController {
	@Autowired
	private JdbcTemplate jdbcT;
	@Autowired
	private HomePageDAO hpDAO;
	
	@GetMapping("/")
	public String getData(Model model) {
		List<Map<String, Object>> leads = jdbcT.queryForList("SELECT * from POTENTIAL_LEADS"); 
		model.addAttribute("leads", leads);
		return "home";
	}
	
	@GetMapping("/home")
	public String getDataRowMapper(Model model) {
		List<PotentialLead> plList = hpDAO.getListOfAllPLsRowMapper(); 
		model.addAttribute("leads", plList);
		return "home";
	}
	
	@GetMapping("/homeMyBatis")
	public String getDataRowMapperMyBatis(Model model) {
		List<PotentialLead> plList = hpDAO.getListOfAllPLsMyBatis();
		model.addAttribute("leads", plList);
		return "home";
	}
	
	@GetMapping("/homeMyBatis5")
	public String getDataRowMapperMyBatis5(Model model) {
		List<PotentialLead> plList = hpDAO.getListOf5PLsMyBatis();
		model.addAttribute("leads", plList);
		return "home";
	}
	
	@GetMapping("/homeMyBatisPLById")
	public String getPLByIDMyBatis(@RequestParam String plID, Model model) {
		List<PotentialLead> plList = hpDAO.getPLByIdMyBatis(plID);
		model.addAttribute("leads", plList);
		return "home";
	}
	
	@GetMapping("/homeJPAById")
	public String getPLByIDJPA(@RequestParam String plID, Model model) {
		PotentialLead pl = hpDAO.getPLByIdJPA(plID);
		List<PotentialLead> plList = new ArrayList<PotentialLead>();
		plList.add(pl);
		model.addAttribute("leads", plList);
		return "home";
	}
	
}
