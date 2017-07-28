package me.salimm.sfb.formats;

public interface Serializer {

	public byte[] serialize(Object obj);
	
	public Object deserialize(byte[] data, Class cls);
	
	public FormatType getFormatType();

}
