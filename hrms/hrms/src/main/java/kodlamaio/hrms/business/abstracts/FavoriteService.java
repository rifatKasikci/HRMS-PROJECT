package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Favorite;
import kodlamaio.hrms.entities.dtos.FavoriteDto;

public interface FavoriteService {

	DataResult<List<Favorite>> getAll();
	
	DataResult<List<Favorite>> getAllByCandidateId(int candidateId);
	
	DataResult<Favorite> getByCandidateIdAndJobAdvertisementId(int candidateId , int jobAdvertisementId);
	
	Result add(int candidateId , int jobAdvertisementId);
	
	Result delete(int candidateId , int jobAdvertisementId);
}
