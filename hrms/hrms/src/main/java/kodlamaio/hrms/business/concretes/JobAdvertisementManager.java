package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobAdvertisementService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CityDao;
import kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import kodlamaio.hrms.dataAccess.abstracts.JobAdvertisementsDao;
import kodlamaio.hrms.entities.concretes.City;
import kodlamaio.hrms.entities.concretes.Employer;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;

@Service
public class JobAdvertisementManager implements JobAdvertisementService{

	private JobAdvertisementsDao jobAdvertisementDao;
	private CityDao cityDao;
	private EmployerDao employerDao;

	@Autowired
	public JobAdvertisementManager(JobAdvertisementsDao jobAdvertisementDao , CityDao cityDao , EmployerDao employerDao) {
		super();
		this.jobAdvertisementDao = jobAdvertisementDao;
		this.cityDao = cityDao;
		this.employerDao = employerDao;
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
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.findByEmployer_Id(employerId));
	}


	@Override
	public Result setAdvertisementInactive(int advertisementId) {
	
		JobAdvertisement referenceEntity = this.jobAdvertisementDao.getOne(advertisementId);
		referenceEntity.setActive(false);
		this.jobAdvertisementDao.save(referenceEntity);
		return new SuccessResult("İş ilanı pasif hale getirildi.");
	}

	@Override
	public Result add(JobAdvertisement jobAdvertisement) {
		if (this.isDataRight(jobAdvertisement).isSuccess()) {
			this.jobAdvertisementDao.save(jobAdvertisement);
		return new SuccessResult("İş ilanı eklendi.");
		}
		
		return new ErrorResult(this.isDataRight(jobAdvertisement).getMessage());
		
	}
	
	private Result isDataRight(JobAdvertisement jobAdvertisement) {
		if (jobAdvertisement.getApplicationDeadline() == null) {
			return new ErrorResult("Son başvuru tarihi boş bırakılamaz.");
		}else if (jobAdvertisement.getDescription().isBlank() || jobAdvertisement.getDescription().equals(null) ) {
			return new ErrorResult("Açıklama bölümü boş bırakılamaz.");
		}else if (jobAdvertisement.getMinSalary() == null || jobAdvertisement.getMinSalary()<= 0) {
			return new ErrorResult("Geçersiz değer girdiniz.");
		}else if (jobAdvertisement.getMaxSalary() == null || jobAdvertisement.getMaxSalary() <= 0) {
			return new ErrorResult("Geçersiz değer girdiniz.");
		}else if (!this.minSalaryEqualsMaxSalaryChecker(jobAdvertisement.getMinSalary(), jobAdvertisement.getMaxSalary())) {
			return new ErrorResult("Minimum ve maksimum değer eşit girilemez.");
		}else if (!this.maxSalaryLessThanMinSalaryChecker(jobAdvertisement.getMinSalary(), jobAdvertisement.getMaxSalary())) {
			return new ErrorResult("Maksimum değer minumum değerden küçük girilemez.");
		}else if (!this.openJobPositionChecker(jobAdvertisement.getNumberOfOpenPostion())) {
			return new ErrorResult("Açık iş pozisyonu sayısı 1'den küçük olamaz.");
		}else if (jobAdvertisement.getApplicationDeadline() == null) {
			return new ErrorResult("Son başvuru tarihi boş bırakılamaz.");
		}else if (!this.isCityExist(jobAdvertisement.getCity())) {
			return new ErrorResult("Geçersiz şehir girdiniz.");
		}else if (!this.isEmployerExist(jobAdvertisement.getEmployer())) {
			return new ErrorResult("Geçersiz işveren girdiniz.");
		}
		
		return new SuccessResult();
		
	}
	
	
	private boolean isCityExist(City city) {
		var referenceCity = this.cityDao.existsById(city.getId());
		
		if (referenceCity) {
			 return true;

			}
				return false;
	}
	
	private boolean isEmployerExist(Employer employer) {
		
		var referenceEmployer = this.employerDao.existsById(employer.getId());
		
		if (referenceEmployer) {
		 return true;

		}
			return false;
	}
	
	
	private boolean minSalaryEqualsMaxSalaryChecker(Double minSalary , Double maxSalary) {
		if (minSalary.equals(maxSalary)) {
			return false;
		}else {
		return true;
		}
	}
	
	private boolean maxSalaryLessThanMinSalaryChecker(Double minSalary , Double maxSalary) {
		if (minSalary > maxSalary) {
			return false;
		}
		return true;
	}
	
	private boolean openJobPositionChecker(int openJobPositionNumber) {
		if (openJobPositionNumber < 1 ) {
			return false;
		}
		return true;
	}
	
	
}
