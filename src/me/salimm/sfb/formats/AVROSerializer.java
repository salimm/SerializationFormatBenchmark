package me.salimm.sfb.formats;

import org.apache.avro.Schema;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.avro.AvroFactory;
import com.fasterxml.jackson.dataformat.avro.AvroMapper;
import com.fasterxml.jackson.dataformat.avro.AvroSchema;

public class AVROSerializer implements Serializer {

	@Override
	public <T extends Object> byte[] serialize(Object obj, Class<T> cls) throws Exception {
		ObjectMapper mapper = new ObjectMapper(new AvroFactory());
		return mapper.writer(getSchema(cls)).writeValueAsBytes(obj);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Object> T deserialize(byte[] data, Class<T> cls) throws Exception {
		AvroMapper mapper = new AvroMapper();
		return (T) mapper.readerFor(cls).with(getSchema(cls)).readValue(data);
	}

	@Override
	public FormatType getFormatType() {
		return FormatType.AVRO;
	}

	public AvroSchema getSchema(Class<?> cls) {
		String schemaStr = null;
		if (cls.isAssignableFrom(double[][].class)) {
			schemaStr = "{\"type\": \"array\", \"items\": {\"type\": \"array\", \"items\": \"double\"}}";
		} else if (cls.isAssignableFrom(int[][].class)) {
			schemaStr = "{\"type\": \"array\", \"items\": {\"type\": \"array\", \"items\": \"int\"}}";
		} else if (cls.isAssignableFrom(String[][].class)) {
			schemaStr = "{\"type\": \"array\", \"items\": {\"type\": \"array\", \"items\": \"string\"}}";
		} else if (cls.isAssignableFrom(int[].class)) {
			schemaStr = "{\"type\": \"array\", \"items\": \"int\"}";
		} else {
			return null;
		}
		Schema raw = new Schema.Parser().setValidate(true).parse(schemaStr);
		AvroSchema schema = new AvroSchema(raw);

		return schema;
	}
}
