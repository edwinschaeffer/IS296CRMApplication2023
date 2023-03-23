package crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import crm.vo.PotentialLead;


public interface PotentialLeadRepository extends JpaRepository<PotentialLead, String> {
// This is the equivalent of saying:  
// SELECT * FROM POTENTIAL_LEADS WHERE Id = 'Id'
	PotentialLead findByid(String id);  
}
