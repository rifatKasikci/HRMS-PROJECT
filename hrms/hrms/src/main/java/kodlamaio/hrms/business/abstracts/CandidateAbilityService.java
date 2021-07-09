package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.CandidateAbility;

public interface CandidateAbilityService {

	Result add(CandidateAbility candidateAbility);
	
	DataResult<List<CandidateAbility>> getAll();
}
