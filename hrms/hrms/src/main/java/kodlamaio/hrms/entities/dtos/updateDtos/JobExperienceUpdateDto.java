package kodlamaio.hrms.entities.dtos.updateDtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobExperienceUpdateDto {
	
	private int id;
	
	private int candidateId;
	
	private int workplaceId;
	
	private int jobPositionId;
	
	private LocalDate startingDate;
	
	private LocalDate endingDate;
}
