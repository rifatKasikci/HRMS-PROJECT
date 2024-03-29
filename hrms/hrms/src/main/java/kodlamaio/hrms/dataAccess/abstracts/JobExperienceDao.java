package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.JobExperience;

public interface JobExperienceDao extends JpaRepository<JobExperience, Integer>{

	List<JobExperience> getByCandidate_Id(int candidateId);
	
	List<JobExperience> getAllByOrderByEndingDateDesc();
	
	List<JobExperience> getAllByCandidateIdOrderByEndingDateDesc(int candidateId);
}
