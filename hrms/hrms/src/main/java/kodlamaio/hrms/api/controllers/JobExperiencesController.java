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

import kodlamaio.hrms.business.abstracts.JobExperienceService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.CandidateAbility;
import kodlamaio.hrms.entities.concretes.JobExperience;
import kodlamaio.hrms.entities.dtos.JobExperienceDto;
import kodlamaio.hrms.entities.dtos.updateDtos.JobExperienceUpdateDto;

@RestController
@RequestMapping("/api/jobExperiences/")
@CrossOrigin
public class JobExperiencesController {

	private JobExperienceService jobExperienceService;

	@Autowired
	public JobExperiencesController(JobExperienceService jobExperienceService) {
		super();
		this.jobExperienceService = jobExperienceService;
	}
	
	@GetMapping("getall")
	public DataResult<List<JobExperience>> getAll(){
		return this.jobExperienceService.getAll();
	}
	
	@GetMapping("getbyid")
	public DataResult<JobExperience> getById(@RequestParam int id){
		return this.jobExperienceService.getById(id);
	}
	
	@PostMapping("add")
	public Result add(@RequestBody JobExperienceDto jobExperienceDto) {
		return this.jobExperienceService.add(jobExperienceDto);
	}
	
	@PostMapping("update")
	public Result update(@RequestBody JobExperienceUpdateDto jobExperienceUpdateDto) {
		return this.jobExperienceService.update(jobExperienceUpdateDto);
	}
	
	@PostMapping("delete")
	public Result delete(@RequestParam int jobExperienceId) {
		return this.jobExperienceService.delete(jobExperienceId);
	}
	
	@GetMapping("getallbyorderbyendingdatedesc")
	public DataResult<List<JobExperience>> getAllByOrderByEndingDateDesc(){
		return this.jobExperienceService.getAllByOrderByEndingDateDesc();
	}
	
	@GetMapping("getallbycandidateidorderbyendingdatedesc")
	public DataResult<List<JobExperience>> getAllByCandidateIdOrderByEndingDateDesc(@RequestParam int candidateId){
		return this.jobExperienceService.getAllByCandidateIdOrderByEndingDateDesc(candidateId);
	}
}
