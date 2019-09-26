package be.afelio.babell.tp_babell.api.utils;

import java.io.IOException;
import java.time.LocalDate;


import com.fasterxml.jackson.core.JsonGenerator;

import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class LocalDateSerializer extends StdSerializer<LocalDate> {
	
	private static final long serialVersionUID = -7715993065947890827L;    
	
	public LocalDateSerializer() {
    super(LocalDate.class);

}    
	@Override
public void serialize(LocalDate value, JsonGenerator generator, SerializerProvider provider)

        throws IOException {
		System.out.println("LocalDateSerializer.serialize()");
    generator.writeString(value.toString());

}

}