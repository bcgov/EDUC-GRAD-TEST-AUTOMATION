package ca.bc.gov.educ.gtts.model.dto.grad.algorithm;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class StudentCourses {
    private List<StudentCourse> studentCourseList;

    @Override
    public String toString() {
        StringBuffer output = new StringBuffer("");

        for (StudentCourse sc : studentCourseList) {
            output.append(sc.toString())
            .append("\n");
        }
        return output.toString();
    }
}
