package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CandidateAbilityService;
import kodlamaio.hrms.business.constants.Messages;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.AbilityDao;
import kodlamaio.hrms.dataAccess.abstracts.CandidateAbilityDao;
import kodlamaio.hrms.dataAccess.abstracts.CandidateDao;
import kodlamaio.hrms.entities.concretes.Ability;
import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.entities.concretes.CandidateAbility;
import kodlamaio.hrms.entities.concretes.JobExperience;
import kodlamaio.hrms.entities.dtos.CandidateAbilityDto;
import kodlamaio.hrms.entities.dtos.updateDtos.CandidateAbilityUpdateDto;

@Service
public class CandidateAbilityManager implements CandidateAbilityService{

	private CandidateAbilityDao candidateAbilityDao;
	private CandidateDao candidateDao;
	private AbilityDao abilityDao;
	
	@Autowired
	public CandidateAbilityManager(CandidateAbilityDao candidateAbilityDao ,
			CandidateDao candidateDao,
			AbilityDao abilityDao) {
		super();
		this.candidateAbilityDao = candidateAbilityDao;
		this.abilityDao = abilityDao;
		this.candidateDao = candidateDao;
	}

	@Override
	public Result add(CandidateAbilityDto candidateAbilityDto) {
		Candidate candidate = this.candidateDao.getOne(candidateAbilityDto.getCandidateId());
		Ability ability = this.abilityDao.getOne(candidateAbilityDto.getAbilityId());
		
		CandidateAbility candidateAbilityForSave = new CandidateAbility();
		candidateAbilityForSave.setCandidate(candidate);
		candidateAbilityForSave.setAbility(ability);
		
		this.candidateAbilityDao.save(candidateAbilityForSave);
		return new SuccessResult(Messages.abilityAdded);
	}

	@Override
	public DataResult<List<CandidateAbility>> getAll() {
		return new SuccessDataResult<List<CandidateAbility>>(this.candidateAbilityDao.findAll() , Messages.abilitiesListed);
	}

	@Override
	public DataResult<List<CandidateAbility>> getAllByCandidateId(int candidateId) {
		return new SuccessDataResult<List<CandidateAbility>>(this.candidateAbilityDao.getByCandidate_Id(candidateId) , Messages.abilitiesListed);
	}

	@Override
	public Result update(CandidateAbilityUpdateDto candidateAbilityUpdateDto) {
		CandidateAbility candidateAbility = this.candidateAbilityDao.getOne(candidateAbilityUpdateDto.getId());
		Ability ability = this.abilityDao.getOne(candidateAbilityUpdateDto.getAbilityId());
		
		candidateAbility.setAbility(ability);
		
		this.candidateAbilityDao.save(candidateAbility);
		return new SuccessResult("Yetenek bilgisi güncellendi");
	}

	@Override
	public Result delete(int candidateAbilityId) {
		CandidateAbility candidateAbility = this.candidateAbilityDao.getOne(candidateAbilityId);
		this.candidateAbilityDao.delete(candidateAbility);
		return new SuccessResult("Yetenek bilgisi silindi.");
	}

	@Override
	public DataResult<CandidateAbility> getById(int candidateAbilityId) {
		return new SuccessDataResult<CandidateAbility>(this.candidateAbilityDao.findById(candidateAbilityId).get());

	}

	

}
