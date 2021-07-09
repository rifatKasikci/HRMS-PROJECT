package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Employee;
import kodlamaio.hrms.entities.concretes.EmployerUpdate;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;

public interface EmployeeService {

	DataResult<List<Employee>> getAll();
	
	DataResult<List<JobAdvertisement>> getJobAdvertisementsInactive();
	
	DataResult<List<EmployerUpdate>> getUnapprovedUpdateRequests();
	

	DataResult<Employee> getById(int employerId);
	
	Result confirmJobAdvertisement(int jobAdvertisementId);
	
	Result confirmEmployerUptade(int employerId);
	
	Result unapproveEmployerUpdate(int employerId);

	Result confirmJobAdvertisement(int jobAdvertisementId);
	
	Result confirmEmployerUptade(int employerId);


	Result add(Employee employee);
	
	Result delete(int jobAdvertisementId);
	
	Result update(Employee employee);
}
