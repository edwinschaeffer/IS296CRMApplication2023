package crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import crm.vo.PotentialLead;


public interface PotentialLeadRepository extends JpaRepository<PotentialLead, String> {
// This is the equivalent of saying:  
// SELECT * FROM POTENTIAL_LEADS WHERE Id = 'Id'
	PotentialLead findByid(String id);  
	List<PotentialLead> findByCity(String city);  
	
	@Query(value = "SELECT * FROM POTENTIAL_LEADS "
			+ "WHERE EMPLOYEE_COUNT = (SELECT MAX(EMPLOYEE_COUNT) FROM POTENTIAL_LEADS)", nativeQuery = true)
	List<PotentialLead> getMaxEmployees();
	
}
