package kodlamaio.hrms.core.utilities.adapters.abstracts;


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
