package kodlamaio.hrms.business.concretes;

import java.util.List;

import kodlamaio.hrms.business.abstracts.JobAdvertisementService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.dataAccess.abstracts.JobAdvertisementsDao;
import kodlamaio.hrms.entities.concretes.Employer;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;

public class JobAdvertisementManager implements JobAdvertisementService{

	private JobAdvertisementsDao jobAdvertisementDao;

	public JobAdvertisementManager(JobAdvertisementsDao jobAdvertisementDao) {
		super();
		this.jobAdvertisementDao = jobAdvertisementDao;
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAll() {
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.findAll() , "İş ilanları listelendi."); 
	}
	
	@Override
	public DataResult<List<JobAdvertisement>> getAllByActiveTrue() {
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.findByActiveTrue() , "Aktif iş ilanları listelendi.");
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAllByEmployerId(int employerId) {
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.findByEmployer_EmployerId(employerId));
	}

	
}
