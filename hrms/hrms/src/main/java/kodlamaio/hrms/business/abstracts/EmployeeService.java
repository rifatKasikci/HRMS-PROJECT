package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Employee;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;

public interface EmployeeService {

	DataResult<List<Employee>> getAll();
	
	DataResult<List<JobAdvertisement>> getJobAdvertisementsInactive();
	
	Result confirmJobAdvertisement(int jobAdvertisementId);

	Result add(Employee employee);
	
	Result delete(int jobAdvertisementId);
}
