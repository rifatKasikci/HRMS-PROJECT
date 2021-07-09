package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.EmployerUpdate;

public interface EmployerUpdateDao extends JpaRepository<EmployerUpdate, Integer>{

	EmployerUpdate findByIsApprovedFalseAndEmployer_Id(int employerId);
	
	List<EmployerUpdate> findByIsApprovedFalse();
	
	List<EmployerUpdate> findByEmployer_Id(int employerId);
}
