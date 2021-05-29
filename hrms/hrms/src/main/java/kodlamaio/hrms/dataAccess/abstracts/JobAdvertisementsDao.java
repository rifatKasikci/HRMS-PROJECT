package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.JobAdvertisements;

public interface JobAdvertisementsDao extends JpaRepository<JobAdvertisements, Integer> {

}
