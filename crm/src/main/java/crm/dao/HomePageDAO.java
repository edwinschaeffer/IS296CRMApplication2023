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

	@Autowired
	private JdbcTemplate jdbcT;
	
	@Autowired
	private PotentialLeadMapper plm;
	
	@Autowired
	private PotentialLeadRepository plRepo;
	
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
	
	public void savePLToDB(PotentialLead pl) {
		plRepo.save(pl);
	}
	public void savePLListToDBJPA(List<PotentialLead> plList) {
		plRepo.saveAll(plList);
	}
	
}
