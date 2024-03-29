package kodlamaio.hrms.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "languages_candidates")
public class LanguageCandidate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@ManyToOne()
	@JoinColumn(name = "candidate_id")
	private Candidate candidate;
	
	@ManyToOne()
	@JoinColumn(name = "language_id")
	private Language language;
	
	@ManyToOne()
	@JoinColumn(name = "stage_id")
	private LanguageStage languageStage;

}
