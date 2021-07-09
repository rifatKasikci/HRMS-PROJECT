package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CandidateSchoolWithDepartmentService;
import kodlamaio.hrms.business.constants.Messages;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CandidateDao;
import kodlamaio.hrms.dataAccess.abstracts.CandidateSchoolWithDepartmentDao;
import kodlamaio.hrms.dataAccess.abstracts.SchoolAndDepartmentDao;
import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.entities.concretes.CandidateSchoolWithDepartment;
import kodlamaio.hrms.entities.concretes.SchoolAndDepartment;
import kodlamaio.hrms.entities.dtos.CandidateSchoolWithDepartmentDto;
import kodlamaio.hrms.entities.dtos.updateDtos.CandidateSchoolWithDepartmentUpdateDto;

@Service
public class CandidateSchoolWithDepartmentManager implements CandidateSchoolWithDepartmentService{

	private CandidateSchoolWithDepartmentDao candidateSchoolWithDepartmentDao;
	private CandidateDao candidateDao;
	private SchoolAndDepartmentDao schoolAndDepartmentDao;
	
	@Autowired
	public CandidateSchoolWithDepartmentManager(CandidateSchoolWithDepartmentDao candidateSchoolWithDepartmentDao , CandidateDao candidateDao , SchoolAndDepartmentDao schoolAndDepartmentDao) {
		super();
		this.candidateSchoolWithDepartmentDao = candidateSchoolWithDepartmentDao;
		this.candidateDao = candidateDao;
		this.schoolAndDepartmentDao = schoolAndDepartmentDao;
	}

	@Override
	public Result add(CandidateSchoolWithDepartment candidateSchoolWithDepartment) {
		this.candidateSchoolWithDepartmentDao.save(candidateSchoolWithDepartment);
		return new SuccessResult(Messages.candidateSchoolAdded);
	}

	@Override
	public DataResult<List<CandidateSchoolWithDepartment>> getAll() {
		return new SuccessDataResult<List<CandidateSchoolWithDepartment>>(this.candidateSchoolWithDepartmentDao.findAll() , Messages.candidateSchoolsListed);
	}

	@Override
	public DataResult<List<CandidateSchoolWithDepartment>> getAllByCandidateIdOrderByEndingDateDesc(int candidateId) {
		return new SuccessDataResult<List<CandidateSchoolWithDepartment>>(this.candidateSchoolWithDepartmentDao.getAllByCandidateIdOrderByEndingDateDesc(candidateId) , Messages.candidateSchoolsListed);
	}

	@Override
	public DataResult<List<CandidateSchoolWithDepartment>> getAllByOrderByEndingDateDesc() {
		return new SuccessDataResult<List<CandidateSchoolWithDepartment>>(this.candidateSchoolWithDepartmentDao.getAllByOrderByEndingDateDesc() , Messages.candidateSchoolsListed);
	}

	@Override
	public Result add(CandidateSchoolWithDepartmentDto candidateSchoolWithDepartmentDto) {
		Candidate candidate = this.candidateDao.getOne(candidateSchoolWithDepartmentDto.getCandidateId());
		SchoolAndDepartment schoolAndDepartment = this.schoolAndDepartmentDao.getOne(candidateSchoolWithDepartmentDto.getSchoolAndDepartmentId());
		CandidateSchoolWithDepartment candidateSchoolWithDepartmentForSave = new CandidateSchoolWithDepartment();
		candidateSchoolWithDepartmentForSave.setCandidate(candidate);
		candidateSchoolWithDepartmentForSave.setSchoolAndDepartment(schoolAndDepartment);
		candidateSchoolWithDepartmentForSave.setStartingDate(candidateSchoolWithDepartmentDto.getStartingDate());
		candidateSchoolWithDepartmentForSave.setEndingDate(candidateSchoolWithDepartmentDto.getEndingDate());
		this.candidateSchoolWithDepartmentDao.save(candidateSchoolWithDepartmentForSave);
		return new SuccessResult("Okul bilgisi eklendi.");
	}

	@Override
	public Result update(CandidateSchoolWithDepartmentUpdateDto candidateSchoolWithDepartmentUpdateDto) {
		CandidateSchoolWithDepartment candidateSchoolWithDepartment = this.candidateSchoolWithDepartmentDao.getOne(candidateSchoolWithDepartmentUpdateDto.getId());
		SchoolAndDepartment schoolAndDepartment = this.schoolAndDepartmentDao.getOne(candidateSchoolWithDepartmentUpdateDto.getSchoolAndDepartmentId());
		candidateSchoolWithDepartment.setSchoolAndDepartment(schoolAndDepartment);
		candidateSchoolWithDepartment.setStartingDate(candidateSchoolWithDepartmentUpdateDto.getStartingDate());
		candidateSchoolWithDepartment.setEndingDate(candidateSchoolWithDepartmentUpdateDto.getEndingDate());
		
		this.candidateSchoolWithDepartmentDao.save(candidateSchoolWithDepartment);
		return new SuccessResult("Okul bilgisi güncellendi");
		
	}

	@Override
	public Result delete(int candidateSchoolWithDepartmentId) {
		CandidateSchoolWithDepartment candidateSchoolWithDepartment = this.candidateSchoolWithDepartmentDao.getOne(candidateSchoolWithDepartmentId);
		this.candidateSchoolWithDepartmentDao.delete(candidateSchoolWithDepartment);
		return new SuccessResult("Okul bilgisi silindi");
		
	}

	@Override
	public DataResult<CandidateSchoolWithDepartment> getById(int id) {
		return new SuccessDataResult<CandidateSchoolWithDepartment>(this.candidateSchoolWithDepartmentDao.findById(id).get());
	}

}
