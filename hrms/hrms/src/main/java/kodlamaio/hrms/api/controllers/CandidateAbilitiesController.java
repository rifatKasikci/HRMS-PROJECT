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

import kodlamaio.hrms.business.abstracts.CandidateAbilityService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.CandidateAbility;
import kodlamaio.hrms.entities.dtos.CandidateAbilityDto;
import kodlamaio.hrms.entities.dtos.updateDtos.CandidateAbilityUpdateDto;

@RestController
@RequestMapping("/api/candidateAbilities")
@CrossOrigin
public class CandidateAbilitiesController {

	private CandidateAbilityService candidateAbilityService;

	@Autowired
	public CandidateAbilitiesController(CandidateAbilityService candidateAbilityService) {
		super();
		this.candidateAbilityService = candidateAbilityService;
	}
	
	@GetMapping("getAll")
	public DataResult<List<CandidateAbility>> getAll() {
		return this.candidateAbilityService.getAll();
				
	}
	
	@GetMapping("getallbycandidateid")
	public DataResult<List<CandidateAbility>> getAllByCandidateId(int candidateId) {
		return this.candidateAbilityService.getAllByCandidateId(candidateId);
	}
	
	@PostMapping("getbyid")
	public DataResult<CandidateAbility> getById(@RequestParam int id){
		return this.candidateAbilityService.getById(id);
	}
	
	@PostMapping("add")
	public Result add(@RequestBody CandidateAbilityDto candidateAbilityDto) {
		return this.candidateAbilityService.add(candidateAbilityDto);
	}
	
	@PostMapping("update")
	public Result update(@RequestBody CandidateAbilityUpdateDto candidateAbilityUpdateDto) {
		return this.candidateAbilityService.update(candidateAbilityUpdateDto);
	}
	
	@PostMapping("delete")
	public Result delete(@RequestParam int candidateAbilityId) {
		return this.candidateAbilityService.delete(candidateAbilityId);
	}
}
