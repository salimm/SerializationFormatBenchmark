package me.salimm.sfb.formats;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.cbor.CBORFactory;


public class CBORSerializer implements Serializer {

	@Override
	public <T extends Object>  byte[] serialize(Object obj, Class<T> cls) throws Exception {
		ObjectMapper mapper = new ObjectMapper(new CBORFactory());
		return mapper.writeValueAsBytes(obj);
	}

	@Override
	public <T extends Object>  T deserialize(byte[] data, Class<T> cls) throws Exception {
		ObjectMapper mapper = new ObjectMapper(new CBORFactory());
		return mapper.readValue(data, cls);
	}

	@Override
	public FormatType getFormatType() {
		return FormatType.CBOR;
	}

}
