package test.by.itechart.flowerty.web.controller;

import java.io.IOException;
import java.nio.charset.Charset;

import org.springframework.http.MediaType;

import test.by.itechart.flowerty.persistence.repository.HelperTestsRepository;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Eugene Putsykovich(slesh) Mar 26, 2015
 *         <p/>
 *         Helper for test
 */
public final class HelperTestsController extends HelperTestsRepository {
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        return mapper.writeValueAsBytes(object);
    }
}
