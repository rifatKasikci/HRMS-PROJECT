package kodlamaio.hrms.api.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.EmailVerificationCodeService;
import kodlamaio.hrms.core.utilities.results.Result;

@RestController
@RequestMapping("/api/verify/")
public class EmailVerificationCodesController {

	private EmailVerificationCodeService emailVerificationService;

	public EmailVerificationCodesController(EmailVerificationCodeService emailVerificationService) {
		super();
		this.emailVerificationService = emailVerificationService;
	}
	
	@PostMapping("update/{verificationCode}/{id}")
	public Result setVerify(@RequestParam String verificationCode,@RequestParam Integer id) {
		return this.emailVerificationService.verifyCode(verificationCode, id);
	}
}
