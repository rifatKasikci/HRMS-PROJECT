package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.EmployerUpdate;

public interface EmployerUpdateService {

	DataResult<List<EmployerUpdate>> getAll();
	
	DataResult<List<EmployerUpdate>> getByEmployerId(int employerId);
	
	DataResult<EmployerUpdate> getUnapprovedUpdateByEmployerId(int employerId);
	
	DataResult<EmployerUpdate> getById(int employerUpdateId);
	
	Result add(EmployerUpdate employerUpdate);
}
