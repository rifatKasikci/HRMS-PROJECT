package kodlamaio.hrms.business.concretes;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CandidateService;
import kodlamaio.hrms.business.abstracts.EmailVerificationCodeService;
import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.core.utilities.adapters.abstracts.UserCheckManager;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CandidateDao;
import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.entities.concretes.EmailVerificationCode;

@Service
public class CandidateManager implements CandidateService{

	private CandidateDao candidateDao;
	private EmailVerificationCodeService emailVerificationService;
	private UserService userService;
	 
	
	@Autowired
	public CandidateManager(CandidateDao candidateDao , EmailVerificationCodeService emailVerificationService , UserService userService) {
		super();
		this.candidateDao = candidateDao;
		this.emailVerificationService = emailVerificationService;
		this.userService = userService;
		
	}
	
	

	@Override
	public DataResult<List<Candidate>> getAll() {
		return new SuccessDataResult<List<Candidate>>(this.candidateDao.findAll(),"Data listelendi");
	}

	@Override
	public Result add(Candidate candidate , String passwordRepeat) {
		UserCheckManager userCheckManager = new UserCheckManager();
		if (isDataRightChecker(candidate, passwordRepeat).isSuccess() && userCheckManager.checkUser(candidate)) {
			this.candidateDao.save(candidate);
			this.emailVerificationService.createCode(new EmailVerificationCode(), candidate.getId());
			return new SuccessResult("İş arayan eklendi.");
		}
		return new ErrorResult(isDataRightChecker(candidate, passwordRepeat).getMessage());
	}
	
	
	private Result isDataRightChecker(Candidate candidate , String passwordRepeat) {
		if (candidate.getFirstName().isBlank() || candidate.equals(null)) { 
			return new ErrorResult("İsim boş bırakılamaz");
		}else if (candidate.getLastName().isBlank() || candidate.getLastName().equals(null)) { 
			return new ErrorResult("Soyad boş bırakılamaz");
		}else if (candidate.getIdentificationNumber().isBlank() || candidate.getIdentificationNumber().equals(null) ) {
			return new ErrorResult("Vatandaşlık numarası boş bırakılamaz");
		}else if (candidate.getBirthDate().equals(null)) { 
			return new ErrorResult("Doğum tarihi alanı boş bırakılamaz");
		}else if (candidate.getPassword().isBlank() || candidate.getPassword().equals(null)) {
			return new ErrorResult("Şifre alanı boş bırakılamaz");
		}else if (candidate.getEmail().isBlank() || candidate.getEmail().equals(null)) {
			return new ErrorResult("Email alanı boş bırakılamaz");
		}else if (!this.isRealEmail(candidate)) {
			return new ErrorResult("E-posta geçersiz.");
		}else if (passwordRepeat.isBlank() || passwordRepeat.equals(null) || !this.passwordRepeatChecker(candidate, passwordRepeat)) {
			return new ErrorResult("Şifreler uyuşmuyor");
		}else if (!isNationelIdentityAlreadyExist(candidate.getIdentificationNumber()) || !isNationelIdentityRight(candidate.getIdentificationNumber()) || candidate.getIdentificationNumber().startsWith("0")) {
			return new ErrorResult("Bu kişi zaten kayıtlı");
		}else if (!isEmailAlreadyExist(candidate.getEmail())) {
			return new ErrorResult("Bu e-posta adresi daha önce alınmış.");
		}
		
		return new SuccessResult("İş arayan eklendi.");
	}

	
	private boolean isRealEmail(Candidate candidate) {
		
		String regexPattern =  "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(regexPattern);
		Matcher matcher = pattern.matcher(candidate.getEmail());
		if (!matcher.matches()) {
			return false;
		}
		
		return true;
	}
	
	private boolean passwordRepeatChecker(Candidate candidate , String passwordRepeat) {
		
		if (!candidate.getPassword().equals(passwordRepeat)) {
			return false;
		}
		return true;
		
	}
	
	private boolean isNationelIdentityAlreadyExist(String nationelIdentity) {
		
		if (!this.candidateDao.findAllByIdentificationNumber(nationelIdentity).isEmpty()) {
			return false;
		}
		
		return true;
	}
	
	private boolean isNationelIdentityRight(String nationelIdentity) {
		String regex = "^[0-9]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(nationelIdentity);
		
		if (!matcher.matches()) {
			return false;
		}
		
		return true;
	}
	
	private boolean isEmailAlreadyExist(String email) {
		
		if (!this.userService.getUserByEmail(email).getData().isEmpty()) {
			return false;
		}
		
		return true;
	}
}

