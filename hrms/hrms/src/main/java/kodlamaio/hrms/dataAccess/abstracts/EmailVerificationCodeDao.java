package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.EmailVerificationCode;

public interface EmailVerificationCodeDao extends JpaRepository<EmailVerificationCode, Integer>{

	EmailVerificationCode findByUserId(int userId);
}
