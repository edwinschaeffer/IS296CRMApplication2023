package crm.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import crm.vo.PotentialLead;

@Service
public class HomePageDAO {

	@Autowired
	private JdbcTemplate jdbcT;
	
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
}
