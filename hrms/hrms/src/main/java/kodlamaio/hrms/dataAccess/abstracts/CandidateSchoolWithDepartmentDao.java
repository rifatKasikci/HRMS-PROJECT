package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import kodlamaio.hrms.entities.concretes.CandidateSchoolWithDepartment;

public interface CandidateSchoolWithDepartmentDao extends JpaRepository<CandidateSchoolWithDepartment, Integer>{

	
	List<CandidateSchoolWithDepartment> getAllByCandidateIdOrderByEndingDateDesc(int candidateId);
	
	List<CandidateSchoolWithDepartment>  getAllByCandidate_Id(int candidateId);
	
	List<CandidateSchoolWithDepartment>  getAllByOrderByEndingDateDesc();
}
