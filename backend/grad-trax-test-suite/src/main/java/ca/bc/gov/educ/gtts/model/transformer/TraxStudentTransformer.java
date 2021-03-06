package ca.bc.gov.educ.gtts.model.transformer;

import ca.bc.gov.educ.gtts.model.dto.TraxStudentDto;
import ca.bc.gov.educ.gtts.model.entity.TraxStudentEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TraxStudentTransformer {

    ModelMapper modelMapper;

    @Autowired
    public TraxStudentTransformer(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public TraxStudentDto transformToDTO(TraxStudentEntity entity){
        return (entity != null) ? modelMapper.map(entity, TraxStudentDto.class) : null;
    }

}
