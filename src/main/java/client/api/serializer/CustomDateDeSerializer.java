package client.api.serializer;


import client.api.utils.DateConvertUtils;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by qinhailong on 15-11-20.
 */
public class CustomDateDeSerializer extends StdScalarDeserializer<LocalDateTime> {

    public CustomDateDeSerializer() {
        super(LocalDateTime.class);
    }

    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonToken currentToken = p.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NUMBER_INT) {
            String dateTimeAsString = p.getText().trim();
            if (!"".equals(dateTimeAsString)) {
                return DateConvertUtils.asLocalDateTime(new Date(Long.parseLong(dateTimeAsString)));
            }

        }
        return null;
    }
}