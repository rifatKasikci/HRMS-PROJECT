package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.CandidateAbility;
import kodlamaio.hrms.entities.concretes.CandidateImage;
import kodlamaio.hrms.entities.concretes.CandidateSchoolWithDepartment;
import kodlamaio.hrms.entities.concretes.CurriculumVitae;
import kodlamaio.hrms.entities.concretes.JobExperience;
import kodlamaio.hrms.entities.concretes.LanguageCandidate;
import kodlamaio.hrms.entities.concretes.SocialMediaAccount;
import kodlamaio.hrms.entities.dtos.CurriculumVitaeDetailDto;
import kodlamaio.hrms.entities.dtos.CurriculumVitaeUpdateDto;

public interface CurriculumVitaeService {

	Result add(CurriculumVitae curriculumVitae);
	
	Result update(CurriculumVitaeUpdateDto curriculumVitaeUpdateDto);
	
	DataResult<CurriculumVitaeDetailDto> getCurriculumVitaeByCandidateId(int candidateId);
	
	DataResult<List<CandidateSchoolWithDepartment>> getAllSchoolByCandidateId(int candidateId);
	
	DataResult<List<LanguageCandidate>> getAllLanguageCandidateByCandidateId(int candidateId);
	
	DataResult<List<CandidateAbility>> getAllCandidateAbilityByCandidateId(int candidateId);
	
	DataResult<List<JobExperience>> getAllJobExperienceByCandidateId(int candidateId);
	
	DataResult<CandidateImage> getCandidateImageByCandidateId(int candidateId);

	DataResult<List<SocialMediaAccount>> getAllSocialMediaAccountByCandidateId(int candidateId);
	
	
	
	
	
	
	
	
	
}
