package kodlamaio.hrms.business.concretes;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kodlamaio.hrms.business.abstracts.CandidateImageService;
import kodlamaio.hrms.business.constants.Messages;
import kodlamaio.hrms.core.utilities.helpers.ImageUploadService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CandidateImageDao;
import kodlamaio.hrms.entities.concretes.CandidateImage;

@Service
public class CandidateImageManager implements CandidateImageService{

	private CandidateImageDao candidateImageDao;
	private ImageUploadService imageUploadService;

	

	@Autowired
	public CandidateImageManager(CandidateImageDao candidateImageDao ,
			ImageUploadService imageUploadService) {
		super();
		this.candidateImageDao = candidateImageDao;
		this.imageUploadService = imageUploadService;
	}

	@Override
	public Result add(CandidateImage candidateImage , MultipartFile file) {
		
		Map<String , String> result = (Map<String , String>) this.imageUploadService.save(file).getData();
		String photoUrl = result.get("url");
		
		if (this.isHavePhoto(candidateImage.getCandidate().getId())) {
			candidateImage = this.candidateImageDao.getByCandidate_Id(candidateImage.getCandidate().getId());
			candidateImage.setImageUrl(photoUrl);
			this.candidateImageDao.save(candidateImage);
			return new SuccessResult("Resim bilgisi güncellendi.");
		}
		
		candidateImage.setImageUrl(photoUrl);
		
		this.candidateImageDao.save(candidateImage);
		return new SuccessResult(Messages.imageAdded);
		
	}

	@Override
	public DataResult<List<CandidateImage>> getAll() {
	return new SuccessDataResult<List<CandidateImage>>(this.candidateImageDao.findAll() , Messages.imagesListed);
	}

	@Override
	public DataResult<CandidateImage> getByCandidateId(int candidateId) {
		return new SuccessDataResult<CandidateImage>(this.candidateImageDao.getByCandidate_Id(candidateId));
	}
	
	private boolean isHavePhoto(int candidateId) {
		var photos = this.candidateImageDao.getByCandidate_Id(candidateId);
		
		if (true) {
			return true;
		}
		return false;
	}

	
}
