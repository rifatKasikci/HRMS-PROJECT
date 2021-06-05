package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.CandidateSchoolWithDepartment;

public interface CandidateSchoolWithDepartmentService {

	
	Result add(CandidateSchoolWithDepartment candidateSchoolWithDepartment);
	
	DataResult<List<CandidateSchoolWithDepartment>> getAll();
	
	DataResult<List<CandidateSchoolWithDepartment>> getAllByCandidateIdOrderByEndingDateDesc(int candidateId);
	
	DataResult<List<CandidateSchoolWithDepartment>> getAllByOrderByEndingDateDesc();

}
