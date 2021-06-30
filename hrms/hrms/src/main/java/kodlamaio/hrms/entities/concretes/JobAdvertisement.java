package kodlamaio.hrms.entities.concretes;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "job_advertisements")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","favorites"})
public class JobAdvertisement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "release_date")
	private LocalDate releaseDate;
	
	@Column(name = "application_deadline")
	private LocalDate applicationDeadline;
	
	@Column(name = "min_salary")
	private Double minSalary;
	
	@Column(name = "max_salary")
	private Double maxSalary;
	
	@Column(name = "number_of_open_position")
	private int numberOfOpenPostion;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "is_activate")
	private boolean active;
	
	@Column(name = "is_deleted")
	private boolean isDeleted;
	
	@ManyToOne()
	@JoinColumn(name = "way_of_working_id")
	private WayOfWorking wayOfWorking;
	
	@ManyToOne()
	@JoinColumn(name = "working_time_id")
	private WorkingTime workingTime;
	
	@ManyToOne()
	@JoinColumn(name = "city_id")
	private City city;
	
	@ManyToOne()
	@JoinColumn(name = "employer_id")
	private Employer employer;
	
	@ManyToOne()
	@JoinColumn(name = "job_position_id")
	private JobPosition jobPosition;
	
	@JsonIgnore
	@OneToMany(mappedBy = "jobAdvertisement")
	private List<Favorite> favorites;
}
