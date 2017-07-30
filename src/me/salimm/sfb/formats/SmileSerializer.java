package me.salimm.sfb.formats;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.smile.SmileFactory;

public class SmileSerializer implements Serializer {

	@Override
	public <T extends Object> byte[] serialize(Object obj, Class<T> cls) throws Exception {
		ObjectMapper mapper = new ObjectMapper(new SmileFactory());
		return mapper.writeValueAsBytes(obj);
	}

	@Override
	public <T extends Object> Object deserialize(byte[] data, Class<T> cls) throws Exception {
		ObjectMapper mapper = new ObjectMapper(new SmileFactory());
		return mapper.readValue(data, cls);
	}

	@Override
	public FormatType getFormatType() {
		return FormatType.SMILE;
	}

}
