package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.FavoriteService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CandidateDao;
import kodlamaio.hrms.dataAccess.abstracts.FavoriteDao;
import kodlamaio.hrms.dataAccess.abstracts.JobAdvertisementsDao;
import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.entities.concretes.Favorite;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;

@Service
public class FavoriteManager implements FavoriteService{

	private FavoriteDao favoriteDao;

	private CandidateDao candidateDao;
	private JobAdvertisementsDao jobAdvertisementDao;
	
	@Autowired
	public FavoriteManager(FavoriteDao favoriteDao ,
			CandidateDao candidateDao , 
			JobAdvertisementsDao jobAdvertisementsDao) {
		super();
		this.favoriteDao = favoriteDao;
		this.candidateDao = candidateDao;
		this.jobAdvertisementDao = jobAdvertisementsDao;
	}
	
	@Override
	public DataResult<List<Favorite>> getAll() {
		return new SuccessDataResult<List<Favorite>>(this.favoriteDao.findAll());
	}

	@Override
	public Result add(int candidateId , int jobAdvertisementId) {
		Candidate candidate = this.candidateDao.getOne(candidateId);
		JobAdvertisement jobAdvertisement = this.jobAdvertisementDao.getOne(jobAdvertisementId);
		
		Favorite favorite = new Favorite();
		favorite.setCandidate(candidate);
		favorite.setJobAdvertisement(jobAdvertisement);
		
		this.favoriteDao.save(favorite);
		return new SuccessResult("İlan favorilere eklendi");
	}

	@Override
	public Result delete(int candidateId , int jobAdvertisementId) {
		Favorite favorite = this.favoriteDao.findByCandidate_IdAndJobAdvertisement_Id(candidateId, jobAdvertisementId);
		this.favoriteDao.delete(favorite);
		return new SuccessResult("İlan favorilerden silindi");
	}

	@Override
	public DataResult<List<Favorite>> getAllByCandidateId(int candidateId) {
		return new SuccessDataResult<List<Favorite>>(this.favoriteDao.findAllByCandidate_Id(candidateId));
	}

	@Override
	public DataResult<Favorite> getByCandidateIdAndJobAdvertisementId(int candidateId, int jobAdvertisementId) {
		return new SuccessDataResult<Favorite>(this.favoriteDao.findByCandidate_IdAndJobAdvertisement_Id(candidateId, jobAdvertisementId));
	}

	

}
