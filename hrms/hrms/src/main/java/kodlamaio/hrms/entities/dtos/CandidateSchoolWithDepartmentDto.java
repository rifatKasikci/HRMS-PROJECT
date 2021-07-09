package kodlamaio.hrms.entities.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateSchoolWithDepartmentDto {

	private int candidateId;
	
	private int schoolAndDepartmentId;
	
	private LocalDate startingDate;
	
	private LocalDate endingDate;
}
