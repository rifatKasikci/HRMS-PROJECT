package kodlamaio.hrms.entities.dtos.updateDtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateSchoolWithDepartmentUpdateDto {
	
	private int id;
	
	private int candidateId;
	
	private int schoolAndDepartmentId;
	
	private LocalDate startingDate;
	
	private LocalDate endingDate;
}
