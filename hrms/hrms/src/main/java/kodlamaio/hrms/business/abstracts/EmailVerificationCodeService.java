package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.EmailVerificationCode;

public interface EmailVerificationCodeService {

	void createCode(EmailVerificationCode verificationCode , int userId);
	
	Result verifyCode(String verificationCode , int id);
}
