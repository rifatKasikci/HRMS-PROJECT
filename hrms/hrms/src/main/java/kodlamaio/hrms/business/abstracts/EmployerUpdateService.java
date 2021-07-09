package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.EmployerUpdate;

public interface EmployerUpdateService {

	DataResult<List<EmployerUpdate>> getAll();
	
	Result add(EmployerUpdate employerUpdate);
}
