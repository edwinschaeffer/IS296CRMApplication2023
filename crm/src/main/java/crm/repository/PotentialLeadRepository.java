package crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import crm.vo.PotentialLead;


public interface PotentialLeadRepository extends JpaRepository<PotentialLead, String> {

	PotentialLead findByid(String id);  
}
