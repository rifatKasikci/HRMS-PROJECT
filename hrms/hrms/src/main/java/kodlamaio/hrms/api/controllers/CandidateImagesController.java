package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kodlamaio.hrms.business.abstracts.CandidateImageService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.entities.concretes.CandidateImage;

@RestController
@RequestMapping("/api/candidateImages/")
@CrossOrigin
public class CandidateImagesController {

	private CandidateImageService candidateImageService;
	
	@Autowired
	public CandidateImagesController(CandidateImageService candidateImageService) {
		super();
		this.candidateImageService = candidateImageService;
	}
	
	@GetMapping("getall")
	public DataResult<List<CandidateImage>> getAll(){
		return this.candidateImageService.getAll();
	}
	
	@GetMapping("getallbycandidateid")
	public DataResult<CandidateImage> getAllByCandidateId(int candidateId){
		return this.candidateImageService.getByCandidateId(candidateId);
	}
	
	@PostMapping("add")
	public Result add(@RequestParam int candidateId ,@RequestParam MultipartFile file){
		CandidateImage candidateImage = new CandidateImage();
		Candidate candidate = new Candidate();
		candidate.setId(candidateId);
		candidateImage.setCandidate(candidate);
		
		return this.candidateImageService.add(candidateImage, file);
		
	}
}
