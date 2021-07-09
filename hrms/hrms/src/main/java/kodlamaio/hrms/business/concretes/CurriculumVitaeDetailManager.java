package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CurriculumVitaeDetailService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.dataAccess.abstracts.CandidateAbilityDao;
import kodlamaio.hrms.dataAccess.abstracts.CandidateImageDao;
import kodlamaio.hrms.dataAccess.abstracts.CandidateSchoolWithDepartmentDao;
import kodlamaio.hrms.dataAccess.abstracts.JobExperienceDao;
import kodlamaio.hrms.dataAccess.abstracts.LanguageCandidateDao;
import kodlamaio.hrms.dataAccess.abstracts.SocialMediaAccountDao;
import kodlamaio.hrms.entities.concretes.CandidateAbility;
import kodlamaio.hrms.entities.concretes.CandidateImage;
import kodlamaio.hrms.entities.concretes.CandidateSchoolWithDepartment;
import kodlamaio.hrms.entities.concretes.JobExperience;
import kodlamaio.hrms.entities.concretes.LanguageCandidate;
import kodlamaio.hrms.entities.concretes.SocialMediaAccount;

@Service
public class CurriculumVitaeDetailManager implements CurriculumVitaeDetailService{

	private CandidateSchoolWithDepartmentDao candidateSchoolWithDepartmentDao;
	private LanguageCandidateDao languageCandidateDao;
	private CandidateAbilityDao candidateAbilityDao;
	private CandidateImageDao candidateImageDao;
	private JobExperienceDao jobExperienceDao;
	private SocialMediaAccountDao socialMediaAccountDao;
	
	
	@Autowired
	public CurriculumVitaeDetailManager(CandidateSchoolWithDepartmentDao candidateSchoolWithDepartmentDao,
			LanguageCandidateDao languageCandidateDao, CandidateAbilityDao candidateAbilityDao,
			CandidateImageDao candidateImageDao, JobExperienceDao jobExperienceDao,
			SocialMediaAccountDao socialMediaAccountDao) {
		super();
		this.candidateSchoolWithDepartmentDao = candidateSchoolWithDepartmentDao;
		this.languageCandidateDao = languageCandidateDao;
		this.candidateAbilityDao = candidateAbilityDao;
		this.candidateImageDao = candidateImageDao;
		this.jobExperienceDao = jobExperienceDao;
		this.socialMediaAccountDao = socialMediaAccountDao;
	}

	@Override
	public DataResult<List<CandidateSchoolWithDepartment>> getAllCandidateSchoolWithDepartmentByCandidateId(
			int candidateId) {
		return new SuccessDataResult<List<CandidateSchoolWithDepartment>>(this.candidateSchoolWithDepartmentDao.getAllByCandidate_Id(candidateId));
	}

	@Override
	public DataResult<List<LanguageCandidate>> getAllLanguageCandidateByCandidateId(int candidateId) {
		return new SuccessDataResult<List<LanguageCandidate>>(this.languageCandidateDao.getByCandidate_Id(candidateId));
	}

	@Override
	public DataResult<List<CandidateAbility>> getAllCandidateAbilityByCandidateId(int candidateId) {
		return new SuccessDataResult<List<CandidateAbility>>(this.candidateAbilityDao.getByCandidate_Id(candidateId));
	}

	@Override
	public DataResult<CandidateImage> getCandidateImageByCandidateId(int candidateId) {
		return new SuccessDataResult<CandidateImage>(this.candidateImageDao.getByCandidate_Id(candidateId));
	}

	@Override
	public DataResult<List<JobExperience>> getAllJobExperienceByCandidateId(int candidateId) {
		return new SuccessDataResult<List<JobExperience>>(this.jobExperienceDao.getByCandidate_Id(candidateId));
	}

	@Override
	public DataResult<List<SocialMediaAccount>> getAllSocialMediaAccountByCandidateId(int candidateId) {
		return new SuccessDataResult<List<SocialMediaAccount>>(this.socialMediaAccountDao.getByCandidate_Id(candidateId));
	}

}
