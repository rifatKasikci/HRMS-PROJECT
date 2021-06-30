package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import kodlamaio.hrms.entities.concretes.JobAdvertisement;
import kodlamaio.hrms.entities.dtos.JobAdvertisementFilter;

public interface JobAdvertisementsDao extends JpaRepository<JobAdvertisement, Integer> {

	List<JobAdvertisement> findById(int id);
	
	@Query("From JobAdvertisement where active = true and isDeleted = false")
	List<JobAdvertisement> findByActiveTrue(Pageable pageable);
	
	@Query("Select ja from kodlamaio.hrms.entities.concretes.JobAdvertisement ja where ((:#{#jobAdvertisementFilter.cityId}) IS NULL or ja.city.id IN (:#{#jobAdvertisementFilter.cityId}))"
			+ "or ((:#{#jobAdvertisementFilter.workingTimeId}) IS NULL or ja.workingTime.id IN (:#{#jobAdvertisementFilter.workingTimeId}))"
			+ "and (ja.active = true and ja.isDeleted = false) ")
	List<JobAdvertisement> findByActiveTrueAndFiltered(@Param("jobAdvertisementFilter") JobAdvertisementFilter jobAdvertisementFilter,Pageable pageable);
	
	List<JobAdvertisement> findByActiveFalse();
	
	List<JobAdvertisement> findByEmployer_Id(int employerId);
	
	@Query("From JobAdvertisement where active = false and isDeleted = false")
	List<JobAdvertisement> findUnapprovedAdvertisements(Pageable pageable);
  
}
