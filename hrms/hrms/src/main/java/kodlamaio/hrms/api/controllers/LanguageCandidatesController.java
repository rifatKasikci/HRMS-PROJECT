package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.LanguageCandidateService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.LanguageCandidate;

@RestController
@RequestMapping("/api/languageCandidatesController/")
public class LanguageCandidatesController {
	
	private LanguageCandidateService languageCandidateService;

	public LanguageCandidatesController(LanguageCandidateService languageCandidateService) {
		super();
		this.languageCandidateService = languageCandidateService;
	}
	
	@GetMapping("getall")
	public DataResult<List<LanguageCandidate>> getAll(){
		return this.languageCandidateService.getAll();
	}
	
	@PostMapping("add")
	public Result add(@RequestBody LanguageCandidate languageCandidate) {
		return this.languageCandidateService.add(languageCandidate);
	}
}
