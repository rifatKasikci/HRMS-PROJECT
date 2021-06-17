package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EmployeeService;
import kodlamaio.hrms.business.constants.Messages;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.EmployeeDao;
import kodlamaio.hrms.dataAccess.abstracts.JobAdvertisementsDao;
import kodlamaio.hrms.entities.concretes.Employee;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;

@Service
public class EmployeeManager implements EmployeeService{

	private EmployeeDao employeeDao;
	private JobAdvertisementsDao jobAdvertisementDao;
	
	@Autowired
	public EmployeeManager(EmployeeDao employeeDao , JobAdvertisementsDao jobAdvertisementDao) {
		super();
		this.employeeDao = employeeDao;
		this.jobAdvertisementDao = jobAdvertisementDao;
	}

	@Override
	public DataResult<List<Employee>> getAll() {
		return new SuccessDataResult<List<Employee>>(this.employeeDao.findAll(),Messages.employeesListed);
	}

	@Override
	public Result add(Employee employee) {
		employeeDao.save(employee);
		return new SuccessResult(Messages.employeeAdded);
	}

	@Override
	public DataResult<List<JobAdvertisement>> getJobAdvertisementsInactive() {
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.findByActiveFalse());
	}

	@Override
	public Result confirmJobAdvertisement(int jobAdvertisementId) {
		JobAdvertisement refereceObject = this.jobAdvertisementDao.getOne(jobAdvertisementId);
		refereceObject.setActive(true);
		this.jobAdvertisementDao.save(refereceObject);
		return new SuccessResult();
		
	}
	
	@Override
	public Result delete(int jobAdvertisementId) {
		JobAdvertisement objectForDelete = this.jobAdvertisementDao.getOne(jobAdvertisementId);
		this.jobAdvertisementDao.delete(objectForDelete);
		return new SuccessResult();
	}

}
