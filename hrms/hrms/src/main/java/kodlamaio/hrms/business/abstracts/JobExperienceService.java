package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobExperience;
import kodlamaio.hrms.entities.dtos.JobExperienceDto;
import kodlamaio.hrms.entities.dtos.updateDtos.JobExperienceUpdateDto;

public interface JobExperienceService {

	Result add(JobExperienceDto jobExperienceDto);
	
	Result update(JobExperienceUpdateDto jobExperienceUpdateDto);
	
	Result delete(int jobExperienceId);
	
	DataResult<List<JobExperience>> getAll();
	
	DataResult<List<JobExperience>> getAllByOrderByEndingDateDesc();
	
	DataResult<List<JobExperience>> getAllByCandidateIdOrderByEndingDateDesc(int candidateId);
	
	DataResult<JobExperience> getById(int id);
	

}
