package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import kodlamaio.hrms.business.abstracts.JobAdvertisementService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;

@RestController
@RequestMapping("/api/jobadvertisement/")
@Service
public class JobAdvertisementsContoller {

	private JobAdvertisementService jobAdvertisementService;

	@Autowired
	public JobAdvertisementsContoller(JobAdvertisementService jobAdvertisementService) {
		super();
		this.jobAdvertisementService = jobAdvertisementService;
	}
	
	@GetMapping("getall")
	public DataResult<List<JobAdvertisement>> getAll(){
		return this.jobAdvertisementService.getAll();
	}
	
	@GetMapping("getallbyactivetrue")
	public DataResult<List<JobAdvertisement>> getAllByActiveTrue(){
		return this.jobAdvertisementService.getAllByActiveTrue();
	}
	
	@GetMapping("getallbyemployerid")
	public DataResult<List<JobAdvertisement>> getAllByEmployerId(@RequestParam int employerId){
		return this.jobAdvertisementService.getAllByEmployerId(employerId);
	}
	
	
	@PostMapping("setadvertisementinactive")
	public Result setAdvertisementInactive(int advertisementId) {
		return this.jobAdvertisementService.setAdvertisementInactive(advertisementId);
	}
	
	@PostMapping("add")
	public Result add(@RequestBody JobAdvertisement jobAdvertisement) {
		return this.jobAdvertisementService.add(jobAdvertisement);
	}
	
	
}
