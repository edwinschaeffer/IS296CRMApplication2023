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
	// Transforms the Area column in the database and returns to screen
	@GetMapping("/changeArea")
	public String getChangedArea(Model model) {
		model.addAttribute("leads", hpDAO.transformAreaJPA());
		return "home";
	}
	@GetMapping("/changeAreaJDBCTemplate")
	public String getChangedAreaJDBCTemplate(Model model) {
		model.addAttribute("leads", hpDAO.transformAreaJDBCTemplate());
		return "home";
	}
	@GetMapping("/maxEmpByArea")
	public @ResponseBody List<Map<String, Object>> getmaxEmpByArea() {
		return hpDAO.maxEmpByArea();
	}
	
	@GetMapping("/home")
	public String getDataRowMapper(Model model) {
		List<PotentialLead> plList = hpDAO.getListOfAllPLsRowMapper(); 
		System.out.println(plList.get(0).getId());
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
	// Technically you do not need value="plId" if the variable name
	// matches the expression. So if the variable name = plId you can
	// omit the value="plId"
	@GetMapping("/homeMyBatisPLById/{plId}")
	public String getPLByIDMyBatisByVarb(@PathVariable(value="plId") String plIDre, Model model) {
		List<PotentialLead> plList = hpDAO.getPLByIdMyBatis(plIDre);
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
	@GetMapping("/homeJPAFindByCity/{city}")
	public String getPLFindByCityJPA(@PathVariable String city, Model model) {
		List<PotentialLead> plList = hpDAO.getPLByCityJPA(city);
		model.addAttribute("leads", plList);
		return "home";
	}
	@RequestMapping("/submitLead")
	public String getLeadPage() {
		return "submitLead";
	}
	@GetMapping("/editStandardById/{plId}")
	public String getPLByIDMyBatisGoogleMap(@PathVariable(value="plId") String plIDre, Model model) {
		List<PotentialLead> plList = hpDAO.getPLByIdMyBatis(plIDre);
		model.addAttribute("leads", plList);
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
	@GetMapping("/submitLeadMyBatisPLById")
	public @ResponseBody String getPLByIDMyBatisSubmitLead(@RequestParam String plID, Model model) throws JsonProcessingException  {
		ObjectMapper om = new ObjectMapper();
		return om.writeValueAsString(hpDAO.getPLByIdMyBatis(plID));
	}
	
	@GetMapping("/getMaxEmployees")
	public String getMaxEmployees(Model model) {
		model.addAttribute("leads", hpDAO.getMaxEmployees());
		return "home";
	}
	@GetMapping("/getMap")
	public String getGoogleMap(Model model) {
		List<PotentialLead> plList = hpDAO.getListOfAllPLsMyBatis();
		model.addAttribute("leads", plList);
		return "map";
	}
	
}
