package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.LanguageCandidateService;
import kodlamaio.hrms.business.constants.Messages;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CandidateDao;
import kodlamaio.hrms.dataAccess.abstracts.LanguageCandidateDao;
import kodlamaio.hrms.dataAccess.abstracts.LanguageDao;
import kodlamaio.hrms.dataAccess.abstracts.LanguageStageDao;
import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.entities.concretes.JobExperience;
import kodlamaio.hrms.entities.concretes.Language;
import kodlamaio.hrms.entities.concretes.LanguageCandidate;
import kodlamaio.hrms.entities.concretes.LanguageStage;
import kodlamaio.hrms.entities.dtos.LanguageCandidateDto;
import kodlamaio.hrms.entities.dtos.updateDtos.LanguageCandidateUpdateDto;

@Service
public class LanguageCandidateManager implements LanguageCandidateService{

	private LanguageCandidateDao languageCandidateDao;
	private CandidateDao candidateDao;
	private LanguageDao languageDao;
	private LanguageStageDao languageStageDao;
	
	@Autowired
	public LanguageCandidateManager(LanguageCandidateDao languageCandidateDao ,
			CandidateDao candidateDao ,
			LanguageDao languageDao ,
			LanguageStageDao languageStageDao) {
		super();
		this.languageCandidateDao = languageCandidateDao;
		this.candidateDao = candidateDao;
		this.languageDao = languageDao;
		this.languageStageDao = languageStageDao;
	}

	@Override
	public Result add(LanguageCandidateDto languageCandidateDto) {
		Candidate candidate = this.candidateDao.getOne(languageCandidateDto.getCandidateId());
		Language language = this.languageDao.getOne(languageCandidateDto.getLanguageId());
		LanguageStage languageStage = this.languageStageDao.getOne(languageCandidateDto.getLanguageStageId());
		
		LanguageCandidate languageCandidateForSave = new LanguageCandidate();
		languageCandidateForSave.setCandidate(candidate);
		languageCandidateForSave.setLanguage(language);
		languageCandidateForSave.setLanguageStage(languageStage);
		
		languageCandidateDao.save(languageCandidateForSave);
		return new SuccessResult(Messages.languageCandidateAdded);
	}

	@Override
	public DataResult<List<LanguageCandidate>> getAll() {
		return new SuccessDataResult<List<LanguageCandidate>>(this.languageCandidateDao.findAll() , Messages.languageCandidatesListed);
	}

	@Override
	public DataResult<List<LanguageCandidate>> getAllByCandidateId(int candidateId) {
		return new SuccessDataResult<List<LanguageCandidate>>(this.languageCandidateDao.getByCandidate_Id(candidateId) , Messages.languageCandidatesListed);
	}

	@Override
	public Result update(LanguageCandidateUpdateDto languageCandidateUpdateDto) {
		LanguageCandidate languageCandidate = this.languageCandidateDao.getOne(languageCandidateUpdateDto.getId());
		Language language = this.languageDao.getOne(languageCandidateUpdateDto.getLanguageId());
		LanguageStage languageStage = this.languageStageDao.getOne(languageCandidateUpdateDto.getLanguageStageId());
		
		languageCandidate.setLanguage(language);
		languageCandidate.setLanguageStage(languageStage);
		
		this.languageCandidateDao.save(languageCandidate);
		return new SuccessResult("Dil bilgisi eklendi.");
	}

	@Override
	public Result delete(int languageCandidateId) {
		LanguageCandidate languageCandidate = this.languageCandidateDao.getOne(languageCandidateId);
		this.languageCandidateDao.delete(languageCandidate);
		return new SuccessResult("Dil bilgisi silindi");
	}

	@Override
	public DataResult<LanguageCandidate> getById(int id) {
		return new SuccessDataResult<LanguageCandidate>(this.languageCandidateDao.findById(id).get());

	}

}
