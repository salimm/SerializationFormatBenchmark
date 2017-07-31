package me.salimm.sfb.formats;


import com.fasterxml.jackson.databind.ObjectMapper;

import de.undercouch.bson4jackson.BsonFactory;

public class BSONSerializer implements Serializer {

	@Override
	public <T extends Object> byte[] serialize(Object obj, Class<T> cls) throws Exception {
		ObjectMapper mapper = new ObjectMapper(new BsonFactory());
		return mapper.writeValueAsBytes(obj);
	}

	@Override
	public <T extends Object> T deserialize(byte[] data, Class<T> cls) throws Exception {
		ObjectMapper mapper = new ObjectMapper(new BsonFactory());
		return mapper.readValue(data, cls);
	}

	@Override
	public FormatType getFormatType() {
		return FormatType.BSON;
	}

}
