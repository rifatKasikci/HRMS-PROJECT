package kodlamaio.hrms.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.CurriculumVitaeService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.CurriculumVitae;
import kodlamaio.hrms.entities.dtos.CurriculumVitaeDetailDto;

@RestController
@RequestMapping("/api/curriculumVitaesController/")
@CrossOrigin
public class CurriculumVitaesController {

	private CurriculumVitaeService curriculumVitaeService;

	@Autowired
	public CurriculumVitaesController(CurriculumVitaeService curriculumVitaeService) {
		super();
		this.curriculumVitaeService = curriculumVitaeService;
	}
	
	@GetMapping("getcurriculumvitaedetails")
	public DataResult<CurriculumVitaeDetailDto> getCurriculumVitae(@RequestParam int candidateId){
		return this.curriculumVitaeService.getCurriculumVitaeByCandidateId(candidateId);
	}
	
	@PostMapping("add")
	public Result add(@RequestBody CurriculumVitae curriculumVitae) {
		return this.curriculumVitaeService.add(curriculumVitae);
	}
	
	@PostMapping("update")
	public Result update(@RequestBody CurriculumVitae curriculumVitae) {
		return this.curriculumVitaeService.update(curriculumVitae);
	}
	
	
	
}
