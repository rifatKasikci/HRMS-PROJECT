package kodlamaio.hrms.entities.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.entities.concretes.CandidateAbility;
import kodlamaio.hrms.entities.concretes.CandidateSchoolWithDepartment;
import kodlamaio.hrms.entities.concretes.CurriculumVitae;
import kodlamaio.hrms.entities.concretes.JobExperience;
import kodlamaio.hrms.entities.concretes.LanguageCandidate;
import kodlamaio.hrms.entities.concretes.SocialMediaAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurriculumVitaeDetailDto {

	@JsonIgnore
	private Candidate candidate;
	
	private List<CandidateSchoolWithDepartment> candidateSchoolWithDepartments;
	
	private List<JobExperience> jobExperiences;
	
	private List<LanguageCandidate> languageCandidates;
	
	private List<CandidateAbility> candidateAbilities;
	
	private List<SocialMediaAccount> socialMediaAccounts;
	
	private CurriculumVitae curriculumVitae; 
	
}
