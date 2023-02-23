package crm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import crm.vo.PotentialLead;

@Mapper
public interface PotentialLeadMapper {

	@Select("SELECT * FROM POTENTIAL_LEADS")
	// Mybatis is case insensitive. We only have to map the fields that have the underscores (or named differently)
	@Results (id = "plResultMap", value = {
			@Result(property = "ageOfBusiness", column = "AGE_OF_BUSINESS"), 
			@Result(property = "employeeCount", column = "EMPLOYEE_COUNT"),
			@Result(property = "potentialLeadLocationLatitude", 
								column = "POTENTIAL_LEAD_LOCATION__LATITUDE__S"),
			@Result(property = "potentialLeadLocationLongitude", 
			                    column = "POTENTIAL_LEAD_LOCATION__LONGITUDE__S"),
			@Result(property = "zipCode", column = "ZIP_CODE")
	})
	public List<PotentialLead> getListOfAllPLs();
	
	@Select("SELECT * FROM POTENTIAL_LEADS LIMIT 5")
	@ResultMap("plResultMap")
	public List<PotentialLead> getListOf5PLs();
	
	@Select("SELECT * FROM POTENTIAL_LEADS WHERE ID = #{plID}")
	@ResultMap("plResultMap")
	public List<PotentialLead> getPLById(String plID);
	
}
