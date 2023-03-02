package crm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	@RequestMapping("/submitLead")
	public String getLeadPage() {
		return "submitLead";
	}
	@PostMapping("/postLead") 
	public String postLead(@RequestBody PotentialLead pl){
		System.out.println(pl.getCompany());
		hpDAO.savePLToDB(pl);
		return "redirect:/homeMyBatis";
	}
	@PostMapping("/postLead2") 
	public @ResponseBody String postLeadObjectMapper(@RequestBody String pl) throws JsonMappingException, JsonProcessingException{
		ObjectMapper om = new ObjectMapper();
		JsonNode jn = om.readTree(pl);
		JsonNode companyField = jn.get("company");
		System.out.println(companyField.asText());
		return "great job - response from server";
	}
	
}
