package trading.api.configuration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CustomInstantDeserializer extends StdDeserializer<Instant> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    // Implement default constructor since StdDeserializer does not.
    public CustomInstantDeserializer() {
        this(null);
    }

    public CustomInstantDeserializer(Class<?> t) {
        super(t);
    }

    @Override
    public Instant deserialize(
            JsonParser jsonParser,
            DeserializationContext context)
            throws IOException {
        String date = jsonParser.getText();
        try {
            OffsetDateTime offsetDateTime = OffsetDateTime.parse(date, this.formatter);
            return offsetDateTime.toInstant();
        } catch (DateTimeParseException e) {
            throw new RuntimeException(e);
        }
    }
}