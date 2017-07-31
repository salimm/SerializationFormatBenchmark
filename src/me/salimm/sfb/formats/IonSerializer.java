package me.salimm.sfb.formats;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.ion.IonObjectMapper;

public class IonSerializer implements Serializer {

	@Override
	public <T extends Object> byte[] serialize(Object obj, Class<T> cls) throws Exception {
		ObjectMapper mapper = new IonObjectMapper();
		return mapper.writeValueAsBytes(obj);
	}

	@Override
	public <T extends Object> Object deserialize(byte[] data, Class<T> cls) throws Exception {
		ObjectMapper mapper = new IonObjectMapper();
		return mapper.readValue(data, cls);
	}

	@Override
	public FormatType getFormatType() {
		return FormatType.ION;
	}

}
