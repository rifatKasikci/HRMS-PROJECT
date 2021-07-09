package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EmployerUpdateService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.EmployerUpdateDao;
import kodlamaio.hrms.entities.concretes.EmployerUpdate;

@Service
public class EmployerUpdateManager implements EmployerUpdateService{

	private EmployerUpdateDao employerUpdateDao;
	
	@Autowired
	public EmployerUpdateManager(EmployerUpdateDao employerUpdateDao) {
		super();
		this.employerUpdateDao = employerUpdateDao;
	}

	@Override
	public DataResult<List<EmployerUpdate>> getAll() {
		return new SuccessDataResult<List<EmployerUpdate>>(this.employerUpdateDao.findAll());
	}

	@Override
	public Result add(EmployerUpdate employerUpdate) {
		this.employerUpdateDao.save(employerUpdate);
		return new SuccessResult();
	}

	@Override
	public DataResult<List<EmployerUpdate>> getByEmployerId(int employerId) {
		return new SuccessDataResult<List<EmployerUpdate>>(this.employerUpdateDao.findByEmployer_Id(employerId));
	}

	@Override
	public DataResult<EmployerUpdate> getUnapprovedUpdateByEmployerId(int employerId) {
		return new SuccessDataResult<EmployerUpdate>(this.employerUpdateDao.findByIsApprovedFalseAndEmployer_Id(employerId));
	}

	@Override
	public DataResult<EmployerUpdate> getById(int employerUpdateId) {
		 return new SuccessDataResult<EmployerUpdate>(this.employerUpdateDao.findById(employerUpdateId).get());
	}

	

}
