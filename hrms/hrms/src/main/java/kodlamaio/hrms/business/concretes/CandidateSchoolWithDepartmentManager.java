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
import kodlamaio.hrms.dataAccess.abstracts.CandidateSchoolWithDepartmentDao;
import kodlamaio.hrms.entities.concretes.CandidateSchoolWithDepartment;

@Service
public class CandidateSchoolWithDepartmentManager implements CandidateSchoolWithDepartmentService{

	private CandidateSchoolWithDepartmentDao candidateSchoolWithDepartmentDao;
	
	@Autowired
	public CandidateSchoolWithDepartmentManager(CandidateSchoolWithDepartmentDao candidateSchoolWithDepartmentDao) {
		super();
		this.candidateSchoolWithDepartmentDao = candidateSchoolWithDepartmentDao;
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

}
