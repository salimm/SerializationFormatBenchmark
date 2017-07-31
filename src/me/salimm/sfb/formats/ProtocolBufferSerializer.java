package me.salimm.sfb.formats;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.protobuf.ProtobufMapper;
import com.fasterxml.jackson.dataformat.protobuf.schema.ProtobufSchema;
import com.fasterxml.jackson.dataformat.protobuf.schema.ProtobufSchemaLoader;

public class ProtocolBufferSerializer implements Serializer {

	@Override
	public <T extends Object> byte[] serialize(Object obj, Class<T> cls) throws Exception {
		ObjectMapper mapper = new ProtobufMapper();
		if (obj instanceof int[]) {
			return mapper.writer(getSchema(cls)).writeValueAsBytes(new Container2((int[]) obj));
		}
		if (obj instanceof double[][]) {
			return mapper.writer(getSchema(cls)).writeValueAsBytes(new Container1((double[][]) obj));
		}

		return null;

	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Object> Object deserialize(byte[] data, Class<T> cls) throws Exception {
		ObjectMapper mapper = new ProtobufMapper();
		if (cls.isAssignableFrom(double[].class)) {
			return (T) ((Container1) mapper.readerFor(Container1.class).with(getSchema(cls)).readValue(data)).getData();
		} else if (cls.isAssignableFrom(int[].class)) {
			return (T) ((Container2) mapper.readerFor(Container2.class).with(getSchema(cls)).readValue(data)).getData();
		} else {
			return null;
		}

	}

	@Override
	public FormatType getFormatType() {
		return FormatType.PROTOCOL_BUFFERS;
	}

	public ProtobufSchema getSchema(Class<?> cls) {
		String schemaStr = null;
		if (cls.isAssignableFrom(double[][].class)) {
			schemaStr = "";
		} else if (cls.isAssignableFrom(int[].class)) {
			schemaStr = "message Container1 { repeated int32 data = 1 [packed=true]; }";
		} else {
			return null;
		}
		ProtobufSchema schema;
		try {
			schema = ProtobufSchemaLoader.std.parse(schemaStr);
			return schema;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}

class Container1 {
	private double[][] data;

	public Container1(double[][] data) {
		this.data = data;
	}

	public Container1() {
	}

	public double[][] getData() {
		return data;
	}

	public void setData(double[][] data) {
		this.data = data;
	}

}

class Container2 {
	private int[] data;

	public Container2(int[] data) {
		this.data = data;
	}

	public Container2() {
	}

	public int[] getData() {
		return data;
	}

	public void setData(int[] data) {
		this.data = data;
	}

}