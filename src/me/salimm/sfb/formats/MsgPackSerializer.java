package me.salimm.sfb.formats;

import org.msgpack.jackson.dataformat.MessagePackFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MsgPackSerializer implements Serializer {

	@Override
	public <T extends Object> byte[] serialize(Object obj, Class<T> cls) throws Exception {
		ObjectMapper mapper = new ObjectMapper(new MessagePackFactory());
		return mapper.writeValueAsBytes(obj);
	}

	@Override
	public <T extends Object> T deserialize(byte[] data, Class<T> cls) throws Exception {
		ObjectMapper mapper = new ObjectMapper(new MessagePackFactory());
		return mapper.readValue(data, cls);
	}

	@Override
	public FormatType getFormatType() {
		return FormatType.MSG_PACK;
	}

}
