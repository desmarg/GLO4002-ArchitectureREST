package trading.api.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import trading.domain.datetime.InvalidDateException;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.time.format.DateTimeParseException;

/**
 * This class uses the jackson modules to enable proper date/java8 stuff formatting.
 * See : https://github.com/FasterXML/jackson-modules-java8
 * You can modify it as you wish to suit your needs, but do not set the "indent output" option.
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class CustomJsonProvider extends JacksonJaxbJsonProvider {
    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(new JavaTimeModule());
        mapper.registerModule(new Jdk8Module());
    }

    public CustomJsonProvider() {
        super();
        this.setMapper(mapper);
    }

    @Override
    public Object readFrom(Class<Object> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException {
        try {
            return super.readFrom(type, genericType, annotations, mediaType, httpHeaders, entityStream);
        } catch(JsonMappingException jme) {
            if (jme.getCause() instanceof DateTimeParseException) {
                throw new InvalidDateException();
            } else {
                throw jme;
            }
        }
    }
}
