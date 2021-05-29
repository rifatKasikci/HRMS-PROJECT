package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.JobAdvertisement;

public interface JobAdvertisementsDao extends JpaRepository<JobAdvertisement, Integer> {

	List<JobAdvertisement> findByActiveTrue();
	
	List<JobAdvertisement> findByEmployer_EmployerId(int employerId);
  
}
