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
import kodlamaio.hrms.dataAccess.abstracts.CandidateAbilityDao;
import kodlamaio.hrms.entities.concretes.CandidateAbility;

@Service
public class CandidateAbilityManager implements CandidateAbilityService{

	private CandidateAbilityDao candidateAbilityDao;
	
	@Autowired
	public CandidateAbilityManager(CandidateAbilityDao candidateAbilityDao) {
		super();
		this.candidateAbilityDao = candidateAbilityDao;
	}

	@Override
	public Result add(CandidateAbility candidateAbility) {
	this.candidateAbilityDao.save(candidateAbility);
	return new SuccessResult(Messages.abilityAdded);
	}

	@Override
	public DataResult<List<CandidateAbility>> getAll() {
		return new SuccessDataResult<List<CandidateAbility>>(this.candidateAbilityDao.findAll() , Messages.abilitiesListed);
	}

	

}
