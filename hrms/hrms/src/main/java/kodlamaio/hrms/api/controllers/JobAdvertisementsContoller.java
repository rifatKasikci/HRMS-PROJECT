package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import kodlamaio.hrms.entities.dtos.JobAdvertisementDto;
import kodlamaio.hrms.entities.dtos.JobAdvertisementFilter;

@RestController
@RequestMapping("/api/jobAdvertisement/")
@CrossOrigin
public class JobAdvertisementsContoller {

	private JobAdvertisementService jobAdvertisementService;

	@Autowired
	public JobAdvertisementsContoller(JobAdvertisementService jobAdvertisementService) {
		super();
		this.jobAdvertisementService = jobAdvertisementService;
	}
	
	@GetMapping("getall")
	public DataResult<List<JobAdvertisement>> getAll(@RequestParam int pageNo ,@RequestParam int pageSize){
		return this.jobAdvertisementService.getAll(pageNo,pageSize);
	}
	
	@GetMapping("getallbyactivetrue")
	public DataResult<List<JobAdvertisement>> getAllByActiveTrue(@RequestParam int pageNo,@RequestParam int pageSize){
		return this.jobAdvertisementService.getAllByActiveTrue(pageNo,pageSize);
	}
	
	@GetMapping("getallbyemployerid")
	public DataResult<List<JobAdvertisement>> getAllByEmployerId(@RequestParam int employerId){
		return this.jobAdvertisementService.getAllByEmployerId(employerId);
	}
	
	@GetMapping("findunapprovedadvertisements")
	public DataResult<List<JobAdvertisement>> findUnapprovedAdvertisements(@RequestParam int pageNo,@RequestParam int pageSize){
		return this.jobAdvertisementService.getUnapprovedAdvertisements(pageNo,pageSize);
	}
	
	@GetMapping("getbyid")
	public DataResult<List<JobAdvertisement>> getById(@RequestParam int id){
		return this.jobAdvertisementService.getById(id);
	}
	
		
	@PostMapping("add")
	public Result add(@RequestBody JobAdvertisementDto jobAdvertisementDto) {
		return this.jobAdvertisementService.add(jobAdvertisementDto);
	}
	
	@PostMapping("findbyactivetrueandfiltered")
	public DataResult<List<JobAdvertisement>> findByActiveTrueAndFiltered(@RequestParam int pageNo ,@RequestParam int pageSize,@RequestBody JobAdvertisementFilter jobAdvertisementFilter){
		
		return this.jobAdvertisementService.findByActiveTrueAndFiltered(pageNo, pageSize,jobAdvertisementFilter);
		
	}
	
	
}
