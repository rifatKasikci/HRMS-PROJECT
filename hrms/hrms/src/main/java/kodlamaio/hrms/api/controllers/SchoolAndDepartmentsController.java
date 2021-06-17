package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.SchoolAndDepartmentService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.SchoolAndDepartment;

@RestController
@RequestMapping("/api/schoolAndDepartmentsController")
@CrossOrigin
public class SchoolAndDepartmentsController {
	
	private SchoolAndDepartmentService schoolAndDepartmentService;

	@Autowired
	public SchoolAndDepartmentsController(SchoolAndDepartmentService schoolAndDepartmentService) {
		super();
		this.schoolAndDepartmentService = schoolAndDepartmentService;
	}
	
	@GetMapping("getall")
	public DataResult<List<SchoolAndDepartment>> getAll(){
		return this.schoolAndDepartmentService.getAll();
	}
	
	@PostMapping("add")
	public Result add(@RequestBody SchoolAndDepartment schoolAndDepartment){
		return this.schoolAndDepartmentService.add(schoolAndDepartment);
	}
	
	

}
