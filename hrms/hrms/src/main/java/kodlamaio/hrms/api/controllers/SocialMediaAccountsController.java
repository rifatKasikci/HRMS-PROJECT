package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.SocialMediaAccountService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.SocialMediaAccount;

@RestController
@RequestMapping("/api/socialMediaAccounts/")
@CrossOrigin
public class SocialMediaAccountsController {

	private SocialMediaAccountService socialMediaAccountService;

	@Autowired
	public SocialMediaAccountsController(SocialMediaAccountService socialMediaAccountService) {
		super();
		this.socialMediaAccountService = socialMediaAccountService;
	}
	
	@GetMapping("getall")
	public DataResult<List<SocialMediaAccount>> getAll(){
		return this.socialMediaAccountService.getAll();
	}
	
	@GetMapping("getallbycandidateid")
	public DataResult<List<SocialMediaAccount>> getAllByCandidateId(int candidateId){
		return this.socialMediaAccountService.getAllByCandidateId(candidateId);
	}
	
	@PostMapping("add")
	public Result add(@RequestBody SocialMediaAccount socialMediaAccount) {
		return this.socialMediaAccountService.add(socialMediaAccount);
	}
}
