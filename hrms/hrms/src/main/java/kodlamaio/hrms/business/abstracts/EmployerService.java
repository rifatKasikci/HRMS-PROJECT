package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Employer;

public interface EmployerService {

	DataResult<List<Employer>> getAll();
	
	DataResult<Employer> getById(int id);
	
	Result setAdvertisementInactive(int advertisementId);
	
	Result add(Employer employer , String passwordRepeat);
	
	Result update(Employer employer);
}
