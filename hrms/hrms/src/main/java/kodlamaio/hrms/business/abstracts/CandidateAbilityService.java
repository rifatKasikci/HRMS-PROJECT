package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.CandidateAbility;
import kodlamaio.hrms.entities.dtos.CandidateAbilityDto;
import kodlamaio.hrms.entities.dtos.updateDtos.CandidateAbilityUpdateDto;

public interface CandidateAbilityService {

	Result add(CandidateAbilityDto candidateAbilityDto);
	
	Result update(CandidateAbilityUpdateDto candidateAbilityUpdateDto);
	
	Result delete(int candidateAbilityId);
	
	DataResult<List<CandidateAbility>> getAll();
	
	DataResult<List<CandidateAbility>> getAllByCandidateId(int candidateId);
	
	DataResult<CandidateAbility> getById(int candidateAbilityId);
}
