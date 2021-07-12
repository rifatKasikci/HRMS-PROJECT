package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.LanguageCandidateService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.LanguageCandidate;
import kodlamaio.hrms.entities.dtos.LanguageCandidateDto;
import kodlamaio.hrms.entities.dtos.updateDtos.LanguageCandidateUpdateDto;

@RestController
@RequestMapping("/api/languageCandidatesController/")
@CrossOrigin
public class LanguageCandidatesController {
	
	private LanguageCandidateService languageCandidateService;

	@Autowired
	public LanguageCandidatesController(LanguageCandidateService languageCandidateService) {
		super();
		this.languageCandidateService = languageCandidateService;
	}
	
	@GetMapping("getall")
	public DataResult<List<LanguageCandidate>> getAll(){
		return this.languageCandidateService.getAll();
	}
	
	@GetMapping("getbyid")
	public DataResult<LanguageCandidate> getById(@RequestParam int id){
		return this.languageCandidateService.getById(id);
	}
	
	@PostMapping("add")
	public Result add(@RequestBody LanguageCandidateDto languageCandidateDto) {
		return this.languageCandidateService.add(languageCandidateDto);
	}
	
	@PostMapping("update")
	public Result update(@RequestBody LanguageCandidateUpdateDto languageCandidateUpdateDto) {
		return this.languageCandidateService.update(languageCandidateUpdateDto);
	}
	
	@PostMapping("delete")
	public Result delete(@RequestParam int languageCandidateId) {
		return this.languageCandidateService.delete(languageCandidateId);
	}
	
	@GetMapping("getAllbycandidateid")
	public DataResult<List<LanguageCandidate>> getAllByCandidateId(int candidateId){
		return this.languageCandidateService.getAllByCandidateId(candidateId);
	}
}
