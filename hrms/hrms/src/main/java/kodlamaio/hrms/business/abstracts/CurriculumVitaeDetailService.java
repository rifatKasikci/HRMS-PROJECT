package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.entities.concretes.CandidateAbility;
import kodlamaio.hrms.entities.concretes.CandidateImage;
import kodlamaio.hrms.entities.concretes.CandidateSchoolWithDepartment;
import kodlamaio.hrms.entities.concretes.JobExperience;
import kodlamaio.hrms.entities.concretes.LanguageCandidate;
import kodlamaio.hrms.entities.concretes.SocialMediaAccount;

public interface CurriculumVitaeDetailService {

	DataResult<List<CandidateSchoolWithDepartment>> getAllCandidateSchoolWithDepartmentByCandidateId(int candidateId);
	
	DataResult<List<LanguageCandidate>> getAllLanguageCandidateByCandidateId(int candidateId);
	
	DataResult<List<CandidateAbility>> getAllCandidateAbilityByCandidateId(int candidateId);
	
	DataResult<CandidateImage> getCandidateImageByCandidateId(int candidateId);
	
	DataResult<List<JobExperience>> getAllJobExperienceByCandidateId(int candidateId);

	DataResult<List<SocialMediaAccount>> getAllSocialMediaAccountByCandidateId(int candidateId);

	


	

	
	
}
