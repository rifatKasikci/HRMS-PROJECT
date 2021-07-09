package kodlamaio.hrms.business.concretes;

import java.io.Console;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kodlamaio.hrms.business.abstracts.CurriculumVitaeDetailService;
import kodlamaio.hrms.business.abstracts.CurriculumVitaeService;
import kodlamaio.hrms.business.constants.Messages;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CurriculumVitaeDao;
import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.entities.concretes.CandidateAbility;
import kodlamaio.hrms.entities.concretes.CandidateImage;
import kodlamaio.hrms.entities.concretes.CandidateSchoolWithDepartment;
import kodlamaio.hrms.entities.concretes.CurriculumVitae;
import kodlamaio.hrms.entities.concretes.JobExperience;
import kodlamaio.hrms.entities.concretes.LanguageCandidate;
import kodlamaio.hrms.entities.concretes.SocialMediaAccount;
import kodlamaio.hrms.entities.dtos.CurriculumVitaeDetailDto;
import kodlamaio.hrms.entities.dtos.CurriculumVitaeUpdateDto;

@Service
public class CurriculumVitaeManager implements CurriculumVitaeService {

	private CurriculumVitaeDao curriculumVitaeDao;
	private CurriculumVitaeDetailService curriculumVitaeDetailService;
	
	@Autowired
	public CurriculumVitaeManager(CurriculumVitaeDao curriculumVitaeDao,
			CurriculumVitaeDetailService curriculumVitaeDetailService) {
		super();
		this.curriculumVitaeDao = curriculumVitaeDao;
		this.curriculumVitaeDetailService = curriculumVitaeDetailService;
	}

	@Override
	public Result add(CurriculumVitae curriculumVitae) {
		this.curriculumVitaeDao.save(curriculumVitae);
		return new SuccessResult(Messages.curriculumVitaeAdded);
	}

	@Override
	public DataResult<CurriculumVitaeDetailDto> getCurriculumVitaeByCandidateId(int candidateId) {
		Candidate candidate = new Candidate();
		candidate.setId(candidateId);
		CurriculumVitaeDetailDto curriculumVitaeDetailDto = new CurriculumVitaeDetailDto(
				candidate,
				this.getAllSchoolByCandidateId(candidateId).getData(),
				this.getAllJobExperienceByCandidateId(candidateId).getData(),
				this.getAllLanguageCandidateByCandidateId(candidateId).getData(),
				this.getAllCandidateAbilityByCandidateId(candidateId).getData(),
				this.getAllSocialMediaAccountByCandidateId(candidateId).getData(),
				this.getCandidateImageByCandidateId(candidateId).getData(),
				this.curriculumVitaeDao.getByCandidate_Id(candidateId)
				);
		
		return new SuccessDataResult<CurriculumVitaeDetailDto>(curriculumVitaeDetailDto , Messages.curriculumVitaesListed);
	}

	@Override

	public Result update(CurriculumVitaeUpdateDto curriculumVitaeUpdateDto) {
		CurriculumVitae curriculumVitaeForUpdate = this.curriculumVitaeDao.getByCandidate_Id(curriculumVitaeUpdateDto.getCandidateId());
		curriculumVitaeForUpdate.setCoverLetter(curriculumVitaeUpdateDto.getCoverLetter());

	public Result update(CurriculumVitae curriculumVitae) {
		CurriculumVitae curriculumVitaeForUpdate = this.curriculumVitaeDao.getOne(curriculumVitae.getId());
		curriculumVitaeForUpdate = curriculumVitae;

		this.curriculumVitaeDao.save(curriculumVitaeForUpdate);
		return new SuccessResult("Cv updated");
	}


	@Override
	public DataResult<List<CandidateSchoolWithDepartment>> getAllSchoolByCandidateId(int candidateId) {
		return this.curriculumVitaeDetailService.getAllCandidateSchoolWithDepartmentByCandidateId(candidateId);
	}

	@Override
	public DataResult<List<LanguageCandidate>> getAllLanguageCandidateByCandidateId(int candidateId) {
		return this.curriculumVitaeDetailService.getAllLanguageCandidateByCandidateId(candidateId);
	}

	@Override
	public DataResult<List<CandidateAbility>> getAllCandidateAbilityByCandidateId(int candidateId) {
		return this.curriculumVitaeDetailService.getAllCandidateAbilityByCandidateId(candidateId);
	}

	@Override
	public DataResult<List<JobExperience>> getAllJobExperienceByCandidateId(int candidateId) {
		return this.curriculumVitaeDetailService.getAllJobExperienceByCandidateId(candidateId);
	}

	@Override
	public DataResult<CandidateImage> getCandidateImageByCandidateId(int candidateId) {
		return this.curriculumVitaeDetailService.getCandidateImageByCandidateId(candidateId);
	}

	@Override
	public DataResult<List<SocialMediaAccount>> getAllSocialMediaAccountByCandidateId(int candidateId) {
		return this.curriculumVitaeDetailService.getAllSocialMediaAccountByCandidateId(candidateId);
	}

	
	

}


