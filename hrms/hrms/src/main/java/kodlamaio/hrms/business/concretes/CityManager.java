package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CityService;
import kodlamaio.hrms.business.constants.Messages;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CityDao;
import kodlamaio.hrms.entities.concretes.City;

@Service
public class CityManager implements CityService{

	private CityDao cityDao;
	
	@Autowired
	public CityManager(CityDao cityDao) {
		this.cityDao = cityDao;
	}
	
	@Override
	public DataResult<List<City>> getAll() {
		return new SuccessDataResult<List<City>>(this.cityDao.findAll() , Messages.citiesListed);
	}

	@Override
	public Result add(City city) {
		if (this.isDataRightChecker(city).isSuccess()) {
			this.cityDao.save(city);
		return new SuccessResult(Messages.cityAdded);
		}
		return isDataRightChecker(city);
		
	}
	
	private Result isDataRightChecker(City city) {
		if (city.getCityName().isBlank() || city.getCityName().equals(null)  ) {
			return new ErrorResult("Şehir ismi boş bırakılamaz.");
		}
		
		return new SuccessResult();
	}
}
