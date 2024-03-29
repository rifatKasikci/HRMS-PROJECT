package kodlamaio.hrms.entities.concretes;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import kodlamaio.hrms.entities.abstracts.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "candidates")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","favorites"})
public class Candidate extends User{

	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "identification_number")
	private String identificationNumber;
	
	@Column(name = "birth_date")
	private LocalDate birthDate;
	
	@JsonIgnore
	@OneToMany(mappedBy = "candidate")
	private List<CandidateSchoolWithDepartment> candidateSchoolWithDepartments;
	
   	@JsonIgnore
	@OneToMany(mappedBy = "candidate")
	private List<CandidateAbility> candidateAbilities;
	
   	@JsonIgnore
	@OneToMany(mappedBy = "candidate")
	private List<JobExperience> jobExperiences;
	
   	@JsonIgnore
	@OneToMany(mappedBy = "candidate")
	private List<LanguageCandidate> languageCandidates;
	
   	@JsonIgnore
	@OneToMany(mappedBy = "candidate")
	private List<SocialMediaAccount> socialMediaAccounts;
   	
   	@JsonIgnore
   	@OneToMany(mappedBy = "candidate")
   	private List<CurriculumVitae> curriculumVitaes;
   	
   	@JsonIgnore
   	@OneToMany(mappedBy = "candidate")
   	private List<Favorite> favorites;
}
