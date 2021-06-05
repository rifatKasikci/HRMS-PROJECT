package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.SchoolAndDepartmentService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.SchoolAndDepartmentDao;
import kodlamaio.hrms.entities.concretes.SchoolAndDepartment;

@Service
public class SchoolAndDepartmentManager implements SchoolAndDepartmentService{

	private SchoolAndDepartmentDao schoolAndDepartmentDao;

	@Autowired
	public SchoolAndDepartmentManager(SchoolAndDepartmentDao schoolAndDepartmentDao) {
		super();
		this.schoolAndDepartmentDao = schoolAndDepartmentDao;
	}

	@Override
	public Result add(SchoolAndDepartment schoolAndDepartment) {
		this.schoolAndDepartmentDao.save(schoolAndDepartment);
		return new SuccessResult();
	}

	@Override
	public DataResult<List<SchoolAndDepartment>> getAll() {
		return new SuccessDataResult<List<SchoolAndDepartment>>(this.schoolAndDepartmentDao.findAll());
	}
	
	
}
