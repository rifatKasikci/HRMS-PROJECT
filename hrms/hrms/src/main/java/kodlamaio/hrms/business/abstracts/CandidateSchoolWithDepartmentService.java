package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.CandidateSchoolWithDepartment;
import kodlamaio.hrms.entities.dtos.CandidateSchoolWithDepartmentDto;
import kodlamaio.hrms.entities.dtos.updateDtos.CandidateSchoolWithDepartmentUpdateDto;

public interface CandidateSchoolWithDepartmentService {

	
	Result add(CandidateSchoolWithDepartment candidateSchoolWithDepartment);
	
	DataResult<List<CandidateSchoolWithDepartment>> getAll();
	
	DataResult<List<CandidateSchoolWithDepartment>> getAllByCandidateIdOrderByEndingDateDesc(int candidateId);
	
	DataResult<List<CandidateSchoolWithDepartment>> getAllByOrderByEndingDateDesc();
	
	DataResult<CandidateSchoolWithDepartment> getById(int id);
	
	Result add(CandidateSchoolWithDepartmentDto candidateSchoolWithDepartmentDto);
	
	Result update(CandidateSchoolWithDepartmentUpdateDto candidateSchoolWithDepartmentDto);
	
	Result delete(int candidateSchoolWithDepartmentId);

}
