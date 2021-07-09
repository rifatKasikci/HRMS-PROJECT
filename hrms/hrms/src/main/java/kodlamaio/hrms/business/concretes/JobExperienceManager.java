package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobExperienceService;
import kodlamaio.hrms.business.constants.Messages;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CandidateDao;
import kodlamaio.hrms.dataAccess.abstracts.JobExperienceDao;
import kodlamaio.hrms.dataAccess.abstracts.JobPositionDao;
import kodlamaio.hrms.dataAccess.abstracts.WorkplaceDao;
import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.entities.concretes.JobExperience;
import kodlamaio.hrms.entities.concretes.JobPosition;
import kodlamaio.hrms.entities.concretes.Workplace;
import kodlamaio.hrms.entities.dtos.JobExperienceDto;
import kodlamaio.hrms.entities.dtos.updateDtos.JobExperienceUpdateDto;

@Service
public class JobExperienceManager implements JobExperienceService{

	private JobExperienceDao jobExperienceDao;
	private CandidateDao candidateDao;
	private JobPositionDao jobPositionDao;
	private WorkplaceDao workplaceDao;
	
	
	@Autowired
	public JobExperienceManager(JobExperienceDao jobExperienceDao , 
			CandidateDao candidateDao , 
			JobPositionDao jobPositionDao , 
			WorkplaceDao workplaceDao) {
		super();
		this.jobExperienceDao = jobExperienceDao;
		this.candidateDao = candidateDao;
		this.jobPositionDao = jobPositionDao;
		this.workplaceDao = workplaceDao;
	}

	@Override
	public Result add(JobExperienceDto jobExperienceDto) {
		Candidate candidate = this.candidateDao.getOne(jobExperienceDto.getCandidateId());
		JobPosition jobPosition = this.jobPositionDao.getOne(jobExperienceDto.getJobPositionId());
		Workplace workplace = this.workplaceDao.getOne(jobExperienceDto.getWorkplaceId());
		
		JobExperience jobExperienceForSave = new JobExperience();
		jobExperienceForSave.setCandidate(candidate);
		jobExperienceForSave.setJobPosition(jobPosition);
		jobExperienceForSave.setWorkplace(workplace);
		jobExperienceForSave.setStartingDate(jobExperienceDto.getStartingDate());
		jobExperienceForSave.setEndingDate(jobExperienceDto.getEndingDate());
		
		this.jobExperienceDao.save(jobExperienceForSave);
		return new SuccessResult(Messages.jobExperienceAdded);
		
	}

	@Override
	public DataResult<List<JobExperience>> getAll() {
		return new SuccessDataResult<List<JobExperience>>(this.jobExperienceDao.findAll() , Messages.jobExperiencesListed);
	}

	@Override
	public DataResult<List<JobExperience>> getAllByOrderByEndingDateDesc() {
		return new SuccessDataResult<List<JobExperience>>(this.jobExperienceDao.getAllByOrderByEndingDateDesc() , Messages.jobExperiencesListed);
	}

	@Override
	public DataResult<List<JobExperience>> getAllByCandidateIdOrderByEndingDateDesc(int candidateId) {
		return new SuccessDataResult<List<JobExperience>>(this.jobExperienceDao.getAllByCandidateIdOrderByEndingDateDesc(candidateId) , Messages.jobExperiencesListed);
	}

	@Override
	public Result update(JobExperienceUpdateDto jobExperienceUpdateDto) {
		JobExperience jobExperienceForUpdate = this.jobExperienceDao.getOne(jobExperienceUpdateDto.getId());
		JobPosition jobPosition = this.jobPositionDao.getOne(jobExperienceUpdateDto.getJobPositionId());
		Workplace workplace = this.workplaceDao.getOne(jobExperienceUpdateDto.getWorkplaceId());
		
		jobExperienceForUpdate.setJobPosition(jobPosition);
		jobExperienceForUpdate.setWorkplace(workplace);
		jobExperienceForUpdate.setStartingDate(jobExperienceUpdateDto.getStartingDate());
		jobExperienceForUpdate.setEndingDate(jobExperienceUpdateDto.getEndingDate());
		
		this.jobExperienceDao.save(jobExperienceForUpdate);
		return new SuccessResult("İş tecrübesi güncellendi.");
	}

	@Override
	public Result delete(int jobExperienceId) {
		JobExperience jobExperience = this.jobExperienceDao.getOne(jobExperienceId);
		this.jobExperienceDao.delete(jobExperience);
		return new SuccessResult("İş tecrübesi silindi.");
	}

	@Override
	public DataResult<JobExperience> getById(int id) {
		return new SuccessDataResult<JobExperience>(this.jobExperienceDao.findById(id).get());
	}

}
