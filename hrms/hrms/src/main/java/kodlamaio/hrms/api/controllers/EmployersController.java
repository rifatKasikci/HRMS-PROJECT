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

import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Employer;

@RestController
@RequestMapping("/api/employers/")
@CrossOrigin
public class EmployersController{

	private EmployerService employerService;
	
	@Autowired
	public EmployersController(EmployerService employerService) {
		super();
		this.employerService = employerService;
	}
	
	@GetMapping("getall")
	public DataResult<List<Employer>> getAll(){
		return this.employerService.getAll();
	}
	
	@GetMapping("getbyid")
	public DataResult<Employer> getById(@RequestParam int id){
		return this.employerService.getById(id);
	}
	
	@PostMapping("setadvertisementinactive")
	public Result setAdvertisementInactive(@RequestParam int advertisementId) {
		return this.employerService.setAdvertisementInactive(advertisementId);
	}
	
	@PostMapping("add")
	public Result add(@RequestBody Employer employer,@RequestParam String passwordRepeat){
		return this.employerService.add(employer, passwordRepeat);
	}
	
	@PostMapping("update")
	public Result update(@RequestBody Employer employer) {
		return this.employerService.update(employer);
	}
}
