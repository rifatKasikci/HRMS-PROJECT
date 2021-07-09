package kodlamaio.hrms.entities.dtos.updateDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LanguageCandidateUpdateDto {
	
	private int id;
	
	private int candidateId;
	
	private int languageId;
	
	private int languageStageId;
}
