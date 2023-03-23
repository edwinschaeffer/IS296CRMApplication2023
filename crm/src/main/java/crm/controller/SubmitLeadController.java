package crm.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import crm.dao.HomePageDAO;
import crm.service.ExcelUtility;
import crm.vo.PotentialLead;

@Controller
public class SubmitLeadController {
	@Autowired
	private ExcelUtility eu;
	@Autowired
	private HomePageDAO hpDAO;

	@PostMapping("/dbUpload")
	public String uploadSpreadSheet(Model model, @RequestParam("file") MultipartFile file) throws IOException {
		hpDAO.savePLListToDBJPA(eu.transformExceltoPL(file));
		List<PotentialLead> plList = hpDAO.getListOfAllPLsRowMapper(); 
		model.addAttribute("leads", plList);
		return "home";
	}
}
