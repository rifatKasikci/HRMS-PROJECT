package kodlamaio.hrms.core.utilities.adapters.concretes;


import kodlamaio.hrms.core.utilities.adapters.abstracts.UserCheckService;
import kodlamaio.hrms.entities.concretes.Candidate;

public class UserCheckManager implements UserCheckService{

	
	@Override
	public boolean checkUser(Candidate candidate) {
		if (candidate.getIdentificationNumber().length() != 11) {
			return false;
		}
		return true;
	}

}
