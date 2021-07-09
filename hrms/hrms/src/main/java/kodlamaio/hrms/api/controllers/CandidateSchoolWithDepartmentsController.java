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

import kodlamaio.hrms.business.abstracts.CandidateSchoolWithDepartmentService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.entities.concretes.CandidateSchoolWithDepartment;
import kodlamaio.hrms.entities.dtos.CandidateSchoolWithDepartmentDto;
import kodlamaio.hrms.entities.dtos.updateDtos.CandidateSchoolWithDepartmentUpdateDto;

@RestController
@RequestMapping("/api/candidateSchoolWithDepartments/")
@CrossOrigin
public class CandidateSchoolWithDepartmentsController {

	private CandidateSchoolWithDepartmentService candidateSchoolWithDepartmentService;

	@Autowired
	public CandidateSchoolWithDepartmentsController(
			CandidateSchoolWithDepartmentService candidateSchoolWithDepartmentService) {
		super();
		this.candidateSchoolWithDepartmentService = candidateSchoolWithDepartmentService;
	}
	
	@GetMapping("getall")
	public DataResult<List<CandidateSchoolWithDepartment>> getAll(){
		return this.candidateSchoolWithDepartmentService.getAll();
	}
	
	@PostMapping("add")
	public Result add(@RequestBody CandidateSchoolWithDepartmentDto candidateSchoolWithDepartmentDto){
		return this.candidateSchoolWithDepartmentService.add(candidateSchoolWithDepartmentDto);
	}
	
	@PostMapping("update")
	public Result update(@RequestBody CandidateSchoolWithDepartmentUpdateDto candidateSchoolWithDepartmentUpdateDto){
		return this.candidateSchoolWithDepartmentService.update(candidateSchoolWithDepartmentUpdateDto);
	}
	
	@PostMapping("delete")
	public Result delete(@RequestParam int candidateSchoolWithDepartmentId){
		return this.candidateSchoolWithDepartmentService.delete(candidateSchoolWithDepartmentId);
	}
	
	
	@GetMapping("getallbycandidateidorderbyendingdatedesc")
	public DataResult<List<CandidateSchoolWithDepartment>> getAllByCandidateIdOrderByEndingDateDesc(int candidateId){
		return this.candidateSchoolWithDepartmentService.getAllByCandidateIdOrderByEndingDateDesc(candidateId);
	}
	
	@GetMapping("getallbyorderbyendingdatedesc")
	public DataResult<List<CandidateSchoolWithDepartment>> getAllByOrderByEndingDateDesc(){
		return this.candidateSchoolWithDepartmentService.getAllByOrderByEndingDateDesc();
	}
	
	@GetMapping("getbyid")
	public DataResult<CandidateSchoolWithDepartment> getById(@RequestParam int id) {
		return this.candidateSchoolWithDepartmentService.getById(id);
	}
}
