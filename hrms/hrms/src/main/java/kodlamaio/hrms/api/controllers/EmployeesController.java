package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.EmployeeService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Employee;
import kodlamaio.hrms.entities.concretes.EmployerUpdate;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;

@RestController
@RequestMapping("/api/employees/")
@CrossOrigin
public class EmployeesController {

	private EmployeeService employeeService;
	
	@Autowired
	public EmployeesController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}
	
	@GetMapping("getall")
	public DataResult<List<Employee>> getAll(){
		return this.employeeService.getAll();
	}
	
	@GetMapping("getjobadvertisementsinactive")
	public DataResult<List<JobAdvertisement>> getJobAdvertisementsInactive(){
		return this.employeeService.getJobAdvertisementsInactive();
	}
	

	@GetMapping("getbyid")
	public DataResult<Employee> getById(@RequestParam int employerId){
		return this.employeeService.getById(employerId);
	}
	
	@GetMapping("getunapprovedupdaterequests")
	public DataResult<List<EmployerUpdate>> getUnapprovedUpdateRequests(){
		return this.employeeService.getUnapprovedUpdateRequests();
	}
	
	@PostMapping("confirmjobadvertisement")
	public Result confirmJobAdvertisement(@RequestParam int jobAdvertisementId) {
		return this.employeeService.confirmJobAdvertisement(jobAdvertisementId);
	}
	
	@PostMapping("add")
	public Result add(@RequestBody Employee employee){
		return this.employeeService.add(employee);
	}
	
	@PostMapping("update")
	public Result update(@RequestBody Employee employee) {
		return this.employeeService.update(employee);
	}
	
	@PostMapping("confirmemployeruptade")
	public Result confirmEmployerUptade(int employerId) {
		return this.employeeService.confirmEmployerUptade(employerId);
	}
	
	@PostMapping("unapproveemployeruptade")
	public Result unapproveEmployerUptade(int employerId) {
		return this.employeeService.unapproveEmployerUpdate(employerId);
	}
	

	@DeleteMapping("deletejobadvertisement")
	public Result delete(int jobAdvertisementId) {
		return this.employeeService.delete(jobAdvertisementId);
	}
	
	
}
