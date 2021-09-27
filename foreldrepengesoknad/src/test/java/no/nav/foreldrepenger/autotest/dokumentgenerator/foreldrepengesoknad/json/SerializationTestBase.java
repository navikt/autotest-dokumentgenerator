package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json;

import static no.nav.foreldrepenger.felles.integrasjon.rest.DefaultJsonMapper.getObjectMapper;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;


public class SerializationTestBase {

    protected static final Logger LOG = LoggerFactory.getLogger(SerializationTestBase.class);
    protected static ObjectMapper mapper;

    @BeforeAll
    public static void beforeAll() {
        mapper = getObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.registerModule(new ParameterNamesModule(JsonCreator.Mode.PROPERTIES));

        mapper.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
        mapper.setVisibility(PropertyAccessor.SETTER, JsonAutoDetect.Visibility.NONE);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.setVisibility(PropertyAccessor.CREATOR, JsonAutoDetect.Visibility.ANY);

        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    protected static void test(Object obj) {
        test(obj, true);
    }

    private static String serialize(Object obj) throws JsonProcessingException {
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
    }

    private static void test(Object obj, boolean log) {
        try {
            if (log) {
                LOG.info("{}", obj);
            }
            var serialized = serialize(obj);
            if (log) {
                LOG.info("Serialized as {}", serialized);
            }
            var deserialized = mapper.readValue(serialized, obj.getClass());
            if (log) {
                LOG.info("{}", deserialized);
            }
            assertEquals(obj, deserialized);
        } catch (Exception e) {
            LOG.error("Oops", e);
            fail(obj.getClass().getSimpleName() + " failed");
        }
    }
}
