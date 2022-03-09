package ca.bc.gov.educ.gmts.model.programapi;

import java.util.UUID;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Component
public class OptionalProgramRequirement extends BaseModel {

	private UUID optionalProgramRequirementID; 
	private OptionalProgram optionalProgramID; 
	private OptionalProgramRequirementCode optionalProgramRequirementCode;
}
