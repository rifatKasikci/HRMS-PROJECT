package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.abstracts.User;

@RestController
@RequestMapping("/api/users/")
public class UsersController {

	private UserService userService;
	
	public UsersController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("getall")
	public DataResult<List<User>> getAll(){
		return this.userService.getAll();
	}
	
	@GetMapping("getbyemail")
	public DataResult<List<User>> getByEmail(String email){
	  return this.userService.getUserByEmail(email);
	}
	
	
	
	@PostMapping("add")
	public Result add(@RequestBody User user){
		return this.userService.add(user);
	}
	
	
}
