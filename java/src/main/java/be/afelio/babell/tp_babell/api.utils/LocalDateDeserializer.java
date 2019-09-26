package be.afelio.babell.tp_babell.api.utils;



import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;


public class LocalDateDeserializer extends StdDeserializer<LocalDate> {
	private static final long serialVersionUID = -7715993065947890827L;

	public LocalDateDeserializer() {
		super(LocalDate.class);
	}

	@Override
    public LocalDate deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
        return LocalDate.ofInstant(Instant.parse(parser.readValueAs(String.class)), ZoneOffset.UTC);
    }
	}


