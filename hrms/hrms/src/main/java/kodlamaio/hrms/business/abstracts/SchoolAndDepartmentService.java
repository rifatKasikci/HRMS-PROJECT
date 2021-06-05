package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.SchoolAndDepartment;

public interface SchoolAndDepartmentService {
	
	Result add(SchoolAndDepartment schoolAndDepartment);
	
	DataResult<List<SchoolAndDepartment>> getAll();

}
