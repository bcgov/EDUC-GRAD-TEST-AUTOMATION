package ca.bc.gov.educ.gmts.model.programapi;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Component
public class ProgramRequirementCode extends BaseModel {

	private String proReqCode;
	private String label;
	private String description;
	private RequirementTypeCode requirementTypeCode;
	private String requiredCredits;
	private String notMetDesc;
	private String requiredLevel;
	private String languageOfInstruction;
	private String activeRequirement;
	private String requirementCategory;
}
