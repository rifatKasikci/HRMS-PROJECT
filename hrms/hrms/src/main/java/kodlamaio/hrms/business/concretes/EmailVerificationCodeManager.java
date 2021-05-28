package kodlamaio.hrms.business.concretes;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EmailVerificationCodeService;
import kodlamaio.hrms.core.utilities.RandomCodeGenerator.RandomCodeGenerator;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.EmailVerificationCodeDao;
import kodlamaio.hrms.entities.concretes.EmailVerificationCode;

@Service
public class EmailVerificationCodeManager implements EmailVerificationCodeService{

	private EmailVerificationCodeDao emailVerificationCodeDao;
	
	@Autowired
	public EmailVerificationCodeManager(EmailVerificationCodeDao emailVerificationCodeDao) {
		super();
		this.emailVerificationCodeDao = emailVerificationCodeDao;
	}

	@Override
	public void createCode(EmailVerificationCode verificationCode, int userId) {
		EmailVerificationCode _code = verificationCode;
		_code.setVerificationCode(null);
		_code.setVerified(false);
		
		if (_code.isVerified() == false) {
			RandomCodeGenerator randomCodeGenerator = new RandomCodeGenerator();
			_code.setVerified(true);
			_code.setVerificationCode(randomCodeGenerator.createRandomCode());
			_code.setUserId(userId);
			this.emailVerificationCodeDao.save(_code);
			
			
		}
		
		return;
	}

	@Override
	public Result verifyCode(String verificationCode, int id) {
		EmailVerificationCode refrence = this.emailVerificationCodeDao.findByUserId(id);
		if (refrence.getVerificationCode().equals(verificationCode) && refrence.isVerified() == false) {
		 refrence.setVerified(true);
		 return new SuccessResult("E-posta doğrulandı.");
		}else if (refrence.isVerified() == true) {
			return new ErrorResult("E-posta zaten doğrulanmış.");
		}
		
		return new ErrorResult("Doğrulama geçersiz");
		
	}

}
