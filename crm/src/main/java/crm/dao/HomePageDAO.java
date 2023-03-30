package crm.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import crm.mapper.PotentialLeadMapper;
import crm.repository.PotentialLeadRepository;
import crm.vo.PotentialLead;

@Service
public class HomePageDAO {

	private JdbcTemplate jdbcT;
	
	private PotentialLeadMapper plm;
	
	@Autowired
	private PotentialLeadRepository plRepo;
	
	/* Constructor injection. 
 	   Since there is a JdbcTemplate type returned in the Config class
	   the JdbcTemplate exists in the factory and Spring will auto-inject the
	   JdbcTemplate without an @Autowired annotation */
	public HomePageDAO(JdbcTemplate jdbcT) {
		this.jdbcT = jdbcT;
	}
	
	public List<PotentialLead> getListOfAllPLsRowMapper() {
		return jdbcT.query("SELECT * FROM POTENTIAL_LEADS", new RowMapper<PotentialLead>() {

			@Override
			public PotentialLead mapRow(ResultSet rs, int rowNum) throws SQLException {
				PotentialLead pl = new PotentialLead();
				pl.setId(rs.getString("ID"));
				pl.setAgeOfBusiness(rs.getString("AGE_OF_BUSINESS"));
				pl.setCompany(rs.getString("COMPANY"));
				pl.setCity(rs.getString("CITY").toLowerCase());
				return pl;
			}
			
		});
	}
	
	public List<PotentialLead> getListOfAllPLsMyBatis() {
		return plm.getListOfAllPLs();
	}
	
	public List<PotentialLead> getListOf5PLsMyBatis() {
		return plm.getListOf5PLs();
	}
	
	public List<PotentialLead> getPLByIdMyBatis(String plID) {
		return plm.getPLById(plID);
	}
	
	public PotentialLead getPLByIdJPA(String plId) {
		PotentialLead pl = plRepo.findByid(plId);
		return pl;
	}
	public List<PotentialLead> getPLByCityJPA(String plCity) {
		List<PotentialLead> pl = plRepo.findByCity(plCity);
		return pl;
	}
	
	public void savePLToDB(PotentialLead pl) {
		plRepo.save(pl);
	}
	public void savePLListToDBJPA(List<PotentialLead> plList) {
		plRepo.saveAll(plList);
	}
	
	public List<PotentialLead> getMaxEmployees() {
		return plRepo.getMaxEmployees();
	}
	// Setter injection
	// Here we use Setter Injection in the Annotation-Configured Class
	@Autowired
	public void setPLM(PotentialLeadMapper plm) {
		// In theory we could do additional things prior to setting the variable
		this.plm = plm;
	}
	
}
