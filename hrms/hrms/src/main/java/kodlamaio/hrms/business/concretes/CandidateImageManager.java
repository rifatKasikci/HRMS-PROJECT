package kodlamaio.hrms.business.concretes;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kodlamaio.hrms.business.abstracts.CandidateImageService;
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
		
		candidateImage.setImageUrl(photoUrl);
		
		this.candidateImageDao.save(candidateImage);
		return new SuccessResult("Fotoğraf eklendi.");
		
	}

	@Override
	public DataResult<List<CandidateImage>> getAll() {
	return new SuccessDataResult<List<CandidateImage>>(this.candidateImageDao.findAll());
	}

	
}
