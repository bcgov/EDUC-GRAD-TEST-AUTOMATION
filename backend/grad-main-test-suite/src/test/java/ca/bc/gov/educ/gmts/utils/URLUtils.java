package ca.bc.gov.educ.gmts.utils;

import ca.bc.gov.educ.gmts.config.RequiredProperties;
import ca.bc.gov.educ.gmts.config.TestConfig;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

public class URLUtils {

    /**
     * Helper method for building urls
     * @param requiredProperties a base url, for example RequiredProperties.STUDENT_COURSE_API_URL (required)
     * @param path the path portion of url, for example "api/v1/endpoint"
     * @param params optional params map
     * @return
     * @throws URISyntaxException
     */
    public static String getURL(RequiredProperties requiredProperties, String path, Map<String, String> params) throws URISyntaxException {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUri(
                new URI(TestConfig.getInstance().getApiEndPoint(requiredProperties)));
        if(path != null && path.length() > 0){
            builder.path(path);
        }
        if(params != null && !params.isEmpty()){
            builder.queryParams(convertMapToMultiValueMap(params));
        }
        return builder.build().toString();
    }

    /**
     * Internal helper
     * @param params
     * @return
     */
    private static MultiValueMap<String, String> convertMapToMultiValueMap(Map<String, String> params){
        MultiValueMap<String, String> p = new LinkedMultiValueMap<>();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            p.add(entry.getKey(), entry.getValue());
        }
        return p;
    }


}
