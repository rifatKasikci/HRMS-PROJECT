package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.LanguageCandidate;
import kodlamaio.hrms.entities.dtos.LanguageCandidateDto;
import kodlamaio.hrms.entities.dtos.updateDtos.LanguageCandidateUpdateDto;

public interface LanguageCandidateService {

	Result add(LanguageCandidateDto languageCandidateDto);
	
	Result update(LanguageCandidateUpdateDto languageCandidateUpdateDto);

	Result delete(int languageCandidateId);
	
	DataResult<List<LanguageCandidate>> getAll();
	
	DataResult<List<LanguageCandidate>> getAllByCandidateId(int candidateId);
	
	DataResult<LanguageCandidate> getById(int id);
}
