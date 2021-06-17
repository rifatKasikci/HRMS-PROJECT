package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.hrms.entities.concretes.JobAdvertisement;

public interface JobAdvertisementsDao extends JpaRepository<JobAdvertisement, Integer> {

	List<JobAdvertisement> findById(int id);
	
	@Query("From JobAdvertisement where active = true and isDeleted = false")
	List<JobAdvertisement> findByActiveTrue();
	
	List<JobAdvertisement> findByActiveFalse();
	
	List<JobAdvertisement> findByEmployer_Id(int employerId);
	
	@Query("From JobAdvertisement where active = false and isDeleted = false")
	List<JobAdvertisement> findUnapprovedAdvertisements();
  
}
