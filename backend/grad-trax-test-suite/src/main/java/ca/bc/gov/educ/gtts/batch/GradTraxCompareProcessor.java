package ca.bc.gov.educ.gtts.batch;

import ca.bc.gov.educ.gtts.exception.GenericHTTPRequestServiceException;
import ca.bc.gov.educ.gtts.exception.NotFoundException;
import ca.bc.gov.educ.gtts.model.dto.GradSearchStudent;
import ca.bc.gov.educ.gtts.model.dto.TraxGradComparatorDto;
import ca.bc.gov.educ.gtts.model.dto.grad.algorithm.GraduationData;
import ca.bc.gov.educ.gtts.model.entity.TswTranDemogEntity;
import ca.bc.gov.educ.gtts.model.entity.TswTranNonGradEntity;
import ca.bc.gov.educ.gtts.model.transformer.TraxGradComparisonTransformer;
import ca.bc.gov.educ.gtts.services.ComparatorService;
import ca.bc.gov.educ.gtts.services.GradService;
import ca.bc.gov.educ.gtts.services.TraxService;
import org.javers.core.diff.Diff;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GradTraxCompareProcessor implements ItemProcessor<String, String> {

    @Autowired
    private GradService gradService;
    @Autowired
    private TraxService traxService;
    @Autowired
    private TraxGradComparisonTransformer traxGradComparisonTransformer;
    @Autowired
    private ComparatorService comparatorService;


    @Override
    public String process(String pen) throws Exception {
        System.out.print("Processing: " + pen);
        String msg = "";
        // get info from TRAX
        TraxGradComparatorDto traxGradComparatorDtoFromTrax = getTraxGradComparatorDtoFromTrax(pen);
        // fetch info from GRAD
        TraxGradComparatorDto traxGradComparatorDtoFromGRAD = getTraxGradComparatorDtoFromGradAlgorithm(pen);
        // compare
        Diff diffs = comparatorService.compareTraxGradDTOs(traxGradComparatorDtoFromTrax, traxGradComparatorDtoFromGRAD);
        // if diffs, report
        if(diffs.hasChanges()){
            msg = pen + pen + " had the following diffs: " + diffs.prettyPrint();
            //reportService.reportDifferences(pen, diffs);
        } else {
            System.out.println(" -- No differences.");
        }
        return msg;
    }
    private TraxGradComparatorDto getTraxGradComparatorDtoFromTrax(String pen) throws NotFoundException {
        TswTranDemogEntity tswTranDemogEntity = traxService.getTransDemogEntity(pen);
        if(tswTranDemogEntity == null){
            throw new NotFoundException("Student with pen: " + pen + " not found in TRAX");
        }
        List<TswTranNonGradEntity> nonGradReasons = traxService.getNonGradReasons(pen);
        return traxGradComparisonTransformer.getTraxGradComparatorDto(tswTranDemogEntity, nonGradReasons);
    }

    private TraxGradComparatorDto getTraxGradComparatorDtoFromGradAlgorithm(String pen) throws NotFoundException, GenericHTTPRequestServiceException {
        GradSearchStudent gradSearchStudent = gradService.getStudentByPen(pen);
        GraduationData projectedGraduationData = gradService.runProjectedGraduation(gradSearchStudent.getStudentID(), gradSearchStudent.getProgram());
        return traxGradComparisonTransformer.getTraxGradComparatorDto(projectedGraduationData);
    }


}
