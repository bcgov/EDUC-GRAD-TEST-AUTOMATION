package ca.bc.gov.educ.gmts.model.programapi;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Component
public class GradRuleDetails {

	private String ruleCode;
	private String requirementName;
	private String programCode;
	private String optionalProgramCode;
	
	@Override
	public String toString() {
		return "GradRuleDetails [ruleCode=" + ruleCode + ", requirementName=" + requirementName + ", programCode="
				+ programCode + "]";
	}
	
}
