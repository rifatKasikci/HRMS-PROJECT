package kodlamaio.hrms.business.abstracts;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;
import kodlamaio.hrms.entities.dtos.JobAdvertisementDto;
import kodlamaio.hrms.entities.dtos.JobAdvertisementFilter;

public interface JobAdvertisementService {
	
	DataResult<List<JobAdvertisement>> getById(int id);
	
	DataResult<List<JobAdvertisement>> getUnapprovedAdvertisements(int pageNo , int pageSize);
	
	DataResult<List<JobAdvertisement>> getAll(int pageNo , int pageSize);

	DataResult<List<JobAdvertisement>> getAllByActiveTrue(int pageNo , int pageSize);
	
	DataResult<List<JobAdvertisement>> findByActiveTrueAndFiltered(int pageNo, int pageSize,JobAdvertisementFilter jobAdvertisementFilter);
	
	DataResult<List<JobAdvertisement>> getAllByEmployerId(int employerId);
	
	Result add(JobAdvertisementDto jobAdvertisementDto);
	
	}
