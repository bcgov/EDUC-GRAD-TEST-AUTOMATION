package ca.bc.gov.educ.gmts.model.courseapi;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class CourseRequirements {
    List<CourseRequirement> courseRequirementList;
}
