package me.salimm.sfb.formats;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.protobuf.ProtobufFactory;
import com.fasterxml.jackson.dataformat.protobuf.schema.NativeProtobufSchema;
import com.fasterxml.jackson.dataformat.protobuf.schema.ProtobufSchema;
import com.fasterxml.jackson.dataformat.protobuf.schemagen.ProtobufSchemaGenerator;

public class ProtoBufSerializer implements Serializer {

	@Override
	public <T extends Object> byte[] serialize(Object obj, Class<T> cls) throws Exception {
		if (obj instanceof double[][]) {
			return serialize((double[][]) obj);
		}
		return null;
	}

	private byte[] serialize(double[][] vals) throws IOException {
		Container1 cont = new Container1(vals);
		ObjectMapper mapper = new ObjectMapper(new ProtobufFactory());
		ProtobufSchemaGenerator gen = new ProtobufSchemaGenerator();
		mapper.acceptJsonFormatVisitor(Container1.class, gen);
		ProtobufSchema schemaWrapper = gen.getGeneratedSchema();
		NativeProtobufSchema nativeProtobufSchema = schemaWrapper.getSource();

		String asProtofile = nativeProtobufSchema.toString();
		System.out.println(asProtofile);
		return null;
	}

	@Override
	public <T extends Object> Object deserialize(byte[] data, Class<T> cls) throws Exception {
		ObjectMapper mapper = new ObjectMapper(new ProtobufFactory());
		return mapper.readValue(data, cls);
	}

	@Override
	public FormatType getFormatType() {
		return FormatType.PROTOCOL_BUFFERS;
	}

}

class Container1 {
	private double[][] value;

	public Container1(double[][] value) {
		this.value = value;
	}

	public Container1() {
	}

	public Object getValue() {
		return value;
	}

	public void setValue(double[][] value) {
		this.value = value;
	}
}