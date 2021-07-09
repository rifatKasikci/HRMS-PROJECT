package kodlamaio.hrms.business.concretes;

import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EmailVerificationCodeService;
import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.business.abstracts.EmployerUpdateService;
import kodlamaio.hrms.business.constants.Messages;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import kodlamaio.hrms.dataAccess.abstracts.EmployerUpdateDao;
import kodlamaio.hrms.dataAccess.abstracts.JobAdvertisementsDao;
import kodlamaio.hrms.entities.concretes.EmailVerificationCode;
import kodlamaio.hrms.entities.concretes.Employer;
import kodlamaio.hrms.entities.concretes.EmployerUpdate;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;
import kodlamaio.hrms.entities.dtos.EmployerUpdateDto;

@Service
public class EmployerManager implements EmployerService{

	private EmployerDao employerDao;
	private JobAdvertisementsDao jobAdvertisementDao;
	private EmailVerificationCodeService emailVerificationService;
	private EmployerUpdateDao employerUpdateDao;
	
	@Autowired
	public EmployerManager(EmployerDao employerDao , 
			EmailVerificationCodeService emailVerificationService , 
			JobAdvertisementsDao jobAdvertisementDao , 
			EmployerUpdateDao employerUpdateDao) {
		super();
		this.employerDao = employerDao;
		this.jobAdvertisementDao = jobAdvertisementDao;
		this.emailVerificationService = emailVerificationService;
		this.employerUpdateDao = employerUpdateDao;
		
	}

	@Override
	public DataResult<List<Employer>> getAll() {
		return new SuccessDataResult<List<Employer>>(this.employerDao.findAll(),Messages.employersListed);
	}
	
	@Override
		public Result setAdvertisementInactive(int advertisementId) {
		JobAdvertisement referenceEntity = this.jobAdvertisementDao.getOne(advertisementId);
		referenceEntity.setActive(false);
		referenceEntity.setDeleted(true);
		this.jobAdvertisementDao.save(referenceEntity);
		return new SuccessResult(Messages.jobAdvertisementInactivated);
		}
	
	
	@Override
	public Result add(Employer employer , String passwordRepeat) {
		if (isDataRight(employer, passwordRepeat).isSuccess()) {
			this.employerDao.save(employer);
			this.emailVerificationService.createCode(new EmailVerificationCode(), employer.getId());
	     	return new SuccessResult(Messages.employerAdded);
		}
		return new ErrorResult(this.isDataRight(employer, passwordRepeat).getMessage());
		
		
		
	}
	
	
	@Override
	public Result update(Employer employer) {
		
		Employer employerForUpdate = this.employerDao.getOne(employer.getId());
	
		employerForUpdate = employer;
		
		EmployerUpdateDto employerUpdateRequest = new EmployerUpdateDto();
		
		employerUpdateRequest.setEmployerId(employerForUpdate.getId());
		employerUpdateRequest.setEmail(employerForUpdate.getEmail());
		employerUpdateRequest.setPassword(employerForUpdate.getPassword());
		employerUpdateRequest.setCompanyName(employerForUpdate.getCompanyName());
		employerUpdateRequest.setPhoneNumber(employerForUpdate.getPhoneNumber());
		employerUpdateRequest.setWebAddress(employerForUpdate.getWebAddress());
		
		EmployerUpdate employerUpdate = new EmployerUpdate();
		
		employerUpdate.setEmployer(employerForUpdate);
		employerUpdate.setEmployerUpdateDto(employerUpdateRequest);
		employerUpdate.setApproved(false);
		
		if (!this.isThereAnUpdate(employerForUpdate.getId())) {
			employerUpdate.setId(this.employerUpdateDao.findByIsApprovedFalseAndEmployer_Id(employerForUpdate.getId()).getId());
			
			this.employerUpdateDao.save(employerUpdate);
			
			return new SuccessResult("Güncelleme isteği güncellendi.");
		
		}
		
		this.employerUpdateDao.save(employerUpdate);
		
		return new SuccessResult("Güncelleme isteği gönderildi.");
	
		
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
	
	private boolean isThereAnUpdate(int employerId) {
		
		if (Objects.isNull(this.employerUpdateDao.findByIsApprovedFalseAndEmployer_Id(employerId))) {
			return true;
		}
			return false;
		
	}

	

	
}
