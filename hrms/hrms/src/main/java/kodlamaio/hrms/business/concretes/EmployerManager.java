package kodlamaio.hrms.business.concretes;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EmailVerificationCodeService;
import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import kodlamaio.hrms.entities.concretes.EmailVerificationCode;
import kodlamaio.hrms.entities.concretes.Employer;

@Service
public class EmployerManager implements EmployerService{

	private EmployerDao employerDao;
	private EmailVerificationCodeService emailVerificationService;
	
	@Autowired
	public EmployerManager(EmployerDao employerDao , EmailVerificationCodeService emailVerificationService) {
		super();
		this.employerDao = employerDao;
		this.emailVerificationService = emailVerificationService;
	}

	@Override
	public DataResult<List<Employer>> getAll() {
		return new SuccessDataResult<List<Employer>>(this.employerDao.findAll(),"Data listelendi");
	}

	@Override
	public Result add(Employer employer , String passwordRepeat) {
		if (isDataRight(employer, passwordRepeat).isSuccess()) {
			this.employerDao.save(employer);
			this.emailVerificationService.createCode(new EmailVerificationCode(), employer.getId());
	     	return new SuccessResult("İşveren eklendi.");
		}
		return new ErrorResult(this.isDataRight(employer, passwordRepeat).getMessage());
		
		
		
	}
	
	
	private Result isDataRight(Employer employer , String passwordRepeat) {
		
		if (employer.getCompanyName().isBlank() || employer.getCompanyName().equals(null)) {
			return new ErrorResult("Şirket ismi boş bırakılamaz.");
		}else if (employer.getEmail().isBlank() || employer.getEmail().equals(null)) {
			return new ErrorResult("E-posta alanı boş bırakılamaz.");
		}else if (employer.getPassword().isBlank() || employer.getPassword().equals(null)) {
			return new ErrorResult("Şifre alanı boş bırakılamaz.");
		}else if (employer.getWebAddress().isBlank() || employer.getWebAddress().equals(null)) {
			return new ErrorResult("Web adresi alanı boş bırakılamaz.");
		}else if (!isEmailAllreadyExist(employer)) {
			return new ErrorResult("E-posta adresi zaten alınmış.");
		}else if (!isRealEmployer(employer)) {
			return new ErrorResult("E-posta adresi geçersiz.");
		}else if (!isWebAddressTrue(employer)) {
			return new ErrorResult("Web adresi geçersiz.");
		}else if (!isPasswordRepeatTrue(employer , passwordRepeat)) {
			return new ErrorResult("Şifreleriniz uyuşmuyor.");
		}
		
		return new SuccessResult("İşveren eklendi.");
}
	
	private boolean isEmailAllreadyExist(Employer employer) {
		if(employerDao.findAllByEmail(employer.getEmail()).stream().count() != 0) {
			return false;
		}
		return true;
	}
	
	private boolean isRealEmployer(Employer employer) {
		 String regex = "^(.+)@(.+)$";
	     Pattern pattern = Pattern.compile(regex);
	     Matcher matcher = pattern.matcher(employer.getEmail());
	     if(!matcher.matches()) {
	    	 return false;
	     }
	     
	     return true;
	     
	}
	
	private boolean isWebAddressTrue(Employer employer) {
		
		//https://www.company.com
		String webAddress = employer.getWebAddress();
		String[] webAdderessArray = webAddress.replace(".","_").split("_");
		String newWebAddress = webAdderessArray[1];
		String regex = "(http://|https://)(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(webAddress);
		
		
		if (!employer.getEmail().contains(newWebAddress) || !matcher.matches()) {
			return false;
		}
		
		return true;
		
		}
	
	private boolean isPasswordRepeatTrue(Employer employer , String passwordRepeat) {
		if (!employer.getPassword().equals(passwordRepeat)) {
			return false;
		}
		
		return true;
	}
	
}
