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
import kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import kodlamaio.hrms.dataAccess.abstracts.EmployerUpdateDao;
import kodlamaio.hrms.dataAccess.abstracts.JobAdvertisementsDao;
import kodlamaio.hrms.entities.concretes.Employee;
import kodlamaio.hrms.entities.concretes.Employer;
import kodlamaio.hrms.entities.concretes.EmployerUpdate;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;

@Service
public class EmployeeManager implements EmployeeService{

	private EmployeeDao employeeDao;
	private JobAdvertisementsDao jobAdvertisementDao;
	private EmployerUpdateDao employerUpdateDao;
	private EmployerDao employerDao;
	
	@Autowired
	public EmployeeManager(EmployeeDao employeeDao , 
			JobAdvertisementsDao jobAdvertisementDao , 
			EmployerUpdateDao employerUpdateDao ,
			EmployerDao employerDao) {
		super();
		this.employeeDao = employeeDao;
		this.jobAdvertisementDao = jobAdvertisementDao;
		this.employerUpdateDao = employerUpdateDao;
		this.employerDao = employerDao;
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

	@Override
	public Result update(Employee employee) {
		Employee employeeForUpdate = this.employeeDao.getOne(employee.getId());
		employeeForUpdate = employee;
		this.employeeDao.save(employeeForUpdate);
		return new SuccessResult("Employee Updated");
	}

	@Override
	public Result confirmEmployerUptade(int employerId) {
		EmployerUpdate updateForApprove = new EmployerUpdate();
		
		updateForApprove = this.employerUpdateDao.findByIsApprovedFalseAndEmployer_Id(employerId);
		
		updateForApprove.setApproved(true);
		
		this.employerUpdateDao.save(updateForApprove);
		
		Employer employerForUpdate = this.employerDao.getOne(employerId);
		
		employerForUpdate.setEmail(updateForApprove.getEmployerUpdateDto().getEmail());
		employerForUpdate.setPassword(updateForApprove.getEmployerUpdateDto().getPassword());
		employerForUpdate.setCompanyName(updateForApprove.getEmployerUpdateDto().getCompanyName());
		employerForUpdate.setPhoneNumber(updateForApprove.getEmployerUpdateDto().getPhoneNumber());
		employerForUpdate.setWebAddress(updateForApprove.getEmployerUpdateDto().getWebAddress());
		
		this.employerDao.save(employerForUpdate);
		
		return new SuccessResult("Güncelleme onaylandı.");
				
	}

	@Override
	public DataResult<List<EmployerUpdate>> getUnapprovedUpdateRequests() {
		return new SuccessDataResult<List<EmployerUpdate>>(this.employerUpdateDao.findByIsApprovedFalse());
	}

	@Override
	public DataResult<Employee> getById(int employerId) {
		return new SuccessDataResult<Employee>(this.employeeDao.findById(employerId).get());
	}

	@Override
	public Result unapproveEmployerUpdate(int employerId) {
		EmployerUpdate employerUpdate = this.employerUpdateDao.findByIsApprovedFalseAndEmployer_Id(employerId);
		this.employerUpdateDao.delete(employerUpdate);
		return new SuccessResult("Güncelleme reddedildi.");
	}

	

}
