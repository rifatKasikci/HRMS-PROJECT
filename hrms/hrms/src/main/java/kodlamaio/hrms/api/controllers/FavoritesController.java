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

import kodlamaio.hrms.business.abstracts.FavoriteService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Favorite;

import kodlamaio.hrms.entities.dtos.FavoriteDto;

@RestController
@RequestMapping("/api/favorites/")
@CrossOrigin


@RestController
@RequestMapping("/api/favorites/")

public class FavoritesController {

	private FavoriteService favoriteService;

	@Autowired
	public FavoritesController(FavoriteService favoriteService) {
		super();
		this.favoriteService = favoriteService;
	}
	
	@GetMapping("getall")
	public DataResult<List<Favorite>> getAll(){
		return this.favoriteService.getAll();
	}

	@GetMapping("getallbycandidateid")
	public DataResult<List<Favorite>> getAllByCandidateId(@RequestParam int candidateId){
		return this.favoriteService.getAllByCandidateId(candidateId);
	}
	
	@GetMapping("getbycandidateidandjobadvertisementid")
	public DataResult<Favorite> getByCandidateIdAndJobAdvertisementId(@RequestParam int candidateId, @RequestParam int jobAdvertisementId){
		return this.favoriteService.getByCandidateIdAndJobAdvertisementId(candidateId , jobAdvertisementId);
	}
	
	@PostMapping("add")
	public Result add(@RequestParam int candidateId, @RequestParam int jobAdvertisementId){
		return this.favoriteService.add(candidateId , jobAdvertisementId);
	}
	
	@PostMapping("delete")
	public Result delete(@RequestParam int candidateId, @RequestParam int jobAdvertisementId){
		return this.favoriteService.delete(candidateId , jobAdvertisementId);
	}
	

	@PostMapping("add")
	public Result add(@RequestBody Favorite favorite){
		return this.favoriteService.add(favorite);
	}

}
