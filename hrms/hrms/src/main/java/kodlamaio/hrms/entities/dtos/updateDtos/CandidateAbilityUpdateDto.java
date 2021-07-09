package kodlamaio.hrms.entities.dtos.updateDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateAbilityUpdateDto {

	private int id;
	
	private int candidateId;
	
	private int abilityId;
}
