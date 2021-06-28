package kodlamaio.hrms.business.abstracts;

import java.util.List;

import org.springframework.data.domain.Page;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;
import kodlamaio.hrms.entities.dtos.JobAdvertisementDto;

public interface JobAdvertisementService {
	
	DataResult<List<JobAdvertisement>> getById(int id);
	
	DataResult<List<JobAdvertisement>> getUnapprovedAdvertisements(int pageNo , int pageSize);
	
	DataResult<List<JobAdvertisement>> getAll(int pageNo , int pageSize);

	DataResult<List<JobAdvertisement>> getAllByActiveTrue(int pageNo , int pageSize);
	
	DataResult<List<JobAdvertisement>> getAllByEmployerId(int employerId);
	
	Result add(JobAdvertisementDto jobAdvertisementDto);
	
	}
