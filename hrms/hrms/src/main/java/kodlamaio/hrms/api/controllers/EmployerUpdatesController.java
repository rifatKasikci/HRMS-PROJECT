package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.EmployerUpdateService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.entities.concretes.EmployerUpdate;

@RestController
@RequestMapping("/api/employerUpdates/")
@CrossOrigin
public class EmployerUpdatesController {

	private EmployerUpdateService employerUpdateService;

	@Autowired
	public EmployerUpdatesController(EmployerUpdateService employerUpdateService) {
		super();
		this.employerUpdateService = employerUpdateService;
	}
	
	@GetMapping("getbyemployerid")
	public DataResult<List<EmployerUpdate>> getByEmployerId(@RequestParam int employerId){
		return this.employerUpdateService.getByEmployerId(employerId);
	}
	
	@GetMapping("getbyid")
	public DataResult<EmployerUpdate> getById(@RequestParam int employerUpdateId){
		return this.employerUpdateService.getById(employerUpdateId);
	}
	
	@GetMapping("getunapprovedupdatebyemployerid")
	public DataResult<EmployerUpdate> getUnapprovedUpdateByEmployerId(@RequestParam int employerId){
		return this.employerUpdateService.getUnapprovedUpdateByEmployerId(employerId);
	}
	
	
}
