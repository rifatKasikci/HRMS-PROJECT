package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.LanguageStageService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.LanguageStageDao;
import kodlamaio.hrms.entities.concretes.LanguageStage;

@Service
public class LanguageStageManager implements LanguageStageService {

	private LanguageStageDao languageStageDao;

	@Autowired
	public LanguageStageManager(LanguageStageDao languageStageDao) {
		super();
		this.languageStageDao = languageStageDao;
	}

	@Override
	public Result add(LanguageStage languageStage) {
		this.languageStageDao.save(languageStage);
		return new SuccessResult();
	}

	@Override
	public DataResult<List<LanguageStage>> getAll() {
		return new SuccessDataResult<List<LanguageStage>>(this.languageStageDao.findAll());
	}
	
	
}
