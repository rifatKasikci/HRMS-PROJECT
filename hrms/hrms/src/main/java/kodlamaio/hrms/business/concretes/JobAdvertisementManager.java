package kodlamaio.hrms.business.concretes;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import kodlamaio.hrms.business.abstracts.JobAdvertisementService;
import kodlamaio.hrms.business.constants.Messages;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CityDao;
import kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import kodlamaio.hrms.dataAccess.abstracts.JobAdvertisementsDao;
import kodlamaio.hrms.dataAccess.abstracts.JobPositionDao;
import kodlamaio.hrms.dataAccess.abstracts.WayOfWorkingDao;
import kodlamaio.hrms.dataAccess.abstracts.WorkingTimeDao;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;
import kodlamaio.hrms.entities.dtos.JobAdvertisementDto;
import kodlamaio.hrms.entities.dtos.JobAdvertisementFilter;

@Service
public class JobAdvertisementManager implements JobAdvertisementService{

	private JobAdvertisementsDao jobAdvertisementDao;
	private CityDao cityDao;
	private EmployerDao employerDao;
	private JobPositionDao jobPositionDao;
	private WayOfWorkingDao wayOfWorkingDao;
	private WorkingTimeDao workingTimeDao;

	@Autowired
	public JobAdvertisementManager(JobAdvertisementsDao jobAdvertisementDao , 
			CityDao cityDao , 
			EmployerDao employerDao , 
			JobPositionDao jobPositionDao , 
			WayOfWorkingDao wayOfWorkingDao , 
			WorkingTimeDao workingTimeDao) {
		
		super();
		this.jobAdvertisementDao = jobAdvertisementDao;
		this.cityDao = cityDao;
		this.employerDao = employerDao;
		this.jobPositionDao = jobPositionDao;
		this.wayOfWorkingDao = wayOfWorkingDao;
		this.workingTimeDao = workingTimeDao;
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.findAll(pageable).getContent());
	}
	
	@Override
	public DataResult<List<JobAdvertisement>> getAllByActiveTrue(int pageNo,int pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.findByActiveTrue(pageable),  Messages.activeJobAdvertisementsListed);
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAllByEmployerId(int employerId) {
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.findByEmployer_Id(employerId) ,  Messages.jobAdvertisementsListed);
	}
	
	@Override
	public DataResult<List<JobAdvertisement>> getUnapprovedAdvertisements(int pageNo,int pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.findUnapprovedAdvertisements(pageable));

	}

	@Override
	public Result add(JobAdvertisementDto jobAdvertisementDto) {
		JobAdvertisement jobAdvertisementToSave = new JobAdvertisement();
		jobAdvertisementToSave.setEmployer(this.employerDao.getOne(jobAdvertisementDto.getEmployerId()));
		jobAdvertisementToSave.setCity(this.cityDao.getOne(jobAdvertisementDto.getCityId()));
		jobAdvertisementToSave.setJobPosition(this.jobPositionDao.getOne(jobAdvertisementDto.getJobPositionId()));
		jobAdvertisementToSave.setWayOfWorking(this.wayOfWorkingDao.getOne(jobAdvertisementDto.getWayOfWorkingId()));
		jobAdvertisementToSave.setWorkingTime(this.workingTimeDao.getOne(jobAdvertisementDto.getWorkingTimeId()));
		jobAdvertisementToSave.setMinSalary(jobAdvertisementDto.getMinSalary());
		jobAdvertisementToSave.setMaxSalary(jobAdvertisementDto.getMaxSalary());
		LocalDate localDate = LocalDate.now();
		jobAdvertisementToSave.setReleaseDate(localDate);
		jobAdvertisementToSave.setApplicationDeadline(jobAdvertisementDto.getApplicationDeadline());
		jobAdvertisementToSave.setNumberOfOpenPostion(jobAdvertisementDto.getNumberOfOpenPosition());
		jobAdvertisementToSave.setDescription(jobAdvertisementDto.getDescription());
		jobAdvertisementToSave.setActive(false);
		jobAdvertisementToSave.setDeleted(false);
		this.jobAdvertisementDao.save(jobAdvertisementToSave);
		return new SuccessResult(Messages.jobAdvertisementAdded);
		
		}	
	
	
	@Override
	public DataResult<List<JobAdvertisement>> getById(int id) {
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.findById(id));
	}

	@Override
	public DataResult<List<JobAdvertisement>> findByActiveTrueAndFiltered(int pageNo , int pageSize,JobAdvertisementFilter jobAdvertisementFilter) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.findByActiveTrueAndFiltered(jobAdvertisementFilter,pageable));
	}

	
	
	
	
	


	
	
	
}
