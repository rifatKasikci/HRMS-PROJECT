package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Favorite;

public interface FavoriteService {

	DataResult<List<Favorite>> getAll();
	
	Result add(Favorite favorite);
}
